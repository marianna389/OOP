package ru.nsu.mkorovkina.task112;

import java.util.ArrayList;
import java.util.List;

/**
 * Player: cards and score.
 */
public class Player {
    private final List<Card> playerCards = new ArrayList<>();

    public void addPlayerCard(Card newCard) {
        playerCards.add(newCard);
    }

    /**
     * Calculates the score from the current cards.
     *
     * @return the score
     */
    public int getPlayerScore() {
        int score = 0;
        int acesCount = 0;
        for (Card card : playerCards) {
            if (card.getCardRank() == Rank.ACE) {
                acesCount++;
            }
            score += card.getCardValue();
        }
        while (acesCount > 0 && score > 21) {
            score -= 10;
            acesCount--;
        }
        return score;
    }

    private int acesCountToReduce() {
        int score = 0;
        int acesCount = 0;
        int acesToReduce = 0;
        for (Card card : playerCards) {
            if (card.getCardRank() == Rank.ACE) {
                acesCount++;
            }
            score += card.getCardValue();
        }
        while (acesCount > 0 && score > 21) {
            score -= 10;
            acesCount--;
            acesToReduce++;
        }
        return acesToReduce;
    }

    /**
     * Prints the player's cards, showing reduced aces as 1.
     */
    public void printPlayerCards() {
        int acesToReduce = acesCountToReduce();
        int size = playerCards.size();
        int count = 0;
        System.out.print("[");
        for (Card card : playerCards) {
            if (card.getCardRank() == Rank.ACE && acesToReduce > 0) {
                System.out.print(card.getCardRank().getRank() + " "
                        + card.getCardSuit().getSuitName()  + " (1)");
                acesToReduce--;
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
    }

    public boolean overflow() {
        return getPlayerScore() > 21;
    }

    public boolean blackJack() {
        return getPlayerScore() == 21;
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void clearPlayerCards() {
        playerCards.clear();
    }
}
