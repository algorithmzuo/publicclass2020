package class124;

// 给你一个整数数组 nums
// 请你找出一个具有最大和的连续子数组(子数组最少包含一个元素)
// 返回其最大和
// 子数组 是数组中的一个连续部分
// 测试链接 : https://leetcode.cn/problems/maximum-subarray/
public class Code04_SubArrayMaxSum {

	public static int maxSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			pre = Math.max(arr[i], arr[i] + pre);
			max = Math.max(max, pre);
		}
		return max;
	}

}
