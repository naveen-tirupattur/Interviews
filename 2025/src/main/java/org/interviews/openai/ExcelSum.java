package org.interviews.openai;

import java.util.ArrayList;
import java.util.List;

class Cell {
  int row;
  char column;
  int val;
  List<String> formulas;


  public Cell(int row, char col, int val) {
    this.row = row;
    this.column = col;
    this.val = val;
    this.formulas = new ArrayList<>();
  }
}

public class ExcelSum {
  Cell[][] sheet;
  int height;
  int width;

  public ExcelSum(int height, char width) {
    this.height = height + 1;
    this.width = width - 'A' + 1;
    sheet = new Cell[this.height][this.width];
    for (int i = 1; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        sheet[i][j] = new Cell(i, (char) (j + 'A'), 0);
      }
    }
  }

  // Helper to convert char column to 0-based int index
  private int getColIndex(char column) {
    return column - 'A';
  }

  private int[] parseCell(String s) {
    char colChar = s.charAt(0);
    int rowNum = Integer.parseInt(s.substring(1));
    return new int[]{rowNum, getColIndex(colChar)};
  }
  

  public void set(int row, char column, int val) {
    if (row < 1 || row > this.height || getColIndex(column) < 0 || getColIndex(column) >= this.width) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    sheet[row][getColIndex(column)] = new Cell(row, column, val);
  }

  public int get(int row, char column) {
    if (row < 1 || row > this.height || getColIndex(column) < 0 || getColIndex(column) >= this.width) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    Cell cell = sheet[row][getColIndex(column)];
    if (cell.formulas.isEmpty()) {
      return sheet[row][getColIndex(column)].val;
    } else {
      return calculateSum(cell.formulas);
    }
  }

  public int sum(int row, char column, List<String> formulas) {
    Cell cell = sheet[row][getColIndex(column)];
    cell.formulas = formulas;
    int totalSum = calculateSum(formulas);
    cell.val = totalSum;
    return totalSum;
  }

  private int calculateSum(List<String> formulas) {
    int totalSum = 0;
    for (String formulaStr : formulas) {
      if (formulaStr.contains(":")) {
        String[] parts = formulaStr.split(":");
        int[] startCoords = parseCell(parts[0]);
        int[] endCoords = parseCell(parts[1]);
        for (int i = startCoords[0]; i <= endCoords[0]; i++) {
          for (int j = startCoords[1]; j <= endCoords[1]; j++) {
            totalSum = totalSum + get(i, (char) (j + 'A'));
          }
        }
      } else {
        int[] coords = parseCell(formulaStr);
        totalSum = totalSum + get(coords[0], (char) (coords[1] + 'A'));
      }
    }
    return totalSum;
  }

  public static void main(String[] args) {
    testCase1_BasicFunctionality();
    System.out.println("----------------------------------------");
    testCase2_SimpleSumWithCacheHit();
    System.out.println("----------------------------------------");
    testCase3_DependencyInvalidationAndRecalculation();
    System.out.println("----------------------------------------");
    testCase4_OverwritingFormula();
    System.out.println("----------------------------------------");
    testCase5_ComplexDependenciesAndRanges();
  }

  private static void testCase1_BasicFunctionality() {
    System.out.println("Running Test Case 1: Basic Functionality");
    ExcelSum excel = new ExcelSum(5, 'C');
    excel.set(1, 'A', 10);
    excel.set(5, 'C', 20);

    assert excel.get(1, 'A') == 10;
    assert excel.get(5, 'C') == 20;
    assert excel.get(3, 'B') == 0;

    System.out.println("Test Case 1 Passed! ✅");
  }

  private static void testCase2_SimpleSumWithCacheHit() {
    System.out.println("Running Test Case 2: Simple Sum with Cache Hit");
    ExcelSum excel = new ExcelSum(3, 'C');
    excel.set(1, 'A', 10);
    excel.set(1, 'B', 20);

    List<String> sumFormula = new ArrayList<>();
    sumFormula.add("A1");
    sumFormula.add("B1");
    excel.sum(1, 'C', sumFormula);

    assert excel.get(1, 'C') == 30; // First call, calculates sum
    assert excel.get(1, 'C') == 30; // Second call, should hit cache

    System.out.println("Test Case 2 Passed! ✅");
  }

  private static void testCase3_DependencyInvalidationAndRecalculation() {
    System.out.println("Running Test Case 3: Dependency Invalidation and Recalculation");
    ExcelSum excel = new ExcelSum(3, 'C');
    excel.set(1, 'A', 10);
    excel.set(1, 'B', 20);

    List<String> sumFormula = new ArrayList<>();
    sumFormula.add("A1");
    sumFormula.add("B1");
    excel.sum(1, 'C', sumFormula);

    assert excel.get(1, 'C') == 30;

    excel.set(1, 'A', 5); // Change a dependency
    assert excel.get(1, 'C') == 25; // Should force recalculation (5 + 20)

    System.out.println("Test Case 3 Passed! ✅");
  }

  private static void testCase4_OverwritingFormula() {
    System.out.println("Running Test Case 4: Overwriting a Formula");
    ExcelSum excel = new ExcelSum(3, 'C');
    excel.set(1, 'A', 10);
    excel.set(1, 'B', 20);

    List<String> sumFormula = new ArrayList<>();
    sumFormula.add("A1");
    sumFormula.add("B1");
    excel.sum(1, 'C', sumFormula);

    assert excel.get(1, 'C') == 30;

    excel.set(1, 'C', 100); // Overwrite formula
    assert excel.get(1, 'C') == 100;

    excel.set(1, 'A', 50); // Change original dependency
    assert excel.get(1, 'C') == 100; // Value should not change

    System.out.println("Test Case 4 Passed! ✅");
  }

  private static void testCase5_ComplexDependenciesAndRanges() {
    System.out.println("Running Test Case 5: Complex Dependencies and Ranges");
    ExcelSum excel = new ExcelSum(5, 'E');
    excel.set(1, 'A', 1);
    excel.set(2, 'A', 2);
    excel.set(3, 'A', 3);
    excel.set(1, 'C', 100);

    List<String> rangeFormula = new ArrayList<>();
    rangeFormula.add("A1:A3");
    excel.sum(4, 'A', rangeFormula); // Sum is 6

    List<String> complexFormula = new ArrayList<>();
    complexFormula.add("A4");
    complexFormula.add("C1");
    excel.sum(5, 'B', complexFormula); // Sum is 6 + 100 = 106

    assert excel.get(5, 'B') == 106;

    excel.set(1, 'A', 10); // Change a base value
    assert excel.get(5, 'B') == 115; // Should be recalculated: (10 + 2 + 3) + 100

    System.out.println("Test Case 5 Passed! ✅");
  }

}
