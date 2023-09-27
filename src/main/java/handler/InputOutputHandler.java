package handler;

import data.Messages;
import structure.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputOutputHandler {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String getInput() throws IOException {
        return reader.readLine().toLowerCase();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWinningMsg(Node node){
        System.out.println(Messages.WINNING_MSG.label + node.getAnimalName());
    }

    public void displayDiffMsg(Node node){
        System.out.println(Messages.DIFF.label + node.getAnimalName());
    }
}
