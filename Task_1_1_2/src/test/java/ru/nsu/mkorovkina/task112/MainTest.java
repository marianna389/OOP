package ru.nsu.mkorovkina.task112;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MainTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Test
    void mainTest1() {
        Deck deck = mock(Deck.class);
        when(deck.takeCard())
                .thenReturn(new Card(Suit.HEARTS, Rank.TEN)) //1 раунд
                .thenReturn(new Card(Suit.DIAMONDS, Rank.FIVE))
                .thenReturn(new Card(Suit.HEARTS, Rank.ACE))
                .thenReturn(new Card(Suit.CLUBS, Rank.ACE))
                .thenReturn(new Card(Suit.HEARTS, Rank.FIVE))
                .thenReturn(new Card(Suit.SPADES, Rank.JACK))
                .thenReturn(new Card(Suit.SPADES, Rank.EIGHT))
                .thenReturn(new Card(Suit.HEARTS, Rank.QUEEN)) //2 раунд
                .thenReturn(new Card(Suit.DIAMONDS, Rank.FOUR))
                .thenReturn(new Card(Suit.SPADES, Rank.ACE))
                .thenReturn(new Card(Suit.CLUBS, Rank.SIX))
                .thenReturn(new Card(Suit.SPADES, Rank.FOUR))
                .thenReturn(new Card(Suit.HEARTS, Rank.JACK)) //3 раунд
                .thenReturn(new Card(Suit.DIAMONDS, Rank.ACE))
                .thenReturn(new Card(Suit.HEARTS, Rank.THREE))
                .thenReturn(new Card(Suit.CLUBS, Rank.TWO));
        when(deck.getSize()).thenReturn(45);
        String input = "1\n0\n1\n1\n0\n1\n0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.mainForTests(deck);
        String output = out.toString();
        assertTrue(output.contains("You drew with the dealer! Score 0:0 draw"));
        assertTrue(output.contains("You won the round! Score 1:0 in your favor"));
        assertTrue(output.contains("You have blackjack!"));
        assertTrue(output.contains("You won the round! Score 2:0 in your favor"));
        assertTrue(output.contains("Victory :)"));
    }

    @Test
    void mainTest2() {
        Deck deck = mock(Deck.class);
        when(deck.takeCard())
                .thenReturn(new Card(Suit.HEARTS, Rank.ACE)) //1 раунд
                .thenReturn(new Card(Suit.DIAMONDS, Rank.JACK))
                .thenReturn(new Card(Suit.HEARTS, Rank.KING))
                .thenReturn(new Card(Suit.CLUBS, Rank.ACE))
                .thenReturn(new Card(Suit.HEARTS, Rank.TWO)) //2 раунд
                .thenReturn(new Card(Suit.SPADES, Rank.FIVE))
                .thenReturn(new Card(Suit.SPADES, Rank.QUEEN))
                .thenReturn(new Card(Suit.CLUBS, Rank.TEN))
                .thenReturn(new Card(Suit.DIAMONDS, Rank.ACE))
                .thenReturn(new Card(Suit.SPADES, Rank.ACE))
                .thenReturn(new Card(Suit.CLUBS, Rank.SIX)) //3 раунд
                .thenReturn(new Card(Suit.SPADES, Rank.NINE))
                .thenReturn(new Card(Suit.HEARTS, Rank.JACK))
                .thenReturn(new Card(Suit.DIAMONDS, Rank.THREE))
                .thenReturn(new Card(Suit.HEARTS, Rank.TEN));
        when(deck.getSize()).thenReturn(45);
        String input = "1\n1\n1\n0\n1\n1\n0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.mainForTests(deck);
        String output = out.toString();
        assertTrue(output.contains("You drew with the dealer! Score 0:0 draw"));
        assertTrue(output.contains("Both you and the dealer have blackjack!"));
        assertTrue(output.contains("You lost the round! Score 0:1 in dealer's favor"));
        assertTrue(output.contains("You lost the round! Score 0:2 in dealer's favor"));
        assertTrue(output.contains("Defeat :("));
        assertTrue(output.contains("You revealed a card Ace Spades (1)"));
        assertTrue(output.contains("  Your cards: [Two Hearts (2), Five Spades (5), " +
                "Ace Diamonds (11), Ace Spades (1)]"));
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}