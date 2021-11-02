package class053;

// 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
public class Code05_LIS {

	// 时间复杂度O(N^2)
	public static int maxLen(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		dp[0] = 1;
		int max = 1;
		for (int i = 1; i < N; i++) {
			dp[i] = 1;
			int preMax = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					preMax = Math.max(preMax, dp[j]);
				}
			}
			dp[i] = Math.max(dp[i], preMax + 1);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static int lengthOfLIS(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			max = Math.max(max, l + 1);
		}
		return max;
	}

}