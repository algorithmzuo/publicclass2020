package class117;

// 测试链接：https://leetcode.cn/problems/maximum-product-subarray/
public class Code02_MaximumProductSubarray {

	public static int maxProduct(int[] nums) {
		// 0结尾时，最好答案；
		// 1结尾时，最好答案；... max -> ans
		int ans = nums[0];
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			// i 结尾
			// 1) nums[i]
			// 2) nums[i] * 之前的最大乘积 -> max
			// 3) nums[i] * 之前的最小乘积 -> min
			// i 结尾 最大乘积 : 1) 2) 3) 取最大！
			// i 结尾 最小乘积 : 1) 2) 3) 取最小！
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			min = curmin;
			max = curmax;
			ans = Math.max(ans, max);
		}
		return ans;
	}

}
