package org.learning.java.springlamiapizzeriacrud.api;

import jakarta.validation.Valid;
import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/pizzas")
public class PizzaRestController {

    @Autowired
    PizzaService pizzaService;

    // lista di tutti i books
//    @GetMapping
//    public List<Book> list(@RequestParam(name = "q") Optional<String> search) {
//        if (search.isPresent()) {
//            return bookService.getFilteredBooks(search.get());
//        }
//        return bookService.getAllBooks();
//    }
    @GetMapping
    public List<Pizza> pizzaList(@RequestParam(name = "q") Optional<String> search) {

        if (search.isPresent()) {
            return pizzaService.getFilteredPizzas(search.get());
        }
        return pizzaService.getAllPizzas();
    }

    // singolo book
//    @GetMapping("/{id}")
//    public Book getById(@PathVariable Integer id) {
//        try {
//            return bookService.getById(id);
//        } catch (BookNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/{id}")
    public Pizza getById(@PathVariable Integer id) {

        try {
            return pizzaService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // create book
//    @PostMapping
//    public Book create(@Valid @RequestBody Book book) {
//        if (!bookService.isValidIsbn(book)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "{\"errors\": \"the isbn must be unique\"}");
//        }
//        return bookService.createBook(book);
//    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        return pizzaService.createPizza(pizza);
    }

    // update book
//    @PutMapping("/{id}")
//    public Book update(@PathVariable Integer id, @Valid @RequestBody Book book) {
//        if (!bookService.isValidIsbn(book)) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                    "{\"errors\": \"the isbn must be unique\"}");
//        }
//        try {
//            return bookService.updateBook(book, id);
//        } catch (BookNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {

        try {
            return pizzaService.update(id, pizza);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    // delete book
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Integer id) {
//        try {
//            boolean success = bookService.deleteById(id);
//            if (!success) {
//                throw new ResponseStatusException(HttpStatus.CONFLICT,
//                        "Unable to delete book because it has borrowings");
//            }
//        } catch (BookNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
//    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        try {
            boolean success = pizzaService.deleteById(id);
            if (!success) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to delete book because it has borrowings");
            }
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
