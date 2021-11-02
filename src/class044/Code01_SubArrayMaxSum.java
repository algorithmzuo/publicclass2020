package class044;

// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
public class Code01_SubArrayMaxSum {

	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] dp = new int[N];
		// 0 arr[0..0]
		dp[0] = arr[0];
		int max = dp[0];
		for (int end = 1; end < N; end++) {
			int p1 = arr[end];
			int p2 = dp[end - 1] + arr[end];
			dp[end] = Math.max(p1, p2);
			max = Math.max(max, dp[end]);
		}
		return max;
	}
	
	
	public static int maxSubArray5(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int pre = arr[0];// 上一步的dp值  pre dp[0]
		int max = pre;
		for (int end = 1; end < N; end++) {
			pre = Math.max(arr[end], pre + arr[end]);
			max = Math.max(max, pre);
		}
		return max;
	}
	
	
	
	
	
	
	

	public static int maxSubArray1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int max = Integer.MIN_VALUE;
		for (int L = 0; L < N; L++) {
			for (int R = L; R < N; R++) {
				int sum = 0;
				for (int i = L; i <= R; i++) {
					sum += arr[i];
				}
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	public static int maxSubArray2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int[] dp = new int[arr.length];
		dp[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			int p1 = arr[i];
			int p2 = dp[i - 1] + arr[i];
			dp[i] = Math.max(p1, p2);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static int maxSubArray3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int pre = arr[0];
		int max = pre;
		for (int i = 1; i < arr.length; i++) {
			pre = Math.max(arr[i], pre + arr[i]);
			max = Math.max(max, pre);
		}
		return max;
	}

}
