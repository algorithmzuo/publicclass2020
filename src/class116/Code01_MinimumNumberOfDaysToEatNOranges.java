package class116;

import java.util.HashMap;

// 来自腾讯面试
// 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
// 吃掉一个橘子。
// 如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
// 如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
// 每天你只能从以上 3 种方案中选择一种方案。
// 请你返回吃掉所有 n 个橘子的最少天数。
// 测试链接 : https://leetcode.cn/problems/minimum-number-of-days-to-eat-n-oranges/
public class Code01_MinimumNumberOfDaysToEatNOranges {

	// 斐波那契数列，求第n项
	// 1 1 2 3 5 8 13 ....

	public static HashMap<Integer, Integer> fdp = new HashMap<>();

	public static int f(int n) {
		if (fdp.containsKey(n)) {
			return fdp.get(n);
		}
		int ans = 0;
		if (n == 1) {
			ans = 1;
		} else if (n == 2) {
			ans = 2;
		} else {
			ans = f(n - 1) + f(n - 2);
		}
		fdp.put(n, ans);
		return ans;
	}

	// 所有的答案都填在这个表里
	// 这个表对所有的过程共用
	// 有橘子数量key，至少几天吃完这个结果
	// key -> value
	public static HashMap<Integer, Integer> dp = new HashMap<>();

	public static int minDays(int n) {
		// 0 0天
		// 1 1天
		if (n <= 1) {
			return n;
		}
		// n >= 2
		if (dp.containsKey(n)) {
			return dp.get(n);
		}
		// 1) 吃掉一个橘子
		// 2) 如果n能被2整除，吃掉一半的橘子，剩下一半
		// 3) 如果n能被3正数，吃掉三分之二的橘子，剩下三分之一
		// 因为方法2）和3），是按比例吃橘子，所以必然会非常快
		// 所以，决策如下：
		// 可能性1：为了使用2）方法，先把橘子吃成2的整数倍，然后直接干掉一半，剩下的n/2调用递归
		// 即，n % 2 + 1 + minDays(n/2)
		// 可能性2：为了使用3）方法，先把橘子吃成3的整数倍，然后直接干掉三分之二，剩下的n/3调用递归
		// 即，n % 3 + 1 + minDays(n/3)
		// 至于方法1)，完全是为了这两种可能性服务的，因为能按比例吃，肯定比一个一个吃快(显而易见的贪心)
		// 1) 吃成，能被2整除，减一半
		// n = 16
		// 1天 吃掉8个橘子 + minDays(8)
		// n = 17
		// 1天 吃掉1个橘子，一天 吃掉8个橘子 + minDays(8)
		int p1 = n % 2 + 1 + minDays(n / 2);
		// 2) 吃成，能被3整除，减2/3, 1/3
		// n = 9
		// 0天，3的整数倍，1天，6个（2/3） + minDays(n / 3)
		// n = 10
		// 1天，3的整数倍，1天，6个（2/3） + minDays(n / 3)
		// n = 11
		// 2天，3的整数倍，1天，6个（2/3） + minDays(n / 3)
		int p2 = n % 3 + 1 + minDays(n / 3);
		int ans = Math.min(p1, p2);
		dp.put(n, ans);
		return ans;
	}

}
