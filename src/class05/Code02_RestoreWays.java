package class05;

public class Code02_RestoreWays {

	/*
	 * 整型数组arr长度为n(3 <= n <= 10^4)，最初每个数字是<=200的正数且满足如下条件： 1. arr[0] <= arr[1] 2.
	 * arr[n-1] <= arr[n-2] 3. arr[i] <= max(arr[i-1], arr[i+1])
	 * 但是在arr有些数字丢失了，比如k位置的数字之前是正数，丢失之后k位置的数字为0。 请你根据上述条件， 计算可能有多少种不同的arr可以满足以上条件。
	 * 比如 [6,0,9] 只有还原成 [6,9,9]满足全部三个条件，所以返回1种。
	 * 
	 */

	public static int ways1(int[] arr) {
		return process1(arr, 0);
	}

	public static int process1(int[] arr, int index) {
		if (index == arr.length) {
			return isValid(arr) ? 1 : 0;
		} else {
			if (arr[index] != 0) {
				return process1(arr, index + 1);
			} else {
				int ways = 0;
				for (int v = 1; v < 201; v++) {
					arr[index] = v;
					ways += process1(arr, index + 1);
				}
				arr[index] = 0;
				return ways;
			}
		}
	}

	public static boolean isValid(int[] arr) {
		if (arr[0] > arr[1]) {
			return false;
		}
		if (arr[arr.length - 1] > arr[arr.length - 2]) {
			return false;
		}
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] > Math.max(arr[i - 1], arr[i + 1])) {
				return false;
			}
		}
		return true;
	}

	public static int ways2(int[] array) {
		int N = array.length + 3;
		int[] arr = new int[N];
		arr[0] = 1;
		arr[1] = 1;
		arr[N - 1] = 1;
		for (int i = 2; i < N - 1; i++) {
			arr[i] = array[i - 2];
		}
		// dp[i][j][0] 表示arr[i] == j 且一定小于arr[i-1]的情况下，合法的数量
		// dp[i][j][1] 表示arr[i] == j 且一定等于arr[i-1]的情况下，合法的数量
		// dp[i][j][2] 表示arr[i] == j 且一定大于arr[i-1]的情况下，合法的数量
		int[][][] dp = new int[N][201][3];
		dp[1][1][1] = 1;
		for (int i = 2; i < N; i++) {
			if (arr[i] != 0) {
				for (int pre = arr[i] + 1; pre < 201; pre++) {
					dp[i][arr[i]][0] += dp[i - 1][pre][0] + dp[i - 1][pre][1];
				}
				dp[i][arr[i]][1] = dp[i - 1][arr[i]][0] + dp[i - 1][arr[i]][1] + dp[i - 1][arr[i]][2];
				for (int pre = 1; pre < arr[i]; pre++) {
					dp[i][arr[i]][2] += dp[i - 1][pre][0] + dp[i - 1][pre][1] + dp[i - 1][pre][2];
				}
			} else {
				for (int v = 1; v < 201; v++) {
					for (int pre = v + 1; pre < 201; pre++) {
						dp[i][v][0] += dp[i - 1][pre][0] + dp[i - 1][pre][1];
					}
					dp[i][v][1] = dp[i - 1][v][0] + dp[i - 1][v][1] + dp[i - 1][v][2];
					for (int pre = 1; pre < v; pre++) {
						dp[i][v][2] += dp[i - 1][pre][0] + dp[i - 1][pre][1] + dp[i - 1][pre][2];
					}
				}
			}
		}
		return dp[N - 1][1][0] + dp[N - 1][1][1];
	}

	public static int ways3(int[] array) {
		int N = array.length + 3;
		int[] arr = new int[N];
		arr[0] = 1;
		arr[1] = 1;
		arr[N - 1] = 1;
		for (int i = 2; i < N - 1; i++) {
			arr[i] = array[i - 2];
		}
		int[][][] dp = new int[N][201][3];
		dp[1][1][1] = 1;
		int[][] sum = new int[201][3];
		for (int v = 1; v < 201; v++) {
			sum[v][1] = 1;
		}
		for (int i = 2; i < N; i++) {
			if (arr[i] != 0) {
				int v = arr[i];
				dp[i][v][0] += sum(v + 1, 200, 0, sum) + sum(v + 1, 200, 1, sum);
				dp[i][v][1] = dp[i - 1][v][0] + dp[i - 1][v][1] + dp[i - 1][v][2];
				dp[i][v][2] += sum(1, v - 1, 0, sum) + sum(1, v - 1, 1, sum) + sum(1, v - 1, 2, sum);
			} else {
				for (int v = 1; v < 201; v++) {
					dp[i][v][0] += sum(v + 1, 200, 0, sum) + sum(v + 1, 200, 1, sum);
					dp[i][v][1] = dp[i - 1][v][0] + dp[i - 1][v][1] + dp[i - 1][v][2];
					dp[i][v][2] += sum(1, v - 1, 0, sum) + sum(1, v - 1, 1, sum) + sum(1, v - 1, 2, sum);
				}
			}
			for (int v = 1; v < 201; v++) {
				sum[v][0] = sum[v - 1][0] + dp[i][v][0];
				sum[v][1] = sum[v - 1][1] + dp[i][v][1];
				sum[v][2] = sum[v - 1][2] + dp[i][v][2];
			}
		}
		return dp[N - 1][1][0] + dp[N - 1][1][1];
	}

	public static int sum(int begin, int end, int relation, int[][] presum) {
		return presum[end][relation] - presum[begin - 1][relation];
	}

	public static int[] generateRandomArray(int len) {
		int[] ans = new int[(int) (Math.random() * len) + 2];
		for (int i = 0; i < ans.length; i++) {
			if (Math.random() < 0.5) {
				ans[i] = 0;
			} else {
				ans[i] = (int) (Math.random() * 200) + 1;
			}
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		System.out.println("arr size : " + arr.length);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 20;
		int testTime = 10000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len);
			// int ans1 = ways1(arr);
			int ans2 = ways2(arr);
			int ans3 = ways3(arr);
			// if (ans1 != ans2) {
			// System.out.println("Oops!");
			// }
			if (ans2 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");

		int[] arr = generateRandomArray(100000);
		System.out.println(arr.length);
		long begin = System.currentTimeMillis();
		ways3(arr);
		long end = System.currentTimeMillis();
		System.out.println("run time : " + (end - begin) + " ms");

		int[] test = { 6, 0, 0, 9 };
		System.out.println(ways3(test));

	}

}
