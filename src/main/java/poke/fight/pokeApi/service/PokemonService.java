package poke.fight.pokeApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import poke.fight.pokeApi.Entity.Pokemon;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.repository.PokemonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public List<PokemonDTO> getAllPokemons() {
        return pokemonRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PokemonDTO> getPokemonById(Long id) {
        return pokemonRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PokemonDTO searchPokemon(String name) {
        Optional<Pokemon> existingPokemon = pokemonRepository.findByName(name.toLowerCase());

        if (existingPokemon.isPresent()) {
            return convertToDTO(existingPokemon.get());
        }

        // Buscar na API externa
        try {
            PokemonDTO externalPokemon = restTemplate.getForObject(
                    POKEAPI_URL + name.toLowerCase(),
                    PokemonDTO.class
            );

            if (externalPokemon != null) {
                // Salvar no banco
                Pokemon pokemon = convertToEntity(externalPokemon);
                Pokemon saved = pokemonRepository.save(pokemon);
                return convertToDTO(saved);
            }
        } catch (Exception e) {
            throw new RuntimeException("Pokémon não encontrado: " + name);
        }

        throw new RuntimeException("Pokémon não encontrado: " + name);
    }

    public PokemonDTO createPokemon(PokemonDTO pokemonDTO) {
        if (pokemonRepository.existsByName(pokemonDTO.getName().toLowerCase())) {
            throw new RuntimeException("Pokémon já existe: " + pokemonDTO.getName());
        }

        Pokemon pokemon = convertToEntity(pokemonDTO);
        Pokemon saved = pokemonRepository.save(pokemon);
        return convertToDTO(saved);
    }

    private PokemonDTO convertToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                "", // sprite vazio pois Pokemon não tem este campo
                pokemon.getWeight(),
                pokemon.getHeight(),
                pokemon.getBaseExperience()
        );
    }

    private Pokemon convertToEntity(PokemonDTO dto) {
        // Usando o construtor correto: (Integer pokeapiId, String name, Integer baseExperience, Integer height, Integer weight)
        return new Pokemon(
                1, // pokeapiId temporário - você pode melhorar isso depois
                dto.getName().toLowerCase(),
                dto.getBaseExperience(),
                dto.getHeight(),
                dto.getWeight()
        );
    }
}
