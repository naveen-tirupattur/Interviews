package my.interviews2017.trees;

public class InOrderWithParent {

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
		
		root.parent = null;
		root2.parent = root;
		root3.parent = root2;
		root4.parent = root2;
		root5.parent = root;
		root6.parent = root5;
		root7.parent = root5;
		
		traverse(root);

	}

	public static void traverse(Node root) {
		Node curr = root, next = null, prev = null;
		while (curr != null) {
			// You came from your parent
			if (curr.parent == prev) {
				if (curr.left != null) { // Check if you left nodes, yes go left
					next = curr.left;
				} else {
					System.out.println(curr.val); // Print the data
					if (curr.right != null) next = curr.right; // Check if you have right child, if yes go right
					else
						next = curr.parent; // Go back to parent
				} 
			} else if (prev == curr.left) { // You came from your left child
				System.out.println(curr.val); // Print the node
				if (curr.right != null) next = curr.right; // Check if you have right child, if yes go right
				else
					next = curr.parent; // Go back to parent
			} else if (prev == curr.right) { // You came from your right child
				next = curr.parent; // Go back to your parent
			}
			prev = curr;
			curr = next;
		}
	}

}
