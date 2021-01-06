package class31;

public class Code01_SwapWithoutTmp {

	public static void main(String[] args) {
		
		int test1 = 23;
		int test2 = 15;
		System.out.println(test1 ^ test2);
		
		
		
		int a = -111;
		int b = 343242111;
		System.out.println(a);
		System.out.println(b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println(a);
		System.out.println(b);
	}

}
