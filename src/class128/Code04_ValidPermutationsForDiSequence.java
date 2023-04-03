package class128;

// 来自亚马逊
// 给定一个长度为 n 的字符串 s ，其中 s[i] 是:
// D 意味着减少
// I 意味着增加
// 有效排列 是对有 n + 1 个在 [0, n]  范围内的整数的一个排列 perm ，使得对所有的 i：
// 如果 s[i] == 'D'，那么 perm[i] > perm[i+1]，以及；
// 如果 s[i] == 'I'，那么 perm[i] < perm[i+1]。
// 返回 有效排列  perm的数量 。因为答案可能很大，所以请返回你的答案对 10^9 + 7 取余。
// 测试链接 : https://leetcode.cn/problems/valid-permutations-for-di-sequence/
public class Code04_ValidPermutationsForDiSequence {

	public static int numPermsDISequence1(String s) {
		return ways1(s.toCharArray(), 0, s.length() + 1, s.length() + 1);
	}

	public static int ways1(char[] s, int i, int less, int n) {
		int ans = 0;
		if (i == n) {
			ans = 1;
		} else if (i == 0 || s[i - 1] == 'D') {
			for (int nextLess = 0; nextLess < less; nextLess++) {
				ans += ways1(s, i + 1, nextLess, n);
			}
		} else { // s[i-1] = 'I'
			for (int nextLess = less; nextLess < n - i; nextLess++) {
				ans += ways1(s, i + 1, nextLess, n);
			}
		}
		return ans;
	}

	public static int numPermsDISequence2(String str) {
		int mod = 1000000007;
		char[] s = str.toCharArray();
		int n = s.length + 1;
		int[][] dp = new int[n + 1][n + 1];
		for (int less = 0; less <= n; less++) {
			dp[n][less] = 1;
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int less = 0; less <= n; less++) {
				if (i == 0 || s[i - 1] == 'D') {
					for (int nextLess = 0; nextLess < less; nextLess++) {
						dp[i][less] = (dp[i][less] + dp[i + 1][nextLess]) % mod;
					}
				} else {
					for (int nextLess = less; nextLess < n - i; nextLess++) {
						dp[i][less] = (dp[i][less] + dp[i + 1][nextLess]) % mod;
					}
				}
			}
		}
		return dp[0][n];
	}

	// 通过观察方法2，得到优化枚举的方法
	public static int numPermsDISequence3(String str) {
		int mod = 1000000007;
		char[] s = str.toCharArray();
		int n = s.length + 1;
		int[][] dp = new int[n + 1][n + 1];
		for (int less = 0; less <= n; less++) {
			dp[n][less] = 1;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (i == 0 || s[i - 1] == 'D') {
				for (int less = 0; less <= n; less++) {
					dp[i][less] = less - 1 >= 0 ? ((dp[i][less - 1] + dp[i + 1][less - 1]) % mod) : 0;
				}
			} else { // s[i-1] = 'I'
				dp[i][n - i - 1] = dp[i + 1][n - i - 1];
				for (int less = n - i - 2; less >= 0; less--) {
					dp[i][less] = (dp[i][less + 1] + dp[i + 1][less]) % mod;
				}
			}
		}
		return dp[0][n];
	}

}
