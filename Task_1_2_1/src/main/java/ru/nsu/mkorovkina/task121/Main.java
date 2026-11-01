package ru.nsu.mkorovkina.task121;

import java.io.IOException;
import java.util.List;

/**
 * Program entry point.
 */
public class Main {
    /**
     * Performs a topological sort of a graph and prints the list of vertices.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        AdjacencyMatrix<Integer> graph = new AdjacencyMatrix<>();
        try {
            graph.readFromFile("graph.txt", Integer::parseInt);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.print(graph);
        List<Integer> vertices;
        try {
            TopologicalSort<Integer> sort = new TopologicalSort<>();
            vertices = sort.topologicalSort(graph);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }
        System.out.print(vertices);
    }
}
