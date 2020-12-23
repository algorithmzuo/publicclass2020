package class30;

public class Code02_AddMinus {

	public static int add(int a, int b) {
		int t = 0;
		while (b != 0) {
			t = a;
			a = a ^ b;
			b = ((t & b) << 1);
		}
		return a;
	}

	public static int minus(int a, int b) {
		return add(a, add(~b, 1));
	}

}
