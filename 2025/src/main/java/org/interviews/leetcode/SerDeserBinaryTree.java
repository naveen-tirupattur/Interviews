package org.interviews.leetcode;

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class SerDeserBinaryTree {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) return "null";
    List<String> buffer = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>() {
    };
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.remove();
      if (node == null) buffer.add("null");
      else {
        buffer.add(String.valueOf(node.val));
        queue.add(node.left);
        queue.add(node.right);
      }
    }

    return String.join(",", buffer);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty() || "null".equals(data)) return null;
    String[] nodes = data.split(",");
    TreeNode parent = new TreeNode(Integer.parseInt(nodes[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(parent);
    int i = 1;
    while (!queue.isEmpty() && i < nodes.length) {
      TreeNode node = queue.remove();
      if (i < nodes.length) {
        String val = nodes[i++];
        if (!"null".equals(val)) {
          node.left = new TreeNode(Integer.parseInt(val));
          queue.add(node.left);
        }
      }

      if (i < nodes.length) {
        String val = nodes[i++];
        if (!"null".equals(val)) {
          node.right = new TreeNode(Integer.parseInt(val));
          queue.add(node.right);
        }
      }
    }
    return parent;
  }

  // Encodes a tree to a single string.
  public String serializePreOrder(TreeNode root) {
    if (root == null) return "null";
    List<String> buffer = new ArrayList<>();
    serializeHelper(root, buffer);
    return String.join(",", buffer);
  }

  public void serializeHelper(TreeNode root, List<String> nodes) {
    if (root == null) {
      nodes.add("null");
      return;
    }
    nodes.add(String.valueOf(root.val));
    serializeHelper(root.left, nodes);
    serializeHelper(root.right, nodes);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserializePreorder(String data) {
    if (data.isEmpty() || "null".equals(data)) return null;
    String[] nodes = data.split(",");
    Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
    return deserializeHelper(queue);
  }

  public TreeNode deserializeHelper(Queue<String> queue) {
    String val = queue.poll();
    if (val.equals("null")) {
      return null;
    }
    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = deserializeHelper(queue);
    node.right = deserializeHelper(queue);
    return node;
  }


  public static void main(String[] args) {
    SerDeserBinaryTree serDeserBinaryTree = new SerDeserBinaryTree();
    TreeNode root = new TreeNode(-5);
//    root.left = new TreeNode(0);
//    root.right = new TreeNode(-10);
    String serialized = serDeserBinaryTree.serializePreOrder(root);
    System.out.println(serialized);
    //serialized = "1,null,null";
    root = serDeserBinaryTree.deserializePreorder(serialized);
    System.out.println(serDeserBinaryTree.serialize(root));


  }
}
