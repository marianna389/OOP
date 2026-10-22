package ru.nsu.mkorovkina.task113;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    //((21-(x*y))+(27/xy))
    private final Expression exp = new Add(new Sub(new Number(21),
            new Mul(new Variable("x"), new Variable("y"))),
            new Div(new Number(27), new Variable("xy")));

    @Test
    void testToString() {
        Expression e = new Add(new Sub(new Number(2), new Mul(new Variable("x"),
                    new Variable("y"))), new Div(new Div(new Number(27),
                        new Variable("xy")), new Number(5)));
        assertEquals("((2-(x*y))+((27/xy)/5))", e.toString());
    }

    @Test
    void derivative() {
        Expression derivativeExp = exp.derivative("y");
        assertEquals("((0-((0*y)+(x*1)))+(((0*xy)-(27*0))/(xy*xy)))",
                derivativeExp.toString());
    }

    @Test
    void eval() {
        assertEquals(20, exp.eval("x = 2; y = 5; xy = 3"));
    }

    @Test
    void testEval() {
        Map<String, Integer> assignment = new HashMap<>();
        assignment.put("x", 2);
        assignment.put("y", 5);
        assignment.put("xy", 3);
        assertEquals(20, exp.eval(assignment));
    }
}
