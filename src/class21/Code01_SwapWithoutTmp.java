package class21;

public class Code01_SwapWithoutTmp {

	public static void main(String[] args) {
		int a = 123;
		int b = -898121;
		System.out.println(a);
		System.out.println(b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}

}
