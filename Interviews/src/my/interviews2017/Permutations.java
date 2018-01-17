package my.interviews2017;

import java.util.Arrays;

public class Permutations {
	
	public static void main(String[] args) {
		
		print(new char[] {'A','B','C'}, 0);
	}
	
	public static void print(char[] s, int j) {
		if (j==s.length) {// If you have reached end of array then print the array
			System.out.println(Arrays.toString(s));
			return;
		}
		
		for(int i=j;i<s.length;i++) {// For each position in array
			swap(s,i,j); // Use one element in that position
			print(s,j+1); // Recurse for next positions
			swap(s,i,j); // Replace it with original element
		}
	}

	public static void swap(char[] s,int i,int j) {
		char temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}
}
