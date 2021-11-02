package class058;

// 来自哈喽单车
// 本题是leetcode原题 : https://leetcode.com/problems/stone-game-iv/
public class Code03_StoneGameIV {

	// 当前的！拿分数的人，如果面对x这个分数，最终 当前的！拿分数的人 会不会赢
	public static boolean win(int x) {
		if (x == 0) {
			return false;
		}
		for (int p = 1; p * p <= x; p++) {
			// 某一个决定
			int pick = p * p;
			// 下一轮的对手，面对的分值
			int rest = x - pick;
			if (!win(rest)) {
				return true;
			}
		}
		return false;
	}

	public static boolean win2(int n) {
		// f[x] -> win(x)
		boolean[] f = new boolean[n + 1];
		// f[0] -> win(0) -> false
		for (int i = 1; i <= n; i++) {
			// f[1] : f[0...0]
			// f[2] : f[0...1]
			// f[3] : f[0...2]
			// f[i] : f[0...i-1]
			for (int p = 1; p * p <= i; p++) {
				if (!f[i - p * p]) {
					f[i] = true;
					break;
				}
			}
		}
		return f[n];
	}

	// 当前的！先手，会不会赢
	// 打表，不能发现规律
	public static boolean winnerSquareGame1(int n) {
		if (n == 0) {
			return false;
		}
		// 当前的先手，会尝试所有的情况，1，4，9，16，25，36....
		for (int i = 1; i * i <= n; i++) {
			// 当前的先手，决定拿走 i * i 这个平方数
			// 它的对手会不会赢？ winnerSquareGame1(n - i * i)
			if (!winnerSquareGame1(n - i * i)) {
				return true;
			}
		}
		return false;
	}

	public static boolean winnerSquareGame2(int n) {
		int[] dp = new int[n + 1];
		dp[0] = -1;
		return process2(n, dp);
	}

	public static boolean process2(int n, int[] dp) {
		if (dp[n] != 0) {
			return dp[n] == 1 ? true : false;
		}
		boolean ans = false;
		for (int i = 1; i * i <= n; i++) {
			if (!process2(n - i * i, dp)) {
				ans = true;
				break;
			}
		}
		dp[n] = ans ? 1 : -1;
		return ans;
	}

	public static boolean winnerSquareGame3(int n) {
		boolean[] dp = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j * j <= i; j++) {
				if (!dp[i - j * j]) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int n = 10000000;
		System.out.println(winnerSquareGame3(n));
	}

}
