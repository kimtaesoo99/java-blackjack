package domain;

import java.util.Collections;
import java.util.List;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    public Participants(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void initCardSetting(final Cards cards) {
        dealer.initCardSetting(cards);
        players.initCardSetting(cards);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(players.getPlayersName());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players.getPlayers());
    }
}
