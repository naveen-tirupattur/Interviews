package my.interviews2017;

public class RemoveDuplicates {

	public static void main(String[] args) {
		
		int[] a = {1,1,1,1,1,1,2};
		System.out.println(remove(a));
	}
	
	public static int remove(int[] a){
		int ind=1,i=1; // Start from first element
		while(i<a.length) { 
			if (a[i] != a[i-1]) {
				a[ind++] = a[i]; // If the previous element is not duplicate update the count
			}
			i++;
		}
		return ind-1;
	}

}
