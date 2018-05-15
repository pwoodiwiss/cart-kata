package itv.kata.cart.exceptions;

/**
 * Exception to indicate that item added to cart does not exist in the store
 */
public class ItemDoesNotExistException extends RuntimeException {
    public ItemDoesNotExistException(String itemCode) {
        super(String.format("Item %s does not exist in store", itemCode));
    }
}
