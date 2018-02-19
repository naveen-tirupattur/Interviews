package my.interviews2017.hard;

public class GCD {

	public static void main(String[] args) {
		System.out.println(find(24,300));

	}
	
	// Recursively reduce the search space
	public static int find(int x, int y) {
		if (x==y) return x; // If both the numbers are equal return the number
		
		if ((x&1)==0 && (y&1)==0) { // If both are even, GCD(X,Y) = 2* GCD(X/2, Y/2)
			return (find(x>>1, y>>1)<<1);
		} else if ((x&1)!=0 && (y&1)==0) { // If one is even, other is odd, divide the even number by 2
			return find(x, y>>1);
		} else if ((x&1)==0 && (y&1)!=0) {
			return find(x>>1, y);
		} else if ((x&1)!=0 && (y&1)!=0) { // If both are odd then subtract maximum from minimum and recurse
			if (x-y>0) return find(x-y, y);
		}
		return find(x,y-x);
	}

}
