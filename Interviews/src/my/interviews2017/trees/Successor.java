package my.interviews2017.trees;

public class Successor {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root4 = new Node(4);
		Node root5 = new Node(5);
		Node root6 = new Node(6);
		Node root7 = new Node(7);
		Node root8 = new Node(8);
		
		root.left = root2;
		root.right = root5;
		
		root2.left = root3;
		root2.right = root4;
		
		root5.left = root6;
		root5.right = root7;
		
		root7.left = root8;

	}
	
	public static Node successor(Node x) {
		if (x == null) return null;
		if (x.right != null) return getLeftMost(x.right); // If you have right child then left most in right child is successor
		while (x.parent != null) { // Keep going up until you are left child of your parent, then your parent is your successor
			if (x.parent.left.val == x.val) return x.parent;
			x = x.parent;
		}
		return null;
	}
	
	public static Node getLeftMost(Node x) {
		while (x.left != null) x = x.left;
		return x;
	}

}
