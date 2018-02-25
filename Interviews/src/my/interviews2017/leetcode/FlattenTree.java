package my.interviews2017.leetcode;

import java.util.Stack;

public class FlattenTree {

	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(1);
		TreeNode root2 = new TreeNode(2);
		TreeNode root3 = new TreeNode(3);
		TreeNode root4 = new TreeNode(4);
		TreeNode root5 = new TreeNode(5);
		TreeNode root6 = new TreeNode(6);
		TreeNode root7 = new TreeNode(7);
		
		root.left = root2;
		root.right = root5;
		
		root2.left = root3;
		root2.right = root4;
		
		root5.left = root6;
		root5.right = root7;
		
		flatten(root);
		System.out.println(root);

	}
	
	// Modify the pointers while doing a pre-order traversal
	public static void flatten(TreeNode root) {
    TreeNode current = root;
    Stack<TreeNode> stack = new Stack<>();
    while (current != null && !stack.isEmpty()) {
    	// If right node is not null, store it in a stack
    	if (current.right!= null) {
    		stack.push(current.right);
    	}
    	
    	// If left node is not null modify the pointers to make node's right point to left node
    	if (current.left != null) {
    		current.right = current.left;
    		current.left = null;
    	} else if (!stack.isEmpty()) { // No left child then get from right node
    		TreeNode temp = stack.pop();
    		current.right = temp;
    	}
    	current = current.right; // keep going right
    }
  }

	public static class TreeNode {
		@Override
		public String toString() {
			return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
		}
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

}
