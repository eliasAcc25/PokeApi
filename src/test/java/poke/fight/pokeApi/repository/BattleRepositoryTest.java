package poke.fight.pokeApi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import poke.fight.pokeApi.Entity.Battle;
import poke.fight.pokeApi.Entity.Pokemon;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BattleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BattleRepository battleRepository;

    private Pokemon pikachu;
    private Pokemon charmander;
    private Battle testBattle;

    @BeforeEach
    void setUp() {
        pikachu = new Pokemon(25, "pikachu", 112, 4, 60);
        charmander = new Pokemon(4, "charmander", 62, 6, 85);

        // Persist pokemons first
        pikachu = entityManager.persistAndFlush(pikachu);
        charmander = entityManager.persistAndFlush(charmander);

        testBattle = new Battle(pikachu, charmander, pikachu);
        testBattle.setBattleDate(LocalDateTime.now().minusHours(1));
    }

    @Test
    void save_NewBattle_ShouldPersistSuccessfully() {
        // When
        Battle saved = battleRepository.save(testBattle);

        // Then
        assertNotNull(saved.getId());
        assertEquals(pikachu.getId(), saved.getPokemon1().getId());
        assertEquals(charmander.getId(), saved.getPokemon2().getId());
        assertEquals(pikachu.getId(), saved.getWinner().getId());
        assertNotNull(saved.getBattleDate());

        // Verify it's actually persisted
        Battle found = entityManager.find(Battle.class, saved.getId());
        assertNotNull(found);
        assertEquals(pikachu.getName(), found.getWinner().getName());
    }

    @Test
    void findAllByOrderByBattleDateDesc_ShouldReturnBattlesOrderedByDate() {
        // Given
        Battle battle1 = new Battle(pikachu, charmander, pikachu);
        battle1.setBattleDate(LocalDateTime.now().minusHours(2));

        Battle battle2 = new Battle(charmander, pikachu, charmander);
        battle2.setBattleDate(LocalDateTime.now().minusHours(1));

        entityManager.persistAndFlush(battle1);
        entityManager.persistAndFlush(battle2);

        // When
        List<Battle> battles = battleRepository.findAllByOrderByBattleDateDesc();

        // Then
        assertEquals(2, battles.size());
        // The more recent battle should come first
        assertTrue(battles.get(0).getBattleDate().isAfter(battles.get(1).getBattleDate()));
        assertEquals(charmander.getId(), battles.get(0).getWinner().getId());
        assertEquals(pikachu.getId(), battles.get(1).getWinner().getId());
    }

    @Test
    void findBattlesByPokemon_ShouldReturnBattlesWithSpecificPokemon() {
        // Given
        Pokemon bulbasaur = new Pokemon(1, "bulbasaur", 64, 7, 69);
        bulbasaur = entityManager.persistAndFlush(bulbasaur);

        Battle battle1 = new Battle(pikachu, charmander, pikachu);  // pikachu vs charmander
        Battle battle2 = new Battle(pikachu, bulbasaur, bulbasaur); // pikachu vs bulbasaur
        Battle battle3 = new Battle(charmander, bulbasaur, charmander); // charmander vs bulbasaur (no pikachu)

        entityManager.persistAndFlush(battle1);
        entityManager.persistAndFlush(battle2);
        entityManager.persistAndFlush(battle3);

        // When
        List<Battle> pikachuBattles = battleRepository.findBattlesByPokemon(pikachu);

        // Then
        assertEquals(2, pikachuBattles.size());

        // All battles should involve pikachu
        for (Battle battle : pikachuBattles) {
            assertTrue(battle.getPokemon1().getId().equals(pikachu.getId()) ||
                      battle.getPokemon2().getId().equals(pikachu.getId()));
        }
    }

    @Test
    void findBattlesByPokemon_NoBattles_ShouldReturnEmptyList() {
        // Given
        Pokemon squirtle = new Pokemon(7, "squirtle", 63, 5, 90);
        squirtle = entityManager.persistAndFlush(squirtle);

        // Create a battle that doesn't involve squirtle
        Battle battle = new Battle(pikachu, charmander, pikachu);
        entityManager.persistAndFlush(battle);

        // When
        List<Battle> squirtleBattles = battleRepository.findBattlesByPokemon(squirtle);

        // Then
        assertTrue(squirtleBattles.isEmpty());
    }
}
