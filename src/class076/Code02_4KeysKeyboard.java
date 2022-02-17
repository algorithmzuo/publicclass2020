package class076;

// 测试链接 : https://leetcode.com/problems/4-keys-keyboard/
public class Code02_4KeysKeyboard {

	public static int maxA(int n) {
		int[] dp = new int[n + 1];
		// dp[1] : 1
		// dp[2] : 2
		// dp[5] : 5
		for (int i = 1; i <= 5 && i <= n; i++) {
			dp[i] = i;
		}
		//         i
		//-1 -1 -1 粘贴
		for (int i = 6; i <= n; i++) {
			dp[i] = Math.max(
					Math.max(dp[i - 3] * 2, dp[i - 4] * 3),
					Math.max(dp[i - 5] * 4, dp[i - 6] * 5));
		}
		return dp[n];
	}

}
