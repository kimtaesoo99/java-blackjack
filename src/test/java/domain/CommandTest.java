package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {

    private static final String YES = "y";
    private static final String NO = "n";

    @Test
    void isCorrectInput() {
        //when
        boolean correct = Command.isCorrectInput(YES);

        //then
        assertThat(correct).isTrue();
    }

    @Test
    void getCommand() {
        //when
        Command command = Command.getCommand(NO);

        //then
        assertThat(command).isEqualTo(Command.NO);
    }

    @Test
    void getStatus() {
        //given
        Command command = Command.YES;

        //when
        boolean status = command.getStatus();

        //then
        assertThat(status).isTrue();
    }
}
