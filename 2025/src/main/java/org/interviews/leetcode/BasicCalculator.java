package org.interviews.leetcode;

import java.util.Stack;

public class BasicCalculator {

  /**
   * Evaluates a string expression.
   *
   * @param s The string representing the expression.
   * @return The result of the evaluation.
   */
  public int calculate(String s) {
    int num = 0, sign = 1, result = 0;
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        num = num * 10 + (c - '0');
      } else if (c == '+') {
        result = result + num * sign;
        sign = 1;
        num = 0;
      } else if (c == '-') {
        result = result + num * sign;
        sign = -1;
        num = 0;
      } else if (c == '(') {
        stack.push(result);
        stack.push(sign);
        result = 0;
        sign = 1;
      } else if (c == ')') {
        result = result + num * sign;
        int prevSign = stack.pop();
        int prevResult = stack.pop();
        result = prevResult + prevSign * result;
        sign = 1;
        num = 0;
      } else {
        continue;
      }
    }
    return result + num * sign;
  }

  public static void main(String[] args) {
    BasicCalculator calculator = new BasicCalculator();
    System.out.println("2-1 + 2 = " + calculator.calculate(" 2-1 + 2 ")); // Output: 3
    System.out.println("(1+(4+5+2)-3)+(6+8) = " + calculator.calculate("(1+(4+5+2)-3)+(6+8)")); // Output: 23
    System.out.println(calculator.calculate("1-(2+3)"));
  }
}