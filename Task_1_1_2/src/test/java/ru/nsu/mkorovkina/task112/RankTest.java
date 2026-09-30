package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    void getRank() {
        assertEquals("Queen", Rank.QUEEN.getRank());
    }

    @Test
    void getRankValue() {
        assertEquals(10, Rank.QUEEN.getRankValue());
    }
}