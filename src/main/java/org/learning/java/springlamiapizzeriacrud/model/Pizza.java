package org.learning.java.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Name;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 5, max = 255, message = "Il Nome della pizza deve esserci e non può superare i 255 caratteri")
    @Column(nullable = false)
    private String name;

    @PositiveOrZero
    @Column(nullable = false)
    private Double price;

    @NotEmpty
    @Size(min = 5, max = 255, message = "La lista degli ingredienti deve esserci e non può superare i 255 caratteri")
    @Column(nullable = false)
    private String description;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {
        if (price == null || price < 5) {
            price = (double) 5;
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
