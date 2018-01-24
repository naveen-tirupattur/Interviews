package my.interviews2017;

/**
 * 
 * @author ntirupattur
 * Find first element greater than k in the array
 * find(13) should return index 6
 * find (10) should return index 5
 *
 */
public class FirstElementGreaterThanK {

	public static void main(String[] args) {
		int[] a = {1, 2, 8, 10, 10, 12, 19};
		System.out.println(firstElement(a,10));
	}
	
	public static int firstElement(int[] a, int key) {
		
		int low = 0, high = a.length-1,result = -1;
		while (low<=high) {
			int mid = low + (high-low)/2;
			if (a[mid] <= key) { //If key is greater than or equal to middle element the next element is to the right
				low = mid+1;
			} else {
				result = mid; // Found an element greater than key, keep going to the left
				high = mid-1;
			}
		}
		return result; // Return the left most yet right of key
	}

}
