package class117;

// 测试链接 : https://leetcode.cn/problems/rotate-image/
public class Code03_RotateImage {

	// 保证传入的参数是正方形矩阵
	public static void rotate(int[][] matrix) {
		// 左上角点，(a,b)
		int a = 0;
		int b = 0;
		// 右下角点，(c,d)
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) {
			rotateEdge(matrix, a++, b++, c--, d--);
		}
	}

	public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
		int tmp = 0;
		for (int i = 0; i < d - b; i++) {
			tmp = m[a][b + i];
			m[a][b + i] = m[c - i][b];
			m[c - i][b] = m[c][d - i];
			m[c][d - i] = m[a + i][d];
			m[a + i][d] = tmp;
		}
	}

}
