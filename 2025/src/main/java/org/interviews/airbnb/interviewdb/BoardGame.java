package org.interviews.airbnb.interviewdb;

public class BoardGame {
  int ROWS = 6;
  int COLS = 7;
  boolean[][] matrix;
  int[] nextAvailableRow;

  public BoardGame() {
    this.matrix = new boolean[ROWS][COLS];
    this.nextAvailableRow = new int[COLS];
    for (int i = 0; i < COLS; i++) {
      nextAvailableRow[i] = ROWS - 1;
    }
  }

  public boolean dropToken(int column, boolean playerToken) {
    // 1. Check if the column is full.
    // 2. If it's not full, place the token.
    // 3. Update the rowsIndex array.
    // 4. Return true if the move was successful, false otherwise.
    if (column < 0 || column >= COLS || nextAvailableRow[column] < 0) return false;
    int row = nextAvailableRow[column];
    matrix[row][column] = playerToken;
    nextAvailableRow[column]--;
    return true;
  }

  public boolean checkWin(int row, int column, boolean playerToken) {
    // Check all four directions
    if (checkHorizontal(row, column, playerToken) ||
      checkVertical(row, column, playerToken) ||
      checkDiagonalUp(row, column, playerToken) ||
      checkDiagonalDown(row, column, playerToken)) {
      return true;
    }
    return false;
  }

  public boolean checkHorizontal(int row, int column, boolean playerToken) {
    int count = 0;
    for (int i = column; i < COLS; i++) {
      if (matrix[row][i] == playerToken) count++;
      else break;
    }
    for (int i = 0; i < column; i++) {
      if (matrix[row][i] == playerToken) count++;
      else break;
    }
    return count >= 4;
  }

  public boolean checkVertical(int row, int column, boolean playerToken) {
    int count = 0;

    // Check downwards
    for (int i = row; i < ROWS; i++) {
      if (matrix[i][column] == playerToken) {
        count++;
      } else {
        break; // Stop if you hit an empty spot or opponent's token
      }
    }

    // A win is found if the count is 4 or more
    return count >= 4;
  }

  public boolean checkDiagonalUp(int row, int col, boolean playerToken) {
    int count = 0;

    // Check up-right
    for (int r = row, c = col; r >= 0 && c < COLS; r--, c++) {
      if (matrix[r][c] == playerToken) {
        count++;
      } else {
        break;
      }
    }

    // Check down-left
    for (int r = row + 1, c = col - 1; r < ROWS && c >= 0; r++, c--) {
      if (matrix[r][c] == playerToken) {
        count++;
      } else {
        break;
      }
    }

    return count >= 4;
  }

  public boolean checkDiagonalDown(int row, int col, boolean playerToken) {
    int count = 0;

    // Check up-left
    for (int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
      if (matrix[r][c] == playerToken) {
        count++;
      } else {
        break;
      }
    }

    // Check down-right
    for (int r = row + 1, c = col + 1; r < ROWS && c < COLS; r++, c++) {
      if (matrix[r][c] == playerToken) {
        count++;
      } else {
        break;
      }
    }

    return count >= 4;
  }

  public boolean isBoardFull() {
    for (int i = 0; i < COLS; i++) {
      if (nextAvailableRow[i] >= 0) {
        return false; // Found at least one non-full column
      }
    }
    return true; // All columns are full
  }
}
