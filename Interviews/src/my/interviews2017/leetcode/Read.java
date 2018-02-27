package my.interviews2017.leetcode;

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

public class Read {
	/**
	 * @param buf Destination buffer
	 * @param n   Maximum number of characters to read
	 * @return    The number of characters read
	 */
	public int read(char[] buf, int n) {
		char[] buff = new char[4]; // Allocate buffer of size 4 as that's the max you can read using read4
		boolean eof = false; // Flag to check if you have read everything from file
		int count = 0; // Keep track of how many characters you have read 
		while (!eof && count<n) { // Keep reading until you have reached the limit
			int r = read4(buff); // Read from file
			if (r < 4) eof = true; // If the size you have read is less than 4, you have reached EOF
			if (r+count > n) { // See if the size you read is more than you are required to 
				r = n-count; 
			}
			for (int i=0;i<r&&count<n;i++) { // Copy into your output buffer from the position where you left off
				buf[count++] = buff[i];
			}
		}
		return count;
	}
	
	public static int read4(char[] buf) {
		return -1;
	}
}
