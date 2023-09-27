import data.KnowledgeBase;
import data.Messages;
import handler.InputOutputHandler;
import structure.Node;
import structure.TreeTraversal;
import structure.TreeTraversalImpl;

import java.io.IOException;

public class Game {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Node root = knowledgeBase.createRootNode();
    private final TreeTraversal treeTraversal = new TreeTraversalImpl();
    private final InputOutputHandler inputOutputHandler = new InputOutputHandler();

    public void playGame() {
        try {
            boolean flag = true;
            inputOutputHandler.displayMessage(Messages.START_MESSAGE.label);
            String userAnswer = inputOutputHandler.getInput();
            while (flag) {
                flag = treeTraversal.fullTreeTraversal(root, userAnswer);
                playGame();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("Загадай животное, а я попробую угадать...");
        game.playGame();
    }
}