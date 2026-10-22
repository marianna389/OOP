package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DivTest {

    @Test
    void testToString() {
        Div exp = new Div(new Variable("x"), new Number(11));
        assertEquals("(x/11)", exp.toString());
    }

    @Test
    void derivative() {
        Div exp = new Div(new Variable("x"), new Number(11));
        Expression derivativeExp = exp.derivative("x");
        assertEquals("(((1*11)-(x*0))/(11*11))", derivativeExp.toString());
    }

    @Test
    void eval() {
        Div exp = new Div(new Variable("x"), new Number(11));
        assertEquals(3, exp.eval("x=33"));
    }

    @Test
    void evalWithZero() {
        Div exp = new Div(new Variable("x"), new Number(0));
        ArithmeticException e = assertThrows(ArithmeticException.class,
                () -> exp.eval("x=33"));
        assertEquals("Division by zero", e.getMessage());
    }
}