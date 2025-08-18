package org.interviews.leetcode;

public class BasicCalculator {

  /**
   * Evaluates a string expression.
   *
   * @param s The string representing the expression.
   * @return The result of the evaluation.
   */
  public int calculate(String s) {
    return 0;
  }

  public static void main(String[] args) {
    BasicCalculator calculator = new BasicCalculator();
    System.out.println("2-1 + 2 = " + calculator.calculate(" 2-1 + 2 ")); // Output: 3
    System.out.println("(1+(4+5+2)-3)+(6+8) = " + calculator.calculate("(1+(4+5+2)-3)+(6+8)")); // Output: 23
    System.out.println(calculator.calculate("1-(    -2)"));
  }
}