package org.interviews.leetcode;

public class MaximumProductSubArray {
  public int maxProduct(int[] nums) {
    int maxProductSoFar = nums[0];
    int minProductSoFar = nums[0];
    int result = maxProductSoFar;
    for (int i = 1; i < nums.length; i++) {
      int current = nums[i];
      int tempMax = Math.max(current, Math.max(maxProductSoFar * current, minProductSoFar * current));
      minProductSoFar = Math.min(current, Math.min(maxProductSoFar * current, minProductSoFar * current));
      maxProductSoFar = tempMax;
      result = Math.max(result, maxProductSoFar);
    }
    return result;
  }
}
