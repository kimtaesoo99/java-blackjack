package domain;

import exception.DuplicateNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantsTest {

    private static final String DEALER = "딜러";
    private static final String ALICE = "Alice";
    private static final String JAKE = "Jake";
    private static final String DUPLICATE_NAME = "A";
    private static final int PLAYER_SIZE = 2;
    private static final int INIT_AMOUNT = 0;


    private Participants participants;

    @BeforeEach
    void beforeEach() {
        Amount amount = new Amount(INIT_AMOUNT);
        List<Player> list = new ArrayList<>();
        list.add(new Player(new Name(ALICE), amount));
        list.add(new Player(new Name(JAKE), amount));
        Dealer dealer = new Dealer(new Name(DEALER), amount);
        participants = new Participants(dealer, new Players(list));
    }

    @Test
    void getDealer() {
        //when
        Participant dealer = participants.getDealer();

        //then
        assertThat(dealer.getName()).isEqualTo(DEALER);
    }

    @Test
    void getPlayersName() {
        //when
        List<String> playersName = participants.getPlayersName();

        //then
        assertThat(playersName).containsExactly(ALICE, JAKE);
    }

    @Test
    void getPlayers() {
        //when
        List<Player> players = participants.getPlayers();

        //then
        assertThat(players.size()).isEqualTo(PLAYER_SIZE);
    }

    @Test
    public void validateDuplicate() {
        //given
        Amount amount = new Amount(INIT_AMOUNT);
        Dealer dealer = new Dealer(new Name(DEALER), amount);
        List<Player> players = new ArrayList<>();
        players.add(new Player(new Name(DUPLICATE_NAME), amount));
        players.add(new Player(new Name(DUPLICATE_NAME), amount));

        //when, then
        Assertions.assertThatThrownBy(() -> participants = new Participants(dealer, new Players(players)))
            .isInstanceOf(DuplicateNameException.class);
    }
}
