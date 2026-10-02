package ru.nsu.mkorovkina.task113;

/**
 * Subtraction of two mathematical expressions.
 */
public class Sub extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    Sub(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Prints the subtraction of two expressions (exp-exp).
     */
    @Override
    public void print() {
        System.out.print("(");
        arg1.print();
        System.out.print("-");
        arg2.print();
        System.out.print(")");
    }

    /**
     * Calculates the derivative of the difference between two expressions.
     * Uses the formula (u - v)' = u' - v'.
     *
     * @param variable variable of differentiation
     * @return new expression containing the result of differentiation
     */
    @Override
    public Expression derivative(String variable) {
        return new Sub(arg1.derivative(variable), arg2.derivative(variable));
    }

    /**
     * Evaluates the value of the expression as difference between values of two sub-expressions.
     *
     * @param varAssignment assignment of values to all variables
     * @return result of the evaluation
     */
    @Override
    public int eval(String varAssignment) {
        return arg1.eval(varAssignment) - arg2.eval(varAssignment);
    }
}
