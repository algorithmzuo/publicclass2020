package class048;

public class Code02_MaxSumFollowUp {

	public static int maxSumSubMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		int N = matrix.length;
		int M = matrix[0].length;
		// 0..0 0..1 0..2 0..N-1
		// 1..1
		int max = Integer.MIN_VALUE;
		for (int s = 0; s < N; s++) {
			int[] arr = new int[M];
			for (int e = s; e < N; e++) {
				for (int i = 0; i < M; i++) {
					arr[i] += matrix[e][i];
				}
				// arr 被加工好了
				max = Math.max(max, maxSumSubArray(arr));
			}
		}
		return max;
	}

	public static int maxSumSubArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int pre = arr[0];// 上一步的dp值 pre dp[0]
		int max = pre;
		for (int end = 1; end < N; end++) {
			pre = Math.max(arr[end], pre + arr[end]);
			max = Math.max(max, pre);
		}
		return max;
	}

	public static int maxSum1(int[][] matrix) {
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;
		for (int ai = 0; ai < n; ai++) {
			for (int aj = 0; aj < m; aj++) {
				for (int bi = ai; bi < n; bi++) {
					for (int bj = aj; bj < m; bj++) {
						max = Math.max(max, sum(matrix, ai, aj, bi, bj));
					}
				}
			}
		}
		return max;
	}

	public static int sum(int[][] matrix, int ai, int aj, int bi, int bj) {
		int sum = 0;
		for (int i = ai; i <= bi; i++) {
			for (int j = aj; j <= bj; j++) {
				sum += matrix[i][j];
			}
		}
		return sum;
	}

	public static int maxSum2(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		int[] s = null;
		for (int i = 0; i != matrix.length; i++) {
			s = new int[matrix[0].length];
			for (int j = i; j != matrix.length; j++) {
				cur = 0;
				for (int k = 0; k != s.length; k++) {
					s[k] += matrix[j][k];
					cur += s[k];
					max = Math.max(max, cur);
					cur = cur < 0 ? 0 : cur;
				}
			}
		}
		return max;
	}

	public static int[][] generateMatrix(int N, int M, int V) {
		int n = (int) (Math.random() * N) + 1;
		int m = (int) (Math.random() * M) + 1;
		int[][] matrix = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				matrix[i][j] = (int) (Math.random() * V) - (int) (Math.random() * V);
			}
		}
		return matrix;
	}

	public static void main(String[] args) {
		int N = 20;
		int M = 20;
		int V = 100;
		int testTime = 50000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[][] matrix = generateMatrix(N, M, V);
			int ans1 = maxSum1(matrix);
			int ans2 = maxSum2(matrix);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");

	}

}
