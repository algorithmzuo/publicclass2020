package class18;

public class Code02_PowXN {

	public static void main(String[] args) {
		long a = 2;
		long n = 13; // 1101
		long t = a;
		long res = 1;
		while (n != 0) {
			//    n = 110111011111
			//  & 1 = 000000000001
			//        000000000000
			// n & (n-1)
			// 把n抹掉最右侧的1
			//    n  = 10010101100010000
			//  n-1  = 10010101100001111
			//    &  = 10010101100000000
			if ((n & 1) == 1) {
				res *= t;
			}
			t = t * t;
			n = n >> 1;
		}
		System.out.println(res);
		System.out.println(Math.pow(2.0, 13));
		System.out.println(-Integer.MIN_VALUE);
		System.out.println(Math.abs(Integer.MIN_VALUE));

	}

	// 求x的n次方
	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		if (n == Integer.MIN_VALUE) {
			return (x == 1D || x == -1D) ? 1D : 0;
		}
		// 4.5  -3
		int pow = Math.abs(n);
		double t = x;
		double ans = 1D;
		while (pow != 0) {
			if ((pow & 1) != 0) {
				ans *= t;
			}
			pow >>= 1;
			t = t * t;
		}
		return n < 0 ? (1D / ans) : ans;
	}

}
