package my.interviews2017;

public class RealSqrt {

	public static void main(String[] args) {
		double x = 1.0;
		System.out.println(sqrt(x));
	}
	
	public static double sqrt(double x) {
		double low = 0.0, high = 0.0, middle = 0.0;
		// The upper and lower limits change based on the number
		if (x < 1) {
			low = 0.0;
			high = x;
		} else {
			low = 1.0;
			high = x;
		}
		// Use binary search to find the approximate sqrt
		while (low<high) {
			middle = low + (high-low)*0.5;
			if (compare(middle*middle, x) == 0 ) return middle;
			if (compare(middle*middle, x) > 0 ) high = middle;
			else low = middle;
		}
		return low;
	}
	
	/**
	 * Function to compare the numbers. The difference is compared to an epsilon value to determine which number is larger.
	 */
	public static int compare(double a, double b) {
		double epsilon = 0.00001;
		if (a-b > epsilon) return 1;
		else if (a-b < -epsilon) return -1;
		else return 0;
	}
}
