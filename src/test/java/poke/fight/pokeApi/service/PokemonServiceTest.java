package poke.fight.pokeApi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import poke.fight.pokeApi.Entity.Pokemon;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.repository.PokemonRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonService pokemonService;

    private Pokemon testPokemon;
    private PokemonDTO testPokemonDTO;

    @BeforeEach
    void setUp() {
        testPokemon = new Pokemon(1, "pikachu", 112, 4, 60);
        testPokemon.setId(1L);

        testPokemonDTO = new PokemonDTO(1L, "pikachu", "", 60, 4, 112);
    }

    @Test
    void getAllPokemons_ShouldReturnListOfPokemonDTOs() {
        // Given
        List<Pokemon> pokemonList = Arrays.asList(testPokemon);
        when(pokemonRepository.findAll()).thenReturn(pokemonList);

        // When
        List<PokemonDTO> result = pokemonService.getAllPokemons();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("pikachu", result.get(0).getName());
        verify(pokemonRepository, times(1)).findAll();
    }

    @Test
    void getPokemonById_ExistingId_ShouldReturnPokemonDTO() {
        // Given
        when(pokemonRepository.findById(1L)).thenReturn(Optional.of(testPokemon));

        // When
        Optional<PokemonDTO> result = pokemonService.getPokemonById(1L);

        // Then
        assertTrue(result.isPresent());
        assertEquals("pikachu", result.get().getName());
        verify(pokemonRepository, times(1)).findById(1L);
    }

    @Test
    void getPokemonById_NonExistingId_ShouldReturnEmpty() {
        // Given
        when(pokemonRepository.findById(999L)).thenReturn(Optional.empty());

        // When
        Optional<PokemonDTO> result = pokemonService.getPokemonById(999L);

        // Then
        assertTrue(result.isEmpty());
        verify(pokemonRepository, times(1)).findById(999L);
    }

    @Test
    void searchPokemon_ExistingInDatabase_ShouldReturnFromDatabase() {
        // Given
        when(pokemonRepository.findByName("pikachu")).thenReturn(Optional.of(testPokemon));

        // When
        PokemonDTO result = pokemonService.searchPokemon("pikachu");

        // Then
        assertNotNull(result);
        assertEquals("pikachu", result.getName());
        verify(pokemonRepository, times(1)).findByName("pikachu");
        verify(restTemplate, never()).getForObject(anyString(), any());
    }

    @Test
    void searchPokemon_NotInDatabase_ShouldFetchFromAPI() {
        // Given
        when(pokemonRepository.findByName("charmander")).thenReturn(Optional.empty());
        when(restTemplate.getForObject(anyString(), eq(PokemonDTO.class)))
                .thenReturn(new PokemonDTO(null, "charmander", "", 85, 6, 62));
        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(testPokemon);

        // When
        PokemonDTO result = pokemonService.searchPokemon("charmander");

        // Then
        assertNotNull(result);
        verify(pokemonRepository, times(1)).findByName("charmander");
        verify(restTemplate, times(1)).getForObject(anyString(), eq(PokemonDTO.class));
        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    @Test
    void searchPokemon_NotFound_ShouldThrowException() {
        // Given
        when(pokemonRepository.findByName("invalidpokemon")).thenReturn(Optional.empty());
        when(restTemplate.getForObject(anyString(), eq(PokemonDTO.class)))
                .thenThrow(new RuntimeException("API Error"));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> pokemonService.searchPokemon("invalidpokemon"));

        assertTrue(exception.getMessage().contains("Pokémon não encontrado"));
    }

    @Test
    void createPokemon_NewPokemon_ShouldCreateSuccessfully() {
        // Given
        when(pokemonRepository.existsByName("bulbasaur")).thenReturn(false);
        when(pokemonRepository.save(any(Pokemon.class))).thenReturn(testPokemon);

        PokemonDTO newPokemonDTO = new PokemonDTO(null, "bulbasaur", "", 69, 7, 64);

        // When
        PokemonDTO result = pokemonService.createPokemon(newPokemonDTO);

        // Then
        assertNotNull(result);
        verify(pokemonRepository, times(1)).existsByName("bulbasaur");
        verify(pokemonRepository, times(1)).save(any(Pokemon.class));
    }

    @Test
    void createPokemon_ExistingPokemon_ShouldThrowException() {
        // Given
        when(pokemonRepository.existsByName("pikachu")).thenReturn(true);

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> pokemonService.createPokemon(testPokemonDTO));

        assertTrue(exception.getMessage().contains("Pokémon já existe"));
        verify(pokemonRepository, times(1)).existsByName("pikachu");
        verify(pokemonRepository, never()).save(any());
    }
}
