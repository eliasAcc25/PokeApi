package poke.fight.pokeApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poke.fight.pokeApi.dtos.BattleDTO;
import poke.fight.pokeApi.dtos.CreateBattleRequestDTO;
import poke.fight.pokeApi.service.BattleService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/batalhas")
@CrossOrigin(origins = "*")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @PostMapping
    public ResponseEntity<BattleDTO> createBattle(@Valid @RequestBody CreateBattleRequestDTO request) {
        try {
            BattleDTO battle = battleService.createBattle(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(battle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BattleDTO> getBattle(@PathVariable Long id) {
        Optional<BattleDTO> battle = battleService.getBattleById(id);
        return battle.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BattleDTO>> getAllBattles() {
        List<BattleDTO> battles = battleService.getAllBattles();
        return ResponseEntity.ok(battles);
    }

    @GetMapping("/pokemon/{pokemonId}")
    public ResponseEntity<List<BattleDTO>> getBattlesByPokemon(@PathVariable Long pokemonId) {
        try {
            List<BattleDTO> battles = battleService.getBattlesByPokemon(pokemonId);
            return ResponseEntity.ok(battles);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
