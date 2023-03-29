package org.learning.java.springlamiapizzeriacrud.repository;

import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRpository extends JpaRepository<Pizza, Integer> {

    public List<String> findByNameContainingIgnoreCase(String name);

}
