package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SuitTest {

    @Test
    void getSuitName() {
        assertEquals("Clubs", Suit.CLUBS.getSuitName());
    }
}