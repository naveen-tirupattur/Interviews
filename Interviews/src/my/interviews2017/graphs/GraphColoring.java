package my.interviews2017.graphs;

import java.util.ArrayList;
import java.util.List;

import my.interviews2017.graphs.Vertex.Color;

public class GraphColoring {

	public static void main(String[] args) {
		
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		
		List<Vertex> v1Edges = new ArrayList<Vertex>();
		v1Edges.add(v2);
		v1Edges.add(v5);
		v1.edges = v1Edges;
		
		List<Vertex> v2Edges = new ArrayList<Vertex>();
		v2Edges.add(v3);
		v2.edges = v2Edges;
		
		List<Vertex> v3Edges = new ArrayList<Vertex>();
		v3Edges.add(v4);
		v3.edges = v3Edges;
		
		List<Vertex> v4Edges = new ArrayList<Vertex>();
		v4Edges.add(v5);
		v4.edges = v4Edges;
		
		List<Vertex> v5Edges = new ArrayList<Vertex>();
		v5Edges.add(v1);
		v5.edges = v5Edges;
		
		
		List<Vertex> graph = new ArrayList<Vertex>();
		graph.add(v1);
		graph.add(v2);
		graph.add(v3);
		graph.add(v4);
		graph.add(v5);
		
		
		System.out.println(color(graph));
		

	}
	
	public static boolean color(List<Vertex> graph) {
		for (Vertex v: graph) {
			if (!v.isVisited) { // For each node if it is not visited
				v.isVisited = true; // Mark as visited
				if (!isColorPossible(v, true)) return false; // Check if coloring is possible
			}
		}
		return true;
	}
	
	public static boolean isColorPossible(Vertex v, boolean isBlack) {
		if (isBlack) v.color = Color.B; // Update the color based on flag
		else v.color = Color.W;
		v.isVisited = true; // Mark the node as visited
		for (Vertex e:v.edges) {
			if (e.color == v.color) return false; // Check if any of the neighbors have the same color
			if (!e.isVisited) { // If neighbor is not visited try recursively on them
				if (!isColorPossible(e, !isBlack)) return false;
			}
		}
		return true;
	}

}
