package structure;

import data.KnowledgeBase;
import data.Messages;
import exception.AnimalContainsException;
import lombok.extern.slf4j.Slf4j;
import utils.InputOutputUtils;

import java.util.Set;

@Slf4j
public class TreeTraversalImpl implements TreeTraversal {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Set<String> setOfAnimals = knowledgeBase.createSetOfAnimals();
    private final InputOutputUtils inputOutputUtils = new InputOutputUtils();

    @Override
    public boolean traverseNode(Node currNode) {
        inputOutputUtils.displayMessage(currNode.getQuestion());
        String userAnswer = inputOutputUtils.getInput();
        if (inputOutputUtils.checkAnswer(userAnswer)) {
            if (userAnswer.equals(Messages.YES)) {
                inputOutputUtils.displayMessageNode(Messages.WINNING_MSG,
                        currNode.getAnimalName());
                return false;
            }
            if (userAnswer.equals(Messages.NO) && !currNode.hasNext()) {
                addNewAnimal(currNode);
                return false;
            }
            return traverseNodeToTheEnd(currNode);
        } else {
            inputOutputUtils.displayMessage(Messages.WRONG_INPUT);
            traverseNode(currNode);
        }
        return false;
    }

    public boolean traverseNodeToTheEnd(Node currNode) {
        boolean flag = true;
        while (flag) {
            inputOutputUtils.displayMessage(currNode.getNo().getQuestion());
            String userAnswer = inputOutputUtils.getInput();
            if (inputOutputUtils.checkAnswer(userAnswer)) {
                currNode = currNode.getNo();
                if (userAnswer.equals(Messages.YES)) {
                    inputOutputUtils.displayMessage("Это " + currNode.getAnimalName() + "?");
                    String secondAnswer = inputOutputUtils.getInput();
                    if (inputOutputUtils.checkAnswer(secondAnswer)) {
                        if (secondAnswer.equals(Messages.YES)) {
                            inputOutputUtils.displayMessageNode(Messages.WINNING_MSG,
                                    currNode.getAnimalName());
                        }
                        if (currNode.hasNext()) {
                            if (secondAnswer.equals(Messages.YES)) {
                                inputOutputUtils.displayMessageNode(Messages.WINNING_MSG, currNode.getAnimalName());
                                return false;
                            }
                        }
                        if (!currNode.hasNext()) {
                            addNewAnimal(currNode);
                            return false;
                        }
                    }
                }
                if (!currNode.hasNext()) {
                    addNewAnimal(currNode);
                    return false;
                } else {
                    traverseNodeToTheEnd(currNode);
                }
                flag = false;
            } else {
                inputOutputUtils.displayMessage(Messages.WRONG_INPUT);
            }
        }
        return false;
    }

    @Override
    public void traverseFullTree(Node currNode, String userAnswer) {
        boolean flag = true;
        while (flag) {
            switch (userAnswer) {
                case "да" -> flag = traverseNode(currNode.getYes());
                case "нет" -> flag = traverseNode(currNode.getNo());
                default -> System.out.println(Messages.WRONG_INPUT);
            }
        }
    }

    public void addNewAnimal(Node node) {
        try {
            inputOutputUtils.displayMessage(Messages.MISTAKE);
            String newNodeName = inputOutputUtils.getInput();
            if (setOfAnimals.contains(newNodeName)) {
                throw new AnimalContainsException(Messages.ANIMAL_CONTAINS_EXC);
            }
            String userAnswerDiff = inputOutputUtils
                    .getDifference(newNodeName, node.getAnimalName());
            node.addNode(node, newNodeName, userAnswerDiff);
            setOfAnimals.add(newNodeName);
        } catch (AnimalContainsException e) {
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

}