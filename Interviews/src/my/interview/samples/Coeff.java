package my.interview.samples;

import java.util.Arrays;

public class Coeff {
  private int[] inputArr;

  public Coeff(int[] inputArr) {
    this.inputArr = inputArr;
  }

  private void run() {
    int[] outputArr = new int[inputArr.length + 1]; 

    // Initialize with first entry in inputArr
    outputArr[outputArr.length - 1] = inputArr[0];
    outputArr[outputArr.length - 2] = 1;

    for (int i = 1; i < inputArr.length; i++) {
      updateCoeff(outputArr, inputArr[i]);
    }

    printOutput(outputArr);
  }

  private void printOutput(int[] outputArr) {
    System.out.println("Input: " + Arrays.toString(inputArr));
    System.out.println("Output: " + Arrays.toString(outputArr));
    System.out.println("---------------------------------------");
  }

  private int[] getSlidedCoeffs(int[] outputArr) {
    int[] result = new int[outputArr.length];

    for (int i = 0; i < result.length - 1; i++) {
      result[i] = outputArr[i + 1];
    }

    return result;
  }

  private int[] getScaledCoeffs(int[] outputArr, int factor) {
    int[] result = new int[outputArr.length];

    for (int i = 0; i < outputArr.length ; i++) {
      result[i] = outputArr[i] * factor;
    }

    return result;
  }

  private void updateCoeff(int[] outputArr, int input) {
      int[] slidedCoeffs = getSlidedCoeffs(outputArr);
      int[] scaledCoeffs = getScaledCoeffs(outputArr, input);

      System.out.println("Slided coeffs: " + Arrays.toString(slidedCoeffs));
      System.out.println("Scaled coeffs: " + Arrays.toString(scaledCoeffs));

      for (int i = 0; i < outputArr.length; i++) {
        outputArr[i] = slidedCoeffs[i];
        outputArr[i] += scaledCoeffs[i];
      }
  }

  public static void main(String[] args) {
    new Coeff(new int[] {1}).run();
    new Coeff(new int[] {1, 1}).run();
    new Coeff(new int[] {1, 1, 1}).run();
    new Coeff(new int[] {-1, -1}).run();
    new Coeff(new int[] {-1, -1, -1}).run();
    new Coeff(new int[] {1, 2, 3}).run();
  }
}
