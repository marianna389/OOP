package ru.nsu.mkorovkina.task111;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class HeapSortTest {

    @Test
    void ordinaryArray() {
        int[] array = new int[] {1, 5, 8, 9, 11, 27, 89, 76, 65, 44, 8, 9};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 5, 8, 8, 9, 9, 11, 27, 44, 65, 76, 89}, array);
    }

    @Test
    void sortedArray() {
        int[] array = new int[] {1, 20, 30, 33, 45, 67, 89};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 20, 30, 33, 45, 67, 89}, array);
    }

    @Test
    void arrayWithDuplicates() {
        int[] array = new int[] {1, 1, 1, 1, 1, 1, 1, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, array);
    }

    @Test
    void singleElementArray() {
        int[] array = new int[] {5};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {5}, array);
    }

    @Test
    void emptyArray() {
        int[] array = new int[] {};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}