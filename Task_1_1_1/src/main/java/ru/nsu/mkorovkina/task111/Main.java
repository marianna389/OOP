package ru.nsu.mkorovkina.task111;

import java.util.Arrays;

/**
 * Starts the sorting algorithm.
 */
public class Main {
    /**
     * Performs sorting on the given array and outputs the result.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] array = new int[] {5, 4, 3, 2, 1};
        HeapSort.heapSort(array);
        System.out.println(Arrays.toString(array));
    }
}

