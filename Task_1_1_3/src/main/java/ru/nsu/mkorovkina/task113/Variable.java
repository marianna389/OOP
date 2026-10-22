package ru.nsu.mkorovkina.task113;

import java.util.Map;

/**
 * Variable.
 */
public class Variable extends Expression {
    private final String name;

    Variable(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the variable.
     *
     * @return string containing the name
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Derivative equals one (x' = 1) when x is the variable of differentiation, otherwise 0.
     *
     * @param variable variable of differentiation
     * @return new expression containing 0 or 1
     */
    @Override
    public Expression derivative(String variable) {
        if (name.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    /**
     * Evaluates the value of a variable by finding it in the assignment.
     *
     * @param assignment assignment of values to all variables
     * @return value of the variable
     */
    @Override
    public int eval(Map<String, Integer> assignment) {
        return assignment.get(name);
    }
}
