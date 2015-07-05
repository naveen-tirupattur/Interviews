/**
 * 
 */
package my.interview.samples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import my.interview.samples.Edge;

/**
 * @author naveenit7sep
 *
 */
public class GraphProblems {


	public static class UndirectedGraphNode
	{
		int label;
		List<UndirectedGraphNode> neighbors;
		boolean visited;
		public UndirectedGraphNode(int l)
		{
			this.label = l;
			this.visited = false;
			this.neighbors = new ArrayList<UndirectedGraphNode>();
		}

		public UndirectedGraphNode getUnvisitedChild()
		{
			for(UndirectedGraphNode node: neighbors)
			{
				if(!node.visited) return node;
			}

			return null;
		}

		public void add(UndirectedGraphNode node)
		{
			this.neighbors.add(node);
		}

		public void visited(boolean b) {
			// TODO Auto-generated method stub
			this.visited = b;

		}

	}

	public static UndirectedGraphNode clone(UndirectedGraphNode root)
	{
		if(root==null) return null;

		root.visited(true);
		UndirectedGraphNode newRoot = new UndirectedGraphNode(root.label);

		for(UndirectedGraphNode node: root.neighbors)
		{
			if(!node.visited)
			{
				newRoot.add(clone(node));
			}
			else
			{

				newRoot.add(node);
			}

		}
		return newRoot;
	}

	public static UndirectedGraphNode cloneBFS(UndirectedGraphNode root,HashMap<Integer, UndirectedGraphNode> map)
	{
		if(root == null) return null;

		Queue<UndirectedGraphNode> queue = new LinkedList<>();
		map = new HashMap<>();

		queue.add(root);
		root.visited(true);
		UndirectedGraphNode newRoot= new UndirectedGraphNode(root.label);
		map.put(Integer.valueOf(newRoot.label), newRoot);

		while(!queue.isEmpty())
		{
			UndirectedGraphNode newNode = queue.remove() ;

			UndirectedGraphNode tnode = map.get(newNode.label);
			for(UndirectedGraphNode node: newNode.neighbors)
			{
				if(!node.visited)
				{
					queue.add(node);
					node.visited(true);
					UndirectedGraphNode x = new UndirectedGraphNode(node.label);
					map.put(Integer.valueOf(node.label), x);
					tnode.add(x);
				}else
				{
					tnode.add(node);
				}
			}
		}
		return newRoot;
	}

	public static UndirectedGraphNode createGraph()
	{
		UndirectedGraphNode root = new UndirectedGraphNode(1);
		UndirectedGraphNode r2 = new UndirectedGraphNode(2);
		UndirectedGraphNode r3 = new UndirectedGraphNode(3);
		UndirectedGraphNode r4 = new UndirectedGraphNode(4);
		UndirectedGraphNode r5 = new UndirectedGraphNode(5);
		UndirectedGraphNode r6 = new UndirectedGraphNode(6);

		root.add(r4);
		root.add(r3);
		root.add(r2);
		r4.add(r5);
		r5.add(r3);
		r2.add(r6);

		return root;

	}	


	public static class DSP
	{
		Map<Node,Set<Node>> shortestPath;
		Map<Node,Double> distance;
		Double dist;
		
		public Map<Node, Set<Node>> getShortestPath() {
			return shortestPath;
		}

		public Map<Node, Double> getDistance() {
			return distance;
		}		
		
		public DSP(EdgeWeightedGraph e,Node src)
		{
			
			this.distance = new HashMap<>();
			this.shortestPath = new HashMap<>();
			shortestPath.put(src, new TreeSet<Node>());
			
			for(Node n: e.getVertices())
			{
				distance.put(n, Double.POSITIVE_INFINITY);
				shortestPath.put(n, new TreeSet<Node>());
			}
			
			distance.put(src, Double.valueOf(0));
			Node v = src;
			while(!v.isVisited)
			{
				v.setVisited(true);
				for(Edge edge: e.getEdges(v))
				{
					Node n = edge.getTo();
					double weight = edge.getWeight();
					if(distance.get(n) > distance.get(v) + weight)
					{
						distance.put(n,distance.get(v)+weight);
						//shortestPath.get(n).add(v);
						
					}
				}
				
				dist = Double.POSITIVE_INFINITY;
				for(Node n:e.getVertices())
				{
					if(!n.isVisited && dist > distance.get(n))
					{
						dist = distance.get(n);
						v = n;
					}
				}
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		//		UndirectedGraphNode root = createGraph();
		//		HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
		//		UndirectedGraphNode newRoot = cloneBFS(root,map);
		//
		//		System.out.println(newRoot.label);

		

		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		
		List<Node> nodesList = new ArrayList<>();
		nodesList.add(a);
		nodesList.add(b);
		nodesList.add(c);
		nodesList.add(d);
		nodesList.add(e);
		nodesList.add(f);
		

		EdgeWeightedGraph g = new EdgeWeightedGraph(nodesList);

		Edge e1 = new Edge(a, b, 2);
		Edge e2 = new Edge(a, d, 1);
		Edge e3 = new Edge(a, c, 5);
		Edge e4 = new Edge(c, e, 2);
		Edge e5 = new Edge(b, c, 1);
		Edge e6 = new Edge(b, e, 3);
		Edge e7 = new Edge(d, f, 2);
		Edge e8 = new Edge(f, e, 1);
		Edge e9 = new Edge(d, b, 1);
		Edge e10 = new Edge(b, f, 1);
		
		g.addEdge(e1);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e4);
		g.addEdge(e5);
		g.addEdge(e6);
		g.addEdge(e7);
		g.addEdge(e8);
		g.addEdge(e9);
		g.addEdge(e10);
		
		
		DSP dsp = new DSP(g, a);
		
		System.out.println(dsp.getDistance().get(f));
		//System.out.println(dsp.getShortestPath().get(f));
		
//		g.addEdge(a,b);
//		g.addEdge(a,c);
//		g.addEdge(a,d);
//		g.addEdge(b,c);
//		g.addEdge(c,f);
//		g.addEdge(b,e);
//		g.addEdge(e,f);

		//		int[] parent = new int[256];
		//		modifiedBFS(a,g,'c',parent);
		//		shortestPath('a'-'0', 'f'-'0', parent);
		//

		//		Graph gp = new Graph();
		//
		//		Node a = new Node("+");
		//		Node b = new Node("+");
		//		Node c = new Node("/");
		//		Node d = new Node("*");
		//		Node e = new Node("2");
		//		Node f = new Node("5");
		//		Node g = new Node("3");
		//		Node h = new Node("4");
		//		
		//
		//
		//		gp.addEdge(a,b);
		//		gp.addEdge(a,c);
		//		gp.addEdge(b,e);
		//		gp.addEdge(b,d);
		//		gp.addEdge(c,d);
		//		gp.addEdge(c,f);
		//		gp.addEdge(d,g);
		//		gp.addEdge(d,h);

		//		DFS(a,gp,null);
		//BFS(a,g,null);

		//g.clearNodes();
	}

	public static void shortestPath(int start,int end,int[] parent)
	{
		if((start == end) || (end == -1))
		{
			char c = (char)(start+'0');
			System.out.println(c);
		}

		else
		{
			shortestPath(start, parent[end], parent);
			char c= (char)(end+'0');
			System.out.println(c);

		}
	}

	public static void print(Graph g)
	{
		System.out.println("Number of Edges: "+g.getEdges());

		System.out.println("Number of Vertices: "+g.getAdjacencyList().size());

		for(Node n: g.getVertices())
		{
			System.out.println("Node: "+n.getData());
			System.out.println(g.getAdjacencyList().get(n));

		}
	}

	public static void DFS(Node root,Graph g,Node find)
	{
		Stack<Node> dfsStack = new Stack<Node>();
		dfsStack.push(root);
		root.setVisited(true);

		while(!dfsStack.isEmpty())
		{
			Node n = dfsStack.peek();
			System.out.println(n.data);
			Node v = g.getUnvisitedNode(n);

			if(v != null)
			{
				if(find != null && v.equals(find)){
					System.out.println("Found Node: "+find.data);
					break;
				}
				v.setVisited(true);
				dfsStack.push(v);
			}else
			{
				dfsStack.pop();
			}
		}

	}

	public static void BFS(Node root,Graph g,Node find)
	{
		Queue<Node> bfsQueue = new LinkedList<Node>();

		bfsQueue.add(root);
		root.setVisited(true);

		while(!bfsQueue.isEmpty())
		{
			Node n = bfsQueue.remove();
			System.out.println(n.data);

			Node v;
			while((v = g.getUnvisitedNode(n))!= null)
			{
				if(find != null && v.equals(find)){
					System.out.println("Found Node: "+find.data);
					break;
				}
				v.setVisited(true);
				bfsQueue.add(v);
			}
		}
	}

	public static void modifiedBFS(Node root,Graph g,char c1,int[] parent)
	{
		Queue<Node> bfsQueue = new LinkedList<Node>();


		for(int i=0;i<128;i++)
		{
			parent[i] = -1;
		}

		bfsQueue.add(root);
		root.setVisited(true);

		while(!bfsQueue.isEmpty())
		{
			Node n = bfsQueue.remove();
			System.out.println(n);

			Node v;
			while((v = g.getUnvisitedNode(n))!= null)
			{
				v.setVisited(true);
				bfsQueue.add(v);
				char c = v.data.charAt(0);
				parent[c-'0'] = n.data.charAt(0)-'0';
			}
		}
		char c2 = (char)(parent[c1-'0']+'0');
		System.out.println("Parent of: "+c1+" "+c2);
	}


}
