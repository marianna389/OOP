package ru.nsu.mkorovkina.task121;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class GraphImplementationTest {
    private Graph<Integer> createGraph(String implementation) {
        switch (implementation) {
            case "AdjacencyList": {
                return new AdjacencyList<>();
            }
            case "AdjacencyMatrix": {
                return new AdjacencyMatrix<>();
            }
            case "IncidenceMatrix": {
                return new IncidenceMatrix<>();
            }
            default: {
                throw new IllegalArgumentException("Unknown implementation: " + implementation);
            }
        }
    }

    static Stream<String> implementation() {
        return Stream.of("AdjacencyList", "AdjacencyMatrix", "IncidenceMatrix");
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void addVertex(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        graph.addVertex(8);
        Set<Integer> vertices = graph.getAllVertices();
        Set<Integer> v = Set.of(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(v, vertices);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void removeVertex(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        graph.removeVertex(3);
        Set<Integer> vertices = graph.getAllVertices();
        Set<Integer> v = Set.of(1, 2, 4, 5, 6, 7);
        assertEquals(v, vertices);
        Graph<Integer> g = createGraph(implementation);
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void addEdge(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        graph.addEdge(3, 4);
        Set<Integer> neighbors = graph.getNeighbors(3);
        Set<Integer> n = Set.of(4, 5, 6);
        assertEquals(n, neighbors);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void removeEdge(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        graph.removeEdge(5, 6);
        Set<Integer> neighbors = graph.getNeighbors(5);
        Set<Integer> n = Set.of();
        assertEquals(n, neighbors);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void getNeighbors(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        Set<Integer> neighbors = graph.getNeighbors(2);
        Set<Integer> n = Set.of(3, 4);
        assertEquals(n, neighbors);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void getAllVertices(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        Set<Integer> neighbors = graph.getAllVertices();
        Set<Integer> n = Set.of(1, 2, 3, 4, 5, 6, 7);
        assertEquals(n, neighbors);
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void testEquals(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        Graph<Integer> g = createGraph(implementation);
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void testHashCode(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
        Graph<Integer> g = createGraph(implementation);
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

    @ParameterizedTest(name = "{0}")
    @MethodSource("implementation")
    void testToString(String implementation) throws IOException {
        Graph<Integer> graph = createGraph(implementation);
        graph.readFromFile("graph.txt", Integer::parseInt);
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

