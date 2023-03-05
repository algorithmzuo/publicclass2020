package class124;

// 给定一个 n × n 的二维矩阵 matrix 表示一个图像
// 请你将图像顺时针旋转 90 度
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵
// 请不要 使用另一个矩阵来旋转图像。
// 测试链接 : https://leetcode.cn/problems/rotate-image/
public class Code01_RotateImage {

	public static void rotate(int[][] matrix) {
		int a = 0;
		int b = 0;
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) {
			rotateEdge(matrix, a++, b++, c--, d--);
		}
	}

	public static void rotateEdge(int[][] m, int a, int b, int c, int d) {
		int tmp = 0;
		for (int i = 0; i < d - b; i++) {
			// i，就是组号！
			// 4 * 4 一共3组
			// n * n 一共n-1组
			tmp = m[a][b + i];
			m[a][b + i] = m[c - i][b];
			m[c - i][b] = m[c][d - i];
			m[c][d - i] = m[a + i][d];
			m[a + i][d] = tmp;
		}
	}

}
