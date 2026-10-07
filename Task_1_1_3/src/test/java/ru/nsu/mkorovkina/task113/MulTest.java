package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MulTest {

    @Test
    void testToString() {
        Mul exp = new Mul(new Variable("x"), new Number(11));
        assertEquals("(x*11)", exp.toString());
    }

    @Test
    void derivative() {
        Mul exp = new Mul(new Variable("x"), new Number(11));
        Expression derivativeExp = exp.derivative("x");
        assertEquals("((1*11)+(x*0))", derivativeExp.toString());
    }

    @Test
    void eval() {
        Mul exp = new Mul(new Variable("x"), new Number(5));
        assertEquals(110, exp.eval("x=22"));
    }
}