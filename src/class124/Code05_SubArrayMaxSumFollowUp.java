package class124;

// 你是一个专业的小偷，计划偷窃沿街的房屋
// 每间房内都藏有一定的现金
// 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统
// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
// 给定一个代表每个房屋存放金额的非负整数数组
// 计算你不触动警报装置的情况下
// 一夜之内能够偷窃到的最高金额
// 测试链接 : https://leetcode.cn/problems/house-robber/
public class Code05_SubArrayMaxSumFollowUp {

	public static int rob(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		if (N == 1) {
			return arr[0];
		}
		if (N == 2) {
			return Math.max(arr[0], arr[1]);
		}
		int[] dp = new int[N];
		dp[0] = arr[0];
		dp[1] = Math.max(arr[0], arr[1]);
		for (int i = 2; i < N; i++) {
			dp[i] = Math.max(Math.max(dp[i - 1], arr[i]), arr[i] + dp[i - 2]);
		}
		return dp[N - 1];
	}

}
