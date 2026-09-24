package ru.nsu.mkorovkina.task112;

import java.util.Scanner;

/**
 * Game logic: turn-taking, scoring, rounds, etc.
 */
public class Game {
    private Deck deck;
    private final Score score;
    private final HumanPlayer person;
    private final Dealer dealer;
    private final Scanner scanner;

    private int round = 1;

    Game() {
        this(new Deck());
        this.deck.shuffle();
    }

    Game(Deck deck) {
        this.deck = deck;
        this.score = new Score();
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
            System.out.printf("  Dealer's cards: [%s, <hidden card>]%n",
                    dealer.cardToString(dealer.getCard(0)));
            System.out.println();
            if (person.blackJack() && dealer.blackJack()) {
                System.out.println("Both you and the dealer have blackjack!");
                score.drawRound();
                if (nextRound()) {
                    continue;
                } else {
                    break;
                }
            } else if (person.blackJack()) {
                System.out.println("You have blackjack!");
                score.personWinsRound();
                if (nextRound()) {
                    continue;
                } else {
                    break;
                }
            } else if (dealer.blackJack()) {
                System.out.println("Dealer has blackjack!");
                score.dealerWinsRound();
                if (nextRound()) {
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("Your turn");
            System.out.println("-------");
            if (!person.humanPlayerRound(scanner, deck, dealer)) {
                score.dealerWinsRound();
            } else {
                System.out.println("Dealer's turn");
                System.out.println("-------");
                System.out.println("Dealer reveals the hidden card "
                        + dealer.cardToString(dealer.getCard(1)));
                System.out.print("  Your cards: ");
                person.printPlayerCards();
                System.out.print("  Dealer's cards: ");
                dealer.printPlayerCards();
                System.out.println();
                if (!dealer.dealerRound(deck, person)) {
                    score.personWinsRound();
                } else {
                    if (person.getPlayerScore() > dealer.getPlayerScore()) {
                        score.personWinsRound();
                    } else if (person.getPlayerScore() < dealer.getPlayerScore()) {
                        score.dealerWinsRound();
                    } else {
                        score.drawRound();
                    }
                }
            }
            if (nextRound()) {
                continue;
            } else {
                break;
            }
        }
        score.gameEnd();
    }

    private boolean nextRound() {
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
