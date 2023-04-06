package org.learning.java.springlamiapizzeriacrud.controller;

import jakarta.validation.Valid;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.java.springlamiapizzeriacrud.service.PizzaService;
import org.learning.java.springlamiapizzeriacrud.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/special-offer")
public class SpecialOfferController {

    @Autowired
    PizzaService pizzaService;

    @Autowired
    SpecialOfferService specialOfferService;


    @GetMapping("/create")
    public String create(@RequestParam(name = "pizzaId") Integer id, Model model) {
        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.setStartingDate(LocalDate.now());
        specialOffer.setEndingDate(LocalDate.now().plusMonths(1));
        Pizza pizza = pizzaService.getById(id);
        specialOffer.setPizza(pizza);
        model.addAttribute("specialOffer", specialOffer);
        return "/special-offer/create";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute SpecialOffer formSpecialOffer,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/special-offer/create";
        }
        SpecialOffer createdSpecialOffer = specialOfferService.create(formSpecialOffer);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        SpecialOffer specialOffer = specialOfferService.getById(id);
        model.addAttribute("specialOffer", specialOffer);
        return "/special-offer/edit";
    }

    @PostMapping("/edit/{id}")
    public String doUpdate(@PathVariable Integer id, @Valid @ModelAttribute("specialOffer")
    SpecialOffer formSpecialOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/pizzas";
        }
        SpecialOffer updatedSO = specialOfferService.update(id, formSpecialOffer);
        return "redirect:/pizzas/" + updatedSO.getPizza().getId();
    }

}
