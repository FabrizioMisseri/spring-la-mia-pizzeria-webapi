package org.learning.java.springlamiapizzeriacrud.service;

import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> result = ingredientRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException();
        }
    }


    public Ingredient createIngredient(Ingredient formIngredient) {
        Ingredient ingredientToPersist = new Ingredient();
        ingredientToPersist.setName(formIngredient.getName());
        return ingredientRepository.save(ingredientToPersist);
    }

    public Ingredient updateIngredient(Ingredient formIngredient, Integer id) {
        Ingredient ingredientToUpdate = getById(id);
        ingredientToUpdate.setName(formIngredient.getName());
        return ingredientRepository.save(ingredientToUpdate);
    }

    public boolean deleteById(Integer id) {
        ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException());
        try {
            ingredientRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
