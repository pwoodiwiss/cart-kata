package itv.kata.cart;

/**
 * Class to hold details of special offers
 */
public class ItemOffer {
    private int qualifyingQuantity;
    private int price;

    public ItemOffer(int qualifyingQuantity, int price) {
        this.qualifyingQuantity = qualifyingQuantity;
        this.price = price;
    }

    /**
     * Calculates the total price of the offer and the remaining items
     * @param itemQuantity quantity of items to check the offer against
     * @return tuple with {@code total} price of the offer and {@code remainder} of items not in the offer
     */
    CalculateResult calculate(int itemQuantity) {
        CalculateResult result = new CalculateResult();

        result.total = (itemQuantity / qualifyingQuantity) * price;
        result.remainder = itemQuantity % qualifyingQuantity;

        return result;
    }

    static class CalculateResult {
        int total;
        int remainder;
    }
}
