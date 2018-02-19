package my.interviews2017.hard;

public class FindFirstMissingPositiveNumber {

	public static void main(String[] args) {
		
		int[] a = {3,5,4,-1,5,1,-1};
		System.out.println(findMissing(a));
	}
	
	// Use array itself as a lookup by placing element a[i] at k-1 if a[i]==k, then iterate the array to find first element where a[i] != i+1
	public static int findMissing(int[] a) {
		int missing = Integer.MIN_VALUE;
		int i =0;
		while (i < a.length) {
			if ((a[i]>0 && a[i] <= a.length) && (a[i] != a[a[i]-1])) { // Check for elements only greater than 0 and less than size of array and which are not in place
				int x = i; // If true then swap elements to keep them in place
				int y = a[i]-1;
				swap(a,x,y);
			} else { // else move forward in the array
				i++;
			}
		}
		
		for (i=0;i<a.length;++i) { // Check for the first element which is not in place
			if (a[i] != i+1) return i+1; 
		}
		
		return missing;
	}
	
	public static final void swap (int[] a, int i, int j) {
	  int t = a[i];
	  a[i] = a[j];
	  a[j] = t;
	}

}
