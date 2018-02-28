package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
	};

	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null) return null;
		Map<UndirectedGraphNode, UndirectedGraphNode> vertexMap = new HashMap<>();
		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		vertexMap.put(node, new UndirectedGraphNode(node.label)); // Add current node to map and queue
		queue.add(node);
		while (!queue.isEmpty()) { // Do a BFS
			UndirectedGraphNode x = queue.remove();
			for (UndirectedGraphNode edge: x.neighbors) { // For all neighbors
				if (!vertexMap.containsKey(edge)) { // Check if neighbor is in map
					vertexMap.put(edge, new UndirectedGraphNode(edge.label)); // else add it to map and add it to queue
					queue.add(edge);
				}
				vertexMap.get(x).neighbors.add(vertexMap.get(edge)); // Add neighbor to parent's clone
			}
		}
		return vertexMap.get(node);
	}

}
