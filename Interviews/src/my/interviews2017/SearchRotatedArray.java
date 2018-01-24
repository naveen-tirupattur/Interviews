package my.interviews2017;

/**
 * 
 * @author ntirupattur
 * Find an element in a rotated sorted array, return -1 if not found
 *
 */
public class SearchRotatedArray {

	public static void main(String[] args) {
		
		int[] a = {4, 5, 6, 7, 0, 1, 2, 3};
		System.out.println(find(a, 0));
		System.out.println(find(a, 9));
		System.out.println(find(a, 4));
		System.out.println(find(a, 3));

	}
	
	public static int find(int[] a, int element) {
		int low = 0,high = a.length-1;
		while (low<=high) {
			int mid = low + (high-low)/2;
			if (a[mid] == element) return mid; // Found in the middle,return it
 			if (a[low] <= a[mid]) { // left side is sorted
				if (element >= a[low] && element < a[mid]) { // lies between the either ends, search in this sub array
					high = mid-1;
				} else {
					low = mid+1; // else search in the other half
				}
			} else { // right side is sorted
				if (element>a[mid] && element<= a[high]) { // lies between the right half, search in this sub array
					low = mid+1;
				} else { 
					high = mid-1; // else search in left half
				}
			}
		}
		return -1;
	}
}
