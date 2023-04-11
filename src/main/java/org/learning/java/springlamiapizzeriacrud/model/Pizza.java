package org.learning.java.springlamiapizzeriacrud.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @JsonIgnore
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<SpecialOffer> specialOffers;

    //@JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

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

    //
    //
    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }


    //
    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(getSpecialOffers(), pizza.getSpecialOffers()) && Objects.equals(getIngredients(), pizza.getIngredients()) && Objects.equals(getId(), pizza.getId()) && Objects.equals(getName(), pizza.getName()) && Objects.equals(getPrice(), pizza.getPrice()) && Objects.equals(getDescription(), pizza.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSpecialOffers(), getIngredients(), getId(), getName(), getPrice(), getDescription());
    }
}
