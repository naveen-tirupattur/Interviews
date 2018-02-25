package my.interviews2017.trees;

public class CheckSymmetric {

	public static void main(String[] args) {
		
		Node root = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		
		root.left = root2;
		root.right = root5;
		
		root2.left = root3;
		root2.right = root4;
		
		root5.left = root6;
		root5.right = root7;
	}
	
	public static boolean isSymmteric(Node root) {
		return (root == null || checkSymmetry(root.left,root.right)); // Check if children are symmetric
	}
	
	public static boolean checkSymmetry(Node n1, Node n2) {
		if (n1 == null && n2== null) return true; //Both children are null
		if (n1 == null || n2== null) return false; // One of them is null then it can't be symmetric
		
		return (n1.val == n2.val) && checkSymmetry(n1.left, n2.right) && checkSymmetry(n1.right, n2.left); // Check children of children.
	}
}
