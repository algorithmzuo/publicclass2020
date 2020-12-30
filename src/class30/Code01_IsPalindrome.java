package class30;

// 测试链接：https://leetcode.com/problems/palindrome-number
public class Code01_IsPalindrome {

	// n<0 不是回文数
	public static boolean isPalindrome(int n) {
		if (n < 0) {
			return false;
		}
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
