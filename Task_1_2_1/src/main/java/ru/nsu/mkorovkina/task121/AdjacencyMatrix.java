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
 * Graph represented by an adjacency matrix.
 *
 * @param <V> vertex type
 */
public class AdjacencyMatrix<V> implements Graph<V> {
    private final Map<V, Integer> vertexToIndex;
    private final Map<Integer, V> indexToVertex;
    private final List<List<Boolean>> matrix;

    AdjacencyMatrix() {
        this.vertexToIndex = new HashMap<>();
        this.indexToVertex = new HashMap<>();
        this.matrix = new ArrayList<>();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return false;
        }
        int n = vertexToIndex.size();
        vertexToIndex.put(vertex, n);
        indexToVertex.put(n, vertex);
        matrix.add(new ArrayList<>());
        for (int i = 0; i < n + 1; i++) {
            matrix.get(n).add(false);
        }
        for (int i = 0; i < n; i++) {
            matrix.get(i).add(false);
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
        if (index != n - 1) {
            Collections.swap(matrix, index, n - 1);
            for (int i = 0; i < n - 1; i++) {
                Collections.swap(matrix.get(i), index, n - 1);
            }
            V newVertex = indexToVertex.get(n - 1);
            vertexToIndex.put(newVertex, index);
            indexToVertex.put(index, newVertex);
        }
        matrix.remove(n - 1);
        for (int i = 0; i < n - 1; i++) {
            matrix.get(i).remove(n - 1);
        }
        vertexToIndex.remove(vertex);
        indexToVertex.remove(n - 1);
        return true;
    }

    @Override
    public boolean addEdge(V from, V to) {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        if (matrix.get(vertexToIndex.get(from)).get(vertexToIndex.get(to))) {
            return false;
        }
        matrix.get(vertexToIndex.get(from)).set(vertexToIndex.get(to), true);
        return true;
    }

    @Override
    public boolean removeEdge(V from, V to) {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        if (!matrix.get(vertexToIndex.get(from)).get(vertexToIndex.get(to))) {
            return false;
        }
        matrix.get(vertexToIndex.get(from)).set(vertexToIndex.get(to), false);
        return true;
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return Collections.emptySet();
        }
        Set<V> neighbors = new HashSet<>();
        int index = vertexToIndex.get(vertex);
        int n = vertexToIndex.size();
        for (int i = 0; i < n; i++) {
            if (matrix.get(index).get(i)) {
                neighbors.add(indexToVertex.get(i));
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
        AdjacencyMatrix<V> other = (AdjacencyMatrix<V>) o;
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
        int edgeCount = 0;
        StringBuilder edges = new StringBuilder();
        Set<V> vertices = getAllVertices();
        for (V vertex : vertices) {
            builder.append(vertex);
            builder.append("\n");
            for (int i = 0; i < matrix.get(vertexToIndex.get(vertex)).size(); i++) {
                if (matrix.get(vertexToIndex.get(vertex)).get(i)) {
                    edgeCount++;
                    edges.append(vertex);
                    edges.append(" ");
                    edges.append(indexToVertex.get(i));
                    edges.append("\n");
                }
            }
        }
        builder.append(edgeCount);
        builder.append("\n");
        builder.append(edges);
        return builder.toString();
    }
}
