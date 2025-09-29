package poke.fight.pokeApi.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "battles")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne indica relacionamento muitos-para-um
    // Uma batalha tem um Pokemon1, mas um Pokemon pode participar de várias batalhas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon1_id", nullable = false)
    private Pokemon pokemon1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon2_id", nullable = false)
    private Pokemon pokemon2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id", nullable = false)
    private Pokemon winner;

    @Column(name = "battle_date", nullable = false)
    private LocalDateTime battleDate;

    // Construtores
    public Battle() {
        this.battleDate = LocalDateTime.now();
    }

    public Battle(Pokemon pokemon1, Pokemon pokemon2, Pokemon winner) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.winner = winner;
        this.battleDate = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Pokemon getPokemon1() {
        return pokemon1;
    }
    public void setPokemon1(Pokemon pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public Pokemon getPokemon2() {
        return pokemon2;
    }
    public void setPokemon2(Pokemon pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public Pokemon getWinner() {
        return winner;
    }
    public void setWinner(Pokemon winner) {
        this.winner = winner;
    }

    public LocalDateTime getBattleDate() {
        return battleDate;
    }
    public void setBattleDate(LocalDateTime battleDate) {
        this.battleDate = battleDate;
    }
}
