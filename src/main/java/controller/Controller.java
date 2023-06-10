package controller;

import domain.*;
import dto.DealerAmountResponse;
import dto.ParticipantResponse;
import dto.PlayerAmountResponse;
import dto.PlayersNameResponse;
import exception.AmountException;
import exception.BlankNameException;
import exception.DuplicateNameException;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static domain.Command.YES;
import static domain.Command.getCommand;

public class Controller {

    private static final String DEALER = "딜러";
    private static final String SEPARATOR = ",";
    private static final int INIT_AMOUNT = 0;

    private Cards cards;
    private Participants participants;
    private Referee referee;

    public void start() {
        initSetting();
        initParticipants();
        initCardSetting();
        addMoreCardOfPlayers();
        addMoreCardOfDealer();
        getParticipantsSumOfCard();
        result();
    }

    private void initSetting() {
        referee = new Referee();
        cards = Cards.createAutoCards();
    }

    private void initParticipants() {
        List<Player> players = new ArrayList<>();
        try {
            Dealer dealer = new Dealer(new Name(DEALER), new Amount(INIT_AMOUNT));
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
            .forEach(name -> players.add(new Player(new Name(name), setPlayerBetAmount(name))));
    }

    private Amount setPlayerBetAmount(final String playerName) {
        try {
            OutputView.printBetAmount(playerName);
            return new Amount(InputView.readBetAmount());
        } catch (AmountException e) {
            OutputView.printErrorMessage(e.getMessage());
            return setPlayerBetAmount(playerName);
        }
    }

    private void initCardSetting() {
        participants.initCardSetting(cards);

        participants.getPlayers()
            .stream()
            .filter(player -> referee.checkBlackJack(player.getSumOfDeck()))
            .forEach(Player::multiplyAmount);

        printInitCardSetting();
    }

    private void printInitCardSetting() {
        OutputView.printInitCardSetting(PlayersNameResponse.toDto(participants));
        OutputView.printDealerFirstCard(ParticipantResponse.toDto((participants.getDealer())));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerCards(ParticipantResponse.toDto(player)));
    }

    private void addMoreCardOfPlayers() {
        OutputView.printNextLine();
        participants.getPlayers()
            .forEach(this::chooseAddCard);
    }

    private void chooseAddCard(final Participant player) {
        Command command = YES;

        while (canAddCard(player, command)) {
            OutputView.printChooseAddMoreCard(ParticipantResponse.toDto(player));
            command = getCommand(InputView.readAddMoreCard());
            addCard(command, player);
            OutputView.printPlayerCards(ParticipantResponse.toDto(player));
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
        OutputView.printDealerSumOfCard(ParticipantResponse.toDto(participants.getDealer()));

        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerSumOfCard(ParticipantResponse.toDto(player)));
    }

    private void result() {
        Dealer dealer = participants.getDealer();

        OutputView.printResultMessage();
        setRevenue(dealer);
        printDealerResult(dealer);
        printPlayersResult();
    }

    private void setRevenue(final Dealer dealer) {
        participants.getPlayers()
            .forEach(player -> referee.distributeRevenue(getResult(player, dealer), player, dealer));
    }

    private String getResult(final Player player, final Dealer dealer) {
        return referee.compareSumOfCard(player.getSumOfDeck(), dealer.getSumOfDeck());
    }

    private void printDealerResult(final Dealer dealer) {
        OutputView.printDealerResult(DealerAmountResponse.toDto(dealer));
    }

    private void printPlayersResult() {
        participants.getPlayers()
            .forEach(player -> OutputView.printPlayerResult(PlayerAmountResponse.toDto(player)));
    }
}

