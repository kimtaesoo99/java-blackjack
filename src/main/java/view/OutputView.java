package view;

import domain.Participant;
import domain.Referee;

import java.util.*;

public class OutputView {

    private static final String FINAL_RESULT = "## 최종 승패";
    private static final String DRAW_TWO_CARD = "에게 2장을 나누었습니다.";
    private static final String DRAW_ONE_CARD = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String DEALER_ONE_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String CARD = "카드: ";
    private static final String SEPARATOR = ", ";
    private static final String RESULT = " - 결과: ";
    private static final String COLON = ": ";
    private static final String NEXT_LINE = "\n";
    private static final String AND = "와 ";
    private static final String EMPTY = "";
    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final String BLANK = " ";
    private static final int PLAYER_INDEX = 1;
    private static final int DEALER_INDEX = 0;
    private static final int FIRST_CARD = 0;
    private static final int INIT_COUNT = 0;
    private static final int INCREASE_COUNT = 1;

    public static void printInitCardSetting(final List<Participant> participants) {
        StringJoiner players = new StringJoiner(SEPARATOR);

        for (int player = PLAYER_INDEX; player < participants.size(); player++) {
            players.add(participants.get(player).getName());
        }

        System.out.println(NEXT_LINE + participants.get(DEALER_INDEX).getName() + AND + players + DRAW_TWO_CARD);
    }

    public static void printDealerFirstCard(final Participant dealer) {
        System.out.println(dealer.getName() + COLON + dealer.getCards().get(FIRST_CARD).getFullName());
    }

    public static void printPlayerCards(final Participant player) {
        String cards = getCards(player);
        System.out.println(player.getName() + CARD + cards);
    }

    public static void printDealerSumOfCard(final Participant dealer) {
        String cards = getCards(dealer);
        System.out.println(dealer.getName() + BLANK + CARD + cards + RESULT + dealer.getSumOfDeck());
    }

    public static void printPlayerSumOfCard(final Participant player) {
        String cards = getCards(player);
        System.out.println(player.getName() + CARD + cards + RESULT + player.getSumOfDeck());
    }

    private static String getCards(Participant player) {
        StringJoiner cards = new StringJoiner(SEPARATOR);
        player.getCards().forEach(card -> cards.add(card.getFullName()));
        return cards.toString();
    }

    public static void printNextLine() {
        System.out.println();
    }

    public static void printChooseAddMoreCard(final Participant participant) {
        System.out.println(participant.getName() + DRAW_ONE_CARD);
    }

    public static void printAddDealerCard() {
        System.out.println(NEXT_LINE + DEALER_ONE_MORE_CARD);
    }

    public static void printResultMessage() {
        System.out.println(NEXT_LINE + FINAL_RESULT);
    }

    public static void printResultPlayers(final Referee referee, final List<Participant> participants) {
        Participant dealer = participants.get(DEALER_INDEX);

        for (int players = PLAYER_INDEX; players < participants.size(); players++) {
            Participant player = participants.get(players);
            System.out.println(player.getName() + COLON + referee.compareSumOfCard(dealer, player));
        }
    }

    public static void printResultDealer(final Referee referee, final List<Participant> participants) {
        String results = getResultDealer(referee, participants);
        String sortedResult = getSortResult(results);
        System.out.println(participants.get(DEALER_INDEX).getName() + COLON + sortedResult);
    }

    private static String getResultDealer(final Referee referee, final List<Participant> participants) {
        StringBuilder results = new StringBuilder();
        Participant dealer = participants.get(DEALER_INDEX);

        for (int players = PLAYER_INDEX; players < participants.size(); players++) {
            Participant player = participants.get(players);
            results.append(referee.compareSumOfCard(player, dealer));
        }
        return results.toString();
    }

    private static String getSortResult(final String results) {
        Map<String, Integer> resultStatus = new HashMap<>();
        Arrays.stream(results.split(EMPTY))
            .forEach(result -> resultStatus.put(result, resultStatus.getOrDefault(result, INIT_COUNT) + INCREASE_COUNT));

        return sortResult(resultStatus);
    }

    private static String sortResult(Map<String, Integer> resultStatus) {
        String sortedResult = EMPTY;
        sortedResult += addResultStatus(resultStatus, WIN);
        sortedResult += addResultStatus(resultStatus, DRAW);
        sortedResult += addResultStatus(resultStatus, LOSE);
        return sortedResult;
    }

    private static String addResultStatus(Map<String, Integer> resultStatus, String status) {
        if (resultStatus.containsKey(status)) {
            return resultStatus.get(status) + status + BLANK;
        }
        return EMPTY;
    }
}
