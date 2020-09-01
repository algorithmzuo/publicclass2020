package class18;

public class Code02_PowXN {

	public static double myPow(double x, int n) {
		if (n == 0) {
			return 1D;
		}
		if (n == Integer.MIN_VALUE) {
			return (x == 1D || x == -1D) ? 1D : 0;
		}
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
