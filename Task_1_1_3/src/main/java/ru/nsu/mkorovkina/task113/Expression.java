package ru.nsu.mkorovkina.task113;

/**
 * Mathematical expression.
 */
public abstract class Expression {
    /**
     * Prints mathematical expression.
     */
    public abstract void print();

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
    public abstract int eval(String varAssignment);
}
