package poke.fight.pokeApi.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateBattleRequestDTO {

    @NotNull(message = "ID do primeiro Pokémon é obrigatório")
    @Positive(message = "ID do primeiro Pokémon deve ser positivo")
    private Integer pokemon1Id;

    @NotNull(message = "ID do segundo Pokémon é obrigatório")
    @Positive(message = "ID do segundo Pokémon deve ser positivo")
    private Integer pokemon2Id;

    // Construtores
    public CreateBattleRequestDTO() {}

    public CreateBattleRequestDTO(Integer pokemon1Id, Integer pokemon2Id) {
        this.pokemon1Id = pokemon1Id;
        this.pokemon2Id = pokemon2Id;
    }

    // Getters e Setters
    public Integer getPokemon1Id() { return pokemon1Id; }
    public void setPokemon1Id(Integer pokemon1Id) { this.pokemon1Id = pokemon1Id; }

    public Integer getPokemon2Id() { return pokemon2Id; }
    public void setPokemon2Id(Integer pokemon2Id) { this.pokemon2Id = pokemon2Id; }
}
