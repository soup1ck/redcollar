import data.KnowledgeBase;
import data.Messages;
import utils.InputOutputUtils;
import lombok.extern.slf4j.Slf4j;
import structure.Node;
import structure.TreeTraversal;
import structure.TreeTraversalImpl;

@Slf4j
public class Game {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Node root = knowledgeBase.createRootNode();
    private final InputOutputUtils inputOutputUtils = new InputOutputUtils();
    private final TreeTraversal treeTraversal = new TreeTraversalImpl();

    public void playGame() {
        inputOutputUtils.displayMessage(Messages.START_MESSAGE);
        String userAnswer = inputOutputUtils.getInput();
        if (inputOutputUtils.checkAnswer(userAnswer)) {
            treeTraversal.traverseFullTree(root, userAnswer);
            if (inputOutputUtils.getContinue().equals(Messages.YES)) {
                playGame();
            } else inputOutputUtils.displayMessage(Messages.END_GAME);
        }
        else {
            System.out.println(Messages.WRONG_INPUT);
            playGame();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println(Messages.START_GAME);
        game.playGame();
    }
}