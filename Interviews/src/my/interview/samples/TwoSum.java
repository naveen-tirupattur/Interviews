package my.interview.samples;

public class TwoSum {

  public static void main(String[] args) {
	
	int[] a = {3,2,4};
	int[] result = twoSum(a,6);
  }

  public static int[] twoSum(int[] nums, int target) {
	for (int i=0;i<nums.length-1;i++) {
	  System.out.println("a[i]"+nums[i]);
	  for (int j=i+1;j<nums.length;j++) {
		System.out.println("a[j]"+nums[j]);
		if ((nums[i]+nums[j]) == target) {
		  System.out.println(nums[i]+","+nums[j]);
		  return (new int[]{nums[i], nums[j]});
		}
	  }
	}
	return null;
  }
}


