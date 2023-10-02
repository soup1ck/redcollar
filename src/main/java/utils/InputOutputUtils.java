package utils;

import data.Messages;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class InputOutputUtils {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String getInput() {
        try {
            return reader.readLine().toLowerCase();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return "";
    }

    public String getDifference(String currAnimal, String actualAnimal) {
        System.out.println(Messages.DIFF
                .formatted(currAnimal, actualAnimal));
        return getInput();
    }

    public String getContinue() {
        System.out.println((Messages.CONTINUE_GAME));
        return getInput();
    }

    public boolean checkAnswer(String answer){
        return answer.equals(Messages.YES) || answer.equals(Messages.NO);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayMessageNode(String message, String param) {
        System.out.println(message.formatted(param));
    }
}
