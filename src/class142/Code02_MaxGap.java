package class142;

// 给定一个无序的数组 nums，返回 数组在排序之后，相邻元素之间最大的差值
// 如果数组元素个数小于 2，则返回 0
// 必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法
// 测试链接 : https://leetcode.cn/problems/maximum-gap/
public class Code02_MaxGap {

	// 如果排序之后，相邻两数的最大差值是什么返回
	public static int maximumGap(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		int len = nums.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len; i++) {
			min = Math.min(min, nums[i]);
			max = Math.max(max, nums[i]);
		}
		if (min == max) {
			return 0;
		}
		// 准备桶，len，len+1
		// i号桶，有没有进来过数字
		boolean[] hasNum = new boolean[len + 1];
		// i号桶，max多少
		int[] maxs = new int[len + 1];
		// i号桶，min多少
		int[] mins = new int[len + 1];
		int bid = 0;
		for (int i = 0; i < len; i++) {
			bid = bucket(nums[i], len, min, max);
			mins[bid] = hasNum[bid] ? Math.min(mins[bid], nums[i]) : nums[i];
			maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], nums[i]) : nums[i];
			hasNum[bid] = true;
		}
		// 只考察桶和桶之间的差值
		// 后一个桶（最小值） - 前一个桶(最大值）  
		// 保证非空
		int res = 0;
		int lastMax = maxs[0];
		int i = 1;
		for (; i <= len; i++) {
			if (hasNum[i]) {
				res = Math.max(res, mins[i] - lastMax);
				lastMax = maxs[i];
			}
		}
		return res;
	}

	public static int bucket(int num, int len, int min, int max) {
		// 一个桶的范围
		double range = (double) (max - min) / (double) len;
		// num和min之间的距离
		double distance = (double) (num - min);
		// 返回桶的编号，向下取整
		return (int) (distance / range);
	}

}
