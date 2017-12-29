package my.interviews2017.recursion;

public class PrintPalindromes {

	public static void main(String[] args) {
		
		String c="";
		printPalindromes("0204451881", c);

	}
	
	public static void printPalindromes(String s, String c) {
		
		if (s!= null && s.length()==0) {
			System.out.println(c);
			return;
		}
		
		for (int i=1;i<=s.length();i++) {
			if (isPalindrome(s.substring(0, i))) {
				c = c+s.substring(0,i)+" ";
				printPalindromes(s.substring(i), c);
				c = c.substring(0,c.length()-2);
			}
		}
	}
	
	public static boolean isPalindrome(String s) {
		int i=0, j = s.length()-1;
		while ( i < s.length()/2) {
			if (s.charAt(i) != s.charAt(j)) return false;
			i++; j--;
		}
		return true;
	}

}
