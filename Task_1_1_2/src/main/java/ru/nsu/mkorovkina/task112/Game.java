package ru.nsu.mkorovkina.task112;

import java.util.Scanner;

/**
 * Game logic: turn-taking, scoring, rounds, etc.
 */
public class Game {
    private Deck deck;
    private final HumanPlayer person;
    private final Dealer dealer;
    private final Scanner scanner;

    private int round = 1;
    private int personWins = 0;
    private int dealerWins = 0;

    Game() {
        this.deck = new Deck();
        this.deck.shuffle();
        this.person = new HumanPlayer();
        this.dealer = new Dealer();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts a Blackjack game.
     */
    public void game() {
        System.out.println("Welcome to Blackjack!");
        while (true) {
            System.out.println("Round " + round);
            if (deck.getSize() < 13) {
                deck = new Deck();
                deck.shuffle();
            }
            person.clearPlayerCards();
            dealer.clearPlayerCards();
            for (int i = 0; i < 2; i++) {
                person.addPlayerCard(deck.takeCard());
            }
            for (int i = 0; i < 2; i++) {
                dealer.addPlayerCard(deck.takeCard());
            }
            System.out.println("Dealer dealt cards");
            System.out.print("  Your cards: ");
            person.printPlayerCards();
            System.out.println(" => " + person.getPlayerScore());
            System.out.println("  Dealer's cards: [" + dealer.getPlayerCards().get(0)
                    + ", <hidden card>]");
            System.out.println();
            if (person.blackJack() && dealer.blackJack()) {
                System.out.println("Both you and the dealer have blackjack!");
                drawRound();
                if (nextRound(scanner)) {
                    continue;
                } else {
                    break;
                }
            } else if (person.blackJack()) {
                System.out.println("You have blackjack!");
                personWinsRound();
                if (nextRound(scanner)) {
                    continue;
                } else {
                    break;
                }
            } else if (dealer.blackJack()) {
                System.out.println("Dealer has blackjack!");
                dealerWinsRound();
                if (nextRound(scanner)) {
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("Your turn");
            System.out.println("-------");
            if (!person.humanPlayerRound(scanner, deck, dealer)) {
                dealerWinsRound();
            } else {
                System.out.println("Dealer's turn");
                System.out.println("-------");
                System.out.println("Dealer reveals the hidden card "
                        + dealer.getPlayerCards().get(1));
                System.out.print("  Your cards: ");
                person.printPlayerCards();
                System.out.println(" => " + person.getPlayerScore());
                System.out.print("  Dealer's cards: ");
                dealer.printPlayerCards();
                System.out.println(" => " + dealer.getPlayerScore());
                System.out.println();
                if (!dealer.dealerRound(deck, person)) {
                    personWinsRound();
                } else {
                    if (person.getPlayerScore() > dealer.getPlayerScore()) {
                        personWinsRound();
                    } else if (person.getPlayerScore() < dealer.getPlayerScore()) {
                        dealerWinsRound();
                    } else {
                        drawRound();
                    }
                }
            }
            if (nextRound(scanner)) {
                continue;
            } else {
                break;
            }
        }
        System.out.println("Game over.");
        if (personWins > dealerWins) {
            System.out.print("Victory :)");
        } else if (personWins < dealerWins) {
            System.out.print("Defeat :(");
        } else {
            System.out.print("Draw");
        }
    }

    private void printScore() {
        if (personWins > dealerWins) {
            System.out.printf("in your favor.%n");
        } else if (personWins < dealerWins) {
            System.out.printf("in dealer's favor.%n");
        } else {
            System.out.printf("draw.%n");
        }
    }

    private void personWinsRound() {
        personWins++;
        System.out.printf("You won the round! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    private void dealerWinsRound() {
        dealerWins++;
        System.out.printf("You lost the round! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    private void drawRound() {
        System.out.printf("You drew with the dealer! Score %d:%d ", personWins, dealerWins);
        printScore();
        System.out.println();
    }

    private boolean nextRound(Scanner scanner) {
        System.out.println("Enter \"1\" to play another round, \"0\" to end the game...");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println();
            round++;
            return true;
        } else {
            return false;
        }
    }
}
