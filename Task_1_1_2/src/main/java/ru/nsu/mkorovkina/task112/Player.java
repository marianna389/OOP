package ru.nsu.mkorovkina.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Player: cards and score.
 */
public class Player {
    private final List<Card> playerCards = new ArrayList<>();
    private int score = 0;
    private int acesCount = 0;
    private int acesToReduce = 0;

    /**
     * Adds a card to the player and recalculates the score and the number of aces.
     *
     * @param newCard the card that needs to be added
     */
    public void addPlayerCard(Card newCard) {
        playerCards.add(newCard);
        if (newCard.getCardRank() == Rank.ACE) {
            acesCount++;
        }
        score += newCard.getCardValue();
        while (acesCount > 0 && score > 21) {
            score -= 10;
            acesCount--;
            acesToReduce++;
        }
    }

    /**
     * String representation of the card in the format: rank suit (value).
     * Shows the value of reduced aces as (1).
     *
     * @param card the card to string
     * @return a string describing the card
     */
    public String cardToString(Card card) {
        int size = playerCards.size();
        int acesReduce = acesToReduce;
        int index = playerCards.indexOf(card);
        for(int i = size - 1; i != index; i--) {
            if (getCard(i).getCardRank() == Rank.ACE && acesReduce > 0) {
                acesReduce--;
            }
        }
        if (card.getCardRank() == Rank.ACE && acesReduce > 0) {
            return String.format("%s %s (1)", card.getCardRank().getRank(),
                    card.getCardSuit().getSuitName());
        }
        return String.format("%s %s (%d)", card.getCardRank().getRank(),
                card.getCardSuit().getSuitName(), card.getCardValue());
    }

    public int getPlayerScore() {
        return score;
    }

    /**
     * Prints the player's cards, showing reduced aces as 1.
     */
    public void printPlayerCards() {
        int size = playerCards.size();
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(cardToString(getCard(i)));
            if (i == size - 1) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
        }
        System.out.println(" => " + score);
    }

    public boolean overflow() {
        return score > 21;
    }

    public boolean blackJack() {
        return score == 21;
    }

    public Card getCard(int index) {
        return playerCards.get(index);
    }

    /**
     * Clears the player's cards, score, and ace count before a new round.
     */
    public void clearPlayerCards() {
        playerCards.clear();
        score = 0;
        acesCount = 0;
        acesToReduce = 0;
    }
}
