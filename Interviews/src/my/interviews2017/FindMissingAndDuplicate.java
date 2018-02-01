package my.interviews2017;

public class FindMissingAndDuplicate {

	public static void main(String[] args) {
		
		find(new int[] {5,3,0,1,4,4});
		
	}
	
	public static void find(int[] a) {
		int xor = 0;
		for (int i=0;i<a.length;i++) { // XOR of elements in array and elements from 0 to n-1 will return XOR of missing and duplicate as other elements would eliminate each other
			xor = xor ^ a[i];
			xor = xor ^ i;
		}
		
		int lowestBit = xor & ~(xor-1); // This returns the lowest bit which is set to 1 in XOR of missing and duplicate number
		int x= 0, y=0;
		for (int i=0;i<a.length;i++) {  
			if((a[i]&lowestBit) != 0)  { // Check if that bit is set to 1 in each element of array
				x = x^a[i]; // XOR of such numbers should return the missing number
			} else {
				y = y^a[i]; // XOR of remaining numbers should return the duplicate number
			}
			
			if((i&lowestBit) != 0)  { // Check if that bit is set to 1 in numbers between 0 to n-1
				x = x^i;
			} else {
				y = y^i;
			}
		}
		System.out.println(x+","+y);
	}
}
