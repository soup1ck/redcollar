package structure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Node {

    private final String animalName;
    private final String question;
    private Node yes;
    private Node no;
    private boolean hasNext;

    public void addNode (Node currNode, String nodeName, String property) {
        currNode.setNo(Node.builder()
                .animalName(nodeName)
                .question("Это " + property + "?")
                .build());
        currNode.setYes(currNode);
        currNode.setHasNext(true);
    }
}
