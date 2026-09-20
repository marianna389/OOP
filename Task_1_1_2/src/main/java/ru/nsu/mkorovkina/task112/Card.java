package ru.nsu.mkorovkina.task112;

/**
 * Card: suit and rank; printing the card.
 */
public class Card {
    private final Suit cardSuit;
    private final Rank cardRank;

    Card(Suit cardSuit, Rank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    public Rank getCardRank() {
        return cardRank;
    }

    public int getCardValue() {
        return cardRank.getRankValue();
    }

    /**
     * String representation of the card in the format: rank suit (value).
     *
     * @return a string describing the card
     */
    @Override
    public String toString() {
        return getCardRank().getRank() + " " + getCardSuit().getSuitName()
                + " (" + getCardValue() + ")";
    }
}
