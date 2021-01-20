package class33;

public class Code03_ZigZagPrintMatrix {

	public static void printMatrixZigZag(int[][] m) {
		int a = 0;
		int b = 0;
		int c = 0;
		int d = 0;
		int er = m.length - 1;
		int ec = m[0].length - 1;
		boolean fromUp = false;
		while (a != er + 1) {
			printLevel(m, a, b, c, d, fromUp);
			a = b == ec ? a + 1 : a;
			b = b == ec ? b : b + 1;
			d = c == er ? d + 1 : d;
			c = c == er ? c : c + 1;
			fromUp = !fromUp;
		}
	}

	public static void printLevel(int[][] m, int a, int b, int c, int d, boolean f) {
		if (f) {
			while (a != c + 1) {
				System.out.print(m[a++][b--] + " ");
			}
		} else {
			while (c != a - 1) {
				System.out.print(m[c--][d++] + " ");
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { 
				{ 1,  2,  3,  4 },
				{ 5,  6,  7,  8 },
				{ 9, 10, 11, 12 } };
		printMatrixZigZag(matrix);

	}

}
