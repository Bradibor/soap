package ru.mirea.pizza;

import org.springframework.stereotype.Service;
import ru.mirea.pizza.model.PizzaUser;
import ru.mirea.pizza.model.xml.Order;
import ru.mirea.pizza.model.xml.Pizza;
import ru.mirea.pizza.model.xml.Topping;
import ru.mirea.pizza.model.xml.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PizzaService {
    private Map<String, PizzaUser> pizzaUsers = new HashMap<>();

    public String registerUser(User user) {
        if (user.getLogin() == null) return "Wrong request! Login must be not null!";
        if (pizzaUsers.containsKey(user.getLogin())) return "User '"+ user.getLogin() + "' already registered!";
        pizzaUsers.put(user.getLogin(), new PizzaUser(user.getLogin()));
        return "User '"+ user.getLogin() + "' successfully registered!";
    }

    public List<Topping> showToppings(User user) {
        if (!pizzaUsers.containsKey(user.getLogin())) throw new IllegalArgumentException("No user found");
        return pizzaUsers.get(user.getLogin()).getAvailableToppings();
    }

    public String makePizza(User user, Pizza pizza) {
        if (!pizzaUsers.containsKey(user.getLogin())) throw new IllegalArgumentException("No user found");
        pizzaUsers.get(user.getLogin())
                .getPizzas()
                .add(pizza);
        return "Pizza successfully added";
    }

    public List<Pizza> showPizza(User user) {
        if (!pizzaUsers.containsKey(user.getLogin())) throw new IllegalArgumentException("No user found");
        return pizzaUsers.get(user.getLogin()).getPizzas();
    }

    public String makeOrder(Order order) {
        if (!pizzaUsers.containsKey(order.getUser().getLogin())) throw new IllegalArgumentException("No user found");
        pizzaUsers.get(order.getUser().getLogin()).getOrders().add(order);
        return "Order successfully added";
    }

    public List<Order> showOrder(User user) {
        if (!pizzaUsers.containsKey(user.getLogin())) throw new IllegalArgumentException("No user found");
        return pizzaUsers.get(user.getLogin()).getOrders();
    }
}
