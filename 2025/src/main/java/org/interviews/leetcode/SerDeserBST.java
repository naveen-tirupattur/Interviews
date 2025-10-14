package org.interviews.leetcode;


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class SerDeserBST {
  
  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    if (root == null) return "";
    StringBuffer sb = new StringBuffer();
    serializeHelper(root, sb);
    return sb.toString().trim();
  }

  public void serializeHelper(TreeNode root, StringBuffer sb) {
    if (root == null) return;
    sb.append(root.val);
    sb.append(" ");
    serializeHelper(root.left, sb);
    serializeHelper(root.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.isEmpty()) return null;
    String[] nodes = data.split(" ");
    TreeNode root = deserializeHelper(nodes, new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
    return root;
  }

  public TreeNode deserializeHelper(String[] nodes, int[] index, int min, int max) {
    if (index[0] >= nodes.length) return null;

    int val = Integer.parseInt(nodes[index[0]]);

    if (val <= min || val >= max) return null;

    TreeNode root = new TreeNode(val);
    index[0]++;
    root.left = deserializeHelper(nodes, index, min, val);
    root.right = deserializeHelper(nodes, index, val, max);
    return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// String tree = ser.serialize(root);
// TreeNode ans = deser.deserialize(tree);
// return ans;
