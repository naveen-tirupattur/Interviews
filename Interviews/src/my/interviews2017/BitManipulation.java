/**
 * 
 */
package my.interviews2017;

/**
 * @author naveen
 *
 */
public class BitManipulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		System.out.println(getClosest(7));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static long swapBits(long x, int i, int j) {
		if ((x>>i&1)!=(x>>j&1)) { // Check if the bits are different
			long bit_mask = 1<<i | 1<<j; // Create a mask by setting only bits at i, j to 1 using OR
			x = x^bit_mask; // XOR with x which will change only the bits which are set to 1 in the mask
		}
		return x;
	}

	public static int getParity(long x) {
		int result = 0;
		while (x>0) {
			if ((x&1) == 1) result++; // While LSB is 1 keep incrementing the count
			x = x >> 1;
		}
		return result%2; // Returns 0 if result is even and 1 if result is odd
	}
	
	public static int getParityFaster(long x) {
		int result=0;
		while (x>0) {
			// XOR 1 will alternate between 0 and 1. 
			// If there are odd number of 1 bits XOR of result would be 1 
			// when all the 1 bits are cleared by x&(x-1)
			result ^= 1;
			x = x&(x-1); // set's the least significant set bit to 0
		}
		return result;
	}
	
	public static long getClosest(long x) throws Exception {
		for (int i=0;i<63;i++) {
			if ( ((x >> i) & 1) != ((x>>(i+1)) & 1)) {
				return x ^ (1 << i | 1 << (i+1));
			}
		}
			throw new Exception("Invalid number. All bits are either 1 or 0");
	}
}

