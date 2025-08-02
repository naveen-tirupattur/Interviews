package org.interviews.hackerrank;


import java.io.*;
  import java.util.*;
  import java.text.*;
  import java.math.*;
  import java.util.regex.*;

public class SimpleTextEditor {
  public static class Operation{
    int op;
    String text;
    public Operation(int op1, String txt){
      op = op1;
      text = txt;
    }
  }
  public static void main(String[] args) {
    Stack<Operation> opsStack = new Stack<>();
    String editorText="";
    Scanner scanner = new Scanner(System.in);
    int numOfOperations = Integer.parseInt(scanner.nextLine());
    for(int i=0;i<numOfOperations;i++){
      String operation = scanner.nextLine();
      int op=0;
      if(operation.length()>1) {
        String[] inputs = operation.split(" ");
        op = Integer.parseInt(inputs[0]);
        if(op == 1) {
          opsStack.push(new Operation(op, inputs[1]));
          editorText += inputs[1];
        } else if(op == 2){
          int index = Integer.parseInt(inputs[1]);
          int length = editorText.length();
          if(index < 0){return;}
          int actualCharsToDelete = Math.min(index, length);
          if(actualCharsToDelete > 0) {
            String textToRemove = editorText.substring(length-index);
            opsStack.push(new Operation(op, textToRemove));
            editorText = editorText.substring(0,length-index);
          }
        } else if(op == 3) {
          int index = Integer.parseInt(inputs[1])-1;
          if(index >=0 && index < editorText.length()) {
            System.out.println(editorText.charAt(index));
          }
        }
      } else {
        op = Integer.parseInt(operation);
        if(op == 4) {
          if(!opsStack.isEmpty()) {
            Operation op1 = opsStack.pop();
            String opTxt = op1.text;
            if(op1.op == 1) {
              int remove = opTxt.length();
              int length = editorText.length();
              if(length == remove) {editorText =""; continue;}
              editorText = editorText.substring(0, length-remove);
            } else if(op1.op == 2) {
              editorText += opTxt;
            }
          }
        }
      }
    }
  }

}

