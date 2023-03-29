package org.learning.java.springlamiapizzeriacrud.controller;


import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.PizzaRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRpository pizzaRpository;

    @GetMapping
    public String index(Model model) {

        List<Pizza> pizzas = pizzaRpository.findAll();
        model.addAttribute("allPizza", pizzas);
        return "pizzas/index";
    }

}
