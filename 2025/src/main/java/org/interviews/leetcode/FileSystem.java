package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;

class FileSystemNode {
  int val;
  Map<String, FileSystemNode> children;

  public FileSystemNode(int val) {
    this.val = val;
    this.children = new HashMap<>();
  }

  @Override
  public String toString() {
    return String.valueOf(this.val);
  }
}

public class FileSystem {

  FileSystemNode rootNode;

  public FileSystem() {
    this.rootNode = new FileSystemNode(-1);
  }

  public boolean createPath(String path, int val) {
    String[] components = path.split("/");
    return createHelper(rootNode, components, 1, val);
  }

  public boolean createHelper(FileSystemNode node, String[] components, int index, int val) {
    if (index == components.length) return false;
    if (node.children.containsKey(components[index])) {
      return createHelper(node.children.get(components[index]), components, index + 1, val);
    } else {
      if (index == components.length - 1) {
        FileSystemNode fileSystemNode = new FileSystemNode(val);
        node.children.put(components[index], fileSystemNode);
        return true;
      }
    }
    return false;
  }

  public int get(String path) {
    String[] components = path.split("/");
    FileSystemNode node = rootNode;
    for (int i = 1; i < components.length; i++) {
      String component = components[i];
      if (!node.children.containsKey(component)) {
        return -1;
      }
      node = node.children.get(component);
    }
    return node.val;
  }

  public static void main(String[] args) {
    FileSystem fs = new FileSystem();
    System.out.println(fs.createPath("/foo", 1));
    System.out.println(fs.createPath("/foo/bar", 1));
    System.out.println(fs.createPath("/foo/bar", 1));
    System.out.println(fs.createPath("/bar/foo", 2));
    System.out.println(fs.get("/foo"));
    System.out.println(fs.get("/bar"));
    System.out.println(fs.get("/foo/bar"));
  }
}
