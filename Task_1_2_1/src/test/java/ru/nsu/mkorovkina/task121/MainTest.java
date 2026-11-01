package ru.nsu.mkorovkina.task121;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {
    private final PrintStream originalOut = System.out;

    @Test
    void main() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(new String[] {});
        String output = out.toString();
        assertTrue(output.contains("7"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("4"));
        assertTrue(output.contains("5"));
        assertTrue(output.contains("6"));
        assertTrue(output.contains("8"));
        assertTrue(output.contains("1 2"));
        assertTrue(output.contains("2 3"));
        assertTrue(output.contains("2 4"));
        assertTrue(output.contains("3 5"));
        assertTrue(output.contains("3 6"));
        assertTrue(output.contains("5 6"));
        assertTrue(output.contains("6 7"));
        assertTrue(output.contains("7 4"));
        assertTrue(output.contains("[1, 2, 3, 5, 6, 7, 4]"));
        System.setOut(originalOut);
    }
}
