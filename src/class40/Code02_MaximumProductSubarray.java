package class40;

// 本次测试链接 : https://leetcode.com/problems/maximum-product-subarray/
public class Code02_MaximumProductSubarray {

	public static int maxProduct(int[] nums) {
		// 答案，最终要返回的！
		int ans = nums[0];
		// min : 必须以0结尾时，最小累乘积
		int min = nums[0];
		// min : 必须以0结尾时，最大累乘积
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			// curmin : 必须以i结尾时，最小累乘积
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			min = curmin;
			max = curmax;
			ans = Math.max(ans, max);
		}
		return ans;
	}

}
