package class125;

// n 个孩子站成一排
// 给你一个整数数组 ratings 表示每个孩子的评分
// 你需要按照以下要求，给这些孩子分发糖果
// 每个孩子至少分配到 1 个糖果
// 相邻两个孩子评分更高的孩子会获得更多的糖果
// 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目
// 测试链接 : https://leetcode.cn/problems/candy/
public class Code03_CandyProblem {

	public static int candy(int[] arr) {
		int n = arr.length;
		int[] left = new int[n];
		left[0] = 1;
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1]) {
				left[i] = left[i - 1] + 1;
			} else {
				left[i] = 1;
			}
		}
		int[] right = new int[n];
		right[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			} else {
				right[i] = 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += Math.max(left[i], right[i]);
		}
		return ans;
	}

}
