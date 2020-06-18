package class10;

public class Code02_GetMax {

	// 请确保，n不是0，就是1
	// 0 -> 1    1 -> 0
	public static int flip(int n) {
		return n ^ 1;
	}

	// 如果n是非负数，返回1
	// 如果n是负数，返回0
	public static int sign(int n) {
		// (n >> 31) & 1   0(int  非负数)     1(int   负数)
		// >>> >> 
		return flip((n >> 31) & 1);
	}

	// 确保  a-b  是不会溢出的
	public static int getMax1(int a, int b) {
		int c = a - b;
		int scA = sign(c); // scA == 1  a - b >= 0  a返回    sca == 0  a-b < 0  b返回
		int scB = flip(scA);
		return a * scA + b * scB;
	}

	public static int getMax2(int a, int b) {
		int c = a - b;
		int sa = sign(a);
		int sb = sign(b);
		int sc = sign(c);
		int difSab = sa ^ sb;
		int sameSab = flip(difSab);
		// 返回a的条件  returnA == 1  应该返回a; 
		// returnA == 0，不应该返回a
		int returnA = difSab * sa + sameSab * sc;
		int returnB = flip(returnA);
		return a * returnA + b * returnB;
	}

	public static void main(String[] args) {
		int a = -16;
		int b = 1;
		System.out.println(getMax1(a, b));
		System.out.println(getMax2(a, b));
		a = 2147483647;
		b = -2147480000;
		System.out.println(getMax1(a, b)); // wrong answer because of overflow
		System.out.println(getMax2(a, b));

	}

}
