package class135;

import java.util.Arrays;
import java.util.HashMap;

// 来自亚马逊
// 给你一个整数数组 arr 和一个整数 k
// 现需要从数组中恰好移除 k 个元素
// 请找出移除后数组中不同整数的最少数目。
// 测试链接 : https://leetcode.cn/problems/least-number-of-unique-integers-after-k-removals/
public class Code03_LeastNumberOfUniqueAfterRemovals {
	
	public static int findLeastNumOfUniqueInts(int[] arr, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		// 3 : 7次
		// 2 : 5次
		// 1 : 6次
		// 9 : 10次
		// [7,5,6,10]
		int n = map.size();
		int[] cnts = new int[n];
		int i = 0;
		for (int cnt : map.values()) {
			cnts[i++] = cnt;
		}
		// [7,5,6,10]
		// [5,6,7,10]
		Arrays.sort(cnts);
		for (i = 0; i < n; i++) {
			k -= cnts[i];
			if (k <= 0) {
				if (k == 0) {
					i++;
				}
				break;
			}
		}
		return n - i;
	}

}
