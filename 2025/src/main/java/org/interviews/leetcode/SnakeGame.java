package org.interviews.leetcode;

import java.util.*;

class Coordinates {
  int row, col; // Using row, col for clarity, consistent with food array

  public Coordinates(int row, int col) {
    this.row = row;
    this.col = col;
  }

  // CRITICAL: Must override equals() and hashCode() for use in Set/Map
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Coordinates that = (Coordinates) o;
    return row == that.row && col == that.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  @Override
  public String toString() {
    return "(" + row + "," + col + ")";
  }
}

public class SnakeGame {
  int[][] foodLocations;
  int foodIndex;         // Index to track which food item is currently active/next to be eaten

  Set<Coordinates> snakeBodyPositions;

  Deque<Coordinates> snakeSegments;

  int height; // Number of rows
  int width;  // Number of columns

  public SnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.foodLocations = food;
    this.foodIndex = 0;

    snakeBodyPositions = new HashSet<>();
    snakeSegments = new LinkedList<>();
    
    Coordinates initialHead = new Coordinates(0, 0);
    snakeSegments.addFirst(initialHead);
    snakeBodyPositions.add(initialHead);
  }

  /**
   * Processes a single move of the snake.
   *
   * @param direction The direction of movement ("U", "D", "L", "R").
   * @return The current score (length - 1) if game continues, -1 if game over.
   */
  public int move(String direction) {
    // 1. Calculate new head position
    Coordinates currentHead = snakeSegments.peekFirst();
    int newHeadRow = currentHead.row;
    int newHeadCol = currentHead.col;

    switch (direction) {
      case "U":
        newHeadRow--;
        break;
      case "D":
        newHeadRow++;
        break;
      case "L":
        newHeadCol--;
        break;
      case "R":
        newHeadCol++;
        break;
      default:
        return -1; // Should not happen with valid input
    }

    Coordinates newHead = new Coordinates(newHeadRow, newHeadCol);

    // 2. Check for Game Over Conditions
    // A. Out of Bounds
    if (newHeadRow < 0 || newHeadRow >= height || newHeadCol < 0 || newHeadCol >= width) {
      return -1; // Game Over
    }

    // B. Collision with self (Important: excluding the tail's *old* position if it moves)
    // If the snake is moving to a spot currently occupied by its body AND that spot is NOT where its tail *will be* after moving.
    // The check `snakeBodyPositions.contains(newHead)` will handle collision with existing body parts.
    // However, if the tail is about to move out of the newHead's spot (because food wasn't eaten),
    // we should temporarily remove the tail's position from the set for accurate collision check.
    // A simpler way: Check collision AFTER potentially removing the tail's old position.

    // Store tail reference before potential removal
    Coordinates tail = snakeSegments.peekLast();
    boolean ateFood = false;

    // 3. Check if newHead lands on active food
    if (foodIndex < foodLocations.length) { // Ensure there's still food in the list
      int foodRow = foodLocations[foodIndex][0];
      int foodCol = foodLocations[foodIndex][1];

      if (newHeadRow == foodRow && newHeadCol == foodCol) {
        ateFood = true;
        foodIndex++; // Move to the next food item for the next time
      }
    }

    // 4. Update Snake's Body and Positions

    // If no food was eaten, the tail moves (meaning its space becomes free)
    if (!ateFood) {
      snakeSegments.removeLast(); // Remove tail from deque
      snakeBodyPositions.remove(tail); // Remove tail's position from set
    }
    // If food WAS eaten, the tail stays, and its position remains in snakeBodyPositions.
    // The snake grows by 1 segment.

    // Now, check for collision with the *remaining* (or extended) body
    // This check *must* happen after `snakeSegments.removeLast()` and `positions.remove(tail)`
    // if `!ateFood` to avoid false collision with the soon-to-be-empty tail spot.
    if (snakeBodyPositions.contains(newHead)) {
      return -1; // Game Over: Collision with body
    }

    // Add the new head to the snake
    snakeSegments.addFirst(newHead); // Add to head of deque
    snakeBodyPositions.add(newHead); // Add to set

    // 5. Return Score (snake length - 1)
    return snakeSegments.size() - 1;
  }

  public static void main(String[] args) {
    System.out.println("--- Test Case 1 (From Problem Description) ---");
    SnakeGame snakeGame1 = new SnakeGame(3, 2, new int[][]{{1, 2}, {0, 1}});

    // Board:
    // [[S], [ ], [ ]]
    // [[ ], [ ], [F]]  // Food at (1,2)

    System.out.println("Move R: " + snakeGame1.move("R") + " (Expected: 0)"); // Head: (0,1)
    // Snake: [(0,1)]
    // Board:
    // [[ ], [S], [ ]]
    // [[ ], [ ], [F]]

    System.out.println("Move D: " + snakeGame1.move("D") + " (Expected: 0)"); // Head: (1,1)
    // Snake: [(1,1)]
    // Board:
    // [[ ], [ ], [ ]]
    // [[ ], [S], [F]]

    System.out.println("Move R: " + snakeGame1.move("R") + " (Expected: 1)"); // Head: (1,2), eats food
    // Snake: [(1,1), (1,2)]
    // Next Food: (0,1)
    // Board:
    // [[ ], [F], [ ]]
    // [[ ], [S], [S]]

    System.out.println("Move U: " + snakeGame1.move("U") + " (Expected: 1)"); // Head: (0,2)
    // Snake: [(0,2), (1,2)]
    // Board:
    // [[ ], [F], [S]]
    // [[ ], [ ], [S]]

    System.out.println("Move L: " + snakeGame1.move("L") + " (Expected: 2)"); // Head: (0,1), eats food
    // Snake: [(0,1), (0,2), (1,2)]
    // Next Food: None
    // Board:
    // [[ ], [S], [S]]
    // [[ ], [ ], [S]]

    System.out.println("Move U: " + snakeGame1.move("U") + " (Expected: -1) - Out of Bounds"); // Head: (-1,1)
    // Game Over

    System.out.println("\n--- Test Case 2 (Self-Collision Example) ---");
    SnakeGame snakeGame2 = new SnakeGame(3, 3, new int[][]{{1, 0}, {1, 1}, {1, 2}, {2, 2}, {2, 1}, {2, 0}, {0, 0}}); // More food for length
    // Make the snake longer to test collision
    // Initial: [(0,0)]
    System.out.println(snakeGame2.move("R")); // (0,1) Score 0
    System.out.println(snakeGame2.move("R")); // (0,2) Score 0
    System.out.println(snakeGame2.move("D")); // (1,2) Score 0
    System.out.println(snakeGame2.move("L")); // (1,1) Score 0
    System.out.println(snakeGame2.move("L")); // (1,0) Score 1 (eats food at 1,0) - Length 2: [(1,1),(1,0)]
    System.out.println(snakeGame2.move("D")); // (2,0) Score 1
    System.out.println(snakeGame2.move("R")); // (2,1) Score 1
    System.out.println(snakeGame2.move("R")); // (2,2) Score 2 (eats food at 2,2) - Length 3: [(2,1),(2,2),(1,2)]

    System.out.println("Attempting self-collision:");
    System.out.println(snakeGame2.move("U")); // (1,2) Score 2 -> head at (1,2), this spot is occupied by its body
    // Should be GAME OVER if (1,2) is still in body AND not old tail.
    // Expected: -1
  }
}