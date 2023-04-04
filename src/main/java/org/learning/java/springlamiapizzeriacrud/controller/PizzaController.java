package org.learning.java.springlamiapizzeriacrud.controller;


import jakarta.validation.Valid;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.PizzaRpository;
import org.learning.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

//    @Autowired
//    private PizzaRpository pizzaRpository;

    @Autowired
    private PizzaService pizzaService;

    //    @GetMapping
//    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
//
//        List<Pizza> pizzas;
//        if (keyword.isEmpty()) {
//            pizzas = pizzaRpository.findAll();
//        } else {
//            pizzas = pizzaRpository.findByNameContainingIgnoreCase(keyword.get());
//            model.addAttribute("keyword", keyword.get());
//        }
//        model.addAttribute("allPizza", pizzas);
//        return "/pizzas/index";
//    }
    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword) {
        List<Pizza> pizzas;
        if (keyword.isEmpty()) {
            pizzas = pizzaService.getAllPizzas();
        } else {
            pizzas = pizzaService.getFilteredPizzas(keyword.get());
            model.addAttribute("keyword", keyword.get());
        }
        model.addAttribute("allPizza", pizzas);

        return "/pizzas/index";
    }


    //    @GetMapping("/{id}")
//    public String show(@PathVariable Integer id, Model model) {
//        Pizza pizza = pizzaRpository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        model.addAttribute("pizza", pizza);
//        return "/pizzas/show";
//    }
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getById(id);
            model.addAttribute("pizza", pizza);
            return "/pizzas/show";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "/pizzas/create";
    }

    // STORE
//    @PostMapping("/create")
//    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//
//            return "/pizzas/create";
//        }
//        Pizza pizzaToPersist = new Pizza();
//        pizzaToPersist.setName(formPizza.getName());
//        pizzaToPersist.setDescription(formPizza.getDescription());
//        pizzaToPersist.setPrice(formPizza.getPrice());
//        pizzaRpository.save(pizzaToPersist);
//
//        return "redirect:/pizzas";
//    }
    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza,
                           BindingResult bindingResult, Model model) {
        // VALIDATION
        if (bindingResult.hasErrors()) {
            // ritorno alla view con il form
            return "/pizzas/create";
        }
        // se non ci sono errori procedo con la persistenza
        pizzaService.createPizza(formPizza);
        return "redirect:/pizzas";
    }

}
