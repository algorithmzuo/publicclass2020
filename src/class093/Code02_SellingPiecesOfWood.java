package class093;

// 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。
// 同时给你一个二维整数数组 prices ，其中 prices[i] = [hi, wi, pricei] 
// 表示你可以以 pricei 元的价格卖一块高为 hi 宽为 wi 的矩形木块。
// 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：
// 沿垂直方向按高度 完全 切割木块，或
// 沿水平方向按宽度 完全 切割木块
// 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。
// 你可以卖多块同样尺寸的木块。
// 你不需要将所有小木块都卖出去。
// 你 不能 旋转切好后木块的高和宽。
// 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。
// 注意你可以切割木块任意次。
// 测试链接 : https://leetcode.cn/problems/selling-pieces-of-wood/
public class Code02_SellingPiecesOfWood {

	public static int zuo(int m, int n, int[][] projects) {
		// 50 * 60
		int[][] prices = new int[m + 1][n + 1];
		for (int[] project : projects) {
			int row = project[0];
			int col = project[1];
			int value = project[2];
			prices[row][col] = Math.max(prices[row][col], value);
		}
		return maxMoney(m, n, prices);
	}

	// 当前还剩下的木板，规格 : m * n
	// 请按照题目说的方式来切割！
	// 每一种规格的报价，都在prices里面
	// 比如，想要查 10 * 20规格的木板，的最好报价，prices[10][20] 值！
	// 返回最大的钱数！
	public static int maxMoney(int m, int n, int[][] prices) {
		if (m == 0 || n == 0) {
			return 0;
		}
		// m > 0, n > 0
		// 可能性1 : 当前的木板根本不切，看看有没有报价
		int p1 = prices[m][n];
		// 可能性2 : 当前的木板水平切
		int p2 = 0;
		for (int up = 1; up < m; up++) {
			int down = m - up;
			int upMoney = maxMoney(up, n, prices);
			int downMoney = maxMoney(down, n, prices);
			int cur = upMoney + downMoney;
			p2 = Math.max(p2, cur);
		}
		// 可能性3 : 当前木板，垂直切
		int p3 = 0;
		for (int left = 1; left < n; left++) {
			int right = n - left;
			int leftMoney = maxMoney(m, left, prices);
			int rightMoney = maxMoney(m, right, prices);
			int cur = leftMoney + rightMoney;
			p3 = Math.max(p3, cur);
		}
		return Math.max(p1, Math.max(p2, p3));
	}

	// 递归尝试版本
	public static long sellingWood1(int m, int n, int[][] prices) {
		// 单一报价
		long[][] values = new long[m + 1][n + 1];
		// 2 * 7 10元
		// 2 * 7 100元
		for (int[] p : prices) {
			values[p[0]][p[1]] = Math.max(values[p[0]][p[1]], p[2]);
		}
		return f1(m, n, values);
	}

	public static long f1(int m, int n, long[][] values) {
		if (m == 0 || n == 0) {
			return 0;
		}
		long ans = values[m][n];
		for (int split = 1; split < m; split++) {
			ans = Math.max(ans, f1(split, n, values) + f1(m - split, n, values));
		}
		for (int split = 1; split < n; split++) {
			ans = Math.max(ans, f1(m, split, values) + f1(m, n - split, values));
		}
		return ans;
	}

	// 递归版本 + 记忆化搜索
	public static long sellingWood2(int m, int n, int[][] prices) {
		long[][] values = new long[m + 1][n + 1];
		for (int[] p : prices) {
			values[p[0]][p[1]] = Math.max(values[p[0]][p[1]], p[2]);
		}
		long[][] dp = new long[m + 1][n + 1];
		// dp[10][20] ：没算过，-1
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = -1;
			}
		}
		return f2(m, n, values, dp);
	}

	// m n 一旦确定，返回值，确定！
	// dp[m][n] == -1 没算过！
	// dp[m][n] != -1 之前算过！结果是什么呢？dp[m][n]的值
	public static long f2(int m, int n, long[][] values, long[][] dp) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (dp[m][n] != -1) {
			return dp[m][n];
		}
		long ans = values[m][n];
		for (int split = 1; split < m; split++) {
			ans = Math.max(ans, f2(split, n, values, dp) + f2(m - split, n, values, dp));
		}
		for (int split = 1; split < n; split++) {
			ans = Math.max(ans, f2(m, split, values, dp) + f2(m, n - split, values, dp));
		}
		dp[m][n] = ans;
		return ans;
	}

	// 严格位置依赖的动态规划版本 + 优化
	// 优化1 : 递归的形式，改成迭代形式，课上讲了
	// 优化2 : prices中的单块收益直接填入dp表即可，如果有更好的分割方案，更新掉
	// 优化3 : 分割只需要枚举一半即可
	public static long sellingWood3(int m, int n, int[][] prices) {
		// dp表！
		long[][] dp = new long[m + 1][n + 1];
		for (int[] p : prices) {
			// {3, 5, 100}
			// 0 1 2
			// dp[3][5] = 100
			dp[p[0]][p[1]] = p[2];
		}
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// 垂直分割
				// i * j = 100 * 100
				// dp[100][1] + dp[100][99]
				// dp[100][2] + dp[100][98]
				// ..
				for (int k = 1; k <= (j >> 1); k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
				}
				// 水平分割
				// 100 * 100
				// 1) 1 * 100 + 99 * 100
				// 1) 2 * 100 + 98 * 100
				// i * j
				// 1) 1 * j + (i - 1) * i;
				// 2) 2 * j + (i - 2) * j;
				// k) k * j + (i - k) * j;
				for (int k = 1; k <= (i >> 1); k++) {
					dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
				}
			}
		}
		return dp[m][n];
	}

}
