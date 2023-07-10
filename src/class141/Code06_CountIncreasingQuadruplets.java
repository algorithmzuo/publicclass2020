package class141;

// 来自思爱普
// 给你一个长度为 n 下标从 0 开始的整数数组 nums
// 它包含 1 到 n 的所有数字，请你返回上升四元组的数目。
// 如果一个四元组 (i, j, k, l) 满足以下条件，我们称它是上升的：
// 0 <= i < j < k < l < n 且
// nums[i] < nums[k] < nums[j] < nums[l] 。
// 测试链接 : https://leetcode.cn/problems/count-increasing-quadruplets/
public class Code06_CountIncreasingQuadruplets {

	public static long countQuadruplets1(int[] nums) {
		int n = nums.length;
		long ans = 0;
		long[] dp = new long[n];
		for (int l = 1; l < n; l++) {
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) {
					ans += dp[j];
				}
			}
			int cnt = 0;
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) {
					cnt++;
				} else {
					dp[j] += cnt;
				}
			}
		}
		return ans;
	}

	// 彻底看不懂版，其实就是上面的代码做了逻辑合并
	public static long countQuadruplets2(int[] nums) {
		int n = nums.length;
		long ans = 0;
		long[] dp = new long[n];
		for (int l = 1; l < n; l++) {
			int cnt = 0;
			for (int j = 0; j < l; j++) {
				if (nums[j] < nums[l]) {
					ans += dp[j];
					cnt++;
				} else {
					dp[j] += cnt;
				}
			}
		}
		return ans;
	}

}
