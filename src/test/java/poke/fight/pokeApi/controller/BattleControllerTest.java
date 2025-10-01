package poke.fight.pokeApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import poke.fight.pokeApi.dtos.BattleDTO;
import poke.fight.pokeApi.dtos.CreateBattleRequestDTO;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.service.BattleService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BattleController.class)
class BattleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BattleService battleService;

    @Autowired
    private ObjectMapper objectMapper;

    private BattleDTO testBattleDTO;
    private CreateBattleRequestDTO testRequest;

    @BeforeEach
    void setUp() {
        PokemonDTO pikachu = new PokemonDTO(1L, "pikachu", "", 60, 4, 112);
        PokemonDTO charmander = new PokemonDTO(2L, "charmander", "", 85, 6, 62);

        testBattleDTO = new BattleDTO(1L, pikachu, charmander, pikachu, LocalDateTime.now());
        testRequest = new CreateBattleRequestDTO(25, 4);
    }

    @Test
    void createBattle_ValidRequest_ShouldCreateBattle() throws Exception {
        // Given
        when(battleService.createBattle(any(CreateBattleRequestDTO.class))).thenReturn(testBattleDTO);

        // When & Then
        mockMvc.perform(post("/api/batalhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pokemon1.name").value("pikachu"))
                .andExpect(jsonPath("$.pokemon2.name").value("charmander"));

        verify(battleService, times(1)).createBattle(any(CreateBattleRequestDTO.class));
    }

    @Test
    void createBattle_InvalidRequest_ShouldReturnBadRequest() throws Exception {
        // Given
        when(battleService.createBattle(any(CreateBattleRequestDTO.class)))
                .thenThrow(new RuntimeException("Um Pokémon não pode batalhar contra si mesmo"));

        // When & Then
        mockMvc.perform(post("/api/batalhas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testRequest)))
                .andExpect(status().isBadRequest());

        verify(battleService, times(1)).createBattle(any(CreateBattleRequestDTO.class));
    }

    @Test
    void getBattle_ExistingBattle_ShouldReturnBattle() throws Exception {
        // Given
        when(battleService.getBattleById(1L)).thenReturn(Optional.of(testBattleDTO));

        // When & Then
        mockMvc.perform(get("/api/batalhas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.winner.name").value("pikachu"));

        verify(battleService, times(1)).getBattleById(1L);
    }

    @Test
    void getBattle_NonExistingBattle_ShouldReturnNotFound() throws Exception {
        // Given
        when(battleService.getBattleById(999L)).thenReturn(Optional.empty());

        // When & Then
        mockMvc.perform(get("/api/batalhas/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(battleService, times(1)).getBattleById(999L);
    }

    @Test
    void getAllBattles_ShouldReturnListOfBattles() throws Exception {
        // Given
        List<BattleDTO> battles = Arrays.asList(testBattleDTO);
        when(battleService.getAllBattles()).thenReturn(battles);

        // When & Then
        mockMvc.perform(get("/api/batalhas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].winner.name").value("pikachu"));

        verify(battleService, times(1)).getAllBattles();
    }

    @Test
    void getBattlesByPokemon_ExistingPokemon_ShouldReturnBattles() throws Exception {
        // Given
        List<BattleDTO> battles = Arrays.asList(testBattleDTO);
        when(battleService.getBattlesByPokemon(1L)).thenReturn(battles);

        // When & Then
        mockMvc.perform(get("/api/batalhas/pokemon/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1));

        verify(battleService, times(1)).getBattlesByPokemon(1L);
    }

    @Test
    void getBattlesByPokemon_NonExistingPokemon_ShouldReturnNotFound() throws Exception {
        // Given
        when(battleService.getBattlesByPokemon(999L))
                .thenThrow(new RuntimeException("Pokémon não encontrado com ID: 999"));

        // When & Then
        mockMvc.perform(get("/api/batalhas/pokemon/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(battleService, times(1)).getBattlesByPokemon(999L);
    }
}
