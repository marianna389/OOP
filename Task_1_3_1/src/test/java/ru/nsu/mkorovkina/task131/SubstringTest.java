package ru.nsu.mkorovkina.task131;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SubstringTest {
    @TempDir
    Path tempDir;

    @Test
    void regularTest() throws IOException {
        Path p = tempDir.resolve("regular.txt");
        Files.writeString(p, "абракадабра", StandardCharsets.UTF_8);
        List<Long> res = Substring.find(p.toString(), "бра");
        assertEquals(List.of(1L, 8L), res);
    }

    @Test
    void overlappingTest() throws IOException {
        Path p = tempDir.resolve("overlapping.txt");
        Files.writeString(p, "ababababab", StandardCharsets.UTF_8);
        List<Long> res = Substring.find(p.toString(), "babab");
        assertEquals(List.of(1L, 3L, 5L), res);
    }

    @Test
    void emptySubStrTest() throws IOException {
        Path p = tempDir.resolve("empty_substr.txt");
        Files.writeString(p, "justtext", StandardCharsets.UTF_8);
        List<Long> res = Substring.find(p.toString(), "");
        assertEquals(List.of(), res);
    }

    @Test
    void emptyFileTest() throws IOException {
        Path p = tempDir.resolve("empty_file.txt");
        Files.writeString(p, "", StandardCharsets.UTF_8);
        List<Long> res = Substring.find(p.toString(), "абавл");
        assertEquals(List.of(), res);
    }

    @Test
    void subStrOnBorderTest() throws IOException {
        Path p = tempDir.resolve("substr_on_border.txt");
        try {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(p.toFile()), StandardCharsets.UTF_8),
                    1 << 16)) {
                int arrSize = 1 << 16;
                char[] arr = new char[arrSize];
                arr[0] = 'б';
                arr[1] = 'р';
                arr[2] = 'л';
                Arrays.fill(arr, 3, arrSize - 2, 'а');
                arr[arrSize - 2] = 'б';
                arr[arrSize - 1] = 'р';
                bufferedWriter.write(arr);
                bufferedWriter.write("лаабрбрлапр");
            }
            List<Long> res = Substring.find(p.toString(), "брла");
            assertEquals(List.of(0L, 65534L, 65541L), res);
        } finally {
            Files.deleteIfExists(p);
        }
    }

    @Test
    void hugeFileTest() throws IOException {
        int arrSize = 1024 * 1024;
        char[] arr = new char[arrSize];
        arr[0] = 'б';
        arr[1] = 'р';
        Arrays.fill(arr, 2, arrSize, 'а');
        long total = Runtime.getRuntime().maxMemory() * 2;
        long arrCount = total / arrSize;
        List<Long> pos = new ArrayList<>();
        for (long i = 0; i < arrCount; i++) {
            pos.add(i * arrSize);
        }
        Path p = tempDir.resolve("huge.txt");
        try {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(p.toFile()), StandardCharsets.UTF_8),
                    1 << 16)) {
                for (long i = 0; i < arrCount; i++) {
                    bufferedWriter.write(arr);
                }
            }
            List<Long> res = Substring.find(p.toString(), "бра");
            assertEquals(pos, res);
        } finally {
            Files.deleteIfExists(p);
        }
    }
}
