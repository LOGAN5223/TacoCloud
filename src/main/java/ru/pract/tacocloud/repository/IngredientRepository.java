package ru.pract.tacocloud.repository;


import org.springframework.data.repository.CrudRepository;
import ru.pract.tacocloud.entity.Ingredient;
import ru.pract.tacocloud.entity.Taco;
import ru.pract.tacocloud.entity.TacoIngredient;

import java.util.List;
import java.util.Optional;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}


