package dto;

import domain.Player;

public class PlayerAmountResponse {

    private final String name;
    private final int betAmount;

    public PlayerAmountResponse(String name, int betAmount) {
        this.name = name;
        this.betAmount = betAmount;
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public static PlayerAmountResponse toDto(final Player player) {
        return new PlayerAmountResponse(player.getName(), player.getAmount());
    }
}
