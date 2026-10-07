package ru.nsu.mkorovkina.task113;

import java.util.Scanner;

/**
 * Program entry point.
 */
public class Main {
    /**
     * Prints, computes the derivative, and evaluates the value with variable assignments
     * for the entered mathematical expressions.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the expression");
            String str = scanner.nextLine();
            Parse parse = new Parse(str);
            Expression expr = parse.parseExpr();
            expr.print();
            System.out.println();
            System.out.println("Enter the variable of differentiation");
            String variable = scanner.nextLine();
            Expression exprDerivative = expr.derivative(variable);
            exprDerivative.print();
            System.out.println();
            System.out.println("Enter assignments for all variables");
            String varAssignment = scanner.nextLine();
            int result = expr.eval(varAssignment);
            System.out.println(result);
            System.out.println("Enter \"1\" if you want to enter another expression, "
                    + "or \"0\" if you want to stop");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice != 1) {
                break;
            }
        }
        scanner.close();
    }
}
