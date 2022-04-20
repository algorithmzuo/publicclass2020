package class084;

// 来自阿里
// 测试链接 : https://www.nowcoder.com/practice/237ae40ea1e84d8980c1d5666d1c53bc
// 提交以下的代码，把类名改成Main
// 可以直接通过
import java.util.Scanner;

public class Code04_ClassicDP {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[] cost = new int[n];
			int[] value = new int[n];
			for (int i = 0; i < n; i++) {
				cost[i] = sc.nextInt();
				value[i] = sc.nextInt();
			}
			int[][] dp = dp(cost, value, m);
			int ans = 0;
			for (int j = 0; j <= m; j++) {
				ans = Math.max(ans, dp[n - 1][j]);
			}
			System.out.println(ans);
			System.out.println(dp[n - 1][m] == -1 ? 0 : dp[n - 1][m]);
		}
		sc.close();
	}

	public static int[][] dp(int[] cost, int[] value, int m) {
		int n = cost.length;
		// dp[i][j] : 背包重量严格等于j，货物在0...i上随意选，最大价值是多少
		int[][] dp = new int[n][m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= m; j++) {
				dp[i][j] = -1;
			}
		}
		for (int k = 1; cost[0] * k <= m; k++) {
			dp[0][cost[0] * k] = k * value[0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= m; j++) {				
				dp[i][j] = dp[i - 1][j];
				if (j - cost[i] >= 0 && dp[i][j - cost[i]] != -1) {
					dp[i][j] = Math.max(dp[i][j], dp[i][j - cost[i]] + value[i]);
				}
			}
		}
		return dp;
	}

}
