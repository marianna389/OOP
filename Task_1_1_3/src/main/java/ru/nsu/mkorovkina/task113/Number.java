package ru.nsu.mkorovkina.task113;

/**
 * Constant.
 */
public class Number extends Expression {
    private final int value;

    Number(int value) {
        this.value = value;
    }

    /**
     * Prints the constant value.
     */
    @Override
    public void print() {
        System.out.print(value);
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
     * @param varAssignment assignment of values to all variables
     * @return constant value
     */
    @Override
    public int eval(String varAssignment) {
        return value;
    }
}
