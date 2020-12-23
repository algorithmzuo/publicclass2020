package class29;

public class Code02_EatGrass {

	// n 一开始的份数
	// 先手、后手，先后：1,4,16,64,....
	// 返回，在先手和后手都做出对自己最优决策的时候，谁会赢？
	// "先手"， "后手"
	// n == 0  先最先遇到0      后手赢
	// n == 1  先直接拿走1，    先手赢
	// n == 2  先 1 后1，      后手赢
	// n == 3  先 1 后 1 先 1  先手赢
	// n == 4  先 4           先手赢 
	public static String winner1(int n) {
		if (n < 5) { // base case
			return (n == 0 || n == 2) ? "后手" : "先手";
		}
		// n >= 5
		// 此时的递归过程
		// 先拿 ： 先手
		// 后拿 ： 后手
		int base = 1; // 此时的先手，决定拿的份数
		while (base <= n) {
			// 如果先手拿base份，会赢的话，直接返回先手赢
			// 此时的先手，如果拿了base份
			// 下一步轮到后手拿，面对  n - base
			// 下一步：winner1(n - base)  返回的是后手赢！
			// 下一步的先手是：此时这个函数里的后手
			// 下一步的后手是：此时这个函数里的先手
			if (winner1(n - base).equals("后手")) {
				return "先手";
			}
			if (base > n / 4) { // 防止溢出
				break;
			}
			base *= 4;
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
