package ru.nsu.mkorovkina.task113;

/**
 * Division of two mathematical expressions.
 */
public class Div extends Expression {
    private final Expression arg1;
    private final Expression arg2;

    Div(Expression arg1, Expression arg2) {
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Prints the division of two expressions (exp/exp).
     */
    @Override
    public void print() {
        System.out.print("(");
        arg1.print();
        System.out.print("/");
        arg2.print();
        System.out.print(")");
    }

    /**
     * Calculates the derivative of the quotient of two expressions.
     * Uses the formula (u / v)' = (u' * v - u * v') / (v * v).
     *
     * @param variable variable of differentiation
     * @return new expression containing the result of differentiation
     */
    @Override
    public Expression derivative(String variable) {
        return new Div(new Sub(new Mul(arg1.derivative(variable), arg2),
                new Mul(arg1, arg2.derivative(variable))), new Mul(arg2, arg2));
    }

    /**
     * Evaluates the value of the expression as the quotient of the values of two sub-expressions.
     *
     * @param varAssignment assignment of values to all variables
     * @return result of the evaluation
     */
    @Override
    public int eval(String varAssignment) {
        return arg1.eval(varAssignment) / arg2.eval(varAssignment);
    }
}
