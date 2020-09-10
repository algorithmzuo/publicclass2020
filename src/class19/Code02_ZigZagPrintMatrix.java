package class19;

public class Code02_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] matrix) {
		int aRow = 0;
		int aCol = 0;
		int bRow = 0;
		int bCol = 0;
		// A和B一定会共同走到右下角的位置
		int endR = matrix.length - 1;
		int endC = matrix[0].length - 1;
		// fromUp = true  斜线打印方向应该从右上走到左下
		// fromUp = false  斜线打印方向应该从左下走到右上
		boolean fromUp = false;
		while (aRow != endR + 1) {
			printLevel(matrix, aRow, aCol, bRow, bCol, fromUp);
			aRow = aCol == endC ? aRow + 1 : aRow;
			aCol = aCol == endC ? aCol : aCol + 1;
			bCol = bRow == endR ? bCol + 1 : bCol;
			bRow = bRow == endR ? bRow : bRow + 1;
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
