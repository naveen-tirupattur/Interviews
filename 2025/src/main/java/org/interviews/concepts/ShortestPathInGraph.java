package org.interviews.concepts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ShortestPathInGraph {

  /*
   * Complete the 'getCost' function below.
   *
   * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
   */

  /*
   * For the weighted graph, <name>:
   *
   * 1. The number of nodes is <name>Nodes.
   * 2. The number of edges is <name>Edges.
   * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
   *
   */

  public static class Node implements Comparable<Node>{
    int id;
    long cost;
    public Node(int id, long cost){
      this.id=id;
      this.cost=cost;
    }

    @Override
    public int compareTo(Node that) {
      return Long.compare(this.cost, that.cost);
    }
  }

  public static void getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
    PriorityQueue<Node> neighbors = new PriorityQueue<>();
    Map<Integer, Long> costMap = new HashMap<>();
    Map<Integer, List<Node>> adjacencyList = new HashMap<>();
    costMap.put(1, 0L);
    for(int i=2;i<=gNodes;i++){
      costMap.put(i, Long.MAX_VALUE);
    }
    for(int i=0;i<gFrom.size();i++){
      int fromNode = gFrom.get(i);
      int toNode = gTo.get(i);
      Node n = new Node(toNode, gWeight.get(i));
      List<Node> nodes = adjacencyList.getOrDefault(fromNode, new ArrayList<Node>());
      nodes.add(n);
      adjacencyList.put(fromNode, nodes);
      Node n1 = new Node(fromNode, gWeight.get(i));
      List<Node> nodes1 = adjacencyList.getOrDefault(toNode, new ArrayList<Node>());
      nodes1.add(n1);
      adjacencyList.put(toNode, nodes1);
    }
    neighbors.add(new Node(1, 0));
    while(!neighbors.isEmpty()) {
      Node current = neighbors.remove();
      if(current.cost > costMap.get(current.id)) continue;
      List<Node> nodes = adjacencyList.get(current.id);
      for(Node v: nodes){
        long cost = current.cost+v.cost;
        if(cost < costMap.get(v.id)) {
          costMap.put(v.id, cost);
          neighbors.add(new Node(v.id, cost));
        }
      }
    }
    System.out.println(costMap);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int gNodes = Integer.parseInt(gNodesEdges[0]);
    int gEdges = Integer.parseInt(gNodesEdges[1]);

    List<Integer> gFrom = new ArrayList<>();
    List<Integer> gTo = new ArrayList<>();
    List<Integer> gWeight = new ArrayList<>();

    for (int i = 0; i < gEdges; i++) {
      String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

      gFrom.add(Integer.parseInt(gFromToWeight[0]));
      gTo.add(Integer.parseInt(gFromToWeight[1]));
      gWeight.add(Integer.parseInt(gFromToWeight[2]));
    }

    getCost(gNodes, gFrom, gTo, gWeight);

    bufferedReader.close();
  }
}

