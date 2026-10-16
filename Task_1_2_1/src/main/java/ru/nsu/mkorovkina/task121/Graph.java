package ru.nsu.mkorovkina.task121;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

/**
 * Interface representing a graph.
 *
 * @param <V> vertex type
 */
public interface Graph<V> {
    /**
     * Adds a new vertex to the graph.
     *
     * @param vertex new vertex
     * @return false if the graph already contains the given vertex, true otherwise
     */
    boolean addVertex(V vertex);

    /**
     * Removes the given vertex from the graph.
     *
     * @param vertex given vertex
     * @return false if the graph doesn't contain the given vertex, true otherwise
     */
    boolean removeVertex(V vertex);

    /**
     * Adds a new edge to the graph.
     *
     * @param from source vertex of an edge
     * @param to destination vertex of an edge
     * @return false if the graph already contains the edge, true otherwise
     */
    boolean addEdge(V from, V to);

    /**
     * Removes the given edge from the graph.
     *
     * @param from source vertex of an edge
     * @param to destination vertex of an edge
     * @return false if the graph doesn't contain the given edge, true otherwise
     */
    boolean removeEdge(V from, V to);

    /**
     * Gets a set of vertices adjacent to the given vertex.
     *
     * @param vertex given vertex
     * @return set of adjacent vertices
     */
    Set<V> getNeighbors(V vertex);

    /**
     * Reads and parses a graph from a fixed-format file.
     *
     * @param fileName file containing a graph
     * @param stringToVertex function that converts a string into a vertex object
     * @throws IOException if can't read data from the given file
     */
    default void readFromFile(String fileName, Function<String, V> stringToVertex)
            throws IOException {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(fileName))) {
            int vertexCount = Integer.parseInt(buffReader.readLine().trim());
            for (int i = 0; i < vertexCount; i++) {
                V vertex = stringToVertex.apply(buffReader.readLine().trim());
                addVertex(vertex);
            }
            int edgeCount = Integer.parseInt(buffReader.readLine().trim());
            for (int i = 0; i < edgeCount; i++) {
                String[] pair = buffReader.readLine().trim().split(" ");
                V from = stringToVertex.apply(pair[0]);
                V to = stringToVertex.apply(pair[1]);
                addEdge(from, to);
            }
        }
    }

    /**
     * Gets the set of all vertices of the graph.
     *
     * @return set of vertices
     */
    Set<V> getAllVertices();
}
