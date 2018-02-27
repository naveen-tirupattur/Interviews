package my.interviews2017.trees;

public class LockNode {

	public static void main(String[] args) {
		
		

	}
	
	// Check the isLocked flag
	public boolean isLocked(Node n) {
		return n.isLocked;
	}
	

	public boolean lock(Node n) {
		if (n.isLocked || n.numChildLocks > 0) return false; // If any of it's children are locked OR the node itself is already locked
		
		Node parent = n.parent; // Check if any of it's ancestors are locked
		while(parent != null) {
			if (parent.isLocked) return false;
			parent = parent.parent;
		}
		
		n.isLocked = true; // Set the lock
		parent = n.parent;
		while(parent != null) { // Update the ancestors lock counts
			parent.numChildLocks++;
			parent = parent.parent;
		}
		return true;
	}
	
	
	public boolean unLock(Node n) {
		if (n.isLocked) {
			n.isLocked = false; // Unlock the node
			Node parent = n.parent; // Decrement the ancestors lock counts
			while (parent != null) {
				parent.numChildLocks--;
				parent = parent.parent;
			}
		}
		return true;
	}
}
