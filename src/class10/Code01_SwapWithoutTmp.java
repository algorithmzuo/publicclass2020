package class10;

public class Code01_SwapWithoutTmp {

	public static void main(String[] args) {
		int a = 111;
		int b = 111;
		System.out.println(a);
		System.out.println(b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}

}
