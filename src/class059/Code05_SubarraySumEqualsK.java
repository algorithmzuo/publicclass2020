package class059;

import java.util.HashMap;

public class Code05_SubarraySumEqualsK {

	public static int subarraySum(int[] nums, int sum) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		// key : 某一个前缀和！
		// value : 这个前缀和，出现了几次！
		HashMap<Integer, Integer> preSumTimesMap = new HashMap<>();
		preSumTimesMap.put(0, 1);
		// 每一步的整体和, 当你遍历到i位置，0~i整体的累加和！
		int all = 0;
		int ans = 0;
		for (int i = 0; i < nums.length; i++) {
			// 0...i的整体累加和了！1000   200   1000 - 200 = 800
			all += nums[i];
			if (preSumTimesMap.containsKey(all - sum)) {
				ans += preSumTimesMap.get(all - sum);
			}
			// 0....i 这个前缀和，一定要去！更新map！
			if (!preSumTimesMap.containsKey(all)) {
				preSumTimesMap.put(all, 1);
			} else {
				preSumTimesMap.put(all, preSumTimesMap.get(all) + 1);
			}
		}
		return ans;
	}

}
