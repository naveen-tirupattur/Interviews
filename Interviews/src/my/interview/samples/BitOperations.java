package my.interview.samples;

import java.util.ArrayList;

public class BitOperations {

	public static void main(String[] args) {

		//System.out.println(findParity(10));
		//swap(107, 0,6);
		//reverseBits(105);
		printClosestInteger(39);
	}

	// Find the closest integer with same number of 1 and 0 bits
	public static void printClosestInteger(int number) {
		System.out.println("Binary representation of number: "+printBinary(number));
		
		int i=0;
		while(number>0) {
			// Find the first least significant bit which differs from its immediate neighbor.
			if((number>>i&1) != (number>>i+1&1)) {
				// If found, swap the bits
				number = swap(number, i,i+1);
				break;
			} else{
				// If not keep incrementing
				i++;
			}	
		}
		System.out.println("Binary representation of closest number: "+printBinary(number));
	}

	// Swap the bits at indexes i and j
	public static int swap(int number, int i, int j) {
		//System.out.println("Before swap: "+printBinary(number));

		// Check if the bits at indexes i,j are not same, if yes
		if((number>>i&1) != (number>>j&1)) {

			// Create a mask with only i and j bits are set to 1
			int mask = 1<<i | 1<<j;

			// use xor to change the bits at indexes. If the bit was 1 at index i, it would be come 0 and vice versa with XOR
			number = number ^ mask;
		}
		//System.out.println("After swap: "+printBinary(number));
		return number;
	}

	// Parity is 1 if number of 1 bits are odd, else its 0
	public static int findParity(int number) {
		int count=0;
		while(number>0) {
			count += number&1;
			number >>= 1;
		}
		return (count%2==0)?0:1;
	}

	// Reverse the bits in an integer
	public static void reverseBits(int number) {
		System.out.println("Before reversing: "+printBinary(number));
		// Start at either ends of number
		int i =0, j = 6;
		while(j>i) {
			// swap the either ends and move towards the center
			number = swap(number,i++,j--);
		}
		System.out.println("After reversing: "+printBinary(number));
	}

	public static abstract class BitInteger {

		public static int size;

		public int fetchColumn(int col){
			return -1;
		}
	}


	public static int findMissingNumber(ArrayList<BitInteger> a) {

		return findMissingNumber(a,0);

	}

	//Go column by column
	public static int findMissingNumber(ArrayList<BitInteger> a, int column) {

		//Check if you have finished all the bits
		if(column >= BitInteger.size) {
			return 0;
		}

		ArrayList<BitInteger> zeroBits = new ArrayList<BitInteger>();
		ArrayList<BitInteger> oneBits = new ArrayList<BitInteger>();

		//Go through elements of array and add them to appropriate array based on bit in column
		for(BitInteger i: a) {
			// if bit is 0 then add it to zeroBits array else to oneBits array
			if(i.fetchColumn(column)==0) {
				zeroBits.add(i);
			}else {
				oneBits.add(i);
			}	
		}
		//if count(lsb=0) >= count(lsb=1) then odd number is missing else even number is missing
		if(zeroBits.size() <= oneBits.size() ) {
			//check the next column
			int v = findMissingNumber(zeroBits, column+1);
			// when recursion comes back the MSB would have been updated, so shift the number to left to accommodate LSB
			return (v<<1)|0;
		}else {
			int v = findMissingNumber(oneBits, column+1);
			return (v<<1)|1;
		}
	}

	//	public static int swapBits(int x) {
	//		
	//	}

	public static int findNext(int x) {
		int count = count1Bits(x);
		if(count==32) return Integer.MAX_VALUE;
		int i = x+1;
		while ( i < Integer.MAX_VALUE) {
			if(count1Bits(i)==count) {
				return i;
			}else
			{
				i++;
			}
		}
		return Integer.MAX_VALUE;

	}

	public static int findPrevious(int x){
		int count = count1Bits(x);
		if(count == 0) return 0;
		int i = x-1;
		while( i > 0) {
			if(count1Bits(i)==count) {
				return i;
			}else {
				i--;
			}
		}
		return 0;
	}

	public static int count1Bits(int x) {
		int count=0;
		while(x !=0) {
			x = (x)&(x-1);
			count++;
		}
		return count;
	}

	public static int update(int x, int y, int i, int j) {
		for(int c=i;c<=j && y!=0;c++) {
			//clear the bit at index c
			x = x & ~(1 << c);
			//get the least significant bit from y
			int rem = y%2;
			//update the bit at index c
			x = x | (rem << c);
			//update y
			y = y/2;

		}
		return x;
	}

	public static String printBinary(int x) {
		String s="";
		while(x !=0) {
			s=x%2+s;
			x=x/2;
		}
		return s;
	}

}
