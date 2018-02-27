package my.interviews2017.trees;

public class PreOrderWithMarkers {
	public static Integer index;
	public static void main(String[] args) {
		int[] a = {1,2,-1,-1,3,4,-1,-1,5,-1,-1};
		index = 0;
		Node root = traverse(a);
		print(root);
	}

	
	public static Node traverse(int[] a) {
		int val = a[index++]; // Fetch the element at index and update the index	
		if (val == -1) return null; // Check if it is a null element

		Node n = new Node(val); // Create a node with element at that index
		n.left = traverse(a); // Recursively create left and right
		n.right = traverse(a);
		return n;
	}

	public static void print(Node n) {
		if (n!= null) {
			System.out.println(n.val);
			print(n.left);
			print(n.right);
		}
	}

}
