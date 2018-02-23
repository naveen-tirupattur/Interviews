package my.interviews2017;

import java.util.Arrays;

public class MoveZeroes {

	public static void main(String[] args) {
		int[] nums = {0, 1, 0, 3, 12};
		System.out.println(Arrays.toString(move(nums)));
	}
	
	public static int[] move(int[] a) {
		int index = 0;
		for (int i=0;i<a.length;i++) {
			if(a[i] !=0) {
				a[index++] = a[i]; // Add element to the index only if it is not zero
			}
		}
		while(index < a.length) { // Fill it with zeros
			a[index++] = 0;
		}
		return a;
	}
	

}
