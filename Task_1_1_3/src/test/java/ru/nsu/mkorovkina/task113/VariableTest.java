package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VariableTest {

    @Test
    void testToString() {
        Variable var = new Variable("x");
        assertEquals("x", var.toString());
    }

    @Test
    void derivativeWithSameVariable() {
        Variable var = new Variable("x");
        Expression res = var.derivative("x");
        assertEquals("1", res.toString());
    }

    @Test
    void derivativeWithDifferentVariable() {
        Variable var = new Variable("x");
        Expression res = var.derivative("y");
        assertEquals("0", res.toString());
    }

    @Test
    void eval() {
        Variable var = new Variable("x");
        assertEquals(8, var.eval("x = 8; y = 9"));
    }
}
