package class55;

// 本题测试链接 : https://leetcode.com/problems/maximum-subarray/
public class Code02_SubArrayMaxSum {

	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int pre = arr[0];
		int max = pre;
		for (int end = 1; end < N; end++) {
			pre = Math.max(arr[end], pre + arr[end]);
			max = Math.max(max, pre);
		}
		return max;
	}

}
