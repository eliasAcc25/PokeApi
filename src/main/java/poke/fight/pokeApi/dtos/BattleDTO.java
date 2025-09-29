package poke.fight.pokeApi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class BattleDTO {

    private Long id;
    private PokemonDTO pokemon1;
    private PokemonDTO pokemon2;
    private PokemonDTO winner;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime battleDate;

    // Construtores
    public BattleDTO() {}

    public BattleDTO(Long id, PokemonDTO pokemon1, PokemonDTO pokemon2,
                     PokemonDTO winner, LocalDateTime battleDate) {
        this.id = id;
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.winner = winner;
        this.battleDate = battleDate;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PokemonDTO getPokemon1() { return pokemon1; }
    public void setPokemon1(PokemonDTO pokemon1) { this.pokemon1 = pokemon1; }

    public PokemonDTO getPokemon2() { return pokemon2; }
    public void setPokemon2(PokemonDTO pokemon2) { this.pokemon2 = pokemon2; }

    public PokemonDTO getWinner() { return winner; }
    public void setWinner(PokemonDTO winner) { this.winner = winner; }

    public LocalDateTime getBattleDate() { return battleDate; }
    public void setBattleDate(LocalDateTime battleDate) { this.battleDate = battleDate; }
}
