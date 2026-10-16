package ru.nsu.mkorovkina.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdjacencyListTest {
    private final AdjacencyList<Integer> graph = new AdjacencyList<>();

    @BeforeEach
    void createGraph() throws IOException {
        graph.readFromFile("graph.txt", Integer::parseInt);
    }

    @Test
    void addVertex() {
        graph.addVertex(8);
        Set<Integer> vertices = graph.getAllVertices();
        Set<Integer> v = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(v, vertices);
    }

    @Test
    void removeVertex() {
        graph.removeVertex(3);
        Set<Integer> vertices = graph.getAllVertices();
        Set<Integer> v = Set.of(1, 2, 4, 5, 6, 7);
        assertEquals(v, vertices);
        AdjacencyList<Integer> g = new AdjacencyList<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 4);
        assertEquals(g, graph);
    }

    @Test
    void addEdge() {
        graph.addEdge(3, 4);
        Set<Integer> neighbors = graph.getNeighbors(3);
        Set<Integer> n = Set.of(4, 5, 6);
        assertEquals(n, neighbors);
    }

    @Test
    void removeEdge() {
        graph.removeEdge(5, 6);
        Set<Integer> neighbors = graph.getNeighbors(5);
        Set<Integer> n = Set.of();
        assertEquals(n, neighbors);
    }

    @Test
    void getNeighbors() {
        Set<Integer> neighbors = graph.getNeighbors(2);
        Set<Integer> n = Set.of(3, 4);
        assertEquals(n, neighbors);
    }

    @Test
    void getAllVertices() {
        Set<Integer> neighbors = graph.getAllVertices();
        Set<Integer> n = Set.of(1, 2, 3, 4, 5, 6, 7);
        assertEquals(n, neighbors);
    }

    @Test
    void testEquals() {
        AdjacencyList<Integer> g = new AdjacencyList<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(3);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        assertEquals(g, graph);
    }

    @Test
    void testHashCode() {
        AdjacencyList<Integer> g = new AdjacencyList<>();
        g.addVertex(1);
        g.addVertex(2);
        g.addVertex(4);
        g.addVertex(5);
        g.addVertex(6);
        g.addVertex(7);
        g.addVertex(3);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 5);
        g.addEdge(3, 6);
        assertEquals(g, graph);
        assertEquals(g.hashCode(), graph.hashCode());
    }

    @Test
    void testToString() {
        String str = graph.toString();
        assertTrue(str.contains("7"));
        assertTrue(str.contains("1"));
        assertTrue(str.contains("2"));
        assertTrue(str.contains("3"));
        assertTrue(str.contains("4"));
        assertTrue(str.contains("5"));
        assertTrue(str.contains("6"));
        assertTrue(str.contains("8"));
        assertTrue(str.contains("1 2"));
        assertTrue(str.contains("2 3"));
        assertTrue(str.contains("2 4"));
        assertTrue(str.contains("3 5"));
        assertTrue(str.contains("3 6"));
        assertTrue(str.contains("5 6"));
        assertTrue(str.contains("6 7"));
        assertTrue(str.contains("7 4"));
    }
}
