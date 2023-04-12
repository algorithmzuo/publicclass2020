package class129;

// 来自亚马逊
// 给定一个长度为 n 的字符串 s ，其中 s[i] 是:
// D 意味着减少
// I 意味着增加
// 有效排列 是对有 n + 1 个在 [0, n]  范围内的整数的一个排列 perm ，使得对所有的 i：
// 如果 s[i] == 'D'，那么 perm[i] > perm[i+1]，以及；
// 如果 s[i] == 'I'，那么 perm[i] < perm[i+1]。
// 返回 有效排列  perm的数量 。因为答案可能很大，所以请返回你的答案对 10^9 + 7 取余。
// 测试链接 : https://leetcode.cn/problems/valid-permutations-for-di-sequence/
public class Code03_ValidPermutationsForDiSequence {
	
	// status : 数字有没有用过，不能这么想，可能性太多了，超过运行时间。10^8的规模
	// 
	//           011010001     0
//	public static int ways(int n, int status,) {
//		
//	}
	
	
	
	
	
	
	

	public static int numPermsDISequence1(String s) {
		return ways1(s.toCharArray(), 0, s.length() + 1, s.length() + 1);
	}

	// i : 已经用了几个数了！可变参数
	// less : 比前一个数(不用管前一个数是什么，假设x)小的数字，还有less个
	// s : str，固定参数，用来描述相邻两数的关系
	// n : 固定参数，一共要使用几个数
	// 返回值(int)：后续满足str关系的排列，有几个?
	// i -> 200
	// less -> 200
	// 200 * 200
	public static int ways1(char[] s, int i, int less, int n) {
		int ans = 0;
		if (i == n) {
			ans = 1;
		} else if (i == 0 || s[i - 1] == 'D') {
			// 	前一个数，是x，
			// a b c d    x    e f g
			//   x > 当前的选择大
			// a  i+1, 0
			// b  i+1, 1
			// c  i+1, 2
			// d  i+1, 3
			for (int nextLess = 0; nextLess < less; nextLess++) {
				ans += ways1(s, i + 1, nextLess, n);
			}
		} else { // s[i-1] = 'I'
			// 前一个数，是x，
			// a b c d    x    e f g
			// less = 4
			// e,    i+1, 4
			// f,    i+1, 5
			// g,    i+1, 6
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
