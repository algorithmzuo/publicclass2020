package class127;

// 来自谷歌
// 给你一个 非递减 的正整数数组 nums 和整数 K
// 判断该数组是否可以被分成一个或几个 长度至少 为 K 的 不相交的递增子序列
// 测试链接 : https://leetcode.cn/problems/divide-array-into-increasing-sequences/
public class Code02_DivideArrayIntoIncreasingSequences {

	public static boolean canDivideIntoSubsequences(int[] nums, int k) {
		int cnt = 1;
		int maxCnt = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] != nums[i]) {
				maxCnt = Math.max(maxCnt, cnt);
				cnt = 1;
			} else {
				cnt++;
			}
		}
		maxCnt = Math.max(maxCnt, cnt);
		return nums.length / maxCnt >= k;
	}

}
