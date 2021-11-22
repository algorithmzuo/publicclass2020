package class067;

import java.util.HashMap;

public class Code01_IsStepSum {

	public static boolean isStepSum(int x) {
		// L = 0 R = stepSum;
		// 可能的答案，只会在L ~ R上
		int L = 0;
		int R = x;
		int M = 0;
		int cur = 0;
		// L <= R 还能继续二分
		while (L <= R) {
			// 求中点
            // M  = (L + R) / 2;
			// M = L + (R - L) / 2
			// a / 2 ->  a >> 1
			M = L + ((R - L) >> 1);
			cur = stepSum(M);
			if (cur == x) {
				return true;
			} else if (cur < x) {
				L = M + 1;
			} else {
				R = M - 1;
			}
		}
		return false;
	}

	// 输入num，
	// 输出num的step sum
	// 代价 : num的十进制位的数量
	public static int stepSum(int num) {
		int sum = 0;
		while (num != 0) {
			sum += num;
			num /= 10;
		}
		return sum;
	}

	// for test
	public static HashMap<Integer, Integer> generateStepSumNumberMap(int numMax) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <= numMax; i++) {
			map.put(stepSum(i), i);
		}
		return map;
	}

	// for test
	public static void main(String[] args) {
		int max = 1000000;
		int maxStepSum = stepSum(max);
		HashMap<Integer, Integer> ans = generateStepSumNumberMap(max);
		System.out.println("测试开始");
		for (int i = 0; i <= maxStepSum; i++) {
			if (ans.containsKey(i) ^ isStepSum(i)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
