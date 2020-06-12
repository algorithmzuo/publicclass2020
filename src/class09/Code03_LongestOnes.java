package class09;

import java.util.ArrayList;
import java.util.List;

public class Code03_LongestOnes {

	public static List<Integer> longestOnes1(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		List<Integer> ans = new ArrayList<>();
		int maxLen = 0;
		for (int i = 0; i < N; i++) { // 每一行的答案，i
			int j = M; // 最左发现的1的位置
			while (j > 0 && matrix[i][j - 1] == 1) {
				j--;
			}
			if (maxLen < M - j) {
				maxLen = M - j;
				ans.clear();
			}
			if (maxLen == M - j) {
				ans.add(i);
			}
		}
		return ans;
	}

	public static List<Integer> longestOnes2(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		List<Integer> ans = new ArrayList<>();
		int maxLen = 0;
		int col = M;
		for (int i = 0; i < N; i++) {
			while (col > 0 && matrix[i][col - 1] == 1) {
				col--;
			}
			// col来到当前i行最左侧的1的位置
			if (maxLen < M - col) {
				maxLen = M - col;
				ans.clear();
			}
			if (matrix[i][col] == 1) {
				ans.add(i);
			}
		}
		return ans;
	}

	public static List<Integer> longestOnes3(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		List<Integer> ans = new ArrayList<>();
		int maxLen = 0;
		for (int i = 0; i < N; i++) {
			int j = mostLeftOne(matrix[i], 0, M - 1);
			if (maxLen < M - j) {
				maxLen = M - j;
				ans.clear();
			}
			if (maxLen == M - j) {
				ans.add(i);
			}
		}
		return ans;
	}

	public static List<Integer> longestOnes4(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		List<Integer> ans = new ArrayList<>();
		int maxLen = 0;
		int col = M;
		for (int i = 0; i < N; i++) {
			col = mostLeftOne(matrix[i], 0, col - 1);
			if (maxLen < M - col) {
				maxLen = M - col;
				ans.clear();
			}
			if (matrix[i][col] == 1) {
				ans.add(i);
			}
		}
		return ans;
	}

	// arr[L..R]上，一定有一个前期：要么都是0，要么都是1，如果0和1都有，0在左侧，1在右侧
	public static int mostLeftOne(int[] arr, int L, int R) {
		int ans = R + 1; // R + 1 ->  [L..R] 没有1的
		int M = 0;
		while (L <= R) {
			M = (L + R) / 2;
			if (arr[M] == 1) {
				ans = M;
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		return ans;
	}

	// for test
	// 随机生成一个最大规模达到rowSize * colSize的矩阵
	// 确保每一行都是0在左侧，1在右侧
	public static int[][] generateRandomMatrix(int rowSize, int colSize) {
		int N = (int) (Math.random() * rowSize) + 1;
		int M = (int) (Math.random() * colSize) + 1;
		int[][] matrix = new int[N][M];
		for (int i = 0; i < N; i++) {
			int[] arr = new int[M];
			int rightStart = (int) (Math.random() * M);
			for (int j = rightStart; j < M; j++) {
				arr[j] = 1;
			}
			matrix[i] = arr;
		}
		return matrix;
	}

	public static boolean equal(List<Integer> ans1, List<Integer> ans2) {
		if (ans1.size() != ans2.size()) {
			return false;
		}
		int N = ans1.size();
		for (int i = 0; i < N; i++) {
			if (!ans1.get(i).equals(ans2.get(i))) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Hello!");
		int rowSize = 30;
		int colSize = 100;
		int testTimes = 300000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[][] matrix = generateRandomMatrix(rowSize, colSize);
			List<Integer> ans1 = longestOnes1(matrix);
			List<Integer> ans2 = longestOnes2(matrix);
			List<Integer> ans3 = longestOnes3(matrix);
			List<Integer> ans4 = longestOnes4(matrix);
			if (!equal(ans1, ans2) || !equal(ans3, ans4) || !equal(ans1, ans3)) {
				System.out.println("Oops");
			}
		}
		System.out.println("test end");

	}

}
