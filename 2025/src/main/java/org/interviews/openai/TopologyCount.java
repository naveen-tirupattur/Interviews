package org.interviews.openai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Message {
}

class CountMessage extends Message {
}

class ResponseMessage extends Message {
  private final int count;

  public ResponseMessage(int count) {
    this.count = count;
  }

  public int getCount() {
    return this.count;
  }
}

class NodeTopology {
  private final int id;
  private final List<NodeTopology> children;

  public NodeTopology(int id, List<NodeTopology> children) {
    this.id = id;
    this.children = children;
  }

  @Override
  public String toString() {
    return "NodeTopology{id=" + id + ", children=" + children + "}";
  }
}

class TopologyMessage extends Message {
}

class TopologyResponseMessage extends Message {
  NodeTopology nodeTopology;

  public TopologyResponseMessage(NodeTopology nodeTopology) {
    this.nodeTopology = nodeTopology;
  }

  public NodeTopology getNodeTopology() {
    return this.nodeTopology;
  }
}

class TopologyNode {
  TopologyNode parent;
  List<TopologyNode> childNodes;
  int id;
  Map<Integer, Integer> childCounts = new HashMap<>();
  Map<Integer, NodeTopology> childTopolgies = new HashMap<>();


  public TopologyNode(int id, TopologyNode parent, List<TopologyNode> childNodes) {
    this.id = id;
    this.parent = parent;
    // Always ensure childNodes is non-null
    this.childNodes = (childNodes != null) ? childNodes : List.of();
  }

  public void sendAsyncMessage(int targetId, Message message) {
    TopologyNodeRegistry.get(targetId).receiveMessage(this.id, message);

  }

  public void receiveMessage(int senderId, Message message) {
    if (message instanceof ResponseMessage) {
      childCounts.put(senderId, ((ResponseMessage) message).getCount());

      if (childCounts.size() == childNodes.size()) {
        int totalCount = 1; // count self
        for (Integer count : childCounts.values()) {
          totalCount += count;
        }

        if (this.parent == null) {
          // Root node
          System.out.println("Final total machine count: " + totalCount);
        } else {
          sendAsyncMessage(this.parent.id, new ResponseMessage(totalCount));
        }

        // Reset state
        childCounts.clear();
      }

    } else if (message instanceof CountMessage) {
      if (childNodes.isEmpty()) {
        // Leaf node
        if (this.parent == null) {
          // Root is also a leaf
          System.out.println("Final total machine count: 1");
        } else {
          sendAsyncMessage(this.parent.id, new ResponseMessage(1));
        }
      } else {
        for (TopologyNode child : childNodes) {
          sendAsyncMessage(child.id, new CountMessage());
        }
      }
    } else if (message instanceof TopologyMessage) {
      if (childNodes.isEmpty()) {
        if (this.parent == null) {
          System.out.println("Final topology: " + this.id);
        } else {
          sendAsyncMessage(this.parent.id, new TopologyResponseMessage(new NodeTopology(this.id, List.of())));
        }
      } else {
        for (TopologyNode child : childNodes) {
          sendAsyncMessage(child.id, new TopologyMessage());
        }
      }
    } else if (message instanceof TopologyResponseMessage) {
      childTopolgies.put(senderId, ((TopologyResponseMessage) message).getNodeTopology());
      if (childTopolgies.size() == childNodes.size()) {
        List<NodeTopology> children = new ArrayList<>();
        children.addAll(childTopolgies.values());
        NodeTopology nodeTopology = new NodeTopology(this.id, children);
        if (this.parent == null) {
          System.out.println("Final Tree Topology: " + nodeTopology);
        } else {
          sendAsyncMessage(this.parent.id, new TopologyResponseMessage(nodeTopology));
        }
        childTopolgies.clear();
      }
    }
  }

  // Only the root calls this to start
  public void startCount() {
    receiveMessage(-1, new CountMessage());
  }

  public void startTopology() {
    receiveMessage(-1, new TopologyMessage());
  }
}

class TopologyNodeRegistry {
  private static final Map<Integer, TopologyNode> registry = new HashMap<>();

  public static void register(TopologyNode node) {
    registry.put(node.id, node);
  }

  public static TopologyNode get(int id) {
    return registry.get(id);
  }
}

public class TopologyCount {
  public static void main(String[] args) {
    // Build tree
    TopologyNode leaf1 = new TopologyNode(1, null, null);
    TopologyNode leaf2 = new TopologyNode(2, null, null);
    TopologyNode middle = new TopologyNode(3, null, List.of(leaf1, leaf2));
    TopologyNode root = new TopologyNode(0, null, List.of(middle));

    // Register all nodes
    TopologyNodeRegistry.register(root);
    TopologyNodeRegistry.register(middle);
    TopologyNodeRegistry.register(leaf1);
    TopologyNodeRegistry.register(leaf2);

    // Fix parent references
    middle.parent = root;
    leaf1.parent = middle;
    leaf2.parent = middle;

    // Start count from root
    root.startCount();
    root.startTopology();
  }
}
