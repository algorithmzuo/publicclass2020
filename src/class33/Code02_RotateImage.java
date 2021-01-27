package class33;

public class Code02_RotateImage {

	public static void rotate(int[][] matrix) {
		int a = 0;
		int b = 0;
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) {
			rotateEdge(matrix, a++, b++, c--, d--);
		}
	}

	// 在二维数组m中，左上角点(a,b), 右下角点(c,d)
	// 只在规定好的框上，顺时针转好
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

	public static void printMatrix(int[][] m) {
		int N = m.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = {
				{ 1, 2, 3 },
				{ 4, 5, 6 },
				{ 7, 8, 9 } };
		printMatrix(matrix);
		System.out.println("========");

		rotate(matrix);
		printMatrix(matrix);
		System.out.println("========");

	}

}
