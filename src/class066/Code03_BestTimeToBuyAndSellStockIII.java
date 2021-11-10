package class066;

//leetcode题目 : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
public class Code03_BestTimeToBuyAndSellStockIII {

	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		int doneOnceMinusBuyMax = -prices[0];
		int doneOnceMax = 0;
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
