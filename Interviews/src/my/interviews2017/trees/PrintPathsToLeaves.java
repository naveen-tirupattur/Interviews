package my.interviews2017.trees;

import java.util.ArrayList;
import java.util.List;

public class PrintPathsToLeaves {

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
		System.out.println(binaryTreePaths(root));
	}

	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> paths = new ArrayList<String>();
		paths(root, paths, "");
		return paths;
	}

	public static void paths(TreeNode root, List<String> paths, String pathSoFar) {
		if (root == null) return;
		if (root != null && root.left == null && root.right== null) { // If you have reached the leaf node add path so far to list
			if (pathSoFar.isEmpty()) {
				pathSoFar = pathSoFar+root.val;
			}else {
				pathSoFar= pathSoFar+"->"+root.val;
			}
			paths.add(new String(pathSoFar));
			return;
		}
		// Update path so far
		if (pathSoFar.isEmpty()) {
			pathSoFar = pathSoFar+root.val;
		}else {
			pathSoFar= pathSoFar+"->"+root.val;
		}
		// Check on left and right subtrees
		paths(root.left,paths,pathSoFar);
		paths(root.right,paths,pathSoFar);
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
