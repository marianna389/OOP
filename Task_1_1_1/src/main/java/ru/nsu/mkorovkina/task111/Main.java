package ru.nsu.mkorovkina.task111;

import java.util.Arrays;
import java.util.Random;

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
        System.out.println();
        System.out.println("Замеры времени сортировки:");
        int size = 1000;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = random.nextInt();
            }
            long start = System.nanoTime();
            HeapSort.heapSort(arr);
            long end = System.nanoTime();
            long time = end - start;
            System.out.printf("Размер: %8d, Время: %.3f мс%n", size, time / 1000000.0);
            size *= 10;
        }
    }
}

