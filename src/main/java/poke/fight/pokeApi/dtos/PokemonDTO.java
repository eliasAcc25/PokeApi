package poke.fight.pokeApi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PokemonDTO{

    private Long id;
    private String name;
    private String sprite;
    private Integer weight;
    private Integer height;

    @JsonProperty("base_experience")
    private Integer baseExperience;

    public PokemonDTO() {}

    public PokemonDTO(Long id, String name, String sprite, Integer weight, Integer height, Integer baseExperience) {
        this.id = id;
        this.name = name;
        this.sprite = sprite;
        this.weight = weight;
        this.height = height;
        this.baseExperience = baseExperience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }
}
