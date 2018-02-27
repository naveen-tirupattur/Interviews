package my.interviews2017.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TeamPhoto2 {

	public static void main(String[] args) {
		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		
		List<Vertex> v0Edges = new ArrayList<Vertex>();
		v0Edges.add(v1);
		v0Edges.add(v4);
		v1.edges = v0Edges;
		
		List<Vertex> v3Edges = new ArrayList<Vertex>();
		v3Edges.add(v0);
		v3.edges = v3Edges;
		
		List<Vertex> v2Edges = new ArrayList<Vertex>();
		v2Edges.add(v0);
		v2Edges.add(v4);
		v2.edges = v2Edges;
		

		List<Vertex> graph = new ArrayList<Vertex>();
		graph.add(v1);
		graph.add(v2);
		graph.add(v3);
		graph.add(v4);
		graph.add(v0);
		
		System.out.println(findMaxTeams(graph));
	}
	
	public static int findMaxTeams(List<Vertex> graph) {
		Stack<Vertex> tStack = new Stack<Vertex>(); 
		for (Vertex v: graph) { // For each vertex in graph do topological sort
			if (!v.isVisited) {
				v.isVisited = true;
				topoSort(v,tStack);
			}
		}
		return findLongestPath(tStack);
		
	}
	
	public static void topoSort(Vertex v, Stack<Vertex> tStack) {
		v.isVisited = true; // Mark node as visited
		for (Vertex x:v.edges) { // Go down it's unvisited neighbors
			if (!x.isVisited) topoSort(x, tStack);
		}
		tStack.push(v); // Push the vertex on top of stack
	}
	
	// The idea is to find longest path from each node down it's chain of topologically sorted vertices
	public static int findLongestPath(Stack<Vertex> tStack) {
		int max = 0;
		while (!tStack.isEmpty()) {
			Vertex v = tStack.pop();
			max = Math.max(max, v.maxDistance); // Check if the distance to reach this node is maximum
			for (Vertex x: v.edges) { // For each children
				x.maxDistance = Math.max(x.maxDistance, v.maxDistance+1); // Update the distance to reach to it to itself or 1 + distance to reach it's parent
			}
		}
		return max;
	}

}
