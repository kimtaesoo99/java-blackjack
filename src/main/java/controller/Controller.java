package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    private static final String DEALER = "딜러";
    private static final String SEPARATOR = ",";
    private static final int PLAYER_INDEX = 1;
    private static final int DEALER_INDEX = 0;
    private static final String YES = "y";

    private final Cards cards = new Cards();

    private List<Participant> participants;

    public void start() {
        initParticipant();
        initCardSetting();
        addMoreCardOfPlayers();
        addMoreCardOfDealer();
        getParticipantsSumOfCard();
        result();
    }

    private void initParticipant() {
        participants = new ArrayList<>();
        participants.add(new Dealer(new Name(DEALER)));
        addPlayers();
    }

    private void addPlayers() {
        try {
            String nameInfo = InputView.readParticipantName();
            String[] names = nameInfo.split(SEPARATOR);
            Arrays.stream(names).forEach(name -> participants.add(new Player(new Name(name))));
        } catch (IllegalStateException e) {
            addPlayers();
        }
    }

    private void initCardSetting() {
        participants.forEach(participant -> {
            participant.add(cards.pick());
            participant.add(cards.pick());
        });

        printInitCardSetting();
    }

    private void printInitCardSetting() {
        OutputView.printInitCardSetting(participants);
        OutputView.printDealerFirstCard(participants.get(DEALER_INDEX));

        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            OutputView.printPlayerCards(participants.get(player));
        }
    }

    private void addMoreCardOfPlayers() {
        OutputView.printNextLine();
        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            chooseAddCard(participants.get(player));
        }
    }

    private void chooseAddCard(final Participant player) {
        String command = YES;

        while ((player.canDraw() && command.equals(YES))) {
            OutputView.printChooseAddMoreCard(player);
            command = InputView.readAddMoreCard();
            addCard(command, player);
            OutputView.printPlayerCards(player);
        }
    }

    private void addCard(final String command, final Participant player) {
        if (command.equals(YES)) {
            player.add(cards.pick());
        }
    }

    private void addMoreCardOfDealer() {
        Participant dealer = participants.get(DEALER_INDEX);
        if (dealer.canDraw()) {
            OutputView.printAddDealerCard();
            dealer.add(cards.pick());
        }
    }

    private void getParticipantsSumOfCard() {
        OutputView.printNextLine();
        OutputView.printDealerSumOfCard(participants.get(DEALER_INDEX));
        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            OutputView.printPlayerSumOfCard(participants.get(player));
        }
    }

    private void result() {
        Referee referee = new Referee();
        OutputView.printResultMessage();
        OutputView.printResultDealer(referee, participants);
        OutputView.printResultPlayers(referee, participants);
    }
}
