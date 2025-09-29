package poke.fight.pokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poke.fight.pokeApi.Entity.Pokemon;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByName(String name);
    boolean existsByName(String name);
    Optional<Pokemon> findByPokeapiId(Integer pokeapiId);
}