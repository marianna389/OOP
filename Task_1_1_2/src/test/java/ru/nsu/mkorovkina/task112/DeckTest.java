package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    void getSize() {
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 5; i++) {
            deck.takeCard();
        }
        assertEquals(47, deck.getSize());
    }
}