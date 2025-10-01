package org.interviews.leetcode;

import java.util.*;

public class Excel {
  private int rows;
  private int cols;
  private int[][] values;
  private Map<String, List<String>> formulaMap;
  private Map<String, Integer> cache; // NEW for caching evaluated results

  public Excel(int height, char width) {
    this.rows = height;
    this.cols = width - 'A' + 1;
    this.values = new int[rows][cols];
    this.formulaMap = new HashMap<>();
    this.cache = new HashMap<>();
  }

  public void set(int row, char column, int val) {
    int r = row - 1;
    int c = column - 'A';
    String key = getKey(row, column);
    values[r][c] = val;
    formulaMap.remove(key); // remove formula if any
    clearCache(); // invalidate cache
  }

  public int get(int row, char column) {
    String key = getKey(row, column);
    if (!formulaMap.containsKey(key)) {
      return values[row - 1][column - 'A'];
    }
    if (cache.containsKey(key)) { // return cached value if exists
      return cache.get(key);
    }
    int result = evaluateFormula(formulaMap.get(key));
    cache.put(key, result); // store in cache
    return result;
  }

  public int sum(int row, char column, String[] numbers) {
    String key = getKey(row, column);
    List<String> refs = new ArrayList<>(Arrays.asList(numbers));
    formulaMap.put(key, refs);
    clearCache(); // formula changed, so clear cache
    int sum = evaluateFormula(refs);
    cache.put(key, sum); // store result in cache
    return sum;
  }

  // ---------- Helper Methods ----------

  private String getKey(int row, char column) {
    return column + String.valueOf(row);
  }

  private int evaluateFormula(List<String> refs) {
    int total = 0;
    for (String ref : refs) {
      if (ref.contains(":")) {
        String[] parts = ref.split(":");
        total += sumRange(parts[0], parts[1]);
      } else {
        total += getCellValue(ref);
      }
    }
    return total;
  }

  private int sumRange(String start, String end) {
    int startCol = start.charAt(0) - 'A';
    int startRow = Integer.parseInt(start.substring(1)) - 1;
    int endCol = end.charAt(0) - 'A';
    int endRow = Integer.parseInt(end.substring(1)) - 1;

    int sum = 0;
    for (int r = startRow; r <= endRow; r++) {
      for (int c = startCol; c <= endCol; c++) {
        String key = (char) ('A' + c) + String.valueOf(r + 1);
        if (formulaMap.containsKey(key)) {
          if (cache.containsKey(key)) {
            sum += cache.get(key);
          } else {
            int val = evaluateFormula(formulaMap.get(key));
            cache.put(key, val);
            sum += val;
          }
        } else {
          sum += values[r][c];
        }
      }
    }
    return sum;
  }

  private int getCellValue(String ref) {
    char colChar = ref.charAt(0);
    int col = colChar - 'A';
    int row = Integer.parseInt(ref.substring(1)) - 1;

    String key = getKey(row + 1, colChar);
    if (formulaMap.containsKey(key)) {
      if (cache.containsKey(key)) {
        return cache.get(key);
      }
      int val = evaluateFormula(formulaMap.get(key));
      cache.put(key, val);
      return val;
    }
    return values[row][col];
  }

  private void clearCache() {
    cache.clear();
  }
}
