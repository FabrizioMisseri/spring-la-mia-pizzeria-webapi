package org.learning.java.springlamiapizzeriacrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@Controller
//@RequestMapping("/")
//public class HomeController {
//
//    @GetMapping
//    public String helloWorldTest(){
//        return "redirect:/home";
//    }
@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String redirectToPizza() {
        return "redirect:/pizzas";
    }
}




