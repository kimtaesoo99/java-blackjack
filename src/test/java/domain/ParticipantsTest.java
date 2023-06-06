package domain;

import exception.DuplicateNameException;
import exception.WrongNameException;
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

    private Participants participants;

    @BeforeEach
    void beforeEach() {
        List<Participant> list = new ArrayList<>();
        list.add(new Player(new Name(ALICE)));
        list.add(new Player(new Name(JAKE)));
        Dealer dealer = new Dealer(new Name(DEALER));
        participants = Participants.createDealerAndPlayers(dealer, list);
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
        List<Participant> players = participants.getPlayers();

        //then
        assertThat(players.size()).isEqualTo(PLAYER_SIZE);
    }

    @Test
    public void validateDuplicate() {
        //given
        Dealer dealer = new Dealer(new Name(DEALER));
        List<Participant> players = new ArrayList<>();
        players.add(new Player(new Name(DUPLICATE_NAME)));
        players.add(new Player(new Name(DUPLICATE_NAME)));

        //when, then
        Assertions.assertThatThrownBy(() -> participants = Participants.createDealerAndPlayers(dealer, players))
            .isInstanceOf(DuplicateNameException.class);
    }

    @Test
    public void validatePlayerName() {
        //given
        Dealer dealer = new Dealer(new Name(DEALER));
        List<Participant> players = new ArrayList<>();
        players.add(new Player(new Name(DEALER)));

        //when, then
        Assertions.assertThatThrownBy(() -> participants = Participants.createDealerAndPlayers(dealer, players))
            .isInstanceOf(WrongNameException.class);
    }
}