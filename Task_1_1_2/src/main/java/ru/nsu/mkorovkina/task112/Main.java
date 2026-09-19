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
}