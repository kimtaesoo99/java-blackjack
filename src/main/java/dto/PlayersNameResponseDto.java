package dto;

import domain.Participants;

import java.util.Collections;
import java.util.List;

public class PlayersNameResponseDto {

    private final List<String> playersName;

    public PlayersNameResponseDto(List<String> playersName) {
        this.playersName = playersName;
    }

    public List<String> getPlayersName() {
        return Collections.unmodifiableList(playersName);
    }

    public static PlayersNameResponseDto toDto(Participants participants) {
        return new PlayersNameResponseDto(participants.getPlayersName());
    }
}
