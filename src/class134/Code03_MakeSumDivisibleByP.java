package class134;

import java.util.HashMap;

// 来自谷歌、亚马逊、Bloomberg、字节跳动、苹果
// 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空）
// 使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
// 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
// 子数组 定义为原数组中连续的一组元素。
// 测试链接 : https://leetcode.cn/problems/make-sum-divisible-by-p/
public class Code03_MakeSumDivisibleByP {

	// 核心 : find = (curMod - allMod + p) % p;
	public int minSubarray(int[] nums, int p) {
		int n = nums.length;
		int allMod = 0;
		for (int num : nums) {
			allMod = (allMod + num) % p;
		}
		if (allMod == 0) {
			return 0;
		}
		// allMod : 整体累加和 % p 得到的，要消除的目标
		// key : 某个余数
		// value : 所有前缀和 , %p得到余数是key的情况下，最晚出现的前缀和
		// 0.....5 %p = 2
		// 0.....7 %p = 2
		// 0....13 %p = 2
		// 余数2，是key，对应的value : 13
		HashMap<Integer, Integer> map = new HashMap<>();
		// 一个数都没有的时候，余数是0，最晚出现在-1位置
		map.put(0, -1);
		int ans = Integer.MAX_VALUE;
		int curMod = 0, find;
		for (int i = 0; i < n; i++) {
			// 0...i 累加和的余数
			curMod = (curMod + nums[i]) % p;
			// 如果p = 7，整体余数2，当前余数5，那么找之前的部分余数是3
			// 如果p = 7，整体余数2，当前余数1，那么找之前的部分余数是6
			// 整体变成下面的公式，可以自己带入各种情况验证
			find = (curMod - allMod + p) % p;
			// find这个余数最晚出现在哪，j位置
			// j+1....i 就是i结尾的情况下，需要删除的最短段!
			// 长度 i - j : i - map.get(find)
			if (map.containsKey(find)) {
				ans = Math.min(ans, i - map.get(find));
			}
			map.put(curMod, i);
		}
		return ans == nums.length ? -1 : ans;
	}

}
