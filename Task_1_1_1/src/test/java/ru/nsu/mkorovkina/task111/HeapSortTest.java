package ru.nsu.mkorovkina.task111;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    @Test
    void heapSort1() {
        int[] array = new int[] {1, 5, 8, 9, 11, 27, 89, 76, 65, 44, 8, 9};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 5, 8, 8, 9, 9, 11, 27, 44, 65, 76, 89}, array);
    }

    @Test
    void heapSort2() {
        int[] array = new int[] {1, 20, 30, 33, 45, 67, 89};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 20, 30, 33, 45, 67, 89}, array);
    }

    @Test
    void heapSort3() {
        int[] array = new int[] {1, 1, 1, 1, 1, 1, 1, 1};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {1, 1, 1, 1, 1, 1, 1, 1}, array);
    }

    @Test
    void heapSort4() {
        int[] array = new int[] {5};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {5}, array);
    }

    @Test
    void heapSort5() {
        int[] array = new int[] {};
        HeapSort.heapSort(array);
        assertArrayEquals(new int[] {}, array);
    }
}