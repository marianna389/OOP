package ru.nsu.mkorovkina.task113;

/**
 * Variable.
 */
public class Variable extends Expression {
    private final String name;

    Variable(String name) {
        this.name = name;
    }

    /**
     * Prints the variable.
     */
    @Override
    public void print() {
        System.out.print(name);
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
     * @param varAssignment assignment of values to all variables
     * @return value of the variable
     * @throws IllegalArgumentException if the variable is not found in the assignment
     */
    @Override
    public int eval(String varAssignment) {
        String[] vars = varAssignment.split(";");
        for (String var : vars) {
            String[] pair = var.split("=");
            String varWithoutSpaces = pair[0].replace(" ", "");
            if (name.equals(varWithoutSpaces)) {
                String valWithoutSpaces = pair[1].replace(" ", "");
                return Integer.parseInt(valWithoutSpaces);
            }
        }
        throw new IllegalArgumentException(String.format("Variable %s not found", name));
    }
}
