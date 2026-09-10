package ru.nsu.mkorovkina.task111;

/**
 * Sorts an array of integers in ascending order using heapsort
 */
public class HeapSort {
    private static void createHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, n, i);
        }
    }

    private static void siftDown(int[] arr, int size, int index) {
        while (true) {
            int leftChild = index * 2 + 1;
            int rightChild = leftChild + 1;
            int maxIndex = index;
            if (leftChild < size && arr[leftChild] > arr[index]) {
                maxIndex = leftChild;
            }
            if (rightChild < size && arr[rightChild] > arr[maxIndex]) {
                maxIndex = rightChild;
            }
            if (maxIndex == index) {
                break;
            }
            swap(arr, index, maxIndex);
            index = maxIndex;
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private static void extractMax(int[] arr, int size) {
        swap(arr, 0, size - 1);
        siftDown(arr, size - 1, 0);
    }

    /**
     * Sorts the array
     * @param arr array of integers to be sorted
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        createHeap(arr);
        for (int i = arr.length; i > 1; i--) {
            extractMax(arr, i);
        }
    }
}
