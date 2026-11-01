package ru.nsu.mkorovkina.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;

class TopologicalSortTest {

    @Test
    void topologicalSort() {
        AdjacencyList<Integer> graph = new AdjacencyList<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addVertex(8);
        graph.addVertex(9);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(6, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 7);
        graph.addEdge(7, 8);
        graph.addEdge(7, 9);
        graph.addEdge(8, 9);
        TopologicalSort<Integer> sort = new TopologicalSort<>();
        List<Integer> vertices = sort.topologicalSort(graph);
        List<Integer> v = List.of(1, 2, 3, 6, 4, 5, 7, 8, 9);
        assertEquals(v, vertices);
    }

    @Test
    void topologicalSortWithCycle() {
        IncidenceMatrix<Integer> graph = new IncidenceMatrix<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 1);
        TopologicalSort<Integer> sorter = new TopologicalSort<>();
        assertThrows(IllegalArgumentException.class, () -> sorter.topologicalSort(graph));
    }
}
