package class090;

// 本题测试链接 : https://leetcode.com/problems/longest-increasing-subsequence
public class Code01_LIS {

//	// O(N^2)
//	// 不够好
//	public static int lisMaxLen1(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		int n = arr.length;
//		int[] dp = new int[n];
//		dp[0] = 1;
//		int max = dp[0];
//		for (int i = 1; i < n; i++) {
//			int pre = 0;
//			for (int j = 0; j < i; j++) {
//				if (arr[j] < arr[i]) {
//					pre = Math.max(pre, dp[j]);
//				}
//			}
//			dp[i] = pre + 1;
//			max = Math.max(max, dp[i]);
//		}
//		return max;
//	}
//
//	public static int lisMaxLen2(int[] arr) {
//		if (arr == null || arr.length == 0) {
//			return 0;
//		}
//		int n = arr.length;
//		int[] dp = new int[n];
//		int[] ends = new int[n];
//		dp[0] = 1;
//		ends[0] = arr[0];
//		// ends数组，有效区
//		// ends = 1....
//		// 0...validSize-1 范围上去二分！
//		int validSize = 1;
//		int max = dp[0];
//		for (int i = 1; i < n; i++) {
//			int cur = arr[i];
//			int endsi = find(ends, validSize, cur);
//			if(endsi == -1) { // ends数组有效区里，都是 < num
//				ends[validSize++] = cur;
//				dp[i] = validSize;
//			} else { // 查找>=num最左的位置，找到了！
//				ends[endsi] = cur;
//				dp[i] = endsi + 1;
//			}
//			max = Math.max(max, dp[i]);
//		}
//		return max;
//	}
//
//	// 在ends数组有效区里，查找>=num最左的位置
//	// 如果没有>=num最左的位置，返回-1
//	public static int find(int[] ends, int size, int num) {
//
//	}

	// 最优解！
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