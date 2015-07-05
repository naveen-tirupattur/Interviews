package my.interview.samples;

import java.util.ArrayList;

public class BitOperations {

	public static void main(String[] args) {
		
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
	
	public static void printBinary(int x) {
		String s="";
		while(x !=0) {
			s=x%2+s;
			x=x/2;
		}
		System.out.println(s);
	}

}
