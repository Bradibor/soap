package ru.mirea.pizza.model;

import lombok.*;
import ru.mirea.pizza.model.xml.Order;
import ru.mirea.pizza.model.xml.Pizza;
import ru.mirea.pizza.model.xml.Topping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PizzaUser {
    private final String userName;
    private List<Topping> availableToppings = defaultToppings;
    private List<Pizza> pizzas = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public final static List<Topping> defaultToppings = new ArrayList<Topping>() {{
        add(new Topping("Колбаски"));
        add(new Topping("Сыр чеддер"));
        add(new Topping("Курица"));
        add(new Topping("Халапеньо"));
        add(new Topping("Помидоры"));
    }};
}
