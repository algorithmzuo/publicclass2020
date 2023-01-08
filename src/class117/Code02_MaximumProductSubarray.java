package class117;

// 测试链接：https://leetcode.cn/problems/maximum-product-subarray/
public class Code02_MaximumProductSubarray {

	public static int maxProduct(int[] nums) {
		int ans = nums[0];
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			min = curmin;
			max = curmax;
			ans  = Math.max(ans, max);
		}
		return ans;
	}

}
