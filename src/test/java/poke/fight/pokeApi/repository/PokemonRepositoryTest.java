package poke.fight.pokeApi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import poke.fight.pokeApi.Entity.Pokemon;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PokemonRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PokemonRepository pokemonRepository;

    private Pokemon testPokemon;

    @BeforeEach
    void setUp() {
        testPokemon = new Pokemon(25, "pikachu", 112, 4, 60);
    }

    @Test
    void findByName_ExistingPokemon_ShouldReturnPokemon() {
        // Given
        entityManager.persistAndFlush(testPokemon);

        // When
        Optional<Pokemon> result = pokemonRepository.findByName("pikachu");

        // Then
        assertTrue(result.isPresent());
        assertEquals("pikachu", result.get().getName());
        assertEquals(25, result.get().getPokeapiId());
    }

    @Test
    void findByName_NonExistingPokemon_ShouldReturnEmpty() {
        // When
        Optional<Pokemon> result = pokemonRepository.findByName("invalidpokemon");

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void findByPokeapiId_ExistingPokemon_ShouldReturnPokemon() {
        // Given
        entityManager.persistAndFlush(testPokemon);

        // When
        Optional<Pokemon> result = pokemonRepository.findByPokeapiId(25);

        // Then
        assertTrue(result.isPresent());
        assertEquals("pikachu", result.get().getName());
        assertEquals(25, result.get().getPokeapiId());
    }

    @Test
    void findByPokeapiId_NonExistingPokemon_ShouldReturnEmpty() {
        // When
        Optional<Pokemon> result = pokemonRepository.findByPokeapiId(999);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void existsByName_ExistingPokemon_ShouldReturnTrue() {
        // Given
        entityManager.persistAndFlush(testPokemon);

        // When
        boolean exists = pokemonRepository.existsByName("pikachu");

        // Then
        assertTrue(exists);
    }

    @Test
    void existsByName_NonExistingPokemon_ShouldReturnFalse() {
        // When
        boolean exists = pokemonRepository.existsByName("invalidpokemon");

        // Then
        assertFalse(exists);
    }

    @Test
    void save_NewPokemon_ShouldPersistSuccessfully() {
        // Given
        Pokemon newPokemon = new Pokemon(4, "charmander", 62, 6, 85);

        // When
        Pokemon saved = pokemonRepository.save(newPokemon);

        // Then
        assertNotNull(saved.getId());
        assertEquals("charmander", saved.getName());
        assertEquals(4, saved.getPokeapiId());

        // Verify it's actually persisted
        Pokemon found = entityManager.find(Pokemon.class, saved.getId());
        assertNotNull(found);
        assertEquals("charmander", found.getName());
    }
}
