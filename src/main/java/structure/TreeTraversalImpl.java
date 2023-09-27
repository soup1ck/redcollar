package structure;

import data.KnowledgeBase;
import data.Messages;
import exception.AnimalContainsException;
import handler.InputOutputHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Set;

@Slf4j
public class TreeTraversalImpl implements TreeTraversal {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Set<String> setOfAnimals = knowledgeBase.createSetOfAnimals();
    private final InputOutputHandler inputOutputHandler = new InputOutputHandler();

    @Override
    public boolean nodeTraversal(Node currNode) {
        try {
            inputOutputHandler.displayMessage(currNode.getQuestion());
            String userAnswer1 = inputOutputHandler.getInput();
            if (userAnswer1.equals(Messages.YES.label)) {
                inputOutputHandler.displayWinningMsg(currNode);
                return false;
            } else if (userAnswer1.equals(Messages.NO.label) && !currNode.isHasNext()) {
                try {
                    addNewAnimal(currNode);
                } catch (AnimalContainsException e) {
                    log.info(e.getMessage());
                }
                return true;
            }
            return deepCrawl(currNode, true, userAnswer1);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean fullTreeTraversal(Node currNode, String userAnswer) {
        boolean flag = true;
        while (flag) {
            switch (userAnswer) {
                case "да" -> {
                    flag = nodeTraversal(currNode.getYes());
                }
                case "нет" -> {
                    flag = nodeTraversal(currNode.getNo());
                }
                default -> {
                    System.out.println("Введите да/нет");
                    flag = false;
                }
            }
        }
        return flag;
    }

    public boolean deepCrawl(Node currNode, boolean flag,
                             String userAnswer1) {
        while (flag) {
            if (userAnswer1.equals(Messages.NO.label) && currNode.isHasNext()) {
                currNode = currNode.getNo();
                try {
                    inputOutputHandler.displayMessage(currNode.getQuestion());
                    String userAnswer3 = inputOutputHandler.getInput();
                    if (userAnswer3.equals(Messages.YES.label)) {
                        inputOutputHandler.displayWinningMsg(currNode);
                        flag = false;
                    } else if (userAnswer3.equals(Messages.NO.label) && !currNode.isHasNext()) {
                        try {
                            addNewAnimal(currNode);
                        } catch (AnimalContainsException e) {
                            log.info(e.getMessage());
                        }
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    public void addNewAnimal(Node node) throws AnimalContainsException {
        try {
            inputOutputHandler.displayMessage(Messages.MISTAKE.label);
            String newNodeName = inputOutputHandler.getInput();
            if (setOfAnimals.contains(newNodeName)) {
                throw new AnimalContainsException(Messages.ANIMAL_CONTAINS_EXC.label);
            }
            inputOutputHandler.displayDiffMsg(node);
            String userAnswerDiff = inputOutputHandler.getInput();
            node.addNode(node, newNodeName, userAnswerDiff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}