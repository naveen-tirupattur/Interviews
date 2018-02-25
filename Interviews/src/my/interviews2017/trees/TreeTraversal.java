package my.interviews2017.trees;

import java.util.Stack;

public class TreeTraversal {

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
		
		//preorder(root);
		inOrder(root);

	}
	
	public static void preorder(TreeNode root) {
    if (root == null) return;
    Stack<TreeNode> stack = new Stack<TreeNode>(); // Use stack instead of recursion
    stack.push(root); // Push root node
    while (!stack.isEmpty()) {
    	TreeNode curr = stack.pop(); // Push right and then left so left comes out first
    	System.out.println(curr.val);
    	if (curr.right != null) stack.push(curr.right);
    	if (curr.left != null) stack.push(curr.left);
    }
  }
	
	public static void inOrder(TreeNode root) {
		if (root == null) return;
		TreeNode curr = root;
		Stack<TreeNode> stack = new Stack<TreeNode>(); // Use stack instead of recursion
    while (curr != null || !stack.isEmpty()) {
    	if (curr != null) { // If current node is not null push it to stack and go left
    		stack.push(curr);
    		curr = curr.left;
    	} else if(!stack.isEmpty()){ // Remove from stack and print it and go right
    		curr = stack.pop();
    		System.out.println(curr.val);
    		curr = curr.right;
    	}
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
