package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberTest {

    @Test
    void testToString() {
        Number num = new Number(6);
        assertEquals("6", num.toString());
    }

    @Test
    void derivative() {
        Number num = new Number(6);
        Expression res = num.derivative("x");
        assertEquals("0", res.toString());
    }

    @Test
    void eval() {
        Number num = new Number(6);
        assertEquals(6, num.eval("x = 8"));
    }
}
