package my.interviews2017;

import java.util.HashSet;

public class TestCollatzConjecture {

	public static void main(String[] args) {
		try {
			System.out.println(test(100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean test(int m) throws Exception{
		HashSet<Long> verifiedNumbers = new HashSet<>();
		for (int i=3;i<=m;i+=2) { // We will skip even numbers because proving the conjecture is straightforward for even numbers.
			long number = i;
			HashSet<Long> tempVerifiedNumbers = new HashSet<Long>(); //A set to keep track of numbers seen so far for this number
			while (number>=m) { // Keep checking while number is greater than itself, once you reach below it you know you have solved for it previously
				if (tempVerifiedNumbers.contains(number)) return false; // If you encounter an already seen number then you are in an infinite loop so return false
				tempVerifiedNumbers.add(number);
				if (number%2!=0) { // If it's an odd number, check if it is in global list 
					if (verifiedNumbers.contains(number)) break;
					verifiedNumbers.add(number);
					long x = 3*number + 1;
					if (x < number) throw new Exception("Overflow");
					number = x;
				} else {
					number = number/2;
				}
			}
		}
		return true;
	}
}
