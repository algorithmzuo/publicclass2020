package class59;

import java.util.HashMap;

public class Code05_SubarraySumEqualsK {

	public static int subarraySum(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		HashMap<Integer, Integer> preSumTimesMap = new HashMap<>();
		// 这一句非常重要，表示没有遍历之前你已经可以得到前缀和为0的情况了
		// 那就是，一个数也没有的时候！
		preSumTimesMap.put(0, 1);
		int all = 0;
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			all += nums[i];
			if (preSumTimesMap.containsKey(all - k)) {
				ans += preSumTimesMap.get(all - k);
			}
			if (!preSumTimesMap.containsKey(all)) {
				preSumTimesMap.put(all, 1);
			} else {
				preSumTimesMap.put(all, preSumTimesMap.get(all) + 1);
			}
		}
		return ans;
	}

}
