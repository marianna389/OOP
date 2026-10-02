package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class MainTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @Test
    void mainTestWithOneVariable() {
        String input = "((x+(2-x))*7)\nx\nx=10\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(new String[] {});
        String output = out.toString();
        assertTrue(output.contains("((x+(2-x))*7)"));
        assertTrue(output.contains("(((1+(0-1))*7)+((x+(2-x))*0))"));
        assertTrue(output.contains("14"));
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void mainTestWithTwoExpressions() {
        String input = "(((x+3)/((var+z)*2))-(2*var))\nvar\nx=21;var = 2; z=1\n1\n"
                + "((((5+y)*7)-y)+z)\ny\ny = 43; z = 11\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Main.main(new String[] {});
        String output = out.toString();
        assertTrue(output.contains("(((x+3)/((var+z)*2))-(2*var))"));
        assertTrue(output.contains("(((((0+0)*((var+z)*2))-((x+3)*(((1+0)*2)+((var+z)*0))))/"
                + "(((var+z)*2)*((var+z)*2)))-((0*var)+(2*1)))"));
        assertTrue(output.contains("0"));
        assertTrue(output.contains("((((5+y)*7)-y)+z)"));
        assertTrue(output.contains("(((((0+1)*7)+((5+y)*0))-1)+0)"));
        assertTrue(output.contains("304"));
        System.setIn(originalIn);
        System.setOut(originalOut);
    }
}