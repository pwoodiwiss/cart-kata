package itv.kata.cart;

import itv.kata.cart.exceptions.ItemDoesNotExistException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * {@code Cart} handles all responsibilities for store shopping cart
 */
public final class Cart {
    private final Store store;
    private Map<String, Integer> items;

    Cart(Store store) {
        this.store = store;
        this.items = new HashMap<>();
    }

    /**
     * Add items to the cart, this throws a {@link ItemDoesNotExistException} when item sku does not exist in the store
     * @param sku the sku of the item to be added to the cart
     * @param quantity the quantity of items to add to the store
     */
    public void addItem(String sku, int quantity) {
        if(!store.containsItem(sku)) {
            throw new ItemDoesNotExistException(sku);
        }

        items.compute(sku, (key, value) ->
             value == null ? quantity : value + quantity
        );
    }

    /**
     * Returns a map of all items in the cart
     * @return {@code Map} with item sku as key and quantity of items in the cart as value
     */
    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    /**
     * Calculates and returns the total cost of the cart
     * @return the total price of all items in the cart
     */
    public int calculateTotalCost() {
        return store.calculateCost(getItems());
    }
}
