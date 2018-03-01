package my.interviews2017.leetcode;

import java.util.Stack;

public class BST2LL {

	public static void main(String[] args) {

		Node root = new Node(4);
		Node root1 = new Node(1);
		Node root2 = new Node(2);
		Node root3 = new Node(3);
		Node root5 = new Node(5);

		root.left = root2;
		root.right = root5;

		root2.left = root1;
		root2.right = root3;

		System.out.println(treeToDoublyList(root));
	}

	public static Node treeToDoublyList(Node root) {
		Node curr = root;
		Node prev = null;
		Stack<Node> stack = new Stack<Node>();
		while( curr != null || !stack.isEmpty()) {
			if (curr != null) {
				prev = curr;
				stack.push(curr);
				curr = curr.left;
			} 

			if (curr == null && !stack.isEmpty()) {
				curr = stack.pop();
				prev.right = curr;
				curr.left = prev;
				prev = prev.right;
				System.out.println(curr.val);
				curr = curr.right;
			}
		}
		return prev;
	}

	/*
  helper function -- given two list nodes, join them
  together so the second immediately follow the first.
  Sets the .next of the first and the .previous of the second.
	 */
	public static void join(Node a, Node b) {
		a.right = b;
		b.left = a;
	}


	/*
  helper function -- given two circular doubly linked
  lists, append them and return the new list.
	 */
	public static Node append(Node a, Node b) {
		// if either is null, return the other
		if (a==null) return(b);
		if (b==null) return(a);

		// find the last node in each using the previous pointer
		Node aLast = a.left;
		Node bLast = b.left;

		// join the two together to make it connected and circular
		join(aLast, b);
		join(bLast, a);

		return(a);
	}

//Do an inorder traversal to print a tree
  // Does not print the ending "\n"
  public static void printTree(Node root) {
      if (root==null) return;
      printTree(root.left);
      System.out.print(Integer.toString(root.val) + " ");
      printTree(root.right);
  }
  
  
  // Do a traversal of the list and print it out
  public static void printList(Node head) {
      Node current = head;
      
      while (current != null) {
          System.out.print(Integer.toString(current.val) + " ");
          current = current.right;
          if (current == head) break;
      }
      
      System.out.println();
  }
  
	/*
  --Recursion--
  Given an ordered binary tree, recursively change it into
  a circular doubly linked list which is returned.
	 */
	public static Node treeToList(Node root) {
		// base case: empty tree -> empty list
		if (root==null) return null;

		// Recursively do the subtrees (leap of faith!)
		Node aList = treeToList(root.left);
		Node bList = treeToList(root.right);

		// Make the single root node into a list length-1
		// in preparation for the appending
		root.left = root;
		root.right = root;

		// At this point we have three lists, and it's
		// just a matter of appending them together
		// in the right order (aList, root, bList)
		aList = append(aList, root);
		aList = append(aList, bList);

		return(aList);
	}
	
	public static class Node {
		@Override
		public String toString() {
			return "Node [val=" + val + ", left=" + left + ", right=" + right + "]";
		}

		public int val;
		public Node left;
		public Node right;

		public Node(int val) {
			this.val = val;
		}

		public Node(int _val,Node _left,Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	};

}
