package org.interviews.airbnb;

public class FindingOcean {

  /**
   * Given: An array of strings where L indicates land and W indicates water,
   * and a coordinate marking a starting point in the middle of the ocean.
   * <p>
   * Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
   * An ocean coordinate is defined to be the initial coordinate if a W, and
   * any coordinate directly adjacent to any other ocean coordinate.
   *
   * @param map    The map as an array of strings.
   * @param row    The starting row.
   * @param column The starting column.
   */
  public void findOcean(String[] map, int row, int column) {
    // Since Strings are immutable in Java, we need a mutable representation of the map.
    // A 2D char array is a good choice.
    char[][] mutableMap = new char[map.length][];
    for (int i = 0; i < map.length; i++) {
      mutableMap[i] = map[i].toCharArray();
    }

    // Call the recursive helper function to perform the flood fill.
    // The helper will need access to the mutable map and the starting coordinates.
    findOceanHelper(mutableMap, row, column);

    // After the flood fill is complete, convert the mutable map back to a String array
    // to match the original input type.
    for (int i = 0; i < map.length; i++) {
      map[i] = new String(mutableMap[i]);
    }
  }

  /**
   * A recursive helper function to perform the flood fill.
   *
   * @param map    The mutable map (2D char array).
   * @param row    The current row.
   * @param column The current column.
   */
  private void findOceanHelper(char[][] map, int row, int column) {
    // TODO: Implement the base cases and the recursive logic here.
    // HINT:
    // 1. Check for out-of-bounds coordinates.
    // 2. Check if the current cell is a 'W' (water).
    // 3. If it's a 'W', change it to 'O' (ocean).
    // 4. Recursively call the helper for all four adjacent cells (up, down, left, right).
    if (row < 0 || row >= map.length || column < 0 || column >= map[0].length || map[row][column] != 'W') return;
    map[row][column] = 'O';
    findOceanHelper(map, row, column + 1);
    findOceanHelper(map, row, column - 1);
    findOceanHelper(map, row + 1, column);
    findOceanHelper(map, row - 1, column);
  }

  public static void main(String[] args) {
    // Test case 1: The example from the problem description
    System.out.println("--- Test Case 1 ---");
    String[] map1 = new String[]{
      "WWWLLLW",
      "WWLLLWW",
      "WLLLLWW"
    };
    System.out.println("Original Map:");
    printMap(map1);

    FindingOcean solution = new FindingOcean();
    solution.findOcean(map1, 0, 1);

    System.out.println("Map after findOcean(map, 0, 1):");
    printMap(map1);

    System.out.println(); // Add a blank line for better separation

    // Test case 2: Starting in the middle of a larger water body
    System.out.println("--- Test Case 2 ---");
    String[] map2 = new String[]{
      "WWWWL",
      "WWLLW",
      "WLLLW",
      "LWWWW"
    };
    System.out.println("Original Map:");
    printMap(map2);

    solution.findOcean(map2, 1, 1);

    System.out.println("Map after findOcean(map, 1, 1):");
    printMap(map2);

    System.out.println(); // Add a blank line

    // Test case 3: Starting on land
    System.out.println("--- Test Case 3 ---");
    String[] map3 = new String[]{
      "WLLWW",
      "LWLWL",
      "WLLWW"
    };
    System.out.println("Original Map:");
    printMap(map3);

    solution.findOcean(map3, 1, 1); // Starting at an 'L'

    System.out.println("Map after findOcean(map, 1, 1):");
    printMap(map3);
  }

  /**
   * Helper method to print the map.
   *
   * @param map The map to print.
   */
  public static void printMap(String[] map) {
    for (String row : map) {
      System.out.println(row);
    }
  }
}
