package test;

public class Code02_Power {

	public static boolean is2Power(int n) {
		return (n & (n - 1)) == 0;
	}

	public static boolean is4Power(int n) {
		//  0x55555555  ->   0101   0101   0101   0101   ....   0101
		return (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
	}

	public static void main(String[] args) {
		int n = 16;
		System.out.println(is2Power(n));
		System.out.println(is4Power(n));
	}

}
