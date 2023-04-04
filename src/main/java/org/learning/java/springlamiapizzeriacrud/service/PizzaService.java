package org.learning.java.springlamiapizzeriacrud.service;

import org.hibernate.validator.constraints.NotEmpty;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.PizzaRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaRpository pizzaRpository;

    public Pizza createPizza(Pizza formPizza) {
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setName(formPizza.getName());
        pizzaToPersist.setDescription(formPizza.getDescription());
        pizzaToPersist.setPrice(formPizza.getPrice());
        return pizzaRpository.save(pizzaToPersist);
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRpository.findAll();
    }

    public List<Pizza> getFilteredPizzas(String keyword) {
        return pizzaRpository.findByNameContainingIgnoreCase(keyword);
    }

    public Pizza getById(Integer id) {
        Optional<Pizza> result = pizzaRpository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException();
        }
    }

    public Pizza update(Integer id, Pizza formPizza) {
        Pizza pizzaToUpdate = getById(id);
        pizzaToUpdate.setName(formPizza.getName());
        pizzaToUpdate.setDescription(formPizza.getDescription());
        pizzaToUpdate.setPrice(formPizza.getPrice());
        return pizzaRpository.save(pizzaToUpdate);
    }

    public boolean deleteById(Integer id) {
        pizzaRpository.findById(id).orElseThrow(() -> new RuntimeException());
        try {
            pizzaRpository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
