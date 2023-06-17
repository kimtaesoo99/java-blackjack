package domain;

import exception.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {

    private static final int INIT_CARD_SIZE = 2;
    private static final int INIT_AMOUNT = 100;
    private static final String JOHN = "John";
    private static final String ALICE = "Alice";

    private Players players;
    private Player player1;
    private Player player2;

    @BeforeEach
    void beforeEach() {
        List<Player> playerList = new ArrayList<>();
        player1 = Player.create(new Account(new Name(ALICE), new Amount(INIT_AMOUNT)));
        player2 = Player.create(new Account(new Name(JOHN), new Amount(INIT_AMOUNT)));
        playerList.add(player1);
        playerList.add(player2);
        players = new Players(playerList);
    }

    @Test
    void initCardSetting() {
        //when
        players.initCardSetting(Cards.createAutoCards());

        //then
        assertThat(player1.getCardsName().size()).isEqualTo(INIT_CARD_SIZE);
        assertThat(player2.getCardsName().size()).isEqualTo(INIT_CARD_SIZE);
    }

    @Test
    void getPlayers() {
        //when
        List<Player> playerList = players.getPlayers();

        //then
        assertThat(playerList).containsExactly(player1, player2);
    }

    @Test
    void getPlayersName() {
        //when
        List<String> playersName = players.getPlayersName();

        //then
        assertThat(playersName).containsExactly(ALICE, JOHN);
    }

    @Test
    public void duplicateNameException() {
        //give
        List<Player> playerList = new ArrayList<>();
        Player player1 = Player.create(new Account(new Name(ALICE), new Amount(INIT_AMOUNT)));
        Player player2 = Player.create(new Account(new Name(ALICE), new Amount(INIT_AMOUNT)));
        playerList.add(player1);
        playerList.add(player2);

        //when, then
        assertThatThrownBy(() -> new Players(playerList))
            .isInstanceOf(DuplicateNameException.class);
    }
}
