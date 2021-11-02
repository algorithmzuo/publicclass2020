package class050;

public class Code04_ZigZagPrintMatrix {

	public static void printZigZag(int[][] m) {
		int N = m.length;
		int M = m[0].length;
		int Ar = 0;
		int Ac = 0;
		int Br = 0;
		int Bc = 0;
		boolean up = true;
		while (Ar != N) {
			// B 左下角的点 A右上角的点
			print(m, Br, Bc, Ar, Ac, up);
			up = !up;
			Ar = Ac == M - 1 ? (Ar + 1) : Ar;
			Ac = Ac == M - 1 ? Ac : (Ac + 1);
			Bc = Br == N - 1 ? (Bc + 1) : Bc;
			Br = (Br == N - 1) ? Br : (Br + 1);
		}
	}

	// (a,b)这个位置，一定在左下方
	// (c,d)这个位置，一定在右上方
	// (a,b) -> (c,d)是一条笔直的斜线！
	// up == true, 请你从(a,b)打印到(c,d)，斜线
	// up == false, 请你从(c,d)打印到(a,b)，斜线
	public static void print(int[][] m, int a, int b, int c, int d, boolean up) {
		if (up) {
			for (;; a--, b++) {
				System.out.print(m[a][b] + " ");
				if (a == c && b == d) {
					return;
				}
			}
		} else {
			for (;; c++, d--) {
				System.out.print(m[c][d] + " ");
				if (a == c && b == d) {
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] matrix = { 
				{ 1,  2,  3,  4 }, 
				{ 5,  6,  7,  8 }, 
				{ 9, 10, 11, 12 } };
		printZigZag(matrix);
	}

}
