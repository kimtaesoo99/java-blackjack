package domain;

public class Referee {

    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int BLACK_JACK = 21;

    public String compareSumOfCard(final Participant dealer, final Participant player) {
        return compare(dealer.getSumOfDeck(), player.getSumOfDeck());
    }

    private String compare(final int dealerSumOfDeck, final int playerSumOfDeck) {
        if (winCase(dealerSumOfDeck, playerSumOfDeck)) {
            return WIN;
        }
        if (drawCase(dealerSumOfDeck, playerSumOfDeck)) {
            return DRAW;
        }
        return LOSE;
    }

    private boolean winCase(final int dealerSumOfDeck, final int playerSumOfDeck) {
        if (dealerSumOfDeck < playerSumOfDeck && playerSumOfDeck <= BLACK_JACK) {
            return true;
        }
        if (dealerSumOfDeck > BLACK_JACK && playerSumOfDeck <= BLACK_JACK) {
            return true;
        }
        return false;
    }

    private boolean drawCase(final int dealerSumOfDeck, final int playerSumOfDeck) {
        if (dealerSumOfDeck == playerSumOfDeck) {
            return true;
        }
        if (dealerSumOfDeck > BLACK_JACK && playerSumOfDeck > BLACK_JACK) {
            return true;
        }
        return false;
    }
}
