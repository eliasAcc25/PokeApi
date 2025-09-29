package poke.fight.pokeApi.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "pokemons")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @Column define propriedades da coluna no banco
    @Column(name = "pokeapi_id", nullable = false, unique = true)
    private Integer pokeapiId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "base_experience")
    private Integer baseExperience;

    private Integer height;
    private Integer weight;

    // Construtores
    public Pokemon() {
        // Construtor vazio obrigatório para JPA
    }

    public Pokemon(Integer pokeapiId, String name, Integer baseExperience,
                   Integer height, Integer weight) {
        this.pokeapiId = pokeapiId;
        this.name = name;
        this.baseExperience = baseExperience;
        this.height = height;
        this.weight = weight;
    }

    // Getters e Setters (métodos para acessar e modificar os campos)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getPokeapiId() {
        return pokeapiId; }
    public void setPokeapiId(Integer pokeapiId) { this.pokeapiId = pokeapiId; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }
    public void setBaseExperience(Integer baseExperience) { this.baseExperience = baseExperience; }

    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) { this.height = height; }

    public Integer getWeight() {
        return weight;
    }
    public void setWeight(Integer weight) {
        this.weight = weight; }
}
