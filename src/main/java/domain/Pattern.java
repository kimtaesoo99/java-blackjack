package domain;

import java.util.Arrays;

public enum Pattern {

    HEART("하트", 0),
    SPADE("스페이드", 1),
    CLOVER("클로버", 2),
    DIAMOND("다이아몬드", 3);

    private static final int PATTERN_TYPE = 4;

    private final String name;
    private final int choose;

    Pattern(String name, int choose) {
        this.name = name;
        this.choose = choose;
    }

    public static Pattern findMatchingPattern(final int matchNumber) {
        return Arrays.stream(Pattern.values())
            .filter(pattern -> isMatchingPattern(matchNumber, pattern))
            .findFirst().orElseThrow();
    }

    private static boolean isMatchingPattern(final int matchNumber, final Pattern pattern) {
        return pattern.choose == matchNumber % PATTERN_TYPE;
    }

    public String getName() {
        return name;
    }
}
