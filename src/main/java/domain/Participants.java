package domain;

import exception.DuplicateNameException;
import exception.WrongNameException;

import java.util.*;

public class Participants {

    private static final int DEALER_INDEX = 0;
    private static final int PLAYER_INDEX = 1;
    private static final String DEALER = "딜러";
    private static final String DUPLICATE_ERROR_MESSAGE = "플레이어 이름은 중복이 될 수 없습니다.";
    private static final String NAME_ERROR_MESSAGE = "플레이어의 이름이 딜러면 안됩니다.";

    private final List<Participant> participants;

    public static Participants createDealerAndPlayers(final Dealer dealer, final List<Participant> players) {
        return new Participants(dealer, players);
    }

    public Participants(final Dealer dealer, final List<Participant> players) {
        validateDuplicateName(players);
        validatePlayersName(players);
        this.participants = players;
        participants.add(DEALER_INDEX, dealer);
    }

    private void validateDuplicateName(final List<Participant> players) {
        Set<String> names = new HashSet<>();
        for (Participant participant : players) {
            names.add(participant.getName());
        }

        if (names.size() != players.size()) {
            throw new DuplicateNameException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private void validatePlayersName(final List<Participant> players) {
        boolean exist = players.stream()
            .anyMatch(player -> player.getName().equals(DEALER));

        if (exist) {
            throw new WrongNameException(NAME_ERROR_MESSAGE);
        }
    }

    public void initCardSetting(final Cards cards) {
        participants.forEach(participant -> {
            participant.add(cards.pick());
            participant.add(cards.pick());
        });
    }

    public Participant getDealer() {
        return participants.get(DEALER_INDEX);
    }

    public List<String> getPlayersName() {
        List<String> names = new ArrayList<>();
        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            names.add(participants.get(player).getName());
        }
        return Collections.unmodifiableList(names);
    }

    public List<Participant> getPlayers() {
        List<Participant> players = new ArrayList<>();
        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            players.add(participants.get(player));
        }
        return Collections.unmodifiableList(players);
    }
}
