package ru.pract.tacocloud.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "taco_ingredients")
public class TacoIngredient {

    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "taco_id", referencedColumnName = "id")
    private Taco taco_id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ingredients_id", referencedColumnName = "id")
    private Ingredient ingredient_id;

}
