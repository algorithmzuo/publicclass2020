package class119;

import java.util.HashMap;
import java.util.HashSet;

// 给定一个未排序的整数数组 nums 
// 找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
// 本题测试链接 : https://leetcode.cn/problems/longest-consecutive-sequence/
public class Code04_LongestConsecutive {

	// 课上讲的解法
	public static int longestConsecutive(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = 0;
		for (int num : nums) {
			if (!map.containsKey(num)) {
				map.put(num, 1);
				int preLen = map.containsKey(num - 1) ? map.get(num - 1) : 0;
				int posLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
				int all = preLen + posLen + 1;
				map.put(num - preLen, all);
				map.put(num + posLen, all);
				len = Math.max(len, all);
			}
		}
		return len;
	}

	// 补充一个两张表：头表、尾表。非常好理解的方法
	// 不是最优解，但是好理解
	public static int longestConsecutive2(int[] nums) {
		HashMap<Integer, Integer> headMap = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> tailMap = new HashMap<Integer, Integer>();
		HashSet<Integer> visited = new HashSet<>();
		for (int num : nums) {
			if (!visited.contains(num)) {
				visited.add(num);
				headMap.put(num, 1);
				tailMap.put(num, 1);
				if (tailMap.containsKey(num - 1)) {
					int preLen = tailMap.get(num - 1);
					int preHead = num - preLen;
					headMap.put(preHead, preLen + 1);
					tailMap.put(num, preLen + 1);
					headMap.remove(num);
					tailMap.remove(num - 1);
				}
				if (headMap.containsKey(num + 1)) {
					int preLen = tailMap.get(num);
					int preHead = num - preLen + 1;
					int postLen = headMap.get(num + 1);
					int postTail = num + postLen;
					headMap.put(preHead, preLen + postLen);
					tailMap.put(postTail, preLen + postLen);
					headMap.remove(num + 1);
					tailMap.remove(num);
				}
			}
		}
		int ans = 0;
		for (int len : headMap.values()) {
			ans = Math.max(ans, len);
		}
		return ans;
	}

}
