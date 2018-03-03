package my.interviews2017.leetcode;

import java.util.HashMap;

public class IntegerToEnglish {

	public static HashMap<Integer, String> map = new HashMap<Integer, String>();
	public static void main(String[] args) {
		fill();
		System.out.println(get(1234567891));
	}

	public static String get(int num) {
		StringBuffer sb = new StringBuffer();
		
		int div = 0;
		if (num < 0) return "";
		if (num == 0) return map.get(num);
		
		// Keep reducing the number
		if (num >=1000000000) {
			div = num/1000000000;
			sb.append(convert(div)+" Billion ");
			num = num%1000000000;
		}
		
		if (num >=1000000) {
			div = num/1000000;
			sb.append(convert(div)+" Million ");
			num = num%1000000;
		}

		if (num >=1000) {
			div = num/1000;
			sb.append(convert(div)+" Thousand ");
			num = num%1000;
		}
		
		if (num >=0) {
			sb.append(" "+convert(num));
		}

		return sb.toString().trim();
	}
	
	public static String convert(int num) {
		StringBuffer sb = new StringBuffer();
		int div = 0;
		// You can have 100 thousands so added logic for 100 here instead of other function
		if (num >=100) {
			div = num/100;
			sb.append(map.get(div)+" Hundred ");
			num = num%100;
		}
		
		if (num >0) {
			if (num > 0 && num <= 20) { // Between 0 and 20 just get the number directly
				sb.append(" "+map.get(num));
			} else {
				div = num/10; // Greater than 20, divide it by 10 and get multiple of 10
				sb.append(" "+map.get(div*10));
				num = num%10;
				if (num>0) // More left in units place get the number
				sb.append(" "+map.get(num));
			}
		}
		return sb.toString();
	}

	public static void fill() {
		map.put(0, "Zero");
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		map.put(4, "Four");
		map.put(5, "Five");
		map.put(6, "Six");
		map.put(7, "Seven");
		map.put(8, "Eight");
		map.put(9, "Nine");
		map.put(10, "Ten");
		map.put(11, "Eleven");
		map.put(12, "Twelve");
		map.put(13, "Thirteen");
		map.put(14, "Fourteen");
		map.put(15, "Fifteen");
		map.put(16, "Sixteen");
		map.put(17, "Seventeen");
		map.put(18, "Eighteen");
		map.put(19, "Nineteen");
		map.put(20, "Twenty");
		map.put(30, "Thirty");
		map.put(40, "Forty");
		map.put(50, "Fifty");
		map.put(60, "Sixty");
		map.put(70, "Seventy");
		map.put(80, "Eighty");
		map.put(90, "Ninety");
	}
}
