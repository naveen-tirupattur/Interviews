package my.interviews2017;

/**
 * 
 * @author ntirupattur
 * Find first occurrence of element k in array, return -1 if not found
 *
 */
public class FirstOccurrenceOfK {

	public static void main(String[] args) {
		int[] a = {-14,-10,2,108,108,243,285,285,285,401};
		System.out.println(findFirst(a, 285));

	}
	
	public static int findFirst(int[] a, int key) {
		int low =0, high = a.length-1;
		while (low <= high) {
			int mid = low + (high-low)/2;
			if (a[mid] == key) { // Found the element
				if (mid == 0) return mid; // If it is first in array return the index
				if (a[mid-1] != a[mid]) return mid ; // If its left element is not same a[mid] then this is first element matching the key
				else { high = mid-1; } // else keep going to the left
			} else if (a[mid] > key) {
				high = mid-1; // middle element is greater than the key,  keep going left
			} else {
				low = mid+1; // middle element is less than the key, keep going right
			}
		}
		return -1; // Did not find the element
	}

}
