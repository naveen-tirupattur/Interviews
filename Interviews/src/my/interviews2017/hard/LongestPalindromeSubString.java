package my.interviews2017.hard;

public class LongestPalindromeSubString {

	public static void main(String[] args) {
		System.out.println(longest("ABBA"));
		System.out.println(longest("ABABA"));
		System.out.println(longest("forgeeksskeegfor"));
	}
	
	public static String longest(String s) {
		int maxLength = 1;
	  int start=0, end = 0;
    int startIndex = 0;
	  if (s == null || s.isEmpty()) return "";
    for (int i=1;i<s.length();i++) {	
      //For even length substrings
      start = i-1;
      end = i;
      while (start>=0 && end < s.length() && s.charAt(end) == s.charAt(start)) { // Keep going while the current string between start, end is a palindrome
        if (end-start+1 > maxLength) { // Not a palindrome anymore, check the maximum length
          maxLength = end-start+1;
          startIndex = start;
        }
        start--;
        end++;
      }
      


      // For odd length substrings
      start = i-1;
      end = i+1;
      while (start>=0 && end < s.length() && s.charAt(end) == s.charAt(start)) {  // Keep going while the current string between start, end is a palindrome
        if (end-start+1 > maxLength) { // Not a palindrome anymore, check the maximum length
          maxLength = end-start+1;
          startIndex = start;
        }
        start--;
        end++;
      }
    }
    return s.substring(startIndex, startIndex+maxLength);
	}
}
