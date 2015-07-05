/**
 * 
 */
package my.interview.samples;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author naveenit7sep
 *
 */
public class Strings {

	public static List<String> subsets = new ArrayList<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//		List<String> a = new ArrayList<String>();
		//		a.add("tea");
		//		a.add("and");
		//		a.add("ace");
		//		a.add("ad");
		//		a.add("eat");
		//		a.add("dan");
		//		
		//		printAnagrams(a);

		//System.out.println("Reverse String: "+reverseStringRecursion("this is a test"));

		//System.out.println("longest common substring: "+longestCommonSubString("ADBABC","BAB"));
		//System.out.println(getEditDistance("SUNDAY","SATURDAY"));

//		ArrayList<String> str = new ArrayList<>();
//		str.add("a");
//		str.add("b");
//		str.add("c");
//		//ArrayList<String> s=getSubsets2(str);
//
//		//				Collections.sort(s);
//		//				
//		String a = "abc";
//		List<String> s=permutations(a);
//		for(String s1: s)
//		{
//			System.out.println(s1);
//		}


		//System.out.println("Is anagram: "+isAnagram2("dogse", "dogsm"));
		//System.out.println(removeDups("abcd efghgad"));

		//findPalindrome("12112112231");
		//String s = "naveen";
		//System.out.println(removeDups(s));

		//		String[] s = new String[]{"fuckyou","fucme","fuckher","fuckoff","fckthatbitch"};
		//		System.out.println(findLongestCommonPrefix(s));


		//System.out.println(getHash("fuckyou"));
		
		printPermutations("ABC");

	}
	//Find the maximum length substring with all unique characters
	public static String findMaxSubString(String s)
	{
		HashSet<Character> hs = new HashSet<Character>();
		int stIndex = 0,endIndex = 0, maxLength = 0, maxStIndex = 0, maxEndIndex = 0;

		while(endIndex < s.length())
		{
			Character endChar = s.charAt(endIndex);
			if(!hs.contains(endChar))
			{
				hs.add(endChar);
				int len = endIndex - stIndex + 1;

				if(len > maxLength)
				{
					maxLength = len;
					maxStIndex = stIndex;
					maxEndIndex = endIndex;
				}
				endIndex++;

			}else
			{
				Character stChar = s.charAt(stIndex);
				stIndex++;
				hs.remove(stChar);
				if(stChar == endChar)
				{
					int len = endIndex - stIndex + 1;
					hs.add(stChar);
					if(len > maxLength)
					{
						maxLength = len;
						maxStIndex = stIndex;
						maxEndIndex = endIndex;
					}
					endIndex++;
				}
			}
		}
		
		
		return s.substring(maxStIndex,maxEndIndex+1);
	}

	public static void findPalindrome(String s)
	{
		String longest="";
		for(int i=0;i<s.length();i++)
		{
			String p = getPalindrome(s,i,i);
			//System.out.println(p);
			if(longest.length()<p.length())
			{
				longest = p;
			}
		}

		System.out.println(longest);
	}

	public static String getPalindrome(String s, int left, int right)
	{
		while(left>=0 && right<=s.length()-1&&s.charAt(left)==s.charAt(right))
		{
			left--; right++;
		}

		return s.substring(left+1,right);
	}


	public static String removeDups(String s)
	{
		String newS="";
		int[] table = new int[256];
		for(int i=0;i<s.length();i++)
		{
			if(table[s.charAt(i)]>0)
			{
				continue;
			}else
			{
				table[s.charAt(i)]++;
				newS +=s.charAt(i);
			}
		}

		return newS;
	}

	public static String findLongestCommonPrefix(String[] words)
	{
		String s = findSmallestString(words);
		String minPrefix=s;
		for(int i=0;i<words.length;i++)
		{
			if(!words[i].equals(s))
			{
				String prefixSoFar="";
				for(int j=0;j<s.length();j++)
				{
					if(words[i].charAt(j)!=s.charAt(j))
					{
						if(prefixSoFar.length()<=minPrefix.length()) 
						{
							minPrefix = prefixSoFar;
						}
					}else
					{
						prefixSoFar+=words[i].charAt(j);
					}
				}
			}
		}
		return minPrefix;
	}

	public static String findSmallestString(String[] words)
	{
		String minWord = words[0];
		for(int i=1;i<words.length;i++)
		{
			if(words[i].length()<= minWord.length())
				minWord = words[i];
		}
		return minWord;
	}

	public static ArrayList<String> getSubsets2(List<String> str)
	{
		ArrayList<String> subset = new ArrayList<String>();

		subset.add("");

		for(String s:str)
		{

			int count = subset.size();
			for(int i=0;i<count;i++)
			{

				String newSubSet = subset.get(i);
				newSubSet = newSubSet.concat(s);
				subset.add(newSubSet);
			}

		}

		return subset;

	}

	public static int getEditDistance(String a,String b)
	{
		int[][]e = new int[a.length()+1][b.length()+1];

		for(int i=0;i<=a.length();i++)
		{
			e[i][0] = i;
		}

		for(int j=0;j<=b.length();j++)
		{
			e[0][j] = j;
		}

		for(int i=0;i<=a.length();i++)
		{
			for(int j=0;j<=b.length();j++)
			{
				if(i==0||j==0) continue;
				if(a.charAt(i-1)==b.charAt(j-1))
				{
					e[i][j] = e[i-1][j-1];
				}else
				{
					int ins = e[i-1][j]+1;
					int del = e[i][j-1]+1;
					int sub = e[i-1][j-1]+1;
					e[i][j] = Math.min(Math.min(ins,del),sub);
				}
			}
		}

		return e[a.length()][b.length()];
	}

	public static boolean isInterleaved(String a,String b,String c)
	{
		if(a.length()+b.length()!= c.length()) return false;

		if(a.isEmpty()||b.isEmpty())
		{
			return (a.concat(b).equals(c));
		}

		if(a.charAt(0)==c.charAt(0))
		{
			return isInterleaved(a.substring(1), b, c.substring(1));
		}

		if(b.charAt(0)==c.charAt(0))
		{
			return isInterleaved(a, b.substring(1),c.substring(1));
		}

		return false;
	}

	public static boolean isTrailing(String a,String b)
	{
		return a.substring(a.length()-b.length()).equals(b);
	}

	public static int longestCommonSubString(String a, String b)
	{
		int[][] lcs = new int[a.length()+1][b.length()+1];
		int max=0;
		String subStr ="";

		for(int i=0;i<=a.length();i++)
		{
			for(int j=0;j<=b.length();j++)
			{
				if(i==0||j==0)
				{
					lcs[i][j] = 0;
					continue;
				}

				if(a.charAt(i-1)==b.charAt(j-1))
				{
					lcs[i][j] = 1 + lcs[i-1][j-1];

				}
				else
				{
					lcs[i][j] = 0;
				}

				if(lcs[i][j] > max)
				{
					max = lcs[i][j];
					subStr = a.substring(i-max, i);
				}
			}
		}

		System.out.println(subStr);

		return max;
	}


	//Less than O(n*m)
	public static boolean containsString(String a1, String b1)
	{
		int i= 0,j=0;
		char[] a = a1.toCharArray();
		char[] b = b1.toCharArray();
		int[] h = new int[256];
		while(i< a.length)
		{
			h[a[i++]-0]++;

		}

		while(j < b.length)
		{

			if(h[b[j]-0]<=0) return false;

			if(h[b[j]-0] > 0) 
			{
				h[b[j++]-0]--;
			}
		}
		return true;

	}

	public static List<String> getWords(String s)
	{
		Set<String> dictionary = new TreeSet<String>();
		List<String> lists = new ArrayList<String>();

		dictionary.add("pine");
		dictionary.add("pineapple");
		dictionary.add("apple");
		dictionary.add("pie");

		for(int i=0;i<=s.length();i++)
		{
			if(dictionary.contains(s.substring(0, i)))
			{
				String suffix = s.substring(i);
				

			}
		}

		return lists;

	}

	public static List<String> permutations(String s)
	{
		List<String> perms = new ArrayList<String>();
		if(s.length()==0) {
			perms.add("");
			return perms;
		}

		char c = s.charAt(0);
		String rem = s.substring(1);
		List<String> p = permutations(rem);
		for(String k:p)
		{
			for(int i=0;i<=k.length();i++)
			{
				String m = k.substring(0,i)+c+k.substring(i);
				perms.add(m);
			}
		}
		return perms;
	}

	
	public static void getPermutations(String s, String permString, List<String> permsList) {
		if(s.length()==0) {
			permsList.add(permString); return;
		}
		
		for(int i=0;i<s.length();i++) {
			getPermutations(s.substring(0, i)+s.substring(i+1), permString+s.charAt(i), permsList);
		}
	}
	
	public static void printPermutations(String s) {
		List<String> permsList = new ArrayList<String>();
		getPermutations(s, "", permsList);
		for(String p:permsList) {
			System.out.println(p);
		}
	}
	
	public static String reverseStringRecursion(String s)
	{
		if(s.length()==0) return "";

		return String.valueOf(s.charAt(s.length()-1)) +reverseStringRecursion(s.substring(0,s.length()-1));
	}

	public static void reverseString(String s)
	{

		int end = 0,start = 0;

		//Reverse the whole string first
		String reversedString = reverseString(s.toCharArray(), start,s.length()-1);

		while(end < s.length())
		{
			if(reversedString.charAt(end) != ' ')
			{
				//Save position of beginning of the word
				start = end;

				//Keep moving forward till you hit a space
				while(end < s.length() && reversedString.charAt(end) !=' ') end++;

				//Move one step back
				end--;

				//Reverse the word
				reversedString = reverseString(reversedString.toCharArray(), start, end);
			}
			end++;
		}

		System.out.println("Output: "+reversedString);
	}

	public static String reverseString(char[] s,int start,int end)
	{
		char temp = ' ';
		while(end > start)
		{
			temp = s[end];
			s[end] = s[start];
			s[start] = temp;
			end--;start++;
		}
		return new String(s);
	}

	public static boolean isSubstring(String x, String y)
	{
		char[]a = x.toCharArray();
		char[]b = y.toCharArray();

		int n = a.length;
		int m = b.length;
		for(int i=0;i<=n - m ;i++)
		{
			int j=0;
			while((j<m) && (a[i+j]==b[j]))
			{
				j++;
			}
			if(j == m) return true;
		}
		return false;
	}

	public static boolean checkParanthesis(String s)
	{
		int counter=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)=='(') counter++;

			if(s.charAt(i) == ')') counter --;
		}
		if(counter ==0) return true;

		return false;
	}

	public static boolean isAnagram2(String s1,String s2)
	{

		if(s1.length()!=s2.length()) return false;

		int[] c1 = new int[256];
		for(int i=0;i<s1.length();i++)
		{
			if(s1.charAt(i)==' ') continue;
			c1[s1.charAt(i)]++;
		}
		for(int i=0;i<s2.length();i++)
		{
			if(s2.charAt(i)==' ') continue;
			c1[s2.charAt(i)]--;
			if(c1[s2.charAt(i)] <0) return false;
		}

		return true;

	}

	public static boolean isAnagram(String s1,String s2)
	{
		if(s1.length()!=s2.length()) return false;

		int[] c1 = new int[256];
		int[] c2 = new int[256];
		for(int i=0;i<s1.length();i++)
		{
			c1[s1.charAt(i)]++;
		}
		for(int i=0;i<s2.length();i++)
		{
			c2[s2.charAt(i)]++;
		}

		for(int i=0;i<s1.length();i++)
		{
			if(c1[i] != c2[i]) return false;
		}

		return true;

	}

	public static void printAnagrams(List<String> words)
	{
		Map<String,List<String>> anagramMap = new HashMap<String,List<String>>();
		for(String s: words)
		{
			String w = getHash(s);
			List<String> anagramsList = anagramMap.get(w);
			if(anagramsList == null) {
				anagramsList = new ArrayList<String>();
			}
			anagramsList.add(s);
			anagramMap.put(w,anagramsList);
		}

		List<String> anagrams = new ArrayList<>();
		for(List<String> aList:anagramMap.values())
		{
			if(aList.size() > 1)
				anagrams.addAll(aList);
		}

		for(String s:anagrams)
		{
			System.out.println(s);
		}

	}

	public static String getHash(String s)
	{
		int[] h = new int[26];
		for(int i=0;i<s.length();i++)
		{
			int a = s.charAt(i)-'a';
			h[a]++;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<26;i++)
		{
			if(h[i]==0) continue;
			char c = (char)('a'+i);
			sb.append(c);
			sb.append(h[i]);
		}
		return sb.toString();
	}

	public static String compress(String s)
	{
		int count=1; char last = s.charAt(0);
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<s.length();i++)
		{
			if(s.charAt(i)==last)
			{
				count++;
			}else
			{
				sb.append(last);
				sb.append(count);
				last = s.charAt(i);
				count = 1;
			}
		}
		sb.append(last);
		sb.append(count);

		return sb.toString();
	}

	public static List<ArrayList<String>> getSubsets(List<String> str)
	{

		List<ArrayList<String>> subset = new ArrayList<ArrayList<String>>();

		int max = 1<<str.size();

		for (int i = 0; i < max;i++)
		{
			ArrayList<String> set = new ArrayList<String>();
			for(int j=0;j<str.size();j++)
			{
				if(((i>>j)&1) == 1) set.add(str.get(j));
			}

			subset.add(set);
		}
		return subset;

	}

	public static void getSubsetsRecursion(List<String> set,int index)
	{
		if(index == set.size())
		{
			String emptySubset = new String("");
			subsets.add(emptySubset);
		}else
		{
			getSubsetsRecursion(set, index+1);
			List<String> tempSubsets = new ArrayList<String>();
			for(String ss: subsets)
			{
				String newSubset = new String("");
				newSubset+=set.get(index);
				newSubset+=ss;
				tempSubsets.add(newSubset);
			}
			subsets.addAll(tempSubsets);
		}
	}




}
