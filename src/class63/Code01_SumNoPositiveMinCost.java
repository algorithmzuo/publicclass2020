package class63;

import java.util.Arrays;

// 来自微软面试
// 给定一个正数数组arr长度为n、正数x、正数y
// 你的目标是让arr整体的累加和<=0
// 你可以对数组中的数num执行以下三种操作中的一种，且每个数最多能执行一次操作 : 
// 1）不变
// 2）可以选择让num变成0，承担x的代价
// 3）可以选择让num变成-num，承担y的代价
// 返回你达到目标的最小代价
// 数据规模 : 面试时面试官没有说数据规模
public class Code01_SumNoPositiveMinCost {

	public static int minOpStep(int[] arr, int x, int y) {
		Arrays.sort(arr); // 小 -> 大
		int n = arr.length;
		for (int l = 0, r = n - 1; l <= r; l++, r--) {
			int tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
		}
		// arr 大 -> 小
		if (x >= y) { // 没有任何必要执行x操作
			int sum = 0;
			for (int num : arr) {
				sum += num;
			}
			int cost = 0;
			for (int i = 0; i < n && sum > 0; i++) {
				sum -= arr[i] << 1;
				cost += y;
			}
			return cost;
		} else {
			for (int i = n - 2; i >= 0; i--) {
				arr[i] += arr[i + 1];
			}
			int benefit = 0;
			// 注意，可以不二分，用不回退的方式！
			// 执行Y操作的数，有0个的时候
			int left = mostLeft(arr, 0, benefit);
			int cost = left * x;
			for (int i = 0; i < n - 1; i++) {
				// 0..i 这些数，都执行Y
				benefit += arr[i] - arr[i + 1];
				left = mostLeft(arr, i + 1, benefit);
				cost = Math.min(cost, (i + 1) * y + (left - i - 1) * x);
			}
			return cost;
		}
	}

	// arr是后缀和数组， arr[l...]中找到值<=v的最左位置
	public static int mostLeft(int[] arr, int l, int v) {
		int r = arr.length - 1;
		int m = 0;
		int ans = arr.length;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] <= v) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

}
