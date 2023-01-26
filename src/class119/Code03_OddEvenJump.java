package class119;

import java.util.TreeMap;

// 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数
// 在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃
// 而第 2、4、6... 次跳跃称为偶数跳跃
// 你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：
// 在进行奇数跳跃时（如，第 1，3，5... 次跳跃），
// 你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值
// 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
// 在进行偶数跳跃时（如，第 2，4，6... 次跳跃)
// 你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值
// 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
//（对于某些索引 i，可能无法进行合乎要求的跳跃。）
// 如果从某一索引开始跳跃一定次数（可能是 0 次或多次），
// 就可以到达数组的末尾（索引 A.length - 1），
// 那么该索引就会被认为是好的起始索引。
// 返回好的起始索引的数量。
// 测试链接 : https://leetcode.cn/problems/odd-even-jump/
public class Code03_OddEvenJump {

	public static int oddEvenJumps(int[] arr) {
		int n = arr.length;
		int[] odd = new int[n];
		int[] even = new int[n];
		TreeMap<Integer, Integer> orderMap = new TreeMap<>();
		for (int i = n - 1; i >= 0; i--) {
			Integer to = orderMap.ceilingKey(arr[i]);
			odd[i] = to == null ? -1 : orderMap.get(to);
			to = orderMap.floorKey(arr[i]);
			even[i] = to == null ? -1 : orderMap.get(to);
			orderMap.put(arr[i], i);
		}
		int ans = 0;
		int[][] dp = new int[n][2];
		for (int i = 0; i < n; i++) {
			if (jump(i, 1, n - 1, odd, even, dp)) {
				ans++;
			}
		}
		return ans;
	}

	public static boolean jump(int cur, int status, int target, int[] odd, int[] even, int[][] dp) {
		if (cur == target) {
			return true;
		}
		if (dp[cur][status] != 0) {
			return dp[cur][status] == 1;
		}
		boolean ans = false;
		if (status == 1 && odd[cur] != -1) {
			ans = jump(odd[cur], 0, target, odd, even, dp);
		}
		if (status == 0 && even[cur] != -1) {
			ans = jump(even[cur], 1, target, odd, even, dp);
		}
		dp[cur][status] = ans ? 1 : -1;
		return ans;
	}

}
