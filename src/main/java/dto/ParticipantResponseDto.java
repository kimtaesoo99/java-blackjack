package dto;

import domain.Card;
import domain.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class ParticipantResponseDto {

    private final List<Card> cards;
    private final String name;
    private final int sum;

    public ParticipantResponseDto(List<Card> cards, String name, int sum) {
        this.name = name;
        this.sum = sum;
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards.stream()
            .map(Card::getFullName)
            .collect(Collectors.toList());
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public static ParticipantResponseDto toDto(Participant participant) {
        return new ParticipantResponseDto(participant.getCards(), participant.getName(), participant.getSumOfDeck());
    }
}
