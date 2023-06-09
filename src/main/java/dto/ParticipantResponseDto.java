package dto;

import domain.Participant;

import java.util.Collections;
import java.util.List;

public class ParticipantResponseDto {

    private final List<String> cardsName;
    private final String name;
    private final int sumOfCards;

    public ParticipantResponseDto(final List<String> cardsName, final String name, final int sumOfCards) {
        this.name = name;
        this.sumOfCards = sumOfCards;
        this.cardsName = cardsName;
    }

    public List<String> getCardsName() {
        return Collections.unmodifiableList(cardsName);
    }

    public String getName() {
        return name;
    }

    public int getSumOfCards() {
        return sumOfCards;
    }

    public static ParticipantResponseDto toDto(final Participant participant) {
        return new ParticipantResponseDto(participant.getCardsName(), participant.getName(), participant.getSumOfDeck());
    }
}
