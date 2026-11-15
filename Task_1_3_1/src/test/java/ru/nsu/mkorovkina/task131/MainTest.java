package ru.nsu.mkorovkina.task131;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.Test;

class MainTest {
    private final PrintStream originalOut = System.out;

    @Test
    void mainTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(new String[] {});
        String output = out.toString();
        System.setOut(originalOut);
        List<Long> expected = List.of(0L, 20L, 32L, 45L, 53L);
        assertEquals(expected.toString(), output);
    }
}
