package domain;

import exception.DuplicateNameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Players {

    private static final String DUPLICATE_ERROR_MESSAGE = "플레이어 이름은 중복이 될 수 없습니다.";

    private final List<Player> players;

    public Players(final List<Player> players) {
        validateDuplicateName(players);
        this.players = players;
    }

    private void validateDuplicateName(final List<Player> players) {
        Set<String> names = new HashSet<>();
        for (final Player player : players) {
            names.add(player.getName());
        }

        if (names.size() != players.size()) {
            throw new DuplicateNameException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    public void initCardSetting(final Cards cards) {
        players.forEach(player -> player.initCardSetting(cards));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public List<String> getPlayersName() {
        List<String> names = new ArrayList<>();
        players.forEach(player -> names.add(player.getName()));
        return Collections.unmodifiableList(names);
    }
}
