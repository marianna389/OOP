package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getPlayerScore() {
        Player player = new Player();
        player.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        player.addPlayerCard(new Card(Suit.CLUBS, Rank.EIGHT));
        player.addPlayerCard(new Card(Suit.HEARTS, Rank.ACE));
        assertEquals(14, player.getPlayerScore());
    }

    @Test
    void printPlayerCards() {
        Player player = new Player();
        player.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        player.addPlayerCard(new Card(Suit.CLUBS, Rank.EIGHT));
        player.addPlayerCard(new Card(Suit.HEARTS, Rank.ACE));
        assertFalse(player.overflow());
    }

    @Test
    void blackJack() {
        Player player = new Player();
        player.addPlayerCard(new Card(Suit.SPADES, Rank.ACE));
        player.addPlayerCard(new Card(Suit.CLUBS, Rank.KING));
        assertTrue(player.blackJack());
        player.clearPlayerCards();
        assertEquals(0, player.getPlayerScore());
    }
}