package class119;

import java.util.Arrays;
import java.util.HashMap;

// 给你一份工作时间表 hours，上面记录着某一位员工每天的工作小时数
// 我们认为当员工一天中的工作小时数大于 8 小时的时候，那么这一天就是「劳累的一天」
// 所谓「表现良好的时间段」，意味在这段时间内，「劳累的天数」是严格 大于「不劳累的天数」
// 请你返回「表现良好时间段」的最大长度
// 测试链接 : https://leetcode.cn/problems/longest-well-performing-interval/
public class Code01_LongestWellPerformingInterval {

	// 哈希表
	public static int longestWPI1(int[] hours) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int maxLen = 0;
		int sum = 0;
		for (int i = 0; i < hours.length; i++) {
			// 0...i 累加和
			// >8 +1
			// <=8 -1
			sum += hours[i] > 8 ? 1 : -1;
			if (sum > 0) {
				// 0.....i i+1
				maxLen = i + 1;
			} else { // <= 0 0 -1  -3 -4   -5 -6 
				if (map.containsKey(sum - 1)) {
					// 0......6  7.... 17
					//       -5        -4
					maxLen = Math.max(maxLen, i - map.get(sum - 1));
				}
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return maxLen;
	}

	// 数组替代哈希表
	public static int longestWPI2(int[] hours) {
		int n = hours.length;
		int[] early = new int[(n << 1) + 1];
		Arrays.fill(early, -2);
		early[0 + n] = -1;
		int ans = 0;
		int sum = 0;
		for (int i = 0; i < hours.length; i++) {
			sum += hours[i] > 8 ? 1 : -1;
			if (sum > 1) {
				ans = i + 1;
			} else {
				if (sum - 1 + n >= 0 && early[sum - 1 + n] != -2) {
					ans = Math.max(ans, i - early[sum - 1 + n]);
				}
			}
			if (early[sum + n] == -2) {
				early[sum + n] = i;
			}
		}
		return ans;
	}

}
