package org.learning.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.model.Ingredient;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.repository.IngredientRepository;
import org.learning.java.springlamiapizzeriacrud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Ingredient ingredient = ingredientService.getById(id);
            //
            model.addAttribute("ingredient", ingredient);
            return "/ingredients/edit";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/ingredients/create";
        }
        ingredientService.createIngredient(formIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ingredient") Ingredient formIngredient) {
        ingredientService.updateIngredient(formIngredient, id);
        return "redirect:/ingredients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            boolean success = ingredientService.deleteById(id);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "la cancellazione è andata a buon fine");
                return "redirect:/ingredients";
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
