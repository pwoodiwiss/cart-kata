package itv.kata.cart;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StoreTest {
    @Test
    void shouldNotContainItemIfNotAdded() {
        Store store = new Store();

        assertFalse(store.containsItem("X"));
    }

    @Test
    void shouldContainItemAfterAdding() {
        Store store = new Store();

        store.addItemPrice("X", 40);

        assertTrue(store.containsItem("X"));
    }

    @Test
    void shouldCalculateUsingPrice() {
        Store store = new Store();
        store.addItemPrice("X", 40);

        assertEquals(40, store.calculateCost(Collections.singletonMap("X", 1)));
    }

    @Test
    void shouldCalculateUsingQuantity() {
        Store store = new Store();
        store.addItemPrice("Z", 1);

        assertEquals(5, store.calculateCost(Collections.singletonMap("Z", 5)));
    }

    @Test
    void shouldTotalAllItems() {
        Store store = new Store();
        store.addItemPrice("Z", 1);
        store.addItemPrice("Q", 10);


        Map<String, Integer> itemQty = new HashMap<>();
        itemQty.put("Z", 3);
        itemQty.put("Q", 5);
        assertEquals(53, store.calculateCost(itemQty));
    }

    @Test
    void shouldCallItemOfferWhenCalculating() {
        Store store = new Store();
        ItemOffer offer = mock(ItemOffer.class);

        ItemOffer.CalculateResult result = new ItemOffer.CalculateResult();
        result.total = 100;
        result.remainder = 0;
        when(offer.calculate(anyInt())).thenReturn(result);

        store.addItemPrice("Z", 0, offer);

        assertEquals(100, store.calculateCost(Collections.singletonMap("Z", 5)));
        verify(offer).calculate(eq(5));

    }
}
