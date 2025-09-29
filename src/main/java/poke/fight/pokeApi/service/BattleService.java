package poke.fight.pokeApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import poke.fight.pokeApi.Entity.Battle;
import poke.fight.pokeApi.Entity.Pokemon;
import poke.fight.pokeApi.dtos.BattleDTO;
import poke.fight.pokeApi.dtos.CreateBattleRequestDTO;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.repository.BattleRepository;
import poke.fight.pokeApi.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service que contém a lógica de negócio das batalhas Pokémon
 * Responsável por criar batalhas, buscar na API externa e determinar vencedores
 */
@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";
    private final Random random = new Random();

    /**
     * Cria uma nova batalha entre dois Pokémons
     * Busca os Pokémons na API externa se não existirem localmente
     */
    public BattleDTO createBattle(CreateBattleRequestDTO request) {
        // Buscar ou criar os Pokémons
        Pokemon pokemon1 = getOrCreatePokemon(request.getPokemon1Id());
        Pokemon pokemon2 = getOrCreatePokemon(request.getPokemon2Id());

        // Verificar se são Pokémons diferentes
        if (pokemon1.getPokeapiId().equals(pokemon2.getPokeapiId())) {
            throw new RuntimeException("Um Pokémon não pode batalhar contra si mesmo!");
        }

        // Determinar o vencedor da batalha
        Pokemon winner = determineWinner(pokemon1, pokemon2);

        // Criar e salvar a batalha
        Battle battle = new Battle(pokemon1, pokemon2, winner);
        Battle savedBattle = battleRepository.save(battle);

        return convertToDTO(savedBattle);
    }

    /**
     * Busca uma batalha específica pelo ID
     */
    public Optional<BattleDTO> getBattleById(Long id) {
        return battleRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Lista todas as batalhas (mais recentes primeiro)
     */
    public List<BattleDTO> getAllBattles() {
        return battleRepository.findAllByOrderByBattleDateDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Lista batalhas de um Pokémon específico
     */
    public List<BattleDTO> getBattlesByPokemon(Long pokemonId) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(pokemonId);
        if (pokemon.isEmpty()) {
            throw new RuntimeException("Pokémon não encontrado com ID: " + pokemonId);
        }

        return battleRepository.findBattlesByPokemon(pokemon.get())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca ou cria um Pokémon na base de dados
     * Se não existir localmente, busca na PokeAPI
     */
    private Pokemon getOrCreatePokemon(Integer pokeapiId) {
        // Primeiro tenta encontrar no banco local
        Optional<Pokemon> existingPokemon = pokemonRepository.findByPokeapiId(pokeapiId);

        if (existingPokemon.isPresent()) {
            return existingPokemon.get();
        }

        // Se não encontrou, busca na API externa
        try {
            // Faz a requisição para a PokeAPI
            String url = POKEAPI_URL + pokeapiId;
            PokemonApiResponse apiResponse = restTemplate.getForObject(url, PokemonApiResponse.class);

            if (apiResponse == null) {
                throw new RuntimeException("Pokémon não encontrado na PokeAPI com ID: " + pokeapiId);
            }

            // Cria o Pokémon com os dados da API
            Pokemon pokemon = new Pokemon(
                    pokeapiId,
                    apiResponse.getName(),
                    apiResponse.getBase_experience(),
                    apiResponse.getHeight(),
                    apiResponse.getWeight()
            );

            // Salva no banco e retorna
            return pokemonRepository.save(pokemon);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar Pokémon com ID " + pokeapiId + ": " + e.getMessage());
        }
    }

    /**
     * Determina o vencedor da batalha usando lógica baseada em experiência + sorte
     * 70% baseado na experiência, 30% sorte
     */
    private Pokemon determineWinner(Pokemon pokemon1, Pokemon pokemon2) {
        // Obter experiências (usar valor padrão se for null)
        int exp1 = pokemon1.getBaseExperience() != null ? pokemon1.getBaseExperience() : 100;
        int exp2 = pokemon2.getBaseExperience() != null ? pokemon2.getBaseExperience() : 100;

        // Calcular pontuação de batalha (experiência + fator sorte)
        double score1 = exp1 * 0.7 + random.nextInt(100) * 0.3;
        double score2 = exp2 * 0.7 + random.nextInt(100) * 0.3;

        return score1 > score2 ? pokemon1 : pokemon2;
    }

    /**
     * Converte entidade Battle para DTO
     */
    private BattleDTO convertToDTO(Battle battle) {
        return new BattleDTO(
                battle.getId(),
                convertPokemonToDTO(battle.getPokemon1()),
                convertPokemonToDTO(battle.getPokemon2()),
                convertPokemonToDTO(battle.getWinner()),
                battle.getBattleDate()
        );
    }

    /**
     * Converte entidade Pokemon para DTO
     */
    private PokemonDTO convertPokemonToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                "", // sprite vazio por enquanto
                pokemon.getWeight(),
                pokemon.getHeight(),
                pokemon.getBaseExperience()
        );
    }

    /**
     * Classe interna para mapear resposta da PokeAPI
     */
    private static class PokemonApiResponse {
        private Integer id;
        private String name;
        private Integer base_experience;
        private Integer height;
        private Integer weight;

        // Getters e Setters
        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public Integer getBase_experience() { return base_experience; }
        public void setBase_experience(Integer base_experience) { this.base_experience = base_experience; }

        public Integer getHeight() { return height; }
        public void setHeight(Integer height) { this.height = height; }

        public Integer getWeight() { return weight; }
        public void setWeight(Integer weight) { this.weight = weight; }
    }
}
