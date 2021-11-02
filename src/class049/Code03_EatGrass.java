package class049;

public class Code03_EatGrass {

	public static String whoWin(int N) {
		return who(N, "先手");
	}

	// int rest : 剩下多少草
	// String cur : "先手"、"后手" 表示，当前轮到谁
	// 返回："先手"、"后手"，最终谁会赢
	public static String who(int rest, String cur) {
		if (rest == 0) {
			return cur.equals("先手") ? "后手" : "先手";
		}
		// 接下来，rest > 0, cur 将穷尽一切努力!
		int pick = 1;
		while (pick <= rest) {
			if (cur.equals(who(rest - pick, cur.equals("先手") ? "后手" : "先手"))) {
				return cur;
			}
			pick *= 4;
		}
		return cur.equals("先手") ? "后手" : "先手";
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
			System.out.println(i + " : " + whoWin(i));
		}
	}

}
