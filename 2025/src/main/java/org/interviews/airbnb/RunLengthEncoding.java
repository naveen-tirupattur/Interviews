package org.interviews.airbnb;

import java.util.*;

public class RunLengthEncoding {
  public static List<int[]> runLengthEncode(int[] arr) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    while (i < arr.length) {
      int j = i;
      int count = 0;
      int currentVal = arr[i];
      while (j < arr.length && currentVal == arr[j]) {
        count++;
        j++;
      }
      result.add(new int[]{count, currentVal});
      i = j;
    }
    return result;
  }

  public static List<int[]> arithmeticRunLengthEncode(int[] a) {
    List<int[]> result = new ArrayList<>();
    int i = 0;
    while (i < a.length) {
      int startVal = a[i];
      int diff = 0;
      int count = 1;
      if (i + 1 < a.length) {
        diff = a[i + 1] - startVal;
        int j = i + 2;
        while (j < a.length && a[j] - a[j - 1] == diff) {
          count++;
          j++;
        }
        if (count > 1) {
          result.add(new int[]{startVal, diff, count + 1});
          i = j;
          continue;
        }
      }
      result.add(new int[]{startVal, 0, 1});
      i++;
    }
    return result;
  }

  public static Iterator<Integer> arithmeticRunLengthDecoder(List<int[]> encodedArr) {
    return new Iterator<Integer>() {
      int encodedIndex = 0;
      int sequenceIndex = 0;
      int currentCount = 0;
      int currentDiff = 0;
      int currentValue = 0;

      @Override
      public boolean hasNext() {
        return (sequenceIndex < currentCount) || (encodedIndex < encodedArr.size());
      }

      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        // If we're starting a new sequence, initialize the values.
        if (sequenceIndex >= currentCount) {
          int[] nextTriple = encodedArr.get(encodedIndex);
          currentValue = nextTriple[0];
          currentDiff = nextTriple[1];
          currentCount = nextTriple[2];
          encodedIndex++;
          sequenceIndex = 0;
        }

        int result = currentValue + (sequenceIndex * currentDiff);
        sequenceIndex++;
        return result;
      }
    };
  }

  public static void main(String[] args) {
    // Base Problem Example
    int[] baseArray = {1, 1, 1, 3, 6, 6, 1, 1};
    System.out.println("Base RLE Input: " + Arrays.toString(baseArray));
    List<int[]> baseResult = runLengthEncode(baseArray);
    System.out.println("Base RLE Output: " + Arrays.deepToString(baseResult.toArray()));
    System.out.println();

    // Follow Up 1 Example
    int[] arithmeticArray = {3, 4, 5, 7, 8, 10, 15, 20, 25};
    System.out.println("Arithmetic RLE Input: " + Arrays.toString(arithmeticArray));
    List<int[]> arithmeticResult = arithmeticRunLengthEncode(arithmeticArray);
    System.out.println("Arithmetic RLE Output: " + Arrays.deepToString(arithmeticResult.toArray()));
    System.out.println();

    // Follow Up 2 Example: Decoder
    System.out.println("Decoding the output from Follow Up 1...");
    Iterator<Integer> decoder = arithmeticRunLengthDecoder(arithmeticResult);
    List<Integer> decodedList = new ArrayList<>();
    // This loop simulates the `while (hasNext()) { ... }` pattern.
    while (decoder.hasNext()) {
      decodedList.add(decoder.next());
    }
    System.out.println("Decoded List: " + decodedList);
  }
}
