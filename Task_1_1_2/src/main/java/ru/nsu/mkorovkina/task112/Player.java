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

    public int getPlayerScore() {
        return score;
    }

    /**
     * Prints the player's cards, showing reduced aces as 1.
     */
    public void printPlayerCards() {
        int size = playerCards.size();
        int count = 0;
        int acesReduce = acesToReduce;
        System.out.print("[");
        for (Card card : playerCards) {
            if (card.getCardRank() == Rank.ACE && acesReduce > 0) {
                System.out.print(card.getCardRank().getRank() + " "
                        + card.getCardSuit().getSuitName() + " (1)");
                acesReduce--;
            } else {
                System.out.print(card.toString());
            }
            if (count == size - 1) {
                System.out.print("]");
            } else {
                System.out.print(", ");
            }
            count++;
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
