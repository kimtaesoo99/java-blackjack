package dto;

import domain.Dealer;

public class DealerAmountResponse {

    private final String name;
    private final int betAmount;

    public DealerAmountResponse(String name, int betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public static DealerAmountResponse toDto(final Dealer dealer) {
        return new DealerAmountResponse(dealer.getName(), dealer.getAmount());
    }
}
