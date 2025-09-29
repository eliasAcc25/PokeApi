package poke.fight.pokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import poke.fight.pokeApi.Entity.Battle;
import poke.fight.pokeApi.Entity.Pokemon;

import java.util.List;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

    /**
     * Busca todas as batalhas de um Pokemon específico
     * @param pokemon O Pokemon para buscar suas batalhas
     * @return Lista de batalhas onde o Pokemon participou
     */
    @Query("SELECT b FROM Battle b WHERE b.pokemon1 = :pokemon OR b.pokemon2 = :pokemon")
    List<Battle> findBattlesByPokemon(@Param("pokemon") Pokemon pokemon);

    /**
     * Busca todas as batalhas que um Pokemon ganhou
     * @param winner O Pokemon vencedor
     * @return Lista de batalhas vencidas pelo Pokemon
     */
    List<Battle> findByWinner(Pokemon winner);

    /**
     * Busca batalhas ordenadas por data (mais recentes primeiro)
     * @return Lista de batalhas ordenadas por data decrescente
     */
    List<Battle> findAllByOrderByBattleDateDesc();

    /**
     * Conta quantas batalhas um Pokemon venceu
     * @param winner O Pokemon vencedor
     * @return Número de vitórias
     */
    long countByWinner(Pokemon winner);
}
