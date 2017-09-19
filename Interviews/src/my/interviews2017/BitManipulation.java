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
		// TODO Auto-generated method stub

	}

	public static int getParity (long x) {
		int result = 0;
		while (x>0) {
			if((x&1) == 1) result++;
			x = x >> 1;
		}
		return result;
	}
}

