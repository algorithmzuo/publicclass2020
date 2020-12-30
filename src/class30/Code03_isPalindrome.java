package class30;

// 测试链接：https://leetcode.com/problems/palindrome-number
public class Code03_isPalindrome {

	public static boolean isPalindrome(int n) {
		if (n < 0) {
			return false;
		}
		n = Math.abs(n);
		int help = 1;
		while (n / help >= 10) {
			help *= 10;
		}
		while (n != 0) {
			if (n / help != n % 10) {
				return false;
			}
			n = (n % help) / 10;
			help /= 100;
		}
		return true;
	}

}
