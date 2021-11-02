package class018;

public class Code01_SqrtX {

	// x一定非负，输入可以保证
	public static int mySqrt(int x) {
		if (x == 0) {
			return 0;
		}
		// x != 0   1 2
		if (x < 3) {
			return 1;
		}
		// x >= 3
		long ans = 1;
		long L = 1;
		long R = x;
		long M = 0;
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
