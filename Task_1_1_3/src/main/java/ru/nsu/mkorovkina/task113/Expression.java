package ru.nsu.mkorovkina.task113;

import java.util.HashMap;
import java.util.Map;

/**
 * Mathematical expression.
 */
public abstract class Expression {
    /**
     * Prints mathematical expression.
     */
    public void print() {
        System.out.print(toString());
    }

    /**
     *  Converts the mathematical expression into a string.
     *
     * @return string containing the mathematical expression
     */
    @Override
    public abstract String toString();

    /**
     * Calculates the derivative of a mathematical expression.
     *
     * @param variable variable of differentiation
     * @return new expression containing the result of differentiation
     */
    public abstract Expression derivative(String variable);

    /**
     * Evaluates the expression given a variable assignment.
     *
     * @param varAssignment assignment of values to all variables
     * @return result of the calculations
     */
    public int eval(String varAssignment) {
        return eval(createMap(varAssignment));
    }

    /**
     * Evaluates the mathematical expression using variable values from a map.
     *
     * @param assignment map of variable values
     * @return result of the calculations
     */
    public abstract int eval(Map<String, Integer> assignment);

    private static Map<String, Integer> createMap(String varAssignment) {
        Map<String, Integer> assignment = new HashMap<>();
        String[] vars = varAssignment.split(";");
        for (String var : vars) {
            String[] pair = var.split("=");
            String varWithoutSpaces = pair[0].replace(" ", "");
            String valWithoutSpaces = pair[1].replace(" ", "");
            int value = Integer.parseInt(valWithoutSpaces);
            assignment.put(varWithoutSpaces, value);
        }
        return assignment;
    }
}
