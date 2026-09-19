package ru.nsu.mkorovkina.task112;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A deck of 52 cards: creation, shuffling, drawing.
 */
public class Deck {
    private final List<Card> cards = new ArrayList<>();

    Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffle() { Collections.shuffle(cards); }

    public Card takeCard() { return cards.remove(cards.size() - 1); }

    public int getSize() { return cards.size(); }
}
