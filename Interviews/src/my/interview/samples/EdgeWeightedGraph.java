package my.interview.samples;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class EdgeWeightedGraph {

	public Map<Node,Set<Edge>> edgeAdjacencyList;
	public int vertices;
	public int edges;

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public Map<Node, Set<Edge>> getEdgeAdjacencyList() {
		return edgeAdjacencyList;
	}


	public EdgeWeightedGraph(List<Node> nodes)
	{
		this.edgeAdjacencyList = (Map)new HashMap<Node,TreeSet<Edge>>();
		this.edges = 0;
		this.vertices = 0;
		for(Node n: nodes)
		{
			TreeSet<Edge> edgesSet = new TreeSet<>();
			edgeAdjacencyList.put(n, edgesSet);
			vertices++;
		}
	}

	public void addEdge(Edge e)
	{
		edgeAdjacencyList.get(e.getFrom()).add(e);
		edges++;
	}

	public Set<Node> getNodes()
	{
		return edgeAdjacencyList.keySet();
	}

	public Set<Edge> getEdges(Node src)
	{
		return edgeAdjacencyList.get(src);
	}

	public boolean hasVertex(Node n)
	{
		return edgeAdjacencyList.containsKey(n);
	}

	public boolean hasEdge(Edge e)
	{
		return edgeAdjacencyList.get(e.getFrom()).contains(e);
	}

	public Set<Node> getVertices()
	{
		return edgeAdjacencyList.keySet();
	}

	public Node getUnvisitedNode(Node n)
	{

		return null;
	}

	public void clearNodes()
	{
		for(Node n:edgeAdjacencyList.keySet())
		{
			n.setVisited(false);
		}
	}

	public String toString()
	{
		for(Node n:edgeAdjacencyList.keySet())
		{
			return "Node: "+n.data+" "+edgeAdjacencyList.get(n);
		}
		return null;
	}




}
