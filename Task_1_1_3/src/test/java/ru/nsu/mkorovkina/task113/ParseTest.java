package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParseTest {

    @Test
    void parseExprNumber() {
        Parse parse = new Parse("5");
        assertEquals("5", parse.parseExpr().toString());
    }

    @Test
    void parseExprVariable() {
        Parse parse = new Parse("x");
        assertEquals("x", parse.parseExpr().toString());
    }

    @Test
    void parseExprAdd() {
        Parse parse = new Parse("(x+y)");
        assertEquals("(x+y)", parse.parseExpr().toString());
    }

    @Test
    void parseExprSub() {
        Parse parse = new Parse("(9-x)");
        assertEquals("(9-x)", parse.parseExpr().toString());
    }

    @Test
    void parseExprMul() {
        Parse parse = new Parse("(5*xyz)");
        assertEquals("(5*xyz)", parse.parseExpr().toString());
    }

    @Test
    void parseExprDiv() {
        Parse parse = new Parse("(33/a)");
        assertEquals("(33/a)", parse.parseExpr().toString());
    }

    @Test
    void parseExprCompound() {
        Parse parse = new Parse("(x+((y-z)*(72/x)))");
        assertEquals("(x+((y-z)*(72/x)))", parse.parseExpr().toString());
    }
}
