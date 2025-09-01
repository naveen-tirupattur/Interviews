package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;


class RouterNode {
  String value;
  Map<String, RouterNode> children;
  RouterNode wildCharNode;

  public RouterNode() {
    this.children = new HashMap<>();
    this.wildCharNode = null;
  }
}

public class Router {
  private final Map<String, String> routes;
  RouterNode root;

  public Router() {
    this.routes = new HashMap<>();
    this.root = new RouterNode();
  }

  public void addRoute(String path, String result) {
    String[] paths = path.split("/");
    RouterNode node = root;
    for (int i = 1; i < paths.length; i++) {
      if (paths[i].equals("*")) {
        if (node.wildCharNode == null) {
          node.wildCharNode = new RouterNode();
        }
        node = node.wildCharNode;
      } else {
        node = node.children.computeIfAbsent(paths[i], k -> new RouterNode());
      }
    }

    node.value = result;
    routes.put(path, result);
  }

  public String callRoute(String path) {
    if (routes.containsKey(path)) return routes.get(path);
    RouterNode node = root;
    String[] paths = path.split("/");
    for (int i = 1; i < paths.length; i++) {
      if (node.children.containsKey(paths[i])) {
        node = node.children.get(paths[i]);
      } else if (node.wildCharNode != null) {
        node = node.wildCharNode;
      } else {
        return null;
      }
    }
    return node.value;
  }

  public static void main(String[] args) {
    Router router = new Router();

    // Add routes
    router.addRoute("/foo", "foo");
    router.addRoute("/bar/a", "bar");
    router.addRoute("/bar/*/baz", "bar_wildcard");
    router.addRoute("/foo/baz", "foo_specific");

    // Test calls
    System.out.println(router.callRoute("/foo")); // Output: foo
    System.out.println(router.callRoute("/bar/a/baz")); // Output: bar_wildcard
    System.out.println(router.callRoute("/bar/b/baz")); // Output: bar_wildcard
    System.out.println(router.callRoute("/foo/baz")); // Output: foo_specific (prioritizes specific)
    System.out.println(router.callRoute("/nonexistent/path")); // Output: null


  }
}
