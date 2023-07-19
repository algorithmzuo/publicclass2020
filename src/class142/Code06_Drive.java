package class142;

import java.util.Arrays;

public class Code06_Drive {

	// 贪心策略 :
	// 假设一共有10个司机，思路是先让所有司机去A，得到一个总收益
	// 然后看看哪5个司机改换门庭(去B)，可以获得最大的额外收益
	public static int maxMoney(int[][] income) {
		int N = income.length;
		int[] arr = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = income[i][1] - income[i][0];
			sum += income[i][0];
		}
		Arrays.sort(arr);
		int M = N >> 1;
		for (int i = N - 1; i >= M; i--) {
			sum += arr[i];
		}
		return sum;
	}

	// 找到了leetcode上的测试
	// leetcode上让求最小，课上讲的求最大
	// 其实是一个意思
	// 公司计划面试 2n 人。给你一个数组 costs
	// 其中 costs[i] = [aCosti, bCosti]
	// 第 i 人飞往 a 市的费用为 aCosti ，飞往 b 市的费用为 bCosti
	// 返回将每个人都飞到 a 、b 中某座城市的最低费用，要求每个城市都有 n 人抵达。
	// 测试链接 : https://leetcode.cn/problems/two-city-scheduling/
	public static int twoCitySchedCost(int[][] costs) {
		int N = costs.length;
		int[] arr = new int[N];
		int sum = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = costs[i][1] - costs[i][0];
			sum += costs[i][0];
		}
		Arrays.sort(arr);
		int M = N >> 1;
		for (int i = 0; i < M; i++) {
			sum += arr[i];
		}
		return sum;
	}

}
