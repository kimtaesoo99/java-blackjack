package domain;

import exception.WrongMatchNumberException;

import java.util.Arrays;

public enum Number {

    ACE("A", 0),
    TWO("2", 1),
    THREE("3", 2),
    FOUR("4", 3),
    FIVE("5", 4),
    SIX("6", 5),
    SEVEN("7", 6),
    EIGHT("8", 7),
    NINE("9", 8),
    TEN("10", 9),
    KING("K", 10),
    QUEEN("Q", 11),
    JACK("J", 12);

    private static final int NUMBER_TYPE = 13;
    private static final String NUMBER_ERROR_MESSAGE = "잘못된 매칭 번호입니다.";

    private final String name;
    private final int choose;

    Number(final String name, final int choose) {
        this.name = name;
        this.choose = choose;
    }

    public static Number findMatchingNumber(final int matchNumber) {
        return Arrays.stream(Number.values())
            .filter(number -> isMatchingNumber(matchNumber, number))
            .findFirst()
            .orElseThrow(() -> new WrongMatchNumberException(NUMBER_ERROR_MESSAGE));
    }

    private static boolean isMatchingNumber(final int matchNumber, final Number number) {
        return number.choose == matchNumber % NUMBER_TYPE;
    }

    public String getName() {
        return name;
    }
}
