package my.interview.samples;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Graph {

	public Map<Node,Set<Node>> adjacencyList;

	
	public Map<Node, Set<Node>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Map<Node, Set<Node>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public int edges;

	public Graph()
	{
		this.adjacencyList = (Map)new HashMap<Node,TreeSet<Node>>();
		this.edges = 0;
	}

	public void addEdge(Node a,Node b)
	{

		if(!adjacencyList.containsKey(a))
		{	
			Set<Node> nodesList = new TreeSet<Node>();
			adjacencyList.put(a, nodesList);
		}

		if(!adjacencyList.containsKey(b))
		{
			Set<Node> nodesList = new TreeSet<Node>();
			adjacencyList.put(b, nodesList);
		}

		if(!hasEdge(a, b))
		{
			adjacencyList.get(a).add(b);
			edges++;
		}
	}

	public void removeEdge(Node a,Node b)
	{
		if(a == null || b == null) throw new IllegalArgumentException("Atleast one node does'nt exist in the graph");

		if(!adjacencyList.containsKey(a) || !adjacencyList.containsKey(b))
		{
			throw new IllegalArgumentException("No edge exists between these nodes");
		}else
		{
			if(adjacencyList.get(a).contains(b))
			{
				adjacencyList.get(a).remove(b);
				edges--;
			}else
			{
				throw new IllegalArgumentException("No edge exists between these nodes");
			}
		}
	}

	public boolean hasVertex(Node n)
	{
		return adjacencyList.containsKey(n);
	}

	public boolean hasEdge(Node a,Node b)
	{
		if(a == null|| b == null) return true;

		return (adjacencyList.containsKey(a) && adjacencyList.get(a).contains(b));
	}

	public Set<Node> getVertices()
	{
		return adjacencyList.keySet();
	}

	public Set<Node> adjacentVertices(Node n)
	{
		if(adjacencyList.containsKey(n))
			return adjacencyList.get(n);
		else
			return new TreeSet<Node>();
	}

	public Node getUnvisitedNode(Node n)
	{
		for(Node neighbor: adjacentVertices(n))
		{
			if(!neighbor.isVisited())
			{
				return neighbor;
			}
			
		}
		return null;
	}
	
	public void clearNodes()
	{
		for(Node n:adjacencyList.keySet())
		{
			n.setVisited(false);
		}
	}
}
