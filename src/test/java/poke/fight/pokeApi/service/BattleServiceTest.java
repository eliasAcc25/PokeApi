package poke.fight.pokeApi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import poke.fight.pokeApi.Entity.Battle;
import poke.fight.pokeApi.Entity.Pokemon;
import poke.fight.pokeApi.dtos.BattleDTO;
import poke.fight.pokeApi.dtos.CreateBattleRequestDTO;
import poke.fight.pokeApi.repository.BattleRepository;
import poke.fight.pokeApi.repository.PokemonRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BattleServiceTest {

    @Mock
    private BattleRepository battleRepository;

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private PokemonService pokemonService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BattleService battleService;

    private Pokemon pikachu;
    private Pokemon charmander;
    private Battle testBattle;

    @BeforeEach
    void setUp() {
        pikachu = new Pokemon(25, "pikachu", 112, 4, 60);
        pikachu.setId(1L);

        charmander = new Pokemon(4, "charmander", 62, 6, 85);
        charmander.setId(2L);

        testBattle = new Battle(pikachu, charmander, pikachu);
        testBattle.setId(1L);
        testBattle.setBattleDate(LocalDateTime.now());
    }

    @Test
    void createBattle_WithExistingPokemons_ShouldCreateBattleSuccessfully() {
        // Given
        CreateBattleRequestDTO request = new CreateBattleRequestDTO(25, 4);
        when(pokemonRepository.findByPokeapiId(25)).thenReturn(Optional.of(pikachu));
        when(pokemonRepository.findByPokeapiId(4)).thenReturn(Optional.of(charmander));
        when(battleRepository.save(any(Battle.class))).thenReturn(testBattle);

        // When
        BattleDTO result = battleService.createBattle(request);

        // Then
        assertNotNull(result);
        assertNotNull(result.getWinner());
        verify(pokemonRepository, times(1)).findByPokeapiId(25);
        verify(pokemonRepository, times(1)).findByPokeapiId(4);
        verify(battleRepository, times(1)).save(any(Battle.class));
    }

    @Test
    void createBattle_SamePokemon_ShouldThrowException() {
        // Given
        CreateBattleRequestDTO request = new CreateBattleRequestDTO(25, 25);
        when(pokemonRepository.findByPokeapiId(25)).thenReturn(Optional.of(pikachu));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> battleService.createBattle(request));

        assertTrue(exception.getMessage().contains("Um Pokémon não pode batalhar contra si mesmo"));
        verify(battleRepository, never()).save(any());
    }

    @Test
    void createBattle_PokemonNotInDatabase_ShouldFetchFromAPI() {
        // Given
        CreateBattleRequestDTO request = new CreateBattleRequestDTO(25, 4);
        when(pokemonRepository.findByPokeapiId(25)).thenReturn(Optional.empty());
        // Simular que a API retorna null (Pokemon não encontrado)
        when(restTemplate.getForObject(anyString(), any())).thenReturn(null);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> battleService.createBattle(request));

        assertTrue(exception.getMessage().contains("Pokémon não encontrado na PokeAPI"));
        verify(restTemplate, times(1)).getForObject(anyString(), any());
    }

    @Test
    void getBattleById_ExistingBattle_ShouldReturnBattle() {
        // Given
        when(battleRepository.findById(1L)).thenReturn(Optional.of(testBattle));

        // When
        Optional<BattleDTO> result = battleService.getBattleById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(battleRepository, times(1)).findById(1L);
    }

    @Test
    void getBattleById_NonExistingBattle_ShouldReturnEmpty() {
        // Given
        when(battleRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<BattleDTO> result = battleService.getBattleById(999L);

        // Then
        assertTrue(result.isEmpty());
        verify(battleRepository, times(1)).findById(999L);
    }

    @Test
    void getAllBattles_ShouldReturnListOfBattles() {
        // Given
        List<Battle> battles = Arrays.asList(testBattle);
        when(battleRepository.findAllByOrderByBattleDateDesc()).thenReturn(battles);

        // When
        List<BattleDTO> result = battleService.getAllBattles();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(battleRepository, times(1)).findAllByOrderByBattleDateDesc();
    }

    @Test
    void getBattlesByPokemon_ExistingPokemon_ShouldReturnBattles() {
        // Given
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(pikachu));
        when(battleRepository.findBattlesByPokemon(pikachu)).thenReturn(Arrays.asList(testBattle));

        // When
        List<BattleDTO> result = battleService.getBattlesByPokemon(1L);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pokemonRepository, times(1)).findById(1L);
        verify(battleRepository, times(1)).findBattlesByPokemon(pikachu);
    }

    @Test
    void getBattlesByPokemon_NonExistingPokemon_ShouldThrowException() {
        // Given
        when(pokemonRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> battleService.getBattlesByPokemon(999L));

        assertTrue(exception.getMessage().contains("Pokémon não encontrado com ID"));
        verify(pokemonRepository, times(1)).findById(999L);
        verify(battleRepository, never()).findBattlesByPokemon(any());
    }
}
