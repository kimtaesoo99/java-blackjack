package view;

import dto.ParticipantResponseDto;
import dto.PlayersNameResponseDto;

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
    private static final String DEALER = "딜러";
    private static final int FIRST_CARD = 0;
    private static final int INIT_COUNT = 0;
    private static final int INCREASE_COUNT = 1;

    public static void printInitCardSetting(final PlayersNameResponseDto participants) {
        StringJoiner players = new StringJoiner(SEPARATOR);

        participants.getPlayersName()
            .forEach(players::add);

        System.out.println(NEXT_LINE + DEALER + AND + players + DRAW_TWO_CARD);
    }

    public static void printDealerFirstCard(final ParticipantResponseDto dealer) {
        System.out.println(dealer.getName() + COLON + dealer.getCards().get(FIRST_CARD));
    }

    public static void printPlayerCards(final ParticipantResponseDto player) {
        System.out.println(player.getName() + CARD + sortCards(player.getCards()));
    }

    public static void printDealerSumOfCard(final ParticipantResponseDto dealer) {
        System.out.println(dealer.getName() + BLANK + CARD + sortCards(dealer.getCards()) + RESULT + dealer.getSum());
    }

    public static void printPlayerSumOfCard(final ParticipantResponseDto player) {
        System.out.println(player.getName() + CARD + sortCards(player.getCards()) + RESULT + player.getSum());
    }

    private static String sortCards(final List<String> cards) {
        StringJoiner cardNames = new StringJoiner(SEPARATOR);
        for (String card : cards) {
            cardNames.add(card);
        }
        return cardNames.toString();
    }

    public static void printNextLine() {
        System.out.println();
    }

    public static void printErrorMessage(final String error) {
        System.out.println(error);
    }

    public static void printChooseAddMoreCard(final ParticipantResponseDto player) {
        System.out.println(player.getName() + DRAW_ONE_CARD);
    }

    public static void printAddDealerCard() {
        System.out.println(NEXT_LINE + DEALER_ONE_MORE_CARD);
    }

    public static void printResultMessage() {
        System.out.println(NEXT_LINE + FINAL_RESULT);
    }

    public static void printPlayerResult(final String result, final ParticipantResponseDto player) {
        System.out.println(player.getName() + COLON + result);
    }

    public static void printDealerResult(final String results) {
        String sortedResult = getSortResult(results);
        System.out.println(DEALER + COLON + sortedResult);
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
