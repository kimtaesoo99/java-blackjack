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
    private static final int INIT_CARD_SIZE = 2;


    private Participants participants;
    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Amount amount = new Amount(INIT_AMOUNT);
        List<Player> list = new ArrayList<>();
        list.add(Player.create(new Account(new Name(ALICE), amount)));
        list.add(Player.create(new Account(new Name(JAKE), amount)));
        dealer = Dealer.create(new Account(new Name(DEALER), amount));
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
        Dealer dealer = Dealer.create(new Account(new Name(DEALER), amount));
        List<Player> players = new ArrayList<>();
        players.add(Player.create(new Account(new Name(DUPLICATE_NAME), amount)));
        players.add(Player.create(new Account(new Name(DUPLICATE_NAME), amount)));

        //when, then
        Assertions.assertThatThrownBy(() -> participants = new Participants(dealer, new Players(players)))
            .isInstanceOf(DuplicateNameException.class);
    }

    @Test
    public void initCardSetting() {
        //when
        participants.initCardSetting(Cards.createAutoCards());

        //then
        assertThat(dealer.getCardsName().size()).isEqualTo(INIT_CARD_SIZE);
    }
}
