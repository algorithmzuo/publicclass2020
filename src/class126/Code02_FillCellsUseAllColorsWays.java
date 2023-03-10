package class126;

import java.util.Arrays;

// 来自学员问题
// 给定N、M两个参数
// 一共有N个格子，每个格子可以涂上一种颜色，颜色在M种里选
// 当涂满N个格子，并且M种颜色都使用了，叫一种有效方法
// 求一共有多少种有效方法
// 1 <= N, M <= 5000
// 返回结果比较大，请把结果 % 1000000007 之后返回
public class Code02_FillCellsUseAllColorsWays {

	// 暴力方法
	// 为了验证
	public static int ways1(int n, int m) {
		return process(new int[n], new boolean[m + 1], 0, n, m);
	}

	public static int process(int[] path, boolean[] set, int i, int n, int m) {
		if (i == n) {
			Arrays.fill(set, false);
			int colors = 0;
			for (int c : path) {
				if (!set[c]) {
					set[c] = true;
					colors++;
				}
			}
			return colors == m ? 1 : 0;
		} else {
			int ans = 0;
			for (int j = 1; j <= m; j++) {
				path[i] = j;
				ans += process(path, set, i + 1, n, m);
			}
			return ans;
		}
	}

	// 正式方法
	// 时间复杂度O(N*M)
	public static int MAXN = 5001;

	public static int[][] dp = new int[MAXN][MAXN];

	public static int mod = 1000000007;

	public static int ways2(int n, int m) {
		for (int i = 1; i <= n; i++) {
			dp[i][1] = m;
		}
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= m; j++) {
				dp[i][j] = (int) (((long) dp[i - 1][j] * j) % mod);
				dp[i][j] = (int) ((((long) dp[i - 1][j - 1] * (m - j + 1)) + dp[i][j]) % mod);
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		int N = 9;
		int M = 9;
		System.out.println("功能测试开始");
		for (int n = 1; n <= N; n++) {
			for (int m = 1; m <= M; m++) {
				int ans1 = ways1(n, m);
				int ans2 = ways2(n, m);
				if (ans1 != ans2) {
					System.out.println("出错了!");
					System.out.println("n : " + n);
					System.out.println("m : " + m);
					System.out.println("ans1 : " + ans1);
					System.out.println("ans2 : " + ans2);
					break;
				}
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int n = 5000;
		int m = 4877;
		System.out.println("n : " + n);
		System.out.println("m : " + m);
		long start = System.currentTimeMillis();
		int ans = ways2(n, m);
		long end = System.currentTimeMillis();
		System.out.println("取余之后的结果 : " + ans);
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
