package class055;

// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
public class Code02_SubArrayMaxSum {

	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int pre = arr[0]; // 上一步的答案
		int ans = pre;
		for (int i = 1; i < N; i++) {
			// 子数组必须以i位置的数结尾的情况下，最大累加和是多少
			// arr[i] pre + arr[i]
			pre = Math.max(arr[i], pre + arr[i]);
			ans = Math.max(ans, pre);
		}
		return ans;
	}

}
