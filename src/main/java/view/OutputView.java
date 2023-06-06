package view;

import dto.ParticipantResponseDto;
import dto.PlayersNameResponseDto;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

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

    public static void printInitCardSetting(final PlayersNameResponseDto participants) {
        StringJoiner players = new StringJoiner(SEPARATOR);

        participants.getPlayersName()
            .forEach(players::add);

        System.out.println(NEXT_LINE + DEALER + AND + players + DRAW_TWO_CARD);
    }

    public static void printDealerFirstCard(final ParticipantResponseDto dealer) {
        System.out.println(dealer.getName() + COLON + dealer.getCardsName().get(FIRST_CARD));
    }

    public static void printPlayerCards(final ParticipantResponseDto player) {
        System.out.println(player.getName() + CARD + sortCards(player.getCardsName()));
    }

    public static void printDealerSumOfCard(final ParticipantResponseDto dealer) {
        System.out.println(dealer.getName() + BLANK + CARD + sortCards(dealer.getCardsName()) + RESULT + dealer.getSumOfCards());
    }

    public static void printPlayerSumOfCard(final ParticipantResponseDto player) {
        System.out.println(player.getName() + CARD + sortCards(player.getCardsName()) + RESULT + player.getSumOfCards());
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
        int winCount = getContainCount(WIN, results);
        int drawCount = getContainCount(DRAW, results);
        int loseCount = getContainCount(LOSE, results);

        return sortResult(winCount, drawCount, loseCount);
    }

    private static int getContainCount(String resultStatus, String results) {
        return (int) Arrays.stream(results.split(EMPTY))
            .filter(result -> result.equals(resultStatus))
            .count();
    }

    private static String sortResult(int winCount, int drawCount, int loseCount) {
        StringBuilder results = new StringBuilder();
        addResult(winCount, results, WIN);
        addResult(drawCount, results, DRAW);
        addResult(loseCount, results, LOSE);
        return results.toString();
    }

    private static void addResult(int count, StringBuilder results, String result) {
        if (count != 0) {
            results.append(count).append(result).append(BLANK);
        }
    }
}
