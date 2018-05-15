package itv.kata.cart.integration.steps;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import itv.kata.cart.Cart;
import itv.kata.cart.Store;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartSteps implements En {
    private Store store;
    private Cart cart;

    public CartSteps() {
        Before(() -> {
            store = new Store();
        });

        When("^new cart$", () -> {
            cart = store.createCart();
        });

        Then("^calculateTotalCost should return (\\d+)$", (Integer total) -> {
            assertEquals(total.intValue(), cart.calculateTotalCost());
        });

        Given("^The following pricing$", (DataTable stockPricesDT) -> {
            List<PriceRow> prices = stockPricesDT.asList(PriceRow.class);

            for(PriceRow itemPrice: prices) {
                store.addItemPrice(itemPrice.sku, itemPrice.price);
            }
        });

        When("^We have cart with$", (DataTable cartContentDT) -> {
            cart = store.createCart();

            List<CartRow> cartContents = cartContentDT.asList(CartRow.class);

            for(CartRow item: cartContents) {
                cart.addItem(item.sku, item.quantity);
            }
        });
    }

    private static class PriceRow {
        String sku;
        int price;
        String offer;
    }

    private static class CartRow {
        String sku;
        int quantity;
    }
}
