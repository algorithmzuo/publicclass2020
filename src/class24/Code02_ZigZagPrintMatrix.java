package class24;

public class Code02_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		// x -> (a,b) 先往右，再往下
		int a = 0;
		int b = 0;
		
		// y -> (c,d) 先往下，再往右
		int c = 0;
		int d = 0;
		// (endR, endC) 是最右下角的位置
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		// fromUp = true  斜线打印方向应该从右上走到左下
		// fromUp = false  斜线打印方向应该从左下走到右上
		boolean fromUp = false;
		while (a != endR + 1) {
			// (a,b)  (c,d)  方向
			printLevel(matrix, a, b, c, d, fromUp);
			a = b == endC ? a + 1 : a;
			b = b == endC ? b : b + 1;
			d = c == endR ? d + 1 : d;
			c = c == endR ? c : c + 1;
			fromUp = !fromUp;
		}
		System.out.println();
	}

	public static void printLevel(int[][] m, int aRow, int aCol, int bRow, int bCol, boolean f) {
		if (f) {
			while (aRow != bRow + 1) {
				System.out.print(m[aRow++][aCol--] + " ");
			}
		} else {
			while (bRow != aRow - 1) {
				System.out.print(m[bRow--][bCol++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { 
				{ 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
