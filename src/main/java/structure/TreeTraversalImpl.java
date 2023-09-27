package structure;

import data.KnowledgeBase;
import exception.AnimalContainsException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.Set;

@Slf4j
public class TreeTraversalImpl implements TreeTraversal {

    private final KnowledgeBase knowledgeBase = new KnowledgeBase();
    private final Scanner scanner = new Scanner(System.in);
    private final Set<String> setOfAnimals = knowledgeBase.createSetOfAnimals();

    @Override
    public boolean yesNodeTraversal(Node currNode, String userAnswer) {
        currNode = currNode.getYes();
        System.out.println(currNode.getQuestion());
        String userAnswer1 = scanner.nextLine().toLowerCase();
        if (userAnswer1.equals("да")) {
            System.out.println("Ура! Я угадал! Это " + currNode.getAnimalName());
            return false;
        } else if (userAnswer1.equals("нет") && !currNode.isHasNext()) {
            try {
                addNewAnimal(currNode);
            } catch (AnimalContainsException e) {
                log.info(e.getMessage());
            }
            return true;
        }
        return deepCrawl(currNode, true, userAnswer1);
    }

    @Override
    public boolean noNodeTraversal(Node currNode, String userAnswer) {
        currNode = currNode.getNo();
        System.out.println(currNode.getQuestion());
        String userAnswer1 = scanner.nextLine().toLowerCase();
        if (userAnswer1.equals("да")) {
            System.out.println("Ура! Я угадал! Это " + currNode.getAnimalName());
            return false;
        } else if (userAnswer1.equals("нет") && !currNode.isHasNext()) {
            try {
                addNewAnimal(currNode);
            } catch (AnimalContainsException e) {
                System.out.println((e.getMessage()));
            }
            return true;
        }
        return deepCrawl(currNode, true, userAnswer1);
    }

    public boolean deepCrawl(Node currNode, boolean flag,
                             String userAnswer1) {
        while (flag) {
            if (userAnswer1.equals("нет") && currNode.isHasNext()) {
                currNode = currNode.getNo();
                System.out.println(currNode.getQuestion());
                String userAnswer3 = scanner.nextLine().toLowerCase();
                if (userAnswer3.equals("да")) {
                    System.out.println("Ура! Я угадал! Это " + currNode.getAnimalName());
                    flag = false;
                } else if (userAnswer3.equals("нет") && !currNode.isHasNext()) {
                    try {
                        addNewAnimal(currNode);
                    } catch (AnimalContainsException e) {
                        System.out.println((e.getMessage()));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void addNewAnimal(Node node) throws AnimalContainsException {
        System.out.println("Я не угадал :(");
        System.out.println("Введите название животного");
        String userAnswer2 = scanner.nextLine().toLowerCase();
        if(setOfAnimals.contains(userAnswer2)){
           throw new AnimalContainsException("Данное животное уже есть в Базе знаний");
        }
        System.out.println("Чем отличается от "
                + node.getAnimalName() + " ?");
        String userAnswer3 = scanner.nextLine().toLowerCase();
        node.addNode(node, userAnswer2, userAnswer3);
    }
}


