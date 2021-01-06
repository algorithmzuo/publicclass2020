package class31;

public class Code03_GetMax {

	// n 0或1
	// 0 -> 1 1 -> 0
	public static int flip(int n) {
		return n ^ 1;
	}

	// n 任意整数
	// n非负的，返回1
	// n负的，返回0
	public static int sign(int n) {
		// (n >> 31) & 1 n非负的 0 n负的 1
		return flip((n >> 31) & 1);
	}

	public static int getMax1(int a, int b) {
		int c = a - b;
		int scA = sign(c); // a - b >= 0   scA = 1;  a - b <0   scA = 0
		int scB = flip(scA); // a - b >= 0  scB = 0; a - b <0   scB = 1
		return a * scA + b * scB;
	}

	public static int getMax2(int a, int b) {
		int c = a - b;
		int sa = sign(a); // a的符号，非负 1  负 0
		int sb = sign(b); // b的符号，非负 1  负 0
		int sc = sign(c); // a-b的符号，非负 1  负 0
		int difSab = sa ^ sb; // 如果不一样，1；如果一样，0
		int sameSab = flip(difSab);// 如果一样，1；如果不一样，0
		int returnA = difSab * sa + sameSab * sc;
		int returnB = flip(returnA);
		return a * returnA + b * returnB;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = -19;
		System.out.println(getMax1(a, b));
		System.out.println(getMax2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getMax1(a, b)); // wrong answer because of overflow
		System.out.println(getMax2(a, b));
	}

}
