package class067;

// 测试链接：https://leetcode.com/problems/split-array-largest-sum/
public class Code03_SplitArrayLargestSum {

	public static int splitArray(int[] nums, int M) {
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		int l = 0;
		int r = sum;
		int ans = 0;
		while (l <= r) {
			int mid = (l + r) / 2;
			int cur = need(nums, mid);
			if (cur <= M) {
				ans = mid;
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return ans;
	}

	public static int need(int[] arr, long aim) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > aim) {
				return Integer.MAX_VALUE;
			}
		}
		int parts = 1;
		int all = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (all + arr[i] > aim) {
				parts++;
				all = arr[i];
			} else {
				all += arr[i];
			}
		}
		return parts;
	}

}
