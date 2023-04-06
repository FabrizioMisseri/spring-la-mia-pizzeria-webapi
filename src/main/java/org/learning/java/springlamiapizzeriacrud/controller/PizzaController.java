package org.learning.java.springlamiapizzeriacrud.controller;


import jakarta.validation.Valid;
import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.PizzaRpository;
import org.learning.java.springlamiapizzeriacrud.service.IngredientService;
import org.learning.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

//    @Autowired
//    private PizzaRpository pizzaRpository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    IngredientService ingredientService;

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
        //
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.addAttribute("allIngredients", ingredients);
        //
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
    public String doStore(@Valid @ModelAttribute("pizza") Pizza formPizza,
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

    // EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Pizza pizza = pizzaService.getById(id);
            //
            List<Ingredient> ingredients = ingredientService.getAllIngredients();
            model.addAttribute("allIngredients", ingredients);
            //
            model.addAttribute("pizza", pizza);
            return "/pizzas/edit";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PostMapping("/edit/{id}")
    public String doUpdate(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                           BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/pizzas";
//        }
        //Pizza updatedPizza =
        pizzaService.update(id, formPizza);
        return "redirect:/pizzas/{id}";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = pizzaService.deleteById(id);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
                return "redirect:/pizzas";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
