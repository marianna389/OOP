package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AddTest {

    @Test
    void testToString() {
        Add exp = new Add(new Variable("x"), new Number(11));
        assertEquals("(x+11)", exp.toString());
    }

    @Test
    void derivative() {
        Add exp = new Add(new Variable("x"), new Number(11));
        Expression derivativeExp = exp.derivative("x");
        assertEquals("(1+0)", derivativeExp.toString());
    }

    @Test
    void eval() {
        Add exp = new Add(new Variable("x"), new Number(11));
        assertEquals(33, exp.eval("x=22"));
    }
}