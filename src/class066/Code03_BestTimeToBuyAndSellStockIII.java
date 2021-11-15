package class066;

//leetcode题目 : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
public class Code03_BestTimeToBuyAndSellStockIII {

//	public static int maxProfit1(int[] prices) {
//		if (prices == null || prices.length < 2) {
//			return 0;
//		}
//		int n = prices.length;
//		int ans = 0;
//		int[] doneOnceMax = new int[n];
//		// doneOnceMax 去生成！
//		// doneOnceMax[i] 指的是：0~i范围上，只做一笔交易！获得的最大钱！
//		for(int j = 0; j < n; j++) {
//			// 第二笔交易的卖出时机
//			// j时刻
//			// 13时刻，第二笔交易的卖出时机
//			// 第二笔交易的买入时机：0  【0~0】 0
//			//                   1    [0~1] 1 13
//			//                   2   [0~2]  2  13
//			// i j
//			// 0 13
//			// 1 13
//			// ..
//			// 13..13
//			for(int i = 0; i <= j; i++) {
//				int second = prices[j] - prices[i];
//				int first = doneOnceMax[i];// 0...i  只做一笔交易！获得的最大钱！
//				int cur = first + second;
//				ans 
//				
//				
//			}
//			return ans;
//			
////		}
//		
//		
//		
//				
//				
//				
//				
//		for (int i = 1; i < n; i++) {
//
//		}
//		return ans;
//	}

//	public static int maxProfit2(int[] prices) {
//		if (prices == null || prices.length < 2) {
//			return 0;
//		}
//		int n = prices.length;
//		// 指标：doneOnceMinusOneBuyMax[i] : 0 ~ i上，一定要做完一次交易，还得扣除掉一次买入，
//		// 所有情况中，整体的最优！
//		
//		
//		
//		// 第一步：请把如下的含义结构生成
//		int[] doneOnceMax = new int[n]; // 该怎么生成怎么生成！
//		// doneOnceMax[i] : 0..i 做完一笔交易的最好钱数
//		
//		// 第二步，把指标数组生成！
//		int[] doneOnceMinusOneBuyMax = new int[n];
//
//		// doneOnceMinusOneBuyMax[0] : 0 ~ 0
//		// doneOnceMinusOneBuyMax[1] : 0 ~ 1
//		// doneOnceMinusOneBuyMax[2] : 0 ~ 2
//		// ...
//
//		doneOnceMinusOneBuyMax[0] = -prices[0];
//
//
//		for (int i = 1; i < n; i++) {
//			// doneOnceMinusOneBuyMax[i]
//			// 0 ~ i上，一定要做完一次交易，还得扣除掉一次买入
//			// 可能性1：扣除掉的这一次买入，和i无关！
//			int p1 = doneOnceMinusOneBuyMax[i-1];
//			// 可能性2 ：扣除掉的这一次买入，和i有关！
//			int p2 =   doneOnceMax[i]     - prices[i];
//			doneOnceMinusOneBuyMax[i] = Math.max(p1, p2);
//		}
//		
//		// 第三步
//		int ans = 0;
//		for(int i = 0; i < n; i++) {
//			ans = Math.max(ans, doneOnceMinusOneBuyMax[i] + prices[i]);
//		}
//		return ans;
//	}
	
	
	
	public static int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int ans = 0;
		// doneOnceMinusBuyMax -> 指标0
		int doneOnceMinusBuyMax = -prices[0];
		// doneOnceMax -> 0 ~ 0 只做一次交易，获得的最好钱数
		int doneOnceMax = 0;
		// 0 ~ 0 上的最小值！
		int min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			// 0 ~ i整体的最小值！
			min = Math.min(min, prices[i]);
			ans = Math.max(ans, doneOnceMinusBuyMax + prices[i]);
			// 0 ~ i上，只做一次交易，获得的最好钱数
			// 可能性1：只做的这一次交易，不是在i位置卖的，(0~i-1)上一步的doneOnceMax
			// 可能性2：只做的这一次交易，是在i位置卖的, prices[i] - min
			doneOnceMax = Math.max(doneOnceMax, prices[i] - min);
			//  0 ~ i上，只做一次交易 并且 扣掉一次买入，整体获得的最好钱数
			// 可能性1：扣掉一次的这一次买入，不在i上发生，(0~i-1)上一步的doneOnceMinusBuyMax
			// 可能性2：扣掉一次的这一次买入，必须在i上发生，（0~i）上的那次最好交易，- prices[i]
			doneOnceMinusBuyMax = Math.max(doneOnceMinusBuyMax, doneOnceMax - prices[i]);
		}
		return ans;
	}

}
