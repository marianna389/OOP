package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void dealerRoundWithOverflow() {
        HumanPlayer person = new HumanPlayer();
        person.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        Dealer dealer = new Dealer();
        dealer.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        dealer.addPlayerCard(new Card(Suit.CLUBS, Rank.EIGHT));
        dealer.addPlayerCard(new Card(Suit.HEARTS, Rank.ACE));
        Deck deck = new Deck();
        dealer.dealerRound(deck, person);
        assertTrue(dealer.overflow());
    }

    @Test
    void dealerRoundWithoutOverflow() {
        HumanPlayer person = new HumanPlayer();
        person.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        Dealer dealer = new Dealer();
        dealer.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        dealer.addPlayerCard(new Card(Suit.CLUBS, Rank.EIGHT));
        dealer.addPlayerCard(new Card(Suit.HEARTS, Rank.FOUR));
        Deck deck = new Deck();
        dealer.dealerRound(deck, person);
        assertFalse(dealer.overflow());
    }
}