package my.interviews2017;

public class FindRotationPoint {

	public static void main(String[] args) {

		int[] a = {3, 4, 5, 1, 2};
		System.out.println(findRotation(a));
	}
	
	public static int findRotation(int[] a) {
		int low = 0, high = a.length-1;
		
		while (low < high) {
			int mid = low + (high - low)/2;	
			if (a[mid] < a[high] && a[mid] < a[low]) {
				return a[mid];
			} else if (a[mid] <= a[high]) {
				high = mid-1;
			} else if (a[mid] >= a[low]) {
				low = mid+1;
			}
		}
		
		return a[low];		
	}

}
