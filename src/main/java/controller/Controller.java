package controller;

import domain.*;
import dto.PlayersNameResponseDto;
import exception.DuplicateNameException;
import exception.WrongNameException;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Command.YES;
import static domain.Command.getCommand;
import static dto.ParticipantResponseDto.toDto;

public class Controller {

    private static final String DEALER = "딜러";
    private static final String SEPARATOR = ",";

    private Cards cards;
    private Participants participants;
    private Referee referee;

    public void start() {
        initCards();
        initParticipant();
        initCardSetting();
        addMoreCardOfPlayers();
        addMoreCardOfDealer();
        getParticipantsSumOfCard();
        result();
    }

    private void initCards() {
        cards = Cards.createAutoCards();
    }

    private void initParticipant() {
        List<Participant> players = new ArrayList<>();
        try {
            Dealer dealer = new Dealer(new Name(DEALER));
            addPlayers(players);
            participants = Participants.createDealerAndPlayers(dealer, players);
        } catch (DuplicateNameException | WrongNameException e) {
            OutputView.printErrorMessage(e.getMessage());
            initParticipant();
        }
    }

    private void addPlayers(final List<Participant> participantList) {
        String nameInfo = InputView.readParticipantName();
        String[] names = nameInfo.split(SEPARATOR);
        Arrays.stream(names)
            .forEach(name -> participantList.add(new Player(new Name(name))));
    }

    private void initCardSetting() {
        participants.initCardSetting(cards);
        printInitCardSetting();
    }

    private void printInitCardSetting() {
        OutputView.printInitCardSetting(PlayersNameResponseDto.toDto(participants));
        OutputView.printDealerFirstCard(toDto(participants.getDealer()));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerCards(toDto(player)));
    }

    private void addMoreCardOfPlayers() {
        OutputView.printNextLine();
        participants.getPlayers()
            .forEach(this::chooseAddCard);
    }

    private void chooseAddCard(final Participant player) {
        Command command = YES;

        while (wantToAddCard(player, command)) {
            OutputView.printChooseAddMoreCard(toDto(player));
            command = getCommand(InputView.readAddMoreCard());
            addCard(command, player);
            OutputView.printPlayerCards(toDto(player));
        }
    }

    private static boolean wantToAddCard(final Participant player, final Command command) {
        return player.canDraw() && command.getStatus();
    }

    private void addCard(final Command command, final Participant player) {
        if (command.getStatus()) {
            player.add(cards.pick());
        }
    }

    private void addMoreCardOfDealer() {
        Participant dealer = participants.getDealer();
        if (dealer.canDraw()) {
            OutputView.printAddDealerCard();
            dealer.add(cards.pick());
        }
    }

    private void getParticipantsSumOfCard() {
        OutputView.printNextLine();
        OutputView.printDealerSumOfCard(toDto(participants.getDealer()));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerSumOfCard(toDto(player)));
    }

    private void result() {
        referee = new Referee();
        Participant dealer = participants.getDealer();
        OutputView.printResultMessage();
        printDealerResult(dealer);
        printPlayersResult(dealer);
    }

    private void printDealerResult(final Participant dealer) {
        String results = participants.getPlayers()
            .stream()
            .map(player -> getResult(dealer.getSumOfDeck(), player.getSumOfDeck()))
            .collect(Collectors.joining());

        OutputView.printDealerResult(results);
    }

    private void printPlayersResult(final Participant dealer) {
        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerResult(getResult(player.getSumOfDeck(), dealer.getSumOfDeck()), toDto(player)));
    }

    private String getResult(final int firstSum, final int secondSum) {
        return referee.compareSumOfCard(firstSum, secondSum);
    }
}

