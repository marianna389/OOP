package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void testToString() {
        Sub exp = new Sub(new Variable("x"), new Number(11));
        assertEquals("(x-11)", exp.toString());
    }

    @Test
    void derivative() {
        Sub exp = new Sub(new Variable("x"), new Number(11));
        Expression derivativeExp = exp.derivative("x");
        assertEquals("(1-0)", derivativeExp.toString());
    }

    @Test
    void eval() {
        Sub exp = new Sub(new Variable("x"), new Number(11));
        assertEquals(11, exp.eval("x=22"));
    }
}