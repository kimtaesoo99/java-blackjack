package dto;

import domain.Participants;

import java.util.Collections;
import java.util.List;

public class PlayersNameResponse {

    private final List<String> playersName;

    private PlayersNameResponse(final List<String> playersName) {
        this.playersName = playersName;
    }

    public static PlayersNameResponse toDto(final Participants participants) {
        return new PlayersNameResponse(participants.getPlayersName());
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(playersName);
    }
}
