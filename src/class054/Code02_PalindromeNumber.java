package class054;

public class Code02_PalindromeNumber {

	public static boolean isPalindrome(int n) {
		if (n < 0) {
			return false;
		}
		int tmp = 1;
		// num = 7654567
		// tmp = 1000000
		while (n / tmp >= 10) {
			tmp *= 10;
		}
		while (n != 0) {
			if (n / tmp != n % 10) {
				return false;
			}
			// num = 7654567
			// tmp = 1000000
			// n%t = 654567
			// /10 = 65456
			// = 10000
			n = (n % tmp) / 10;
			tmp /= 100;
		}
		return true;
	}

}
