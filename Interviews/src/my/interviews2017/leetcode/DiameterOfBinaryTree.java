package my.interviews2017.leetcode;

public class DiameterOfBinaryTree {

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

		System.out.println(diameter(root));
	}

	public static int diameter(TreeNode root) {
		return diameterUtil(root).diameter;
	}

	public static DiameterNode diameterUtil(TreeNode root) {
		if (root == null) return new DiameterNode(0, 0);

		DiameterNode left = diameterUtil(root.left);
		DiameterNode right = diameterUtil(root.right);

		int height = 1+ Math.max(left.height, right.height);
		int diameter = Math.max(Math.max(left.diameter, right.diameter), left.height+right.height);

		return new DiameterNode(height, diameter);
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

	public static class DiameterNode {
		int diameter;
		int height;

		public DiameterNode(int height, int diameter) {
			this.diameter = diameter;
			this.height = height;
		}
	}



}
