import data.KnowledgeBase;
import structure.Node;
import structure.TreeTraversal;
import structure.TreeTraversalImpl;

import java.util.Scanner;

public class Game {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Node root = knowledgeBase.createRootNode();
    private final TreeTraversal treeTraversal = new TreeTraversalImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void playGame() {
        Node currNode = root;
        boolean flag = true;
        System.out.println(currNode.getQuestion());
        String userAnswer = scanner.nextLine().toLowerCase();
        while (flag) {
            switch (userAnswer) {
                case "да":
                    flag = treeTraversal.yesNodeTraversal(currNode, userAnswer);
                    playGame();
                case "нет":
                    flag = treeTraversal.noNodeTraversal(currNode, userAnswer);
                    playGame();
                default:
                    System.out.println("Введите да/нет");
                    playGame();
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("Загадай животное, а я попробую угадать...");
        game.playGame();
    }
}