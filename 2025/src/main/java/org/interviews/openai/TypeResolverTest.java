package org.interviews.openai;

import java.util.List;

public class TypeResolverTest {
  public static void main(String[] args) {
    System.out.println("--- Starting Test Cases for getReturnType() ---");
    System.out.println("-------------------------------------------------\n");

    // Common Nodes for testing
    Node intNode = new StringNode("int");
    Node strNode = new StringNode("str");
    Node boolNode = new StringNode("bool");
    Node tNode = new StringNode("T");
    Node uNode = new StringNode("U");

    // =========================================================================
    // Test Case 1: Simple function with no generics
    // Function: int -> str
    // Arguments: int
    // Expected Return: str
    // =========================================================================
    System.out.println("Test Case 1: Simple function (int -> str)");
    Function simpleFn = new Function(intNode, strNode);
    Node simpleArgs = intNode;
    Node simpleResult = TypeResolver.getReturnType(simpleFn, simpleArgs);
    System.out.println("Function: " + simpleFn.toStr());
    System.out.println("Arguments: " + simpleArgs.toStr());
    System.out.println("Resolved Type: " + simpleResult.toStr());
    System.out.println("Expected: str\n");

    // =========================================================================
    // Test Case 2: Function with a single generic type
    // Function: <T> -> T
    // Arguments: <bool>
    // Expected Return: bool
    // =========================================================================
    System.out.println("Test Case 2: Single generic function (<T> -> T)");
    Function genericFn = new Function(new ListNode(List.of(tNode)), tNode);
    Node genericArgs = new ListNode(List.of(boolNode));
    Node genericResult = TypeResolver.getReturnType(genericFn, genericArgs);
    System.out.println("Function: " + genericFn.toStr());
    System.out.println("Arguments: " + genericArgs.toStr());
    System.out.println("Resolved Type: " + genericResult.toStr());
    System.out.println("Expected: bool\n");

    // =========================================================================
    // Test Case 3: Function with multiple generic types
    // Function: <T, U> -> <U, T>
    // Arguments: <str, int>
    // Expected Return: <int, str>
    // =========================================================================
    System.out.println("Test Case 3: Multiple generics (<T, U> -> <U, T>)");
    ListNode genericInput = new ListNode(List.of(tNode, uNode));
    ListNode genericOutput = new ListNode(List.of(uNode, tNode));
    Function multiGenericFn = new Function(genericInput, genericOutput);
    ListNode multiGenericArgs = new ListNode(List.of(strNode, intNode));
    Node multiGenericResult = TypeResolver.getReturnType(multiGenericFn, multiGenericArgs);
    System.out.println("Function: " + multiGenericFn.toStr());
    System.out.println("Arguments: " + multiGenericArgs.toStr());
    System.out.println("Resolved Type: " + multiGenericResult.toStr());
    System.out.println("Expected: <int, str>\n");

    // =========================================================================
    // Test Case 4: Mismatched argument count (expected to fail)
    // Function: <T> -> T
    // Arguments: <int, bool>
    // Expected Result: Throws an exception
    // =========================================================================
    System.out.println("Test Case 4: Mismatched argument count (expected to fail)");
    try {
      Node mismatchedArgs = new ListNode(List.of(intNode, boolNode));
      TypeResolver.getReturnType(genericFn, mismatchedArgs);
    } catch (IllegalArgumentException e) {
      System.out.println("Caught expected exception: " + e.getMessage());
      System.out.println("Test passed.\n");
    }

    // =========================================================================
    // Test Case 5: Inconsistent generic mapping (expected to fail)
    // Function: <T, T> -> T
    // Arguments: <int, str>
    // Expected Result: Throws an exception
    // =========================================================================
    System.out.println("Test Case 5: Inconsistent generic mapping (expected to fail)");
    Function inconsistentFn = new Function(new ListNode(List.of(tNode, tNode)), tNode);
    try {
      Node inconsistentArgs = new ListNode(List.of(intNode, strNode));
      TypeResolver.getReturnType(inconsistentFn, inconsistentArgs);
    } catch (IllegalArgumentException e) {
      System.out.println("Caught expected exception: " + e.getMessage());
      System.out.println("Test passed.\n");
    }

    // =========================================================================
    // Test Case 6: Nested types with generics
    // Function: <<T>> -> T
    // Arguments: <<int>>
    // Expected Return: int
    // =========================================================================
    System.out.println("Test Case 6: Nested generics (<<T>> -> T)");
    ListNode nestedTList = new ListNode(List.of(tNode));
    ListNode nestedGenericInput = new ListNode(List.of(nestedTList));
    Function nestedGenericFn = new Function(nestedGenericInput, tNode);

    ListNode nestedIntList = new ListNode(List.of(intNode));
    ListNode nestedGenericArgs = new ListNode(List.of(nestedIntList));
    Node nestedGenericResult = TypeResolver.getReturnType(nestedGenericFn, nestedGenericArgs);

    System.out.println("Function: " + nestedGenericFn.toStr());
    System.out.println("Arguments: " + nestedGenericArgs.toStr());
    System.out.println("Resolved Type: " + nestedGenericResult.toStr());
    System.out.println("Expected: int\n");

    System.out.println("-------------------------------------------------");
    System.out.println("--- All tests completed. ---");
  }
}
