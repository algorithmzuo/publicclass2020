package class21;

public class Code02_GetMax {

	// 输入参数：n，一定要保证，n不是1就是0
	// n == 0 -> 1
	// n == 1 -> 0
	public static int flip(int n) {
		return n ^ 1;
	}

	// 输入参数n，可以是任何一个整数
	// 如果n是非负数，返回1(int)
	// 如果n是负数，返回0(int)
	public static int sign(int n) {
		return flip((n >> 31) & 1);
	}

	// a和b中，谁大返回谁
	public static int getMax1(int a, int b) {
		int c = a - b;
		int scA = sign(c); // c >= 0 scA = 1;   c < 0  scA = 0
		int scB = flip(scA);
		return a * scA + b * scB;
	}

	public static int getMax2(int a, int b) {
		int c = a - b; // c是a-b的差值，有可能溢出，也有可能不溢出
		int sa = sign(a); // a的符号，求出，a>=0  1,  a<0   0
		int sb = sign(b); // b的符号，求出，b>=0  1,  b<0   0
		int sc = sign(c); // c的符号，求出，c>=0  1,  c<0   0
		// 如果a和b的符号，不一样，difSab == 1
		// 如果a和b的符号，  一样，difSab == 0
		int difSab = sa ^ sb; 
		// 如果a和b的符号，一样，sameSab == 1
		// 如果a和b的符号，不一样，sameSab == 0
		int sameSab = flip(difSab);
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
