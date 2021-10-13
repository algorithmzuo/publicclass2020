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

	public static int minOpStep1(int[] arr, int x, int y) {
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

	public static int minOpStep2(int[] arr, int x, int y) {
		Arrays.sort(arr);
		int n = arr.length;
		for (int l = 0, r = n - 1; l <= r; l++, r--) {
			int tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
		}
		if (x >= y) {
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
			int cost = n * x;
			for (int i = 0, r = n; i < r - 1; i++) {
				benefit += arr[i] - arr[i + 1];
				while (r - 1 > i && arr[r - 1] <= benefit) {
					r--;
				}
				cost = Math.min(cost, (i + 1) * y + (r - i - 1) * x);
			}
			return cost;
		}
	}

	// 为了测试
	public static int[] randomArray(int len, int v) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}
		return arr;
	}

	// 为了测试
	public static int[] copyArray(int[] arr) {
		int[] ans = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ans[i] = arr[i];
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int n = 1000;
		int v = 500;
		int c = 50;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * n);
			int[] arr = randomArray(len, v);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int x = (int) (Math.random() * c);
			int y = (int) (Math.random() * c);
			int ans1 = minOpStep1(arr1, x, y);
			int ans2 = minOpStep2(arr2, x, y);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
