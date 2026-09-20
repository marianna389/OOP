package ru.nsu.mkorovkina.task112;

/**
 * Card suit.
 */
public enum Suit {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public String getSuitName() {
        return name;
    }
}
