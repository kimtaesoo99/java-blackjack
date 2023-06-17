package dto;

import domain.Dealer;

public class DealerAmountResponse {

    private final String name;
    private final int betAmount;

    private DealerAmountResponse(final String name, final int betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public static DealerAmountResponse toDto(final Dealer dealer) {
        return new DealerAmountResponse(dealer.getName(), dealer.getAmount());
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
