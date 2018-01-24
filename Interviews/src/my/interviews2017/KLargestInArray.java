package my.interviews2017;

import java.util.Random;

public class KLargestInArray {

	public static void main(String[] args) {
		
		int[] a={0,3,2,1,5,4};
		System.out.println(find(a,6));

	}
	
	public static int find(int[] a, int k) {
		if (k > a.length) return -1; // If the input is greater than size of array return -1
		int low = 0, high = a.length-1;
		while (low <= high) { 
			Random m = new Random();
			int idx = m.nextInt(high-low+1)+low; // Get a random number between high and low
			int pivot = partition(a, low, high, idx); // Partition the elements of array around a random pivot
			if (pivot == k-1) return a[pivot]; // If you have k-1 elements to left which are greater than pivot you have found the kth largest element
			else if (pivot > k-1) {
				high = pivot-1; // If you have more than k-1 elements to your left then go left
			} else {
				low = pivot+1; // If you have less than k-1 elements to your left then go right
			}
		}
		return -1;
	}
	
	public static int partition(int[] a, int low, int high, int idx) {
		int pivot = a[idx], pivotIndex = low;
		swap(a,idx,high); // Move the random pivot index to end
		while (low < high) {
			if (a[low]> pivot) { // If the element is greater than pivot move it to left
				swap(a,pivotIndex++,low);
			}
			low++;
		}
		swap(a, pivotIndex, high); // Moved all elements greater than pivot to it's left now move the pivot element to it's correct position
		return pivotIndex;
	}
	
	public static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

}
