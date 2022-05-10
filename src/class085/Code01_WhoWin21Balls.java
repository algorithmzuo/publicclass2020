package class085;

// 来自微众
// 人工智能岗
// 一开始有21个球，甲和乙轮流拿球，甲先、乙后
// 每个人在自己的回合，一定要拿不超过3个球，不能不拿
// 最终谁的总球数为偶数，谁赢
// 请问谁有必胜策略
public class Code01_WhoWin21Balls {

	// balls = 21
	// ball是奇数
	public static String win(int balls) {
		return process(0, balls, 0, 0);
	}

	// 憋递归！
	// turn 谁的回合！
	// turn == 0 甲回合
	// turn == 1 乙回合
	// rest剩余球的数量
	// 之前，jiaBalls、yiBalls告诉你！
	// 当前，根据turn，知道是谁的回合！
	// 当前，还剩多少球，rest
	// 返回：谁会赢！
	public static String process(int turn, int rest, int jia, int yi) {
		if (rest == 0) {
			return (jia & 1) == 0 ? "甲" : "乙";
		}
		// rest > 0, 还剩下球！
		if (turn == 0) { // 甲的回合！
			// 甲，自己赢！甲赢！
			for (int pick = 1; pick <= Math.min(rest, 3); pick++) {
				// pick 甲当前做的选择
				if (process(1, rest - pick, jia + pick, yi).equals("甲")) {
					return "甲";
				}
			}
			return "乙";
		} else {
			for (int pick = 1; pick <= Math.min(rest, 3); pick++) {
				// pick 甲当前做的选择
				if (process(0, rest - pick, jia, yi + pick).equals("乙")) {
					return "乙";
				}
			}
			return "甲";
		}
	}

	// 为了测试
	public static void main(String[] args) {
		for (int balls = 1; balls <= 500; balls += 2) {
			System.out.println("球数为 " + balls + " 时 , 赢的是 " + win(balls));
		}
	}

}