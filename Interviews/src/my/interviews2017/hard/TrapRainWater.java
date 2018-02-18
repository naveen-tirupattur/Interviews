package my.interviews2017.hard;

public class TrapRainWater {

	public static void main(String[] args) {
		int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
		//int[] a = {0,1,1,2,1,3,3,2,1,0};
		//int[] a = {};
		System.out.println(trap(a));
	}
	
	public static int trap(int[] a) {
		int total = 0;
		if (a.length == 0) return 0;
		int maximumIndex = findMaximumIndex(a); // Find maximum Index
		int start=a[0]; // Start on left end
		for (int i=1;i<=maximumIndex;i++) { // Till the maximum index
			if (a[i] >= start) start = a[i]; // If the slope is going up, no water can be trapped
			else total += (start-a[i]); // Count the difference between tallest point on left till now
		}
		start = a[a.length-1]; // Start on the right end
		for (int i = a.length-2;i>=maximumIndex;i--) { // Till the maximum index
			if (a[i]>= start) start = a[i]; // If the slow is going up,no water can be trapped
			else total += (start-a[i]); // Count the difference between tallest point on right till now
		}
		return total;
	}
	
	public static int findMaximumIndex(int[] a) {
		int maximumIndex = 0;
		for (int i=1;i<a.length;i++) {
			if (a[i] > a[maximumIndex]) {
				maximumIndex = i;
			}
		}
		return maximumIndex;
	}
}
