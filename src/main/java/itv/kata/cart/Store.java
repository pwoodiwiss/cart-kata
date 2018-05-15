package itv.kata.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Store {
    private Map<String, ItemPrice> itemPrices;

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
                .mapToInt((entry) -> itemPrices.get(entry.getKey()).calculateTotalPrice(entry.getValue()))
                .sum();
    }

    /**
     * Adds items with the price to the store
     * @param sku item to add to the store
     * @param price price of the item
     */
    public void addItemPrice(String sku, int price) {
        addItemPrice(sku, price, null);
    }

    /**
     * Adds items with the price to the store
     * @param sku item to add to the store
     * @param price price of the item
     * @param offer multi-buy offer
     */
    public void addItemPrice(String sku, int price, ItemOffer offer) {
        itemPrices.put(sku, new ItemPrice(price, offer));
    }

    /**
     * Returns if the store contains the item given item
     * @param sku item to check
     * @return {@code true} if item is in the store
     */
    boolean containsItem(String sku) {
        return itemPrices.containsKey(sku);
    }

    /**
     * Internal class to hold prices and calculate total price for a quantity of items
     */
    private static class ItemPrice {
        private int price;
        private Optional<ItemOffer> possibleOffer;

        ItemPrice(int price, ItemOffer possibleOffer) {
            this.price = price;
            this.possibleOffer = Optional.ofNullable(possibleOffer);
        }

        int calculateTotalPrice(int itemQuantity) {
            return possibleOffer
                    .map((offer) -> offer.calculate(itemQuantity))
                    .map((result) -> result.total + (price * result.remainder))
                    .orElse(price * itemQuantity);
        }
    }
}
