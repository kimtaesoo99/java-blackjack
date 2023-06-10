package domain;

public class Referee {

    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int BLACK_JACK = 21;
    private static final int MINUS = -1;
    private static final int ZERO_AMOUNT = 0;

    public String compareSumOfCard(final int firstSum, final int secondSum) {
        return compare(firstSum, secondSum);
    }

    public boolean checkBlackJack(final int playerCardOfSum) {
        return playerCardOfSum == BLACK_JACK;
    }

    private String compare(final int firstSum, final int secondSum) {
        if (isWinCase(firstSum, secondSum)) {
            return WIN;
        }
        if (isDrawCase(firstSum, secondSum)) {
            return DRAW;
        }
        return LOSE;
    }

    private boolean isWinCase(final int firstSum, final int secondSum) {
        if (firstSum > secondSum && firstSum <= BLACK_JACK) {
            return true;
        }
        return secondSum > BLACK_JACK && firstSum <= BLACK_JACK;
    }

    private boolean isDrawCase(final int firstSum, final int secondSum) {
        if (firstSum == secondSum) {
            return true;
        }
        return firstSum > BLACK_JACK && secondSum > BLACK_JACK;
    }

    public void distributeRevenue(final String result, final Player player, final Dealer dealer) {
        int amount = player.getAmount();

        distributeWinCase(result, dealer, amount);
        distributeDrawCase(result, player);
        distributeLoseCase(result, player, dealer, amount);
    }

    private void distributeWinCase(final String result, final Dealer dealer, final int amount) {
        if (result.equals(WIN)) {
            dealer.addAmount(amount * MINUS);
        }
    }

    private void distributeDrawCase(final String result, final Player player) {
        if (result.equals(DRAW)) {
            player.setAmount(ZERO_AMOUNT);
        }
    }

    private void distributeLoseCase(final String result, final Player player, final Dealer dealer, final int amount) {
        if (result.equals(LOSE)) {
            player.setAmount(amount * MINUS);
            dealer.addAmount(amount);
        }
    }
}
