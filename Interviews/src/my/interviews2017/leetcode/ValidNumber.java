package my.interviews2017.leetcode;

public class ValidNumber {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		System.out.println(isNumber("..2"));
		System.out.println(System.currentTimeMillis()-time);
	}
	
	public static boolean isNumber(String s) {
		int i =0;
    while (i<s.length()) {
      int c = s.charAt(i)-'0';
      if (c >=0 && c <=9) i++;
      else {
        if (s.charAt(i) == ' ' && s.length() > 1) {
          if (i==0) i++;
          else if (i == s.length()-1) {
            int prev = s.charAt(i-1)-'0';
            if (prev >=0 && prev <=9) i++; else return false;
          }
        }
        else if ((s.charAt(i) == 'e' || s.charAt(i) == '.') && (i !=0 && i!= s.length()-1)) {i++;}
        else return false;
      }
    }
    return true;
  }
}
