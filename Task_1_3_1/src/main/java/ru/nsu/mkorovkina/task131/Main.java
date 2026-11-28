package ru.nsu.mkorovkina.task131;

import java.io.IOException;
import java.util.List;

/**
 * Program entry point.
 */
public class Main {
    /**
     * Finds the starting indices of substring's occurrences in a file (example).
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            List<Long> result = Substring.find("substring.txt", "java");
            System.out.print(result);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            System.exit(1);
        }
    }
}
