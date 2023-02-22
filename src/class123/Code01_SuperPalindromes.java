package class123;

// 如果一个正整数自身是回文数，而且它也是一个回文数的平方，那么我们称这个数为超级回文数。
// 现在，给定两个正整数 L 和 R （以字符串形式表示），
// 返回包含在范围 [L, R] 中的超级回文数的数目。
// 测试链接 : https://leetcode.cn/problems/super-palindromes/
public class Code01_SuperPalindromes {

	// L ... R    "123213213" ~ "31283712710381299823"
	public static int superpalindromesInRange(String left, String right) {
		long l = Long.valueOf(left);
		long r = Long.valueOf(right);
		// 限制是根据开方的范围
		// 400 ~ 1 0000 00000 r
		//     ~ 1 0000 r的开方
		long limit = (long) Math.sqrt((double) r);
		int cnt = 0;
		long seed = 1;
		long enlarge = 0;
		do {
			// seed : 开方的一半！左半边
			// seed == 1
			// 1)  11
			// 2)  1
			// seed = 123
			// 1) 123321 -> 开方的可能性
			// 2) 12321
			// 10000 是开方的边界
			// seed = 99
			// 1) 99 99
			// 2) 999
			// seed = 100
			// 100001
			// 10001
			// seed 101 .... 不需要了！
			// enlarge2 加工出偶数长度的开方结果
			// 123 -> 123321
			// 123321 的平方，在不在l ~ r上，且是不是回文！
			enlarge = enlarge2(seed);
			if (isValid(enlarge * enlarge, l, r)) {
				cnt++;
			}
			// enlarge1 加工出奇数长度的开方结果
			enlarge = enlarge1(seed);
			if (isValid(enlarge * enlarge, l, r)) {
				cnt++;
			}
			// 1 11 1   2 22 2  3 33 3  .... 11 1111 111 12 1221 121 ...
			seed++;
		} while (enlarge < limit);
		return cnt;
	}

	// 123 -> 12321
	public static long enlarge1(long seed) {
		long ans = seed;
		seed /= 10;
		while (seed != 0) {
			ans = ans * 10 + seed % 10;
			seed /= 10;
		}
		return ans;
	}

	// 123 -> 123321
	public static long enlarge2(long seed) {
		long ans = seed;
		while (seed != 0) {
			ans = ans * 10 + seed % 10;
			seed /= 10;
		}
		return ans;
	}

	public static boolean isValid(long ans, long l, long r) {
		return isPalindrome(ans) && ans >= l && ans <= r;
	}

	public static boolean isPalindrome(long n) {
		// n =    3721837
		// help = 1000000
		long help = 1;
		while (n / help >= 10) {
			help *= 10;
		}
		// n =    72183
		// help = 10000
		while (n >= 10) {
			if (n / help != n % 10) {
				return false;
			}
			n = (n % help) / 10;
			help /= 100;
		}
		return true;
	}

}
