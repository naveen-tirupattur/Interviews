package org.interviews.airbnb;

import java.util.ArrayList;
import java.util.List;

class UnionFind {
  private int[] parent;
  private int[] rank;
  private int count;

  public UnionFind(int n) {
    // Initializes n sets, each with one element
    parent = new int[n];
    rank = new int[n];
    count = n;
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      rank[i] = 0;
    }
  }

  public int find(int i) {
    if (parent[i] != i)
      parent[i] = find(parent[i]);
    return parent[i];
  }

  public void union(int i, int j) {
    int rootI = find(i);
    int rootJ = find(j);
    if (rank[rootI] < rank[rootJ]) {
      parent[i] = rootJ;
    } else if (rank[rootI] > rank[rootJ]) {
      parent[j] = rootI;
    } else {
      parent[i] = rootJ;
      rank[rootI]++;
    }
    count--;
  }

  public int getCount() {
    // Returns the number of disjoint sets
    return count;
  }
}

class Rectangle {
  int x1, y1, x2, y2;

  public Rectangle(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
}

public class IntersectingRectangles {
  public int findNumberOfIntersectingRectangles(List<Rectangle> rectangles) {
    if (rectangles == null || rectangles.isEmpty()) {
      return 0;
    }

    int n = rectangles.size();
    UnionFind uf = new UnionFind(n);
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (doIntersect(rectangles.get(i), rectangles.get(j))) {
          uf.union(i, j);
        }
      }
    }
    return uf.getCount();
  }


  private boolean doIntersect(Rectangle r1, Rectangle r2) {
    boolean xIntersect = r1.x2 < r2.x1 || r2.x2 < r1.x1;
    boolean yIntersect = r1.y2 < r2.y1 || r2.y2 < r1.y1;

    return !(xIntersect || yIntersect);
  }

  public static void main(String[] args) {
    IntersectingRectangles solver = new IntersectingRectangles();
    List<Rectangle> rectangles = new ArrayList<>();
    // Example rectangles (x1, y1, x2, y2)
    rectangles.add(new Rectangle(0, 0, 5, 5));   // R1
    rectangles.add(new Rectangle(4, 4, 8, 8));   // R2 (intersects with R1)
    rectangles.add(new Rectangle(10, 10, 12, 12)); // R3
    rectangles.add(new Rectangle(11, 11, 15, 15)); // R4 (intersects with R3)
    rectangles.add(new Rectangle(6, 6, 9, 9));   // R5 (intersects with R2)

    // The expected result should be 2 components: {R1, R2, R5} and {R3, R4}
    int components = solver.findNumberOfIntersectingRectangles(rectangles);
    System.out.println("Number of intersecting components: " + components);
  }
}
