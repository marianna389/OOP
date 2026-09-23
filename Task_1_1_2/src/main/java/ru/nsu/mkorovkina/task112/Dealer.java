package ru.nsu.mkorovkina.task112;

/**
 * Game rules for the dealer.
 */
public class Dealer extends Player {
    Dealer() {
        super();
    }

    /**
     * Plays one round for the dealer.
     *
     * @param deck the deck
     * @param person the player (for printing cards)
     * @return false if the dealer busted; otherwise true
     */
    public boolean dealerRound(Deck deck, HumanPlayer person) {
        while (getPlayerScore() < 17) {
            Card newCard = deck.takeCard();
            addPlayerCard(newCard);
            System.out.println("Dealer reveals a card " + newCard);
            System.out.print("  Your cards: ");
            person.printPlayerCards();
            System.out.print("  Dealer's cards: ");
            printPlayerCards();
            System.out.println();
        }
        return !overflow();
    }
}