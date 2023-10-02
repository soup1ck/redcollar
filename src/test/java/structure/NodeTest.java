package structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private Node root;

    @BeforeEach
    void setUp(){

        root = Node.builder()
                .build();
    }

    @Test
    @DisplayName("Проверка добавления ноды")
    void addNode() {

        assertNull(root.getNo());
        assertFalse(root.hasNext());
        root.addNode(root, "животное1", "вопрос1");
        assertEquals("животное1", root.getNo().getAnimalName());
        assertTrue(root.hasNext());
    }
}