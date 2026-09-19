package ru.nsu.mkorovkina.task112;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    void dealerRound() {
        Deck deck = new Deck();
        HumanPlayer person = new HumanPlayer();
        person.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        Dealer dealer = new Dealer();
        dealer.addPlayerCard(new Card(Suit.SPADES, Rank.FIVE));
        dealer.addPlayerCard(new Card(Suit.CLUBS, Rank.EIGHT));
        dealer.addPlayerCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.addPlayerCard(deck.takeCard());
        dealer.addPlayerCard(deck.takeCard());
        dealer.dealerRound(deck, person);
        assertTrue(dealer.overflow());
    }
}