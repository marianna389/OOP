package ru.nsu.mkorovkina.task121;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Graph represented by an incidence matrix.
 *
 * @param <V> vertex type
 */
public class IncidenceMatrix<V> implements Graph<V> {
    private final Map<V, Integer> vertexToIndex;
    private final Map<Integer, V> indexToVertex;
    private final Map<Edge<V>, Integer> edgeToIndex;
    private final Map<Integer, Edge<V>> indexToEdge;
    private final List<List<Integer>> matrix;

    IncidenceMatrix() {
        this.vertexToIndex = new HashMap<>();
        this.indexToVertex = new HashMap<>();
        this.edgeToIndex = new HashMap<>();
        this.indexToEdge = new HashMap<>();
        this.matrix = new ArrayList<>();
    }

    /**
     * Edge of the graph.
     *
     * @param from source vertex of an edge
     * @param to destination vertex of an edge
     * @param <V> vertex type
     */
    public record Edge<V>(V from, V to) {}

    @Override
    public boolean addVertex(V vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return false;
        }
        int vertexCount = indexToVertex.size();
        vertexToIndex.put(vertex, vertexCount);
        indexToVertex.put(vertexCount, vertex);
        matrix.add(new ArrayList<>());
        int edgeCount = indexToEdge.size();
        for (int i = 0; i < edgeCount; i++) {
            matrix.get(vertexCount).add(0);
        }
        return true;
    }

    @Override
    public boolean removeVertex(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return false;
        }
        int n = vertexToIndex.size();
        int index = vertexToIndex.get(vertex);
        List<Edge<V>> edges = new ArrayList<>();
        for (Edge<V> edge : edgeToIndex.keySet()) {
            if (edge.from().equals(vertex) || edge.to().equals(vertex)) {
                edges.add(edge);
            }
        }
        for (Edge<V> edge : edges) {
            removeEdge(edge.from(), edge.to());
        }
        if (index != n - 1) {
            Collections.swap(matrix, index, n - 1);
            V newVertex = indexToVertex.get(n - 1);
            vertexToIndex.put(newVertex, index);
            indexToVertex.put(index, newVertex);
        }

        matrix.remove(n - 1);
        vertexToIndex.remove(vertex);
        indexToVertex.remove(n - 1);
        return true;
    }

    @Override
    public boolean addEdge(V from, V to) {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        Edge<V> newEdge = new Edge<>(from, to);
        if (edgeToIndex.containsKey(newEdge)) {
            return false;
        }
        int vertexCount = vertexToIndex.size();
        int edgeCount = edgeToIndex.size();
        for (int i = 0; i < vertexCount; i++) {
            if (i == vertexToIndex.get(from)) {
                matrix.get(i).add(1);
            } else if (i == vertexToIndex.get(to)) {
                matrix.get(i).add(-1);
            } else {
                matrix.get(i).add(0);
            }
        }
        edgeToIndex.put(newEdge, edgeCount);
        indexToEdge.put(edgeCount, newEdge);
        return true;
    }

    @Override
    public boolean removeEdge(V from, V to) {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        int vertexCount = vertexToIndex.size();
        int edgeCount = edgeToIndex.size();
        Edge<V> edge = new Edge<>(from, to);
        Integer index = edgeToIndex.get(edge);
        if (index == null) {
            return false;
        }
        if (index != edgeCount - 1) {
            for (int i = 0; i < vertexCount; i++) {
                Collections.swap(matrix.get(i), index, edgeCount - 1);
            }
            Edge<V> newEdge = indexToEdge.get(edgeCount - 1);
            edgeToIndex.put(newEdge, index);
            indexToEdge.put(index, newEdge);
        }
        for (int i = 0; i < vertexCount; i++) {
            matrix.get(i).remove(edgeCount - 1);
        }
        edgeToIndex.remove(edge);
        indexToEdge.remove(edgeCount - 1);
        return true;
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return Collections.emptySet();
        }
        Set<V> neighbors = new HashSet<>();
        int index = vertexToIndex.get(vertex);
        int edgeCount = indexToEdge.size();
        for (int i = 0; i < edgeCount; i++) {
            Edge<V> edge = indexToEdge.get(i);
            if (matrix.get(index).get(i) == 1) {
                neighbors.add(edge.to());
            }
        }
        return neighbors;
    }

    @Override
    public Set<V> getAllVertices() {
        return new HashSet<>(vertexToIndex.keySet());
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IncidenceMatrix<V> other = (IncidenceMatrix<V>) o;
        if (!getAllVertices().equals(other.getAllVertices())) {
            return false;
        }
        Set<V> vertices = getAllVertices();
        for (V vertex : vertices) {
            if (!getNeighbors(vertex).equals(other.getNeighbors(vertex))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAllVertices());
    }

    @Override
    public String toString() {
        int vertexCount = getAllVertices().size();
        StringBuilder builder = new StringBuilder();
        builder.append(vertexCount);
        builder.append("\n");
        Set<V> vertices = getAllVertices();
        for (V vertex : vertices) {
            builder.append(vertex);
            builder.append("\n");
        }
        int edgeCount = edgeToIndex.size();
        builder.append(edgeCount);
        builder.append("\n");
        for (int i = 0; i < edgeCount; i++) {
            Edge<V> edge = indexToEdge.get(i);
            builder.append(edge.from());
            builder.append(" ");
            builder.append(edge.to());
            builder.append("\n");
        }
        return builder.toString();
    }
}

