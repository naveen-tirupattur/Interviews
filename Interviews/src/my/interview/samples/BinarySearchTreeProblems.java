/**
 * 
 */
package my.interview.samples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author naveenit7sep
 *
 */
public class BinarySearchTreeProblems {

	public static Stack<Integer> stack;
	public static int leafLevel = -1;
	public static int preIndex = 0;
	public static int min_so_far = Integer.MIN_VALUE;

	public static class Data {
		int path;
		int height;

		public Data() {
			this.path = 0;
			this.height = 0;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BTNode root = insert(null, 5);
		root = insert(root, 3);
		root = insert(root, 8);
		root = insert(root, 7);
		root = insert(root, 2);
		root = insert(root, 4);
		root = insert(root, 6);
		root = insert(root, 1);
		root = insert(root, 9);

		levelOrderTraversal(root);

		BTNode root1 = insert(null, 8);
		root1 = insert(root1, 5);
		root1 = insert(root1, 2);
		root1 = insert(root1, 7);
		root1 = insert(root1, 1);
		root1 = insert(root1, 3);
		root1 = insert(root1, 4);
		root1 = insert(root1, 6);
		root1 = insert(root1, 9);
		root1 = insert(root1, 15);
		root1 = insert(root1, 13);
		root1 = insert(root1, 17);
		root1 = insert(root1, 11);
		root1 = insert(root1, 14);
		root1 = insert(root1, 18);
		root1 = insert(root1, 19);
		root1 = insert(root1, 20);

		// BTNode n = findMinimumRecursion(root);
		// System.out.println("Minimum: "+n.value);
		//
		// BTNode n1 = findMaximumRecursion(root);
		// System.out.println("Maximum: "+n1.value);

		// System.out.println(findMinimumThanNode(root, 3));
		// levelOrderTraversal(root1);
		// depthOddLevelLeafNode(root1);

		// converBTto2DLL(root);

		// int[] in = new int[]{1,3,4,5,6,7,8};
		// int[] pre = new int[]{5,3,1,4,7,6,8};
		//
		// BTNode root2 = createTree(in, pre, 0,in.length-1);
		//
		// levelOrderTraversal(root2);
		int[] a = new int[10];
		ArrayList<Integer> l = new ArrayList<Integer>();
		int[] maxSum = new int[1];
		System.out.println(maxPathsumThroughRoot(root, a, 0, maxSum, l));
		for (Integer i : l) {
			System.out.println(i);
		}

		// a = diameterInNTime(root, a);
		// System.out.println("diameter: "+a[0]+" height: "+a[1]);

		// Data d = new Data();
		// System.out.println(diameter(root));

		// System.out.println(isComplete(root));

		// reverseLevelOrderTraversal(root);

		// System.out.println(findOddLevelSum(root,true));

		// root = sumOfChildNodes(root);
		// linkedListWithPreOrder(root);

	}

	public static int maxPathSum(BTNode root) {
		if (root == null)
			return 0;

		return root.value + Math.max(maxPathSum(root.left), maxPathSum(root.right));
	}

	public static void linkedListWithInOrder(BTNode root) {
		BTNode current = root, head = null, next = null;
		Stack<BTNode> s = new Stack<BTNode>();
		while (current != null || !s.isEmpty()) {
			if (current != null) {
				s.push(current);
				current = current.left;
			} else {
				BTNode n = s.pop();
				if (head == null)
					head = n;
				if (next != null)
					next.right = n;
				next = n;
				current = n.right;
			}
		}

		// print linkedlist
		while (head != null) {
			System.out.println(head.value);
			head = head.right;
		}
	}

	public static void linkedListWithPreOrder(BTNode root) {
		BTNode head = null, next = null;
		Stack<BTNode> s = new Stack<BTNode>();
		s.push(root);
		while (!s.isEmpty()) {
			BTNode n = s.pop();
			if (head == null)
				head = n;
			if (next != null)
				next.right = n;
			next = n;
			if (n.right != null)
				s.push(n.right);
			if (n.left != null)
				s.push(n.left);
		}

		// print linkedlist
		while (head != null) {
			System.out.println(head.value);
			head = head.right;
		}
	}

	public static void printNodesOfSameLevel(BTNode root, int level, int currentLevel) {
		if (root == null)
			return;

		if (level == currentLevel)
			System.out.println(root.value);

		printNodesOfSameLevel(root.left, level, currentLevel + 1);
		printNodesOfSameLevel(root.right, level, currentLevel + 1);

	}

	public static void printBottomToTop(BTNode root) {
		if (root == null)
			return;
		Queue<BTNode> q = new LinkedList<BTNode>();
		Stack<BTNode> s = new Stack<BTNode>();

		q.add(root);
		while (!q.isEmpty()) {
			BTNode n = q.remove();
			s.push(n);
			if (n.right != null)
				q.add(n.right);
			if (n.left != null)
				q.add(n.left);
		}

		while (!s.isEmpty()) {
			System.out.println((s.pop()).value);
		}
	}

	public static BTNode sumOfChildNodes(BTNode root) {
		if (root == null)
			return null;

		if (root.right == null && root.left == null)
			return root;

		root.left = sumOfChildNodes(root.left);
		root.right = sumOfChildNodes(root.right);

		root.value = root.value + (root.left != null ? root.left.value : 0) + (root.right != null ? root.right.value : 0);

		return root;
	}

	public static int findOddLevelSum(BTNode a, boolean oddLevel) {
		if (a == null)
			return 0;
		int childSum = findOddLevelSum(a.left, !oddLevel) + findOddLevelSum(a.right, !oddLevel);
		if (oddLevel)
			return a.value + childSum;
		else
			return childSum;
	}

	public static void reverseLevelOrderTraversal(BTNode node) {
		Queue<BTNode> q = new LinkedList<BTNode>();
		Stack<BTNode> s = new Stack<>();

		int currentLevel = 1, nextLevel = 0;
		q.add(node);
		while (!q.isEmpty()) {
			BTNode temp = (BTNode) q.remove();
			currentLevel--;
			s.push(temp);

			if (temp.right != null) {
				q.add(temp.right);
				nextLevel++;
			}

			if (temp.left != null) {
				q.add(temp.left);
				nextLevel++;
			}

			if (currentLevel == 0) {
				// System.out.println("\n");
				currentLevel = nextLevel;
				nextLevel = 0;
				s.push(new BTNode(-1, null, null));
			}

		}

		while (!s.isEmpty()) {
			BTNode n = s.pop();
			if (n.value == -1)
				System.out.println("\n");
			else
				System.out.print(n.value + "\t");

		}

	}

	public static BTNode findMinimumRecursion(BTNode n) {
		if (n == null)
			return null;
		if (n.left == null)
			return n;
		return findMinimumRecursion(n.left);
	}

	public static BTNode findMaximumRecursion(BTNode n) {
		if (n == null)
			return null;
		if (n.right == null)
			return n;
		return findMaximumRecursion(n.right);
	}

	public static int findMinimumThanNode(BTNode n, int k) {
		if (n == null)
			return min_so_far;

		if (n.value == k) {
			if (n.left == null)
				return min_so_far;
			BTNode left = n.left;
			while (left.right != null) {
				left = left.right;
			}
			return left.value;
		} else {
			if (n.value < k)

			{
				min_so_far = n.value;
				return findMinimumThanNode(n.right, k);
			} else {
				return findMinimumThanNode(n.left, k);
			}
		}

	}

	public static Data findMaxPathSum(BTNode n) {
		Data d = new Data();
		// if node is null
		if (n == null) {
			d.height = 0;
			d.path = 0;
			return d;
		}

		Data lData = findMaxPathSum(n.left);
		Data rData = findMaxPathSum(n.right);

		d.height = Math.max(lData.height, rData.height) + n.value;
		d.path = Math.max(Math.max(lData.path, rData.path), lData.height + rData.height + n.value);

		return d;

	}

	public static Data diameterInNTime(BTNode n) {
		Data d = new Data();
		if (n == null) {
			d.height = 0;
			d.path = 0;
			return d;
		}

		// get the values for left sub tree
		Data lData = diameterInNTime(n.left);

		// get the values for right sub tree
		Data rData = diameterInNTime(n.right);

		// Get the maximum of diameter of children and height
		d.height = Math.max(lData.height, rData.height) + 1;
		// Get the height
		d.path = Math.max(Math.max(lData.path, rData.path), 1 + lData.height + rData.height);

		return d;
	}

	public static int diameter(BTNode n) {
		if (n == null)
			return 0;

		int lHeight = findDepth(n.left);
		int rHeight = findDepth(n.right);

		return Math.max(Math.max(diameter(n.left), diameter(n.right)), 1 + lHeight + rHeight);
	}

	public static int maxPathsumThroughRoot(BTNode n, int[] a, int level, int[] maxSum, ArrayList<Integer> l) {
		if (n == null)
			return 0;

		a[level] = n.value;

		int sum = 0;

		for (int i = 0; i <= level; i++) {
			sum += a[i];
		}

		if (sum >= maxSum[0]) {
			maxSum[0] = sum;
			l.clear();
			for (int i = 0; i <= level; i++) {
				l.add(a[i]);
			}
		}

		maxPathsumThroughRoot(n.left, a, level + 1, maxSum, l);
		maxPathsumThroughRoot(n.right, a, level + 1, maxSum, l);

		return maxSum[0];

	}

	public static int findRoot(int[] a, int b, int min, int max) {
		for (int i = min; i <= max; i++) {
			if (a[i] == b)
				return i;
		}

		return -1;
	}

	public static BTNode createTree(int[] in, int[] pre, int inMin, int inMax) {
		if (inMin > inMax)
			return null;

		BTNode n = new BTNode(pre[preIndex++], null, null);

		int inIndex = findRoot(in, n.value, inMin, inMax);

		n.left = createTree(in, pre, inMin, inIndex - 1);
		n.right = createTree(in, pre, inIndex + 1, inMax);

		return n;
	}

	public static void converBTto2DLL(BTNode root) {
		BTNode current = root, prev = null;
		Stack<BTNode> s = new Stack<BTNode>();
		while (current != null || !s.isEmpty()) {
			if (current != null) {
				s.push(current);
				current = current.left;
			} else {
				BTNode n = s.pop();
				n.left = prev;
				prev = n;
				current = n.right;
			}
		}

		current = prev;
		prev = null;
		while (current.left != null) {
			prev = current.left;
			prev.right = current;
			current = current.left;
		}

		// print linkedlist
		while (current != null) {
			System.out.println(current.value);
			current = current.right;
		}
	}

	public static boolean printAncestors(BTNode root, int k) {
		if (root == null)
			return false;

		if (root.value == k)
			return true;

		if (printAncestors(root.left, k) || printAncestors(root.right, k)) {
			System.out.println(root.value);
			return true;
		}

		return false;
	}

	public static void depthOddLevelLeafNode(BTNode root) {
		if (root == null)
			return;

		int nodesCLevel = 1, nodesNLevel = 0, height = 0, oddHeight = 0;

		Queue<BTNode> q = new LinkedList<BTNode>();

		q.add(root);
		while (!q.isEmpty()) {
			BTNode n = q.remove();
			nodesCLevel--;

			if (n.left == null && n.right == null) {
				if (height % 2 == 0) {
					oddHeight = height + 1;
				} else {
					oddHeight = height;
				}
			}

			if (n.left != null) {
				q.add(n.left);
				nodesNLevel++;
			}

			if (n.right != null) {
				q.add(n.right);
				nodesNLevel++;
			}

			if (nodesCLevel == 0) {
				nodesCLevel = nodesNLevel;
				nodesNLevel = 0;
				height++;
			}
		}

		System.out.println("Height: " + height);
		System.out.println("Height of odd leaf: " + oddHeight);
	}

	public static void inOrderReverse(BTNode root) {
		BTNode current = root;
		int sum = 0;

		Stack<BTNode> s = new Stack<BTNode>();

		while (current != null || !s.isEmpty()) {
			if (current != null) {
				s.push(current);
				current = current.right;
			} else {
				BTNode c = s.pop();
				sum += c.value;
				System.out.println(sum);
				current = c.left;
			}
		}

	}

	public static BTNode pruneNodes(BTNode root, int k, int sum) {
		if (root == null)
			return null;

		root.left = pruneNodes(root.left, k, sum + root.value);

		root.right = pruneNodes(root.right, k, sum + root.value);

		if ((root.left == null) && (root.right == null)) {
			if (sum + root.value < k) {
				root = null;
			}
		}

		return root;
	}

	public static void printLeft(BTNode root) {
		Queue<BTNode> cQ = new LinkedList<BTNode>();
		Queue<BTNode> nQ = new LinkedList<BTNode>();
		cQ.add(root);
		do {
			System.out.println(cQ.peek().value);
			while (!cQ.isEmpty()) {
				BTNode n = cQ.remove();
				if (n.left != null)
					nQ.add(n.left);
				if (n.right != null)
					nQ.add(n.right);

			}
			System.out.println(nQ.peek().value);
			while (!nQ.isEmpty()) {
				BTNode n = nQ.remove();
				if (n.left != null)
					cQ.add(n.left);
				if (n.right != null)
					cQ.add(n.right);
			}

			if (cQ.isEmpty())
				break;
		} while (true);
	}

	public static void printRight(BTNode root) {
		Stack<BTNode> cs = new Stack<BTNode>();
		Stack<BTNode> ns = new Stack<BTNode>();
		cs.add(root);
		do {
			System.out.println(cs.peek().value);
			while (!cs.isEmpty()) {
				BTNode n = cs.pop();
				if (n.left != null)
					ns.add(n.left);
				if (n.right != null)
					ns.add(n.right);

			}
			System.out.println(ns.peek().value);
			while (!ns.isEmpty()) {
				BTNode n = ns.pop();
				if (n.left != null)
					cs.add(n.left);
				if (n.right != null)
					cs.add(n.right);
			}

			if (cs.isEmpty())
				break;
		} while (true);
	}

	public static int complete(BTNode root) {
		if (root == null)
			return -1;
		else
			return 1 + Math.min(complete(root.left), complete(root.right));
	}

	public static boolean isComplete(BTNode root) {
		if (root == null)
			return true;

		Queue<BTNode> queue = new LinkedList<BTNode>();
		queue.add(root);
		boolean isFullNode = true;
		while (!queue.isEmpty()) {
			BTNode node = queue.remove();

			// if(!isFullNode) return false;

			if (node.left != null) {
				if (!isFullNode)
					return false;
				queue.add(node.left);
			} else {
				isFullNode = false;
			}

			if (node.right != null) {
				if (!isFullNode)
					return false;
				queue.add(node.right);
			} else {
				isFullNode = false;
			}
		}
		return true;
	}

	public static void preOrderIterative(BTNode node) {
		Stack<BTNode> stack = new Stack<BTNode>();

		stack.push(node);

		while (!stack.isEmpty()) {
			BTNode current = stack.pop();
			System.out.print(current.value + " ");
			if (current.right != null)
				stack.push(current.right);
			if (current.left != null)
				stack.push(current.left);

		}
	}

	public static void inOrderIterative(BTNode node) {
		Stack<BTNode> stack = new Stack<BTNode>();
		BTNode current = node;

		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.left;
			} else {
				current = stack.pop();
				System.out.print(current.value + " ");
				current = current.right;
			}
		}

	}

	public static void postOrderIterative(BTNode root) {
		if (root == null)
			return;

		Stack<BTNode> s1 = new Stack<BTNode>();
		Stack<BTNode> s2 = new Stack<BTNode>();

		s1.push(root);

		while (!s1.isEmpty()) {
			BTNode r = s1.pop();

			s2.push(r);

			if (r.left != null)
				s1.push(r.left);
			if (r.right != null)
				s1.push(r.right);

		}

		while (!s2.isEmpty()) {
			System.out.println(s2.pop().value);
		}
	}

	public static void inOrder(BTNode node) {

		if (node == null)
			return;

		inOrder(node.left);
		System.out.print(node.value + " ");
		inOrder(node.right);
	}

	public static void preOrder(BTNode node) {
		if (node == null)
			return;
		System.out.print(node.value + " ");
		preOrder(node.left);
		preOrder(node.right);

	}

	public static void printTreeLevelsAlternatively(BTNode root) {
		Stack<BTNode> currentList = new Stack<BTNode>();
		Stack<BTNode> nextList = new Stack<BTNode>();
		currentList.add(root);
		do {
			while (!currentList.isEmpty()) {
				BTNode node = currentList.pop();
				System.out.print(node.value + " ");

				if (node.left != null)
					nextList.add(node.left);

				if (node.right != null)
					nextList.add(node.right);

			}

			System.out.println("\n");

			while (!nextList.isEmpty()) {
				BTNode node = nextList.pop();

				System.out.print(node.value + " ");
				if (node.right != null)
					currentList.add(node.right);

				if (node.left != null)
					currentList.add(node.left);

			}

			System.out.println("\n");

			if (currentList.size() == 0)
				break;
		} while (true);

	}

	public static boolean isSubTree(BTNode a, BTNode b) {

		if (b == null)
			return true;

		if (a == null)
			return false;

		if (a.value == b.value)
			return equals(a, b);

		return isSubTree(a.left, b) || isSubTree(a.right, b);
	}

	public static boolean equals(BTNode a, BTNode b) {
		if (a == null && b == null)
			return true;

		if (a == null || b == null)
			return false;

		if (a.value != b.value)
			return false;

		return equals(a.left, b.left) && equals(a.right, b.right);

	}

	public static BTNode commonAncestor(BTNode root, BTNode a, BTNode b) {
		if (root == null)
			return null;

		if (a == null || b == null)
			return null;

		boolean is_a_left = exists(root.left, a);
		boolean is_b_left = exists(root.left, b);

		if (is_a_left != is_b_left)
			return root;

		BTNode next = is_a_left ? root.left : root.right;

		return commonAncestor(next, a, b);
	}

	public static boolean exists(BTNode root, BTNode a) {
		if (root == null)
			return false;

		if (a == null)
			return true;

		if (root == a)
			return true;

		return exists(root.left, a) || exists(root.right, a);

	}

	public static boolean isBST(BTNode n, int min, int max) {
		if (n == null)
			return true;

		if (n.value <= min || n.value > max)
			return false;

		return isBST(n.left, min, n.value) && isBST(n.right, n.value, max);
	}

	public static void makeBST() {
		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		BTNode root = createBST(a, 0, a.length - 1);
	}

	public static void makeLists(BTNode root) {
		List<LinkedList<BTNode>> linkedLists = makeLinkedLists(root);

		for (LinkedList<BTNode> linkedList : linkedLists) {
			for (BTNode node : linkedList) {
				System.out.print(node.value + " ");
			}

			System.out.println();
		}
	}

	public static List<LinkedList<BTNode>> makeLinkedLists(BTNode root) {
		Queue<BTNode> q = new LinkedList<BTNode>();
		List<LinkedList<BTNode>> linkedLists = new ArrayList<LinkedList<BTNode>>();
		LinkedList<BTNode> parent = new LinkedList<BTNode>();
		int currentLevel = 1, nextLevel = 0;
		q.add(root);
		while (!q.isEmpty()) {
			BTNode temp = (BTNode) q.remove();
			currentLevel--;
			parent.add(temp);

			if (temp != null) {
				if (temp.left != null) {
					q.add(temp.left);
					nextLevel++;
				}
				if (temp.right != null) {
					q.add(temp.right);
					nextLevel++;
				}

			}

			if (currentLevel == 0) {
				linkedLists.add(parent);
				parent = new LinkedList<BTNode>();
				currentLevel = nextLevel;
				nextLevel = 0;
			}

		}

		return linkedLists;
	}

	public static boolean isBalanced(BTNode root) {
		if (root == null)
			return true;

		if (root.left == null && root.right == null)
			return true;

		int heightDiff = findDepth(root.left) - findDepth(root.right);
		if (Math.abs(heightDiff) > 1)
			return false;

		return true;
	}

	public static BTNode findMinimum(BTNode root) {
		// Check if the root is null
		if (root == null)
			return null;

		BTNode node = root;

		// Keep going to the left most node of the tree.
		while (node.left != null)
			node = node.left;

		return node;
	}

	public static BTNode insert(BTNode parent, int value) {
		// If parent node is null, create a new node and return the new node
		if (parent == null)
			return new BTNode(value, null, null);

		// If the value to be inserted is less than parent node, then insert it as
		// the left child
		if (value < parent.value) {
			parent.left = insert(parent.left, value);
		} // else insert it as the right child
		else
			parent.right = insert(parent.right, value);

		return parent;

	}

	public static void inOrderTraversal(BTNode node) {
		if (node != null) {
			// traverse the left side of tree
			inOrderTraversal(node.left);
			// print the node
			System.out.println(node.value);
			// traverse the right side of tree
			inOrderTraversal(node.right);
		}
	}

	public static BTNode delete(int value, BTNode parent) {

		if (parent == null)
			return parent;

		if (value < parent.value)
			parent.left = delete(value, parent.left);
		else if (value > parent.value)
			parent.right = delete(value, parent.right);
		else if (parent.left != null && parent.right != null) {
			parent.value = findMinimum(parent.right).value;
			parent.right = delete(parent.value, parent.right);
		} else
			parent = (parent.right != null) ? parent.right : parent.left;

		return parent;
	}

	public static void clearNode(BTNode node) {
		node.left = node.right = null;
		node = null;

	}

	public static void levelOrderTraversal(BTNode node) {
		Queue<BTNode> q = new LinkedList<BTNode>();
		int currentLevel = 1, nextLevel = 0;
		q.add(node);
		while (!q.isEmpty()) {
			BTNode temp = (BTNode) q.remove();
			currentLevel--;
			if (temp != null) {
				System.out.print(temp.value + "\t");
				if (temp.left != null) {
					q.add(temp.left);
					nextLevel++;
				}
				if (temp.right != null) {
					q.add(temp.right);
					nextLevel++;
				}

			}

			if (currentLevel == 0) {
				System.out.println("\n");
				currentLevel = nextLevel;
				nextLevel = 0;
			}

		}

	}

	public static int findDepthIteratively(BTNode node) {
		if (node == null)
			return 0;
		int height = 0;
		Queue<BTNode> q = new LinkedList<BTNode>();
		int currentLevel = 1, nextLevel = 0;
		q.add(node);
		while (!q.isEmpty()) {
			BTNode temp = (BTNode) q.remove();
			currentLevel--;
			if (temp != null) {
				if (temp.left != null) {
					q.add(temp.left);
					nextLevel++;
				}
				if (temp.right != null) {
					q.add(temp.right);
					nextLevel++;
				}

			}

			if (currentLevel == 0) {
				currentLevel = nextLevel;
				nextLevel = 0;
				height++;
			}

		}
		return height;
	}

	public static BTNode prune(BTNode node, int a, int b) {

		if (node == null)
			return null;

		node.left = prune(node.left, a, b);
		node.right = prune(node.right, a, b);

		if (node.value <= a) {
			node = node.right;

		} else if (node.value >= b) {
			node = node.left;

		}

		return node;

	}

	public static void bst2LL(BTNode node) {
		if (node == null)
			return;

		bst2LL(node.left);
		stack.push(node.value);
		bst2LL(node.right);
	}

	public static void convertTree2LL(BTNode root) {
		stack = new Stack<>();

		bst2LL(root);

		System.out.println("Stack size: " + stack.size());

		int top = stack.pop();
		LinkedListNode node = new LinkedListNode(top, null);
		while (!stack.isEmpty()) {
			node = new LinkedListNode(stack.pop(), node);
		}

		while (node != null) {

			System.out.println(node.value);
			node = node.next;
		}
	}

	public static boolean checkLeafLevels(BTNode node, int currentLevel) {
		if (node == null)
			return true;

		if (node.right == null && node.left == null) {
			if (leafLevel == -1)
				leafLevel = currentLevel;
			else if (leafLevel != currentLevel)
				return false;
			else
				return true;
		} else {
			return checkLeafLevels(node.left, currentLevel + 1) && checkLeafLevels(node.right, currentLevel + 1);
		}
		return true;
	}

	public static int findDepth(BTNode node) {
		if (node == null)
			return 0;

		if (node.left == null && node.right == null)
			return 1;

		else
			return 1 + Math.max(findDepth(node.left), findDepth(node.right));

	}

	public static BTNode createBST(int[] a, int low, int high) {
		if (low > high) {
			return null;
		}

		int mid = low + (high - low) / 2;

		BTNode root = new BTNode(a[mid], null, null);
		root.left = createBST(a, low, mid - 1);
		root.right = createBST(a, mid + 1, high);

		return root;
	}

	public static void printPathsAtLeaves(BTNode n, int sum, int[] a, int level) {

		if (n == null)
			return;

		a[level] = n.value;
		int t = 0;
		if (n.left == null && n.right == null) {
			for (int i = level; i >= 0; i--) {
				t += a[i];

				if (t == sum)
					print(a, i, level);
			}
		}

		printPathsAtLeaves(n.left, sum, a, level + 1);
		printPathsAtLeaves(n.right, sum, a, level + 1);

	}

	public static void printPaths(BTNode n, int sum, int[] a, int level) {

		if (n == null)
			return;

		a[level] = n.value;
		int t = 0;
		for (int i = level; i >= 0; i--) {
			t += a[i];

			if (t == sum)
				print(a, i, level);
		}

		printPaths(n.left, sum, a, level + 1);
		printPaths(n.right, sum, a, level + 1);

	}

	public static void print(int[] a, int start, int end) {
		for (int i = start; i <= end; i++) {
			System.out.println(a[i]);
		}

		System.out.println("\n");
	}

}
