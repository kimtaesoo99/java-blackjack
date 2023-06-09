package view;

import exception.WrongCommandException;

import java.util.Scanner;

public class InputView {

    public static final String INPUT_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final Scanner scanner = new Scanner(System.in);

    public static String readParticipantName() {
        System.out.println(INPUT_PLAYERS_NAME);
        return scanner.next();
    }

    public static String readAddMoreCard() {
        try {
            String command = scanner.next();
            InputValidation.checkCorrectCommand(command);
            return command;
        } catch (WrongCommandException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readAddMoreCard();
        }
    }
}
