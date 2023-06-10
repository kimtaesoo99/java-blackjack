package dto;

import domain.Participants;

import java.util.Collections;
import java.util.List;

public class PlayersNameResponse {

    private final List<String> playersName;

    public PlayersNameResponse(final List<String> playersName) {
        this.playersName = playersName;
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(playersName);
    }

    public static PlayersNameResponse toDto(final Participants participants) {
        return new PlayersNameResponse(participants.getPlayersName());
    }
}
