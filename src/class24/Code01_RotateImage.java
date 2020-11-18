package class24;

import java.util.HashMap;

public class Code01_RotateImage {

	public static void rotate(int[][] matrix) {
		// matrix.length == matrix[0].length
		// (a,b)  左上角的点，在a行b列上
		int a = 0;
		int b = 0;
		// (a,b)  右下角的点，在c行d列上
		int c = matrix.length - 1;
		int d = matrix[0].length - 1;
		while (a < c) { // 一定是正方形，
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
	
	
	
	public static HashMap<Integer, Integer> map = new HashMap<>();
	
	public static void generateMap() {
		for(int i = 0 ;i < 32;i++) {
			map.put(1 << i , i);
		}
	}
	
	// 返回num最右侧的1，在第几位
	//  高  ......  低
	public static int f(int num) {
		// num = 24
		// num = 00000..0000011000
		//       00000..0000001000
		if(map.size() == 0) {
			generateMap();
		}
		int rightOne = num & (-num); // num & (~num + 1)
		return map.get(rightOne);
	}
	
	
	public static void main(String[] args) {

	}
	
	

}
