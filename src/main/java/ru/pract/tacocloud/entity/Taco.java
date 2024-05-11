package ru.pract.tacocloud.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotNull
    @Size(min=5, message ="Name must be at least 5 characters long")
    private String name;

    private Date created_at = new Date();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "taco_order_id", referencedColumnName = "id")
    private TacoOrder taco_order_id;

    @OneToMany(mappedBy = "taco_id", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<TacoIngredient> tacoIngredients;

    @Size(min=1, message="You must choose at least 1 ingredient")
    @ManyToMany()
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
