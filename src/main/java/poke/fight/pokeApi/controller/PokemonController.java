package poke.fight.pokeApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.fight.pokeApi.dtos.PokemonDTO;
import poke.fight.pokeApi.service.PokemonService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/pokemon")
@CrossOrigin(origins = "*")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<List<PokemonDTO>> getAllPokemons() {
        List<PokemonDTO> pokemons = pokemonService.getAllPokemons();
        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDTO> getPokemonById(@PathVariable Long id) {
        Optional<PokemonDTO> pokemon = pokemonService.getPokemonById(id);
        return pokemon.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<PokemonDTO> searchPokemon(@PathVariable String name) {
        try {
            PokemonDTO pokemon = pokemonService.searchPokemon(name);
            return ResponseEntity.ok(pokemon);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PokemonDTO> createPokemon(@RequestBody PokemonDTO pokemonDTO) {
        try {
            PokemonDTO created = pokemonService.createPokemon(pokemonDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
