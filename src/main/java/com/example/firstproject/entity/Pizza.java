package com.example.firstproject.entity;

import com.example.firstproject.dto.PizzaDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;

    public static Pizza createPizza(PizzaDto pizzaDto) {
        return new Pizza(null, pizzaDto.getName(), pizzaDto.getPrice());
    }

    public void patch(PizzaDto pizzaDto) {
        if (pizzaDto.getName() != null)
            this.name = pizzaDto.getName();
        if (pizzaDto.getPrice() != null)
            this.price = pizzaDto.getPrice();
    }
}
