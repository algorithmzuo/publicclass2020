package class086;

import java.util.Arrays;

// 来自字节
// 5.6笔试
// 给定N件物品，每个物品有重量(w[i])、有价值(v[i])
// 只能最多选两件商品，重量不超过bag，返回价值最大能是多少？
// N <= 10^5, w[i] <= 10^5, v[i] <= 10^5, bag <= 10^5
// 本题的关键点：什么数据范围都很大，唯独只需要最多选两件商品，这个可以利用一下
public class Code01_TwoObjectMaxValue {

	// 暴力方法
	// 为了验证而写的方法
	public static int max1(int[] w, int[] v, int bag) {
		return process1(w, v, 0, 2, bag);
	}

	public static int process1(int[] w, int[] v, int index, int restNumber, int restWeight) {
		if (restNumber < 0 || restWeight < 0) {
			return -1;
		}
		if (index == w.length) {
			return 0;
		}
		int p1 = process1(w, v, index + 1, restNumber, restWeight);
		int p2 = -1;
		int next = process1(w, v, index + 1, restNumber - 1, restWeight - w[index]);
		if (next != -1) {
			p2 = v[index] + next;
		}
		return Math.max(p1, p2);
	}

	// 正式方法
	// 时间复杂度O(N * logN)
	public static int max2(int[] w, int[] v, int bag) {
		int n = w.length;
		int[][] arr = new int[n][2];
		// [  [20,6], [14,8] , [3, 2]        ]
		for (int i = 0; i < n; i++) {
			arr[i][0] = w[i];
			arr[i][1] = v[i];
		}
		// 根据重量排序
		// [  [20,6], [14,8] , [3, 2]        ]
		// [  [3,2] 、[14,8] 、 [20,6] ]
		Arrays.sort(arr, (a, b) -> (a[0] - b[0]));
		// arr是二维数组，但是，只使用价值，不使用重量！
		RMQ rmq = new RMQ(arr);
		int ans = 0;
		for (int i = 0, j = 1; i < n && arr[i][0] <= bag; i++, j++) {
			int right = right(arr, bag - arr[i][0]) + 1;
			int rest = 0;
			// right:查询的和你搭配的货，的范围！
			// j
			if (right == j) {
				rest = rmq.max(1, right - 1);
			} else if (right < j) {
				rest = rmq.max(1, right);
			} else {
				rest = Math.max(rmq.max(1, j - 1), rmq.max(j + 1, right));
			}
			ans = Math.max(ans, arr[i][1] + rest);
		}
		return ans;
	}

	public static int right(int[][] arr, int limit) {
		int l = 0;
		int r = arr.length - 1;
		int m = 0;
		int ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m][0] <= limit) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	public static class RMQ {
		public int[][] max;

		public RMQ(int[][] arr) {
			// n = 16个数
			int n = arr.length;
			// k = 4
			int k = power2(n);
			// 行：1~n
			// 列：0~4
			// 请注意：
			// 数组来说，下标从0开始
			// 对于RMQ来说，下标从1开始
			max = new int[n + 1][k + 1];
			for (int i = 1; i <= n; i++) {
				max[i][0] = arr[i - 1][1];
			}
			for (int j = 1; (1 << j) <= n; j++) {
				for (int i = 1; i + (1 << j) - 1 <= n; i++) {
					max[i][j] = Math.max(max[i][j - 1], max[i + (1 << (j - 1))][j - 1]);
				}
			}
		}

		public int max(int l, int r) {
			if (r < l) {
				return 0;
			}
			int k = power2(r - l + 1);
			return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
		}

		// 求2的几次方，离m最近，又不超过m
		private int power2(int m) {
			int ans = 0;
			while ((1 << ans) <= (m >> 1)) {
				ans++;
			}
			return ans;
		}

	}
	
	
	
	public static class RMQZuo {
		public int[][] max;// 如果传入的数组长度是N，max大小:N*logN大小

		public RMQZuo(int[] arr) {
			// n = 16个数
			int n = arr.length;
			// k = 4
			int k = power2(n);
			// 行：1~n
			// 列：0~4
			// 请注意：
			// 数组来说，下标从0开始
			// 对于RMQ来说，下标从1开始
			max = new int[n + 1][k + 1];
			for (int i = 1; i <= n; i++) {
				max[i][0] = arr[i-1];
			}
			for (int j = 1; (1 << j) <= n; j++) {
				for (int i = 1; i + (1 << j) - 1 <= n; i++) {
					// 10....8(2的3次方)
					// 10...4 14...4
					
					
					// max[10][3] = ?
					// max[10][2] 10...2的2次方个数(4个)
					// i + (1 << (j - 1)) -> 10 + 4 -> 14...2的2次方个数(4个)
					
					max[i][j] = 
							Math.max(max[i][j - 1],
									max[i + (1 << (j - 1))][j - 1]);
				}
			}
		}

		// O(1)
		public int max(int l, int r) {
			if (r < l) {
				return 0;
			}
			int k = power2(r - l + 1);
			// l....r
			// r - l + 1有几个数？2的k次方，离这个个数，最近，且不超过
			// l....  ?....r
			// l...r -> 3...11
			// 11 - 3 + 1 = 9
			// 2的3次方 = 8 <= 9
			// k = 3
			// l...2的3次方个数: max[l][k]
			// r - (1 << k) + 1
			// 11 - 8 + 1 -> 4
			return Math.max(max[l][k], max[r - (1 << k) + 1][k]);
		}

		// 求2的几次方，离m最近，又不超过m
		private int power2(int m) {
			int ans = 0;
			while ((1 << ans) <= (m >> 1)) {
				ans++;
			}
			return ans;
		}

	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v);
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 12;
		int V = 20;
		int testTimes = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] w = randomArray(n, V);
			int[] v = randomArray(n, V);
			int bag = (int) (Math.random() * V * 3);
			int ans1 = max1(w, v, bag);
			int ans2 = max2(w, v, bag);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
