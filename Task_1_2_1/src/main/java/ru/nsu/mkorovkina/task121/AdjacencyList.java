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
 * Graph represented by an adjacency list.
 *
 * @param <V> vertex type
 */
public class AdjacencyList<V> implements Graph<V> {
    private final Map<V, Integer> vertexToIndex;
    private final Map<Integer, V> indexToVertex;
    private final List<List<V>> list;

    AdjacencyList() {
        this.vertexToIndex = new HashMap<>();
        this.indexToVertex = new HashMap<>();
        this.list = new ArrayList<>();
    }

    @Override
    public boolean addVertex(V vertex) {
        if (vertexToIndex.containsKey(vertex)) {
            return false;
        }
        int n = vertexToIndex.size();
        vertexToIndex.put(vertex, n);
        indexToVertex.put(n, vertex);
        list.add(new ArrayList<>());
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
            Collections.swap(list, index, n - 1);
            V newVertex = indexToVertex.get(n - 1);
            vertexToIndex.put(newVertex, index);
            indexToVertex.put(index, newVertex);
        }
        list.remove(n - 1);
        for (int i = 0; i < n - 1; i++) {
            list.get(i).remove(vertex);
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
        if (list.get(vertexToIndex.get(from)).contains(to)) {
            return false;
        }
        list.get(vertexToIndex.get(from)).add(to);
        return true;
    }

    @Override
    public boolean removeEdge(V from, V to) {
        if (!vertexToIndex.containsKey(from) || !vertexToIndex.containsKey(to)) {
            return false;
        }
        if (!list.get(vertexToIndex.get(from)).contains(to)) {
            return false;
        }
        list.get(vertexToIndex.get(from)).remove(to);
        return true;
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return Collections.emptySet();
        }
        Set<V> neighbors = new HashSet<>();
        int index = vertexToIndex.get(vertex);
        int n = list.get(index).size();
        for (int i = 0; i < n; i++) {
            neighbors.add(list.get(index).get(i));
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
        AdjacencyList<V> other = (AdjacencyList<V>) o;
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
            int neighborsSize = list.get(vertexToIndex.get(vertex)).size();
            edgeCount += neighborsSize;
            for (int i = 0; i < neighborsSize; i++) {
                edges.append(vertex);
                edges.append(" ");
                edges.append(list.get(vertexToIndex.get(vertex)).get(i));
                edges.append("\n");
            }
        }
        builder.append(edgeCount);
        builder.append("\n");
        builder.append(edges);
        return builder.toString();
    }
}
