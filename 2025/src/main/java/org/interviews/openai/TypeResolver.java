package org.interviews.openai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Node {
  abstract String toStr();
}

class StringNode extends Node {
  String value;

  public StringNode(String value) {
    this.value = value;
  }

  @Override
  String toStr() {
    return this.value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    StringNode that = (StringNode) obj;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }
}

class ListNode extends Node {
  List<Node> nodes;

  public ListNode(List<Node> nodes) {
    this.nodes = nodes;
  }

  @Override
  String toStr() {
    List<String> results = new ArrayList<>();
    for (Node node : nodes) {
      results.add(node.toStr());
    }
    return "<" + String.join(", ", results) + ">";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    ListNode that = (ListNode) obj;
    return nodes.equals(that.nodes);
  }

  @Override
  public int hashCode() {
    return nodes.hashCode();
  }
}

class Function {
  Node inputParams;
  Node outputType;

  public Function(Node inputParams, Node outputType) {
    this.inputParams = inputParams;
    this.outputType = outputType;
  }

  String toStr() {
    return inputParams.toStr() + " -> " + outputType.toStr();
  }
}

public class TypeResolver {

  public static Node getReturnType(Function function, Node args) {
    Map<String, Node> typeMap = new HashMap<>();
    if (!mapNodesAndMapGenerics(function.inputParams, args, typeMap))
      throw new IllegalArgumentException("Invocation arguments don't match");
    return resolveOutputNode(function.outputType, typeMap);
  }

  public static boolean mapNodesAndMapGenerics(Node paramsNode, Node argsNode, Map<String, Node> typeMap) {
    if (paramsNode instanceof StringNode && argsNode instanceof StringNode) {
      String paramValue = ((StringNode) paramsNode).value;
      String argsValue = ((StringNode) argsNode).value;
      // Check if paramsNode is of generic type
      if (paramValue.length() == 1 && Character.isUpperCase(paramValue.charAt(0))) {
        // If generic type is already in the map, check for consistency.
        if (typeMap.containsKey(paramValue)) {
          return typeMap.get(paramValue).equals(argsValue);
        } else {
          // Otherwise, add the new mapping.
          typeMap.put(paramValue, argsNode);
          return true;
        }
      } else {
        return paramValue.equals(argsValue);
      }
    } else if (paramsNode instanceof ListNode && argsNode instanceof ListNode) {
      List<Node> paramNodes = ((ListNode) paramsNode).nodes;
      List<Node> argNodes = ((ListNode) argsNode).nodes;
      if (argNodes.size() != paramNodes.size()) return true;
      for (int i = 0; i < argNodes.size(); i++) {
        if (!mapNodesAndMapGenerics(paramNodes.get(i), argNodes.get(i), typeMap)) return false;
      }
      return true;
    }
    return false;
  }

  public static Node resolveOutputNode(Node outputType, Map<String, Node> typeMap) {
    if (outputType instanceof StringNode) {
      String outputTypeValue = ((StringNode) outputType).value;
      return typeMap.getOrDefault(outputTypeValue, outputType);
    } else if (outputType instanceof ListNode) {
      List<Node> resolvedNodes = new ArrayList<>();
      for (Node n : ((ListNode) outputType).nodes) {
        resolvedNodes.add(resolveOutputNode(n, typeMap));
      }
      return new ListNode(resolvedNodes);
    }
    return outputType;
  }
}
