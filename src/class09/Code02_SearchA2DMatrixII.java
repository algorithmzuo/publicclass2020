package class09;

public class Code02_SearchA2DMatrixII {

	/*
	 * 评测代码可以直接去leetcode搜索：Search a 2D Matrix II
	 * 
	 */

	public static boolean searchMatrix(int[][] m, int target) {
		if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
			return false;
		}
		int N = m.length;
		int M = m[0].length;
		int row = 0;
		int col = M - 1;
		while (row < N && col >= 0) {
			if (m[row][col] > target) {
				col--;
			} else if (m[row][col] < target) {
				row++;
			} else {
				return true;
			}
		}
		return false;
	}

}
