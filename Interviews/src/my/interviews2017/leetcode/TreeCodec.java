package my.interviews2017.leetcode;

public class TreeCodec {

	public static class TreeNode {
		int val;
		public TreeNode left, right;
		TreeNode(int x) { val = x; }
	}

	public static Integer keyIndex;
	
	// Encodes a tree to a single string.
	public static String serialize(TreeNode root) {
		StringBuffer sb = new StringBuffer();
		serializeUtil(root, sb);
		String traversal = sb.toString();
		return traversal.substring(0, traversal.length()-1);
	}
	
	public static void serializeUtil(TreeNode root, StringBuffer sb) {
		if (root == null) { sb.append("null,"); return;} // Add a null when there is no node
		sb.append(root.val+","); // Append node to SB
		serializeUtil(root.left, sb); // Recursively add children
		serializeUtil(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public static TreeNode deserialize(String data) {
		keyIndex = 0;
		return deserializeUtil(data.split(","));

	}

	public static TreeNode deserializeUtil(String[] nodeArray) {
		String key = nodeArray[keyIndex++]; // Fetch the element at index and update the index
		if (key.equals("null")) return null; // Check if it is a null element
		TreeNode root = new TreeNode(Integer.valueOf(key)); // Create a node with element at that index
		root.left = deserializeUtil(nodeArray); // Recursively create left and right
		root.right = deserializeUtil(nodeArray);
		return root;
	}

	public static void main(String[] args) {
		String traversal = "1,2,null,null,3,4,null,null,5,null,null";
		TreeNode root = deserialize(traversal);
		System.out.println(serialize(root));
	}
}