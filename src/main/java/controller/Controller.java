package controller;

import domain.*;
import dto.ParticipantResponseDto;
import dto.PlayersNameResponseDto;
import exception.BlankNameException;
import exception.DuplicateNameException;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static domain.Command.YES;
import static domain.Command.getCommand;

public class Controller {

    private static final String DEALER = "딜러";
    private static final String SEPARATOR = ",";

    private Cards cards;
    private Participants participants;
    private Referee referee;

    public void start() {
        initCards();
        initParticipants();
        initCardSetting();
        addMoreCardOfPlayers();
        addMoreCardOfDealer();
        getParticipantsSumOfCard();
        result();
    }

    private void initCards() {
        cards = Cards.createAutoCards();
    }

    private void initParticipants() {
        List<Player> players = new ArrayList<>();
        try {
            Dealer dealer = new Dealer(new Name(DEALER));
            addPlayers(players);
            participants = new Participants(dealer, new Players(players));
        } catch (DuplicateNameException | BlankNameException e) {
            OutputView.printErrorMessage(e.getMessage());
            initParticipants();
        }
    }

    private void addPlayers(final List<Player> players) {
        String nameInfo = InputView.readParticipantName();
        String[] names = nameInfo.split(SEPARATOR);
        Arrays.stream(names)
            .forEach(name -> players.add(new Player(new Name(name))));
    }

    private void initCardSetting() {
        participants.initCardSetting(cards);
        printInitCardSetting();
    }

    private void printInitCardSetting() {
        OutputView.printInitCardSetting(PlayersNameResponseDto.toDto(participants));
        OutputView.printDealerFirstCard(ParticipantResponseDto.toDto((participants.getDealer())));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerCards(ParticipantResponseDto.toDto(player)));
    }

    private void addMoreCardOfPlayers() {
        OutputView.printNextLine();
        participants.getPlayers()
            .forEach(this::chooseAddCard);
    }

    private void chooseAddCard(final Participant player) {
        Command command = YES;

        while (canAddCard(player, command)) {
            OutputView.printChooseAddMoreCard(ParticipantResponseDto.toDto(player));
            command = getCommand(InputView.readAddMoreCard());
            addCard(command, player);
            OutputView.printPlayerCards(ParticipantResponseDto.toDto(player));
        }
    }

    private boolean canAddCard(final Participant player, final Command command) {
        return player.canDraw() && command.isDraw();
    }

    private void addCard(final Command command, final Participant player) {
        if (command.isDraw()) {
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
        OutputView.printDealerSumOfCard(ParticipantResponseDto.toDto(participants.getDealer()));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerSumOfCard(ParticipantResponseDto.toDto(player)));
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
            .forEach(player -> OutputView.printPlayerResult(getResult(player.getSumOfDeck(), dealer.getSumOfDeck()),
                ParticipantResponseDto.toDto(player)));
    }

    private String getResult(final int firstSum, final int secondSum) {
        return referee.compareSumOfCard(firstSum, secondSum);
    }
}

