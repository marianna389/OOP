package ru.nsu.mkorovkina.task121;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Topological sort algorithm for a graph.
 *
 * @param <V> vertex type
 */
public class TopologicalSort<V> {
    /**
     * Sorts the given graph in topological order.
     *
     * @param graph given graph
     * @return list of sorted vertices
     */
    public List<V> topologicalSort(Graph<V> graph) {
        List<V> vertices = new ArrayList<>();
        Set<V> black = new HashSet<>();
        Set<V> grey = new HashSet<>();
        for (V vertex : graph.getAllVertices()) {
            if (!black.contains(vertex)) {
                dfs(graph, vertex, vertices, black, grey);
            }
        }
        Collections.reverse(vertices);
        return vertices;
    }

    private void dfs(Graph<V> graph, V startVertex, List<V> vertices, Set<V> black, Set<V> grey) {
        grey.add(startVertex);
        for (V vertex : graph.getNeighbors(startVertex)) {
            if (grey.contains(vertex)) {
                throw new IllegalArgumentException("There is a cycle!");
            }
            if (!black.contains(vertex)) {
                dfs(graph, vertex, vertices, black, grey);
            }
        }
        grey.remove(startVertex);
        black.add(startVertex);
        vertices.add(startVertex);
    }
}
