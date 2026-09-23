package ru.nsu.mkorovkina.task112;

import java.util.Scanner;

/**
 * Game rules for the human player.
 */
public class HumanPlayer extends Player {
    HumanPlayer() {
        super();
    }

    /**
     * Plays one round for the human player.
     *
     * @param scanner the scanner (for input)
     * @param deck the deck
     * @param dealer the dealer (for printing cards)
     * @return false if the player busted; otherwise true
     */
    public boolean humanPlayerRound(Scanner scanner, Deck deck, Dealer dealer) {
        while (true) {
            System.out.println("Enter \"1\" to take a card, \"0\" to stand...");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                Card newCard = deck.takeCard();
                addPlayerCard(newCard);
                System.out.println("You revealed a card " + newCard);
                System.out.print("  Your cards: ");
                printPlayerCards();
                System.out.println("  Dealer's cards: [" + dealer.getCard(0)
                        + ", <hidden card>]");
                System.out.println();
                if (overflow()) {
                    return false;
                }
                if (blackJack()) {
                    return true;
                }
            } else {
                System.out.println();
                break;
            }
        }
        return true;
    }
}
