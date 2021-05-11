package class43;

public class Code02_EatGrass {

	public static String winner1(int n) {
		if (n < 5) {
			return (n == 0 || n == 2) ? "后手" : "先手";
		}
		int eat = 1;
		while (eat <= n) {
			if (winner1(n - eat).equals("后手")) {
				return "先手";
			}
			if (eat > n / 4) { // 防止溢出
				break;
			}
			eat *= 4;
		}
		return "后手";
	}

	public static String winner2(int n) {
		if (n % 5 == 0 || n % 5 == 2) {
			return "后手";
		} else {
			return "先手";
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 50; i++) {
			System.out.println(i + " : " + winner1(i));
		}
	}

}
