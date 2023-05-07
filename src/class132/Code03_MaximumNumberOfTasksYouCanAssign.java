package class132;

import java.util.Arrays;
import java.util.TreeMap;

// 来自华为
// 给你 n 个任务和 m 个工人
// 每个任务需要一定的力量值才能完成
// 需要的力量值保存在下标从 0 开始的整数数组 tasks 中
// 第 i 个任务需要 tasks[i] 的力量才能完成
// 每个工人的力量值保存在下标从 0 开始的整数数组 workers 中
// 第 j 个工人的力量值为 workers[j]
// 每个工人只能完成 一个 任务
// 且力量值需要 大于等于 该任务的力量要求值, 即 workers[j] >= tasks[i]
// 除此以外，你还有 pills 个神奇药丸
// 可以给 一个工人的力量值 增加 strength
// 你可以决定给哪些工人使用药丸
// 但每个工人 最多 只能使用 一片 药丸
// 给你下标从 0 开始的整数数组tasks 和 workers 以及
// 两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
// 测试链接 : https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/
public class Code03_MaximumNumberOfTasksYouCanAssign {

	// 时间复杂度O(N * (logN)平方)
	public static int maxTaskAssign1(int[] tasks, int[] workers, int pills, int strength) {
		int l = 0;
		int r = tasks.length;
		int m, ans = 0;
		Arrays.sort(tasks);
		Arrays.sort(workers);
		while (l <= r) {
			m = (l + r) / 2;
			if (yeah1(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength) <= pills) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	public static int yeah1(int[] tasks, int tl, int tr, int[] workers, int wl, int wr, int strength) {
		if (wl < 0) {
			return Integer.MAX_VALUE;
		}
		TreeMap<Integer, Integer> taskMap = new TreeMap<>();
		for (int i = tl; i <= tr; i++) {
			taskMap.put(tasks[i], taskMap.getOrDefault(tasks[i], 0) + 1);
		}
		int ans = 0;
		for (int i = wl; i <= wr; i++) {
			Integer job = taskMap.floorKey(workers[i]);
			if (job != null) {
				int times = taskMap.get(job);
				if (times == 1) {
					taskMap.remove(job);
				} else {
					taskMap.put(job, times - 1);
				}
			} else {
				job = taskMap.floorKey(workers[i] + strength);
				if (job == null) {
					return Integer.MAX_VALUE;
				}
				ans++;
				int times = taskMap.get(job);
				if (times == 1) {
					taskMap.remove(job);
				} else {
					taskMap.put(job, times - 1);
				}
			}
		}
		return ans;
	}

	// 时间复杂度O(N * logN)
	public static int maxTaskAssign2(int[] tasks, int[] workers, int pills, int strength) {
		int[] help = new int[tasks.length];
		int l = 0;
		int r = tasks.length;
		int m, ans = 0;
		Arrays.sort(tasks);
		Arrays.sort(workers);
		while (l <= r) {
			m = (l + r) / 2;
			if (yeah2(tasks, 0, m - 1, workers, workers.length - m, workers.length - 1, strength, help) <= pills) {
				ans = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	public static int yeah2(int[] tasks, int tl, int tr, int[] workers, int wl, int wr, int strength, int[] help) {
		if (wl < 0) {
			return Integer.MAX_VALUE;
		}
		int l = 0;
		int r = 0;
		int ti = tl;
		int ans = 0;
		for (int wi = wl; wi <= wr; wi++) {
			for (; ti <= tr && tasks[ti] <= workers[wi]; ti++) {
				help[r++] = ti;
			}
			if (l < r && tasks[help[l]] <= workers[wi]) {
				l++;
			} else {
				for (; ti <= tr && tasks[ti] <= workers[wi] + strength; ti++) {
					help[r++] = ti;
				}
				if (l < r) {
					ans++;
					r--;
				} else {
					return Integer.MAX_VALUE;
				}
			}
		}
		return ans;
	}

}
