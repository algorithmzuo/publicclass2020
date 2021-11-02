package class055;

// 本次测试链接 : https://leetcode.com/problems/maximum-product-subarray/
public class Code03_MaximumProductSubarray {

	public static double max(double[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		double premax = arr[0];
		double premin = arr[0];
		double ans = arr[0];
		for (int i = 1; i < arr.length; i++) {
			double p1 = arr[i];
			double p2 = arr[i] * premax;
			double p3 = arr[i] * premin;
			double curmax = Math.max(Math.max(p1, p2), p3);
			double curmin = Math.min(Math.min(p1, p2), p3);
			ans = Math.max(ans, curmax);
			premax = curmax;
			premin = curmin;
		}
		return ans;
	}
	
	public static int maxProduct(int[] nums) {
		int ans = nums[0];
		int min = nums[0];
		int max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int curmin = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
			int curmax = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
			min = curmin;
			max = curmax;
			ans = Math.max(ans, max);
		}
		return ans;
	}

}
