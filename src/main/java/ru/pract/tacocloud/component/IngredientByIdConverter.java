package ru.pract.tacocloud.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import ru.pract.tacocloud.entity.Ingredient;
import ru.pract.tacocloud.repository.IngredientRepository;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @Override

    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
