package class043;

public class Code03_EatGrass {

	public static String whoWin(int grass) {
		return process(grass, "先手");
	}

	// 只有两个选手，分别是字符串 "先手" "后手"
	// 当前，轮到cur这个选手
	// 还剩rest份草
	// 返回谁赢，"先手"先手赢 "后手"后手赢
	public static String process(int rest, String cur) {
		String against = cur.equals("先手") ? "后手" : "先手";
		if (rest == 0) {
			return against;
		}
		// 还剩下不止0份草 1 4 16 64 rest
		int choose = 1;
		while (choose <= rest) {
			int nextRest = rest - choose;
			if (process(nextRest, against).equals(cur)) {
				return cur;
			}
			choose *= 4;
		}
		return against;
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
