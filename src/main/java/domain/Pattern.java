package domain;

import exception.WrongMatchNumberException;

import java.util.Arrays;

public enum Pattern {

    HEART("하트", 0),
    SPADE("스페이드", 1),
    CLOVER("클로버", 2),
    DIAMOND("다이아몬드", 3);

    private static final int PATTERN_TYPE = 4;
    private static final String NUMBER_ERROR_MESSAGE = "잘못된 매칭 번호입니다.";

    private final String name;
    private final int choose;

    Pattern(final String name, final int choose) {
        this.name = name;
        this.choose = choose;
    }

    public static Pattern findMatchingPattern(final int matchNumber) {
        return Arrays.stream(Pattern.values())
            .filter(pattern -> isMatchingPattern(matchNumber, pattern))
            .findAny()
            .orElseThrow(() -> new WrongMatchNumberException(NUMBER_ERROR_MESSAGE));
    }

    private static boolean isMatchingPattern(final int matchNumber, final Pattern pattern) {
        return pattern.choose == matchNumber % PATTERN_TYPE;
    }

    public String getName() {
        return name;
    }
}
