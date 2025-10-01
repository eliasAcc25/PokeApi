package poke.fight.pokeApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.service.PokemonService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PokemonService pokemonService;

    @Autowired
    private ObjectMapper objectMapper;

    private PokemonDTO testPokemonDTO;

    @BeforeEach
    void setUp() {
        testPokemonDTO = new PokemonDTO(1L, "pikachu", "", 60, 4, 112);
    }

    @Test
    void getAllPokemons_ShouldReturnListOfPokemons() throws Exception {
        // Given
        List<PokemonDTO> pokemons = Arrays.asList(testPokemonDTO);
        when(pokemonService.getAllPokemons()).thenReturn(pokemons);

        // When & Then
        mockMvc.perform(get("/api/pokemon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("pikachu"))
                .andExpect(jsonPath("$[0].id").value(1));

        verify(pokemonService, times(1)).getAllPokemons();
    }

    @Test
    void getPokemonById_ExistingPokemon_ShouldReturnPokemon() throws Exception {
        // Given
        when(pokemonService.getPokemonById(1L)).thenReturn(Optional.of(testPokemonDTO));

        // When & Then
        mockMvc.perform(get("/api/pokemon/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pikachu"))
                .andExpect(jsonPath("$.id").value(1));

        verify(pokemonService, times(1)).getPokemonById(1L);
    }

    @Test
    void getPokemonById_NonExistingPokemon_ShouldReturnNotFound() throws Exception {
        // Given
        when(pokemonService.getPokemonById(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/pokemon/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pokemonService, times(1)).getPokemonById(999L);
    }

    @Test
    void searchPokemon_ExistingPokemon_ShouldReturnPokemon() throws Exception {
        // Given
        when(pokemonService.searchPokemon("pikachu")).thenReturn(testPokemonDTO);

        // When & Then
        mockMvc.perform(get("/api/pokemon/search/pikachu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("pikachu"))
                .andExpect(jsonPath("$.id").value(1));

        verify(pokemonService, times(1)).searchPokemon("pikachu");
    }

    @Test
    void searchPokemon_NonExistingPokemon_ShouldReturnNotFound() throws Exception {
        // Given
        when(pokemonService.searchPokemon("invalidpokemon"))
                .thenThrow(new RuntimeException("Pokémon não encontrado"));

        // When & Then
        mockMvc.perform(get("/api/pokemon/search/invalidpokemon")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pokemonService, times(1)).searchPokemon("invalidpokemon");
    }

    @Test
    void createPokemon_ValidPokemon_ShouldCreateAndReturnPokemon() throws Exception {
        // Given
        PokemonDTO newPokemon = new PokemonDTO(null, "bulbasaur", "", 69, 7, 64);
        PokemonDTO createdPokemon = new PokemonDTO(2L, "bulbasaur", "", 69, 7, 64);
        when(pokemonService.createPokemon(any(PokemonDTO.class))).thenReturn(createdPokemon);

        // When & Then
        mockMvc.perform(post("/api/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPokemon)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("bulbasaur"))
                .andExpect(jsonPath("$.id").value(2));

        verify(pokemonService, times(1)).createPokemon(any(PokemonDTO.class));
    }

    @Test
    void createPokemon_DuplicatePokemon_ShouldReturnBadRequest() throws Exception {
        // Given
        when(pokemonService.createPokemon(any(PokemonDTO.class)))
                .thenThrow(new RuntimeException("Pokémon já existe"));

        // When & Then
        mockMvc.perform(post("/api/pokemon")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPokemonDTO)))
                .andExpect(status().isBadRequest());

        verify(pokemonService, times(1)).createPokemon(any(PokemonDTO.class));
    }
}
