package org.learning.java.springlamiapizzeriacrud.service;

import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

}
