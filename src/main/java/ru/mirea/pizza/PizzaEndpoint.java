package ru.mirea.pizza;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.mirea.pizza.model.xml.*;

@Endpoint
@RequiredArgsConstructor
public class PizzaEndpoint {
    private static final String NAMESPACE_URI = "http://www.mirea.ru/pizza";

    private final PizzaService pizzaService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUserRequest")
    @ResponsePayload
    public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {
        RegisterUserResponse response = new RegisterUserResponse();
        response.setResult(pizzaService.registerUser(request.getUser()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "showIngridientsRequest")
    @ResponsePayload
    public ShowIngridientsResponse showIngridients(@RequestPayload ShowIngridientsRequest request) {
        ShowIngridientsResponse response = new ShowIngridientsResponse();
        response.setIngridient(pizzaService.showToppings(request.getUser()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "makePizzaRequest")
    @ResponsePayload
    public MakePizzaResponse makePizza(@RequestPayload MakePizzaRequest request) {
        MakePizzaResponse response = new MakePizzaResponse();
        response.setResult(pizzaService.makePizza(request.getUser(), request.getPizza()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "showPizzaRequest")
    @ResponsePayload
    public ShowPizzaResponse showPizza(@RequestPayload ShowPizzaRequest request) {
        ShowPizzaResponse response = new ShowPizzaResponse();
        response.setUser(request.getUser());
        response.setPizza(pizzaService.showPizza(request.getUser()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "makeOrderRequest")
    @ResponsePayload
    public MakeOrderResponse makeOrder(@RequestPayload MakeOrderRequest request) {
        MakeOrderResponse response = new MakeOrderResponse();
        response.setResult(pizzaService.makeOrder(request.getOrder()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "showOrderRequest")
    @ResponsePayload
    public ShowOrderResponse showOrder(@RequestPayload ShowOrderRequest request) {
        ShowOrderResponse response = new ShowOrderResponse();
        response.setOrder(pizzaService.showOrder(request.getUser()));
        return response;
    }
}
