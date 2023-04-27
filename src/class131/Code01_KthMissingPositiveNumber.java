package class131;

// 来自Facebook、亚马逊、谷歌
// 给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。
// 请你找到这个数组里第 k 个缺失的正整数。
// 测试链接 : https://leetcode.cn/problems/kth-missing-positive-number/
public class Code01_KthMissingPositiveNumber {

	public int findKthPositive(int[] arr, int k) {
		int l = 0;
		int r = arr.length - 1;
		// 0 ~ n-1
		int m = 0;
		// 对号的位置！find！
		int find = arr.length;
		while (l <= r) {
			// 中点下标，m
			m = (l + r) / 2;
			// arr[m] - (m + 1)
			if (arr[m] - (m + 1) >= k) {
				// 缺失的第k个正整数
				// 在 1 ~ arr[m]
				// 范围能不能继续变小，去左侧二分
				// l ..... m  ..... r
				// l     r
				find = m;
				r = m - 1;
			} else {
				// 缺失的第k个正整数
				// 不在 1 ~ arr[m]范围上，不够
				l = m + 1;
			}
		}
		//      x 100
		//     16 17(find)
		// find
		// 0
		//                x
		//  0 1 2 3 4 5 6 7 8(find)
		int preValue = find == 0 ? 0 : arr[find - 1];
		// preValue + ?
		int under = preValue - find;
		return preValue + (k - under);
	}
}
