package class066;

// leetcode题目 : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
public class Code01_BestTimeToBuyAndSellStock {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		// 最好的一笔收入，是多少
		int ans = 0;
		// 0..0 范围上的最小值
		int min = prices[0];
		// 假设每一个i，都是卖出时机！
		for (int i = 1; i < prices.length; i++) {
			// 更新min，min  0...i-1的最小值
			// min  0...i整体的最小值！
			min = Math.min(min, prices[i]);
			// prices[i] - min 挣得最多的钱
			ans = Math.max(ans, prices[i] - min);
		}
		return ans;
	}

}
