package class30;

// 测试链接：https://leetcode.com/problems/sqrtx
public class Code02_MySqrt {

	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		if (x < 3) {
			return 1;
		}
		long ans = 1;
		long L = 1;
		long R = x;
		long M = 0;
		// L...R    1....x
		while (L <= R) {
			M = (L + R) / 2;
			if (M * M <= x) {
				ans = M;
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return (int) ans;
	}

}
