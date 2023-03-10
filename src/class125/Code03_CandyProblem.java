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
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] left = new int[N];
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] < arr[i]) {
				left[i] = left[i - 1] + 1;
			}
		}
		int[] right = new int[N];
		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += Math.max(left[i], right[i]);
		}
		return ans + N;
	}

}
