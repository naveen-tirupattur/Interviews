package org.interviews.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvaluateRPN {
  public static int evalRPN(String[] tokens) {
    Stack<Integer> operands = new Stack<>();
    Set<String> operators = new HashSet<String>();
    operators.add("+");
    operators.add("-");
    operators.add("*");
    operators.add("/");
    for (String token : tokens) {
      if (!operators.contains(token)) {
        operands.push(Integer.parseInt(token));
      } else {
        int op2 = operands.pop();
        int op1 = operands.pop();
        int result = switch (token) {
          case "+" -> op1 + op2;
          case "-" -> op1 - op2;
          case "/" -> op1 / op2;
          case "*" -> op1 * op2;
          default -> 0;
        };
        operands.push(result);
      }
    }
    return operands.peek();
  }

  public static void main(String[] args) {
    String[] inputs = new String[]{"2", "1", "+", "3", "*"};
    System.out.println(evalRPN(inputs));
  }
}
