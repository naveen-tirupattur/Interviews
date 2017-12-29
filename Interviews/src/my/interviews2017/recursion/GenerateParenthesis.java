package my.interviews2017.recursion;

public class GenerateParenthesis {

	public static void main(String[] args) {
		
		String s = new String();
		String p = "ABCD";
		char c[] = new char[] {'(', ')'};
		generateParenthesis(3, 0, 0, s, c);

	}
	
	public static void generateParenthesis(int n, int left, int right, String s, char[] c) {
		
		if(left == n && right == n) { System.out.println(s); return;}
		
		for (int i =0;i<c.length;i++) {
			if (c[i] == '(' && left < n) {
				s = s+c[i];
				generateParenthesis(n, left+1, right, s, c);
				s = s.substring(0, s.length()-1);
				
			} else if ( c[i] == ')' && right < left) {
				s = s+c[i];
				generateParenthesis(n, left, right+1, s, c);
				s = s.substring(0, s.length()-1);
			}
		}
		
	}

}
