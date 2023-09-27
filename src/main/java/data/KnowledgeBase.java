package data;

import structure.Node;

import java.util.HashSet;
import java.util.Set;

public class KnowledgeBase {

    public Node createRootNode() {
        return Node.builder()
                .question("живет на суше?")
                .yes(yesNode)
                .no(noNode)
                .hasNext(true)
                .build();
    }

    private final Node yesNode = Node.builder()
            .question("Это кот?")
            .animalName("кот")
            .build();

    private final Node noNode = Node.builder()
            .question("Это кит?")
            .animalName("кит")
            .build();

    public Set<String> createSetOfAnimals() {
        Set<String> setOfAnimals = new HashSet<>();
        setOfAnimals.add("кот");
        setOfAnimals.add("кит");
        return setOfAnimals;
    }
}
