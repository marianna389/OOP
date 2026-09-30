package ru.nsu.mkorovkina.task112;

/**
 * Program entry point.
 */
public class Main {
    /**
     * Starts a Blackjack game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.game();
    }

    /**
     * Starts a Blackjack game for testing with a mock deck.
     *
     * @param deck the mock deck
     */
    public static void mainForTests(Deck deck) {
        new Game(deck).game();
    }
}