package org.interviews.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeautifulArray {
  public static int getMinSwaps(int[] arr, int[] sortedArray) {
    Map<Integer, Integer> elementsMap = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      elementsMap.put(arr[i], i);
    }
    int swaps = 0;
    for (int i = 0; i < arr.length; i++) {
      int element = arr[i];
      int sortedElement = sortedArray[i];
      if (element == sortedElement) {
        continue;
      } else {
        int sortedIndex = elementsMap.get(sortedElement);
        arr[i] = sortedElement;
        arr[sortedIndex] = element;
        elementsMap.put(element, sortedIndex);
        elementsMap.put(sortedElement, i);
        swaps++;
      }
    }
    return swaps;
  }

  public static int numOfSwaps(List<Integer> arr) {
    int[] intArray = arr.stream().mapToInt(Integer::intValue).toArray();
    int[] sortArray = Arrays.copyOf(intArray, intArray.length);
    Arrays.sort(sortArray);
    int minAsc = getMinSwaps(Arrays.copyOf(intArray, intArray.length), sortArray);
    // Create target array for descending order
    int[] sortedDesc = new int[arr.size()];
    for (int i = 0; i < arr.size(); i++) {
      sortedDesc[i] = sortArray[arr.size() - 1 - i]; // Fill in reverse from sortedAsc
    }
    int minDesc = getMinSwaps(Arrays.copyOf(intArray, intArray.length), sortedDesc);
    return Math.min(minAsc, minDesc);
  }

  public static void main(String[] args) {
    List<Integer> arr = Arrays.asList(7, 15, 12, 3);
    System.out.println(numOfSwaps(arr));
  }
}
