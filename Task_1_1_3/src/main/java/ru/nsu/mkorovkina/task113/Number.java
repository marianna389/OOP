package ru.nsu.mkorovkina.task113;

import java.util.Map;

/**
 * Constant.
 */
public class Number extends Expression {
    private final int value;

    Number(int value) {
        this.value = value;
    }

    /**
     * Converts the constant's value into a string.
     *
     * @return string containing the value
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }

    /**
     * Calculates the derivative of a constant, that is, 0.
     *
     * @param variable variable of differentiation
     * @return new expression containing 0
     */
    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    /**
     * Evaluates the value of the constant; it does not depend on the variable assignment.
     *
     * @param assignment assignment of values to all variables
     * @return constant value
     */
    @Override
    public int eval(Map<String, Integer> assignment) {
        return value;
    }
}
