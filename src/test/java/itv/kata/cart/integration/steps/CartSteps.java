package itv.kata.cart.integration.steps;

import cucumber.api.java8.En;
import itv.kata.cart.Cart;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartSteps implements En {
    private Cart cart;

    public CartSteps() {
        When("^new cart$", () -> {
            cart = new Cart();
        });

        Then("^calculateTotalCost should return (\\d+)$", (Integer total) -> {
            assertEquals(total.intValue(), cart.calculateTotalCost());
        });
    }

}
