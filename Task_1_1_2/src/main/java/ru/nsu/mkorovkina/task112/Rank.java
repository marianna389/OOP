package ru.nsu.mkorovkina.task112;

/**
 * Card rank.
 */
public enum Rank {
    TWO(2, "Two"),
    THREE(3, "Three"),
    FOUR(4, "Four"),
    FIVE(5, "Five"),
    SIX(6, "Six"),
    SEVEN(7, "Seven"),
    EIGHT(8, "Eight"),
    NINE(9, "Nine"),
    TEN(10, "Ten"),
    JACK(10, "Jack"),
    QUEEN(10, "Queen"),
    KING(10, "King"),
    ACE(11, "Ace");

    private final int value;
    private final String rank;

    Rank(int value, String rank) {
        this.value = value;
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public int getRankValue() {
        return value;
    }
}
