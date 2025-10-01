package org.interviews.airbnb.interviewdb;

public class Droplets {

  public static void main(String[] args) {
    // --- Part 1: Problem Definition ---
    int[] heights = {5, 4, 3, 2, 1, 3, 4, 0, 3, 4};
    printTerrain(heights);
    int waterVolume = 8;
    int dropColumnIndex = 1;

    // --- Part 2: Simulation ---
    int[] water = dropWater(heights, waterVolume, dropColumnIndex);

    // --- Part 3: Visualization ---
    printTerrain(water);
  }

  public static int[] dropWater(int[] heights, int waterVolume, int dropColumnIndex) {
    int n = heights.length;
    if (n == 0 || waterVolume == 0) return heights;
    for (int drop = 0; drop < waterVolume; drop++) {
      int pos = dropColumnIndex;
      for (int i = dropColumnIndex - 1; i >= 0; i--) {
        if (heights[i] > heights[pos]) break;
        if (heights[i] < heights[pos]) pos = i;
      }
      if (pos != dropColumnIndex) {
        heights[pos]++;
        continue;
      }

      pos = dropColumnIndex;
      for (int i = dropColumnIndex + 1; i < n; i++) {
        if (heights[i] > heights[pos]) break;
        if (heights[i] < heights[pos]) pos = i;
      }

      heights[pos]++;
    }
    return heights;
  }

  public static void printTerrain(int[] water) {
    System.out.println();
    int max = -1;
    for (int i = 0; i < water.length; i++) {
      max = Math.max(max, water[i]);
    }

    for (int i = max; i >= 1; i--) {
      for (int j = 0; j < water.length; j++) {
        if (water[j] >= i) {
          System.out.print("X");
        } else System.out.print(" ");
      }
      System.out.println();
    }
  }

  public static int trapWater(int[] terrain) {
    int[] leftMax = new int[terrain.length];
    int[] rightMax = new int[terrain.length];
    int maxSoFar = terrain[0];
    leftMax[0] = maxSoFar;
    for (int i = 1; i < terrain.length; i++) {
      maxSoFar = Math.max(maxSoFar, terrain[i]);
      leftMax[i] = maxSoFar;
    }

    maxSoFar = terrain[terrain.length - 1];
    rightMax[terrain.length - 1] = maxSoFar;
    for (int i = terrain.length - 2; i >= 0; i--) {
      maxSoFar = Math.max(maxSoFar, terrain[i]);
      rightMax[i] = maxSoFar;
    }

    int totalWater = 0;
    for (int i = 0; i < terrain.length; i++) {
      totalWater += Math.max(0, Math.min(rightMax[i], leftMax[i]) - terrain[i]);
    }
    return totalWater;
  }

}
