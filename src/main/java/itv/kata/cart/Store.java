package itv.kata.cart;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Integer> itemPrices;

    public Store() {
        itemPrices = new HashMap<>();
    }

    public Cart createCart() {
        return new Cart(this);
    };

    /**
     * Calculate the cost of all the items passed, based on item prices in the store
     * @param items {@code Map} with item sku as key and quantity of items as value
     * @return the total cost of all items at current prices
     */
    int calculateCost(Map<String, Integer> items) {
        return items.entrySet()
                .stream()
                .mapToInt((entry) -> itemPrices.get(entry.getKey()) * entry.getValue())
                .sum();
    }

    /**
     * Adds items with the price to the store
     * @param sku item to add to the store
     * @param price price of the item
     */
    public void addItemPrice(String sku, int price) {
        itemPrices.put(sku, price);
    }


    /**
     * Returns if the store contains the item given item
     * @param sku item to check
     * @return {@code true} if item is in the store
     */
    boolean containsItem(String sku) {
        return itemPrices.containsKey(sku);
    }
}
