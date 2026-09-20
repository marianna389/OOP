package ru.nsu.mkorovkina.task112;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

class MainTest {

    @Test
    void mainTest1() {
        String input = "1\n1\n1\n1\n0\n1\n0\n1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            Main.main(new String[] {});
        } catch (NoSuchElementException expected) {}
    }

    @Test
    void mainTest2() {
        String input = "1\n0\n1\n1\n1\n0\n1\n1\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        try {
            Main.main(new String[] {});
        } catch (NoSuchElementException expected) {}
    }

    @AfterEach
    void restoreSystemIn() {
        System.setIn(System.in);
    }
}