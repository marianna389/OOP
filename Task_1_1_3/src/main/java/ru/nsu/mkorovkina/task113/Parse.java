package ru.nsu.mkorovkina.task113;

/**
 * Mathematical expression parser.
 */
public class Parse {
    private final String str;
    private int pos;

    Parse(String str) {
        this.str = str;
        this.pos = 0;
    }

    /**
     * Creates a mathematical expression based on the given string.
     *
     * @return mathematical expression
     * @throws IllegalArgumentException if the expression does not match the format
     */
    public Expression parseExpr() {
        char symbol = str.charAt(pos);
        if (symbol == '(') {
            pos++;
            Expression arg1 = parseExpr();
            char op = str.charAt(pos++);
            Expression arg2 = parseExpr();
            pos++;
            switch (op) {
                case '+': {
                    return new Add(arg1, arg2);
                }
                case '-': {
                    return new Sub(arg1, arg2);
                }
                case '*': {
                    return new Mul(arg1, arg2);
                }
                case '/': {
                    return new Div(arg1, arg2);
                }
                default: {
                    throw new IllegalArgumentException("Unknown operator: " + op);
                }
            }
        } else if (Character.isLetter(symbol)) {
            StringBuilder nameStr = new StringBuilder();
            while (pos < str.length() && Character.isLetter(str.charAt(pos))) {
                nameStr.append(str.charAt(pos));
                pos++;
            }
            String name = nameStr.toString();
            return new Variable(name);
        } else if (Character.isDigit(symbol)) {
            StringBuilder valueStr = new StringBuilder();
            while (pos < str.length() && Character.isDigit(str.charAt(pos))) {
                valueStr.append(str.charAt(pos));
                pos++;
            }
            String value = valueStr.toString();
            return new Number(Integer.parseInt(value));
        }
        throw new IllegalArgumentException("Incorrectly written expression");
    }
}
