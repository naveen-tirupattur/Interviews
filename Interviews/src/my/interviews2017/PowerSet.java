package my.interviews2017;

/**
 * 
 * @author ntirupattur
 * Set  = [a,b,c]
 * power_set_size = pow(2, 3) = 8
 * Run for binary counter = 000 to 111
 * Value of Counter            Subset
    000                    -> Empty set
    001                    -> a
    010                    -> b
    011                    -> ab
    100                    -> c
    101                    -> ac
    110                    -> bc
    111                    -> abc
 *
 */
public class PowerSet {

	public static void main(String[] args) {
		
		getPowerSet("ABCD");
	}
	
	public static void getPowerSet(String s) {
		System.out.println(""); // print empty set
		for (int i=1;i<Math.pow(2,s.length());i++) { // powerset will contain 2^n elements
			StringBuffer sb = new StringBuffer();
			for (int j=0;j<s.length();j++) { // Iterate the length of string 
				if ((i>>j&1)==1) { // Check if the bit at element j is set to 1
					sb.append(s.charAt(j));
				}
			}
			System.out.println(sb.toString());
		}
	}
}
