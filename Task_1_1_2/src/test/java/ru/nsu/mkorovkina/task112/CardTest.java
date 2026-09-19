package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {
    private final Card card = new Card(Suit.DIAMONDS, Rank.JACK);

    @Test
    void getCardSuit() {
        assertEquals(Suit.DIAMONDS, card.getCardSuit());
    }

    @Test
    void getCardRank() {
        assertEquals(Rank.JACK, card.getCardRank());
    }

    @Test
    void getCardValue() {
        assertEquals(10, card.getCardValue());
    }

    @Test
    void testToString() {
        assertEquals("Jack Diamonds (10)", card.toString());
    }
}