package ru.nsu.mkorovkina.task131;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt algorithm for finding occurrences of a substring in a file.
 */
public class Substring {
    private static int[] prefixFunction(char[] subStrToSymbols) {
        int len = subStrToSymbols.length;
        int[] pi = new int[len];
        pi[0] = 0;
        int j = 0;
        for (int i = 1; i < len; i++) {
            while (j != 0 && subStrToSymbols[i] != subStrToSymbols[j]) {
                j = pi[j - 1];
            }
            if (subStrToSymbols[i] == subStrToSymbols[j]) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    /**
     * Finds the starting indices of a substring within a file.
     *
     * @param fileName the filename
     * @param subString the substring
     * @return list of starting indices for a given substring
     * @throws IOException in case of a file reading error
     */
    public static List<Long> find(String fileName, String subString) throws IOException {
        if (subString == null || subString.isEmpty()) {
            return List.of();
        }
        char[] subStrToSymbols = subString.toCharArray();
        int subStrLen = subStrToSymbols.length;
        int[] pi = prefixFunction(subStrToSymbols);
        List<Long> pos = new ArrayList<>();
        char[] buff = new char[1 << 16];
        int n;
        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8), 1 << 16)) {
            int j = 0;
            long symbolCount = 0;
            while ((n = buffReader.read(buff)) != -1) {
                for (int i = 0; i < n; i++) {
                    while (j != 0 && buff[i] != subStrToSymbols[j]) {
                        j = pi[j - 1];
                    }
                    if (buff[i] == subStrToSymbols[j]) {
                        j++;
                    }
                    if (j == subStrLen) {
                        pos.add(i + 1 - subStrLen + symbolCount);
                        j = pi[j - 1];
                    }
                }
                symbolCount += n;
            }
        }
        return pos;
    }
}
