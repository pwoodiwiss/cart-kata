package itv.kata.cart;

import itv.kata.cart.exceptions.ItemDoesNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CartTest {
    Store store = mock(Store.class);

    @BeforeEach
    void before() {
        when(store.containsItem(any())).thenReturn(true);
    }

    @Test
    void addingItemsShouldGetStored() {
        Cart cart = new Cart(store);

        cart.addItem("A", 3);

        assertEquals(1, cart.getItems().size());
        assertEquals(Integer.valueOf(3), cart.getItems().get("A"));
    }

    @Test
    void addingDuplicatesShouldAccumulateQuantity() {
        Cart cart = new Cart(store);

        cart.addItem("A", 23);
        cart.addItem("A", 19);

        assertEquals(Integer.valueOf(42), cart.getItems().get("A"));
    }

    @Test
    void calculateTotalCostShouldDelegateToStore() {
        Cart cart = new Cart(store);
        cart.addItem("A", 66);
        when(store.calculateCost(anyMap())).thenReturn(7);

        final int result = cart.calculateTotalCost();

        final ArgumentCaptor<Map<String, Integer>> arg = ArgumentCaptor.forClass(Map.class);

        verify(store, times(1)).calculateCost(arg.capture());
        assertEquals(1, arg.getValue().size());
        assertEquals(Integer.valueOf(66), cart.getItems().get("A"));
        assertEquals(7, result);
    }

    @Test
    void shouldErrorWhenAddingItemNotInStore() {
        when(store.containsItem(eq("B"))).thenReturn(false);
        Cart cart = new Cart(store);

        assertThrows(ItemDoesNotExistException.class, () -> {
            cart.addItem("B", 23);
        });
    }
}
