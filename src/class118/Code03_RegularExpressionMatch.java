package class118;

// 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串
// 测试链接 : https://leetcode.cn/problems/regular-expression-matching/
public class Code03_RegularExpressionMatch {

	// 暴力递归方法
	public static boolean isMatch1(String str, String exp) {
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		return f1(s, e, 0, 0);
	}

	public static boolean f1(char[] s, char[] e, int si, int ei) {
		if (ei == e.length) {
			return si == s.length;
		}
		if (si == s.length) {
			return ei + 1 < e.length && e[ei + 1] == '*' && f1(s, e, si, ei + 2);
		}
		if (ei + 1 >= e.length || e[ei + 1] != '*') {
			return (s[si] == e[ei] || e[ei] == '.') && f1(s, e, si + 1, ei + 1);
		}
		// e[ei + 1] == '*'
		boolean ans = f1(s, e, si, ei + 2);
		if (s[si] == e[ei] || e[ei] == '.') {
			ans |= f1(s, e, si + 1, ei);
		}
		return ans;
	}

	// 暴力递归改的动态规划方法
	// 有套路的！课上讲这个套路的！非常有用！
	public static boolean isMatch2(String str, String exp) {
		char[] s = str.toCharArray();
		char[] e = exp.toCharArray();
		int[][] dp = new int[s.length + 1][e.length + 1];
		return f2(s, e, 0, 0, dp);
	}

	public static boolean f2(char[] s, char[] e, int si, int ei, int[][] dp) {
		if (dp[si][ei] != 0) {
			return dp[si][ei] == 1;
		}
		boolean ans = false;
		if (ei == e.length) {
			ans = si == s.length;
		} else if (si == s.length) {
			ans = ei + 1 < e.length && e[ei + 1] == '*' && f2(s, e, si, ei + 2, dp);
		} else if (ei + 1 >= e.length || e[ei + 1] != '*') {
			ans = (s[si] == e[ei] || e[ei] == '.') && f2(s, e, si + 1, ei + 1, dp);
		} else {
			ans = f2(s, e, si, ei + 2, dp);
			if (s[si] == e[ei] || e[ei] == '.') {
				ans |= f2(s, e, si + 1, ei, dp);
			}
		}
		dp[si][ei] = ans ? 1 : -1;
		return ans;
	}

}
