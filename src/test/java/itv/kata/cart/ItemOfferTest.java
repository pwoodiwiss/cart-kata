package itv.kata.cart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemOfferTest {
    @Test
    void shouldCalculateOfferTotalWithoutRemainder() {
        ItemOffer offer = new ItemOffer(2, 10);

        ItemOffer.CalculateResult result = offer.calculate(4);

        assertEquals(20, result.total);
        assertEquals(0, result.remainder);
    }

    @Test
    void shouldCalculateOfferTotalWithRemainder() {
        ItemOffer offer = new ItemOffer(3, 20);

        ItemOffer.CalculateResult result = offer.calculate(4);

        assertEquals(20, result.total);
        assertEquals(1, result.remainder);
    }

    @Test
    void invalidForOffer() {
        ItemOffer offer = new ItemOffer(3, 20);

        ItemOffer.CalculateResult result = offer.calculate(2);

        assertEquals(0, result.total);
        assertEquals(2, result.remainder);
    }
}
