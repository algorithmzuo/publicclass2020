package class08;

public class Code01_SetMatrixZeroes {

	/*
	 * 评测代码可以直接去leetcode搜索：Set Matrix Zeroes
	 * 
	 */

	public static void setZeroes(int[][] matrix) {
		boolean row0Zero = false;
		boolean col0Zero = false;
		int N = matrix.length;
		int M = matrix[0].length;
		for (int i = 0; i < M; i++) {
			if (matrix[0][i] == 0) {
				row0Zero = true;
				break;
			}
		}
		for (int i = 0; i < N; i++) {
			if (matrix[i][0] == 0) {
				col0Zero = true;
				break;
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		if (row0Zero) {
			for (int i = 0; i < M; i++) {
				matrix[0][i] = 0;
			}
		}
		if (col0Zero) {
			for (int i = 0; i < N; i++) {
				matrix[i][0] = 0;
			}
		}
	}

}
