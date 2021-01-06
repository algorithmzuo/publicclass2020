package class31;

public class Code02_AddMinus {

	public static int add(int a, int b) {
		int t = 0;
		while (b != 0) {
			t = a;
			a = a ^ b; // a -> a' 无进位相加信息
			b = ((t & b) << 1); // b -> b' 进位信息
		}
		return a;
	}

	public static int minus(int a, int b) {
		return add(a, add(~b, 1));
	}

	public static void main(String[] args) {
		int a = 8739284;
		int b = 7348472;
		System.out.println(a + b);
		System.out.println(add(a, b));

		System.out.println(a - b);
		System.out.println(minus(a, b));
	}

}
