package ru.nsu.mkorovkina.task112;

/**
 * Prints the game score after each round and at the end of the game.
 */
public class Score {
    private int personWins = 0;
    private int dealerWins = 0;

    private void printScore() {
        if (personWins > dealerWins) {
            System.out.printf("in your favor.%n");
        } else if (personWins < dealerWins) {
            System.out.printf("in dealer's favor.%n");
        } else {
            System.out.printf("draw.%n");
        }
    }

    /**
     * Score if you won the round.
     */
    public void personWinsRound() {
        personWins++;
        System.out.printf("You won the round! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    /**
     * Score if the dealer won the round.
     */
    public void dealerWinsRound() {
        dealerWins++;
        System.out.printf("You lost the round! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    /**
     * Score if you tied a round with the dealer.
     */
    public void drawRound() {
        System.out.printf("You drew with the dealer! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    /**
     * Prints the final game result.
     */
    public void gameEnd() {
        System.out.println("Game over.");
        if (personWins > dealerWins) {
            System.out.print("Victory :)");
        } else if (personWins < dealerWins) {
            System.out.print("Defeat :(");
        } else {
            System.out.print("Draw");
        }
    }
}
