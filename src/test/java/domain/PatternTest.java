package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PatternTest {

    private static final String DIAMOND = "다이아몬드";
    private static final int MATCH_NUMBER = 3;

    @Test
    public void getName() {
        //given
        Pattern pattern = Pattern.DIAMOND;

        //when
        String name = pattern.getName();

        //then
        assertThat(name).isEqualTo(DIAMOND);
    }

    @Test
    public void findMatchPattern() {
        //when
        Pattern matchedPattern = Pattern.findMatchingPattern(MATCH_NUMBER);

        //then
        assertThat(matchedPattern).isEqualTo(Pattern.DIAMOND);
    }
}
