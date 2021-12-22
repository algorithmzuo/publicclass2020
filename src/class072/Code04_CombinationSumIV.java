package class072;

import java.util.Arrays;

// 测试链接 : https://leetcode.com/problems/combination-sum-iv/
public class Code04_CombinationSumIV {

	public static int[] dp = new int[1001];

	public static int combinationSum4(int[] nums, int target) {
		Arrays.fill(dp, 0, target + 1, -1);
		return process1(nums, target);
	}

	public static int process1(int[] nums, int rest) {
		if (rest < 0) {
			return 0;
		}
		if (dp[rest] != -1) {
			return dp[rest];
		}
		int ans = 0;
		if (rest == 0) {
			ans = 1;
		} else {
			for (int num : nums) {
				ans += process1(nums, rest - num);
			}
		}
		dp[rest] = ans;
		return ans;
	}

//	// 剪枝 + 严格位置依赖的动态规划
//	public static int combinationSum4(int[] nums, int target) {
//		Arrays.sort(nums);
//		int[] dp = new int[target + 1];
//		dp[0] = 1;
//		for (int rest = 1; rest <= target; rest++) {
//			for (int i = 0; i < nums.length && nums[i] <= rest; i++) {
//				dp[rest] += dp[rest - nums[i]];
//			}
//		}
//		return dp[target];
//	}

}
