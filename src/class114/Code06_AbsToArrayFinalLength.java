package class114;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 来自国外题目论坛
// 给定一个非负数组arr
// 任何两个数差值的绝对值，如果arr中没有，都要加入到arr里
// 然后新的arr继续，任何两个数差值的绝对值，如果arr中没有，都要加入到arr里
// 一直到arr大小固定
// 请问最终arr长度是多少
// 1 <= arr的长度 <= 10^5
// 0 <= arr的数值 <= 10^5
public class Code06_AbsToArrayFinalLength {

	// 暴力方法
	// 为了验证
	public static int finalLen1(int[] arr) {
		ArrayList<Integer> list = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for (int num : arr) {
			list.add(num);
			set.add(num);
		}
		while (!finish(list, set))
			;
		return list.size();
	}

	public static boolean finish(ArrayList<Integer> list, HashSet<Integer> set) {
		int len = list.size();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				int abs = Math.abs(list.get(i) - list.get(j));
				if (!set.contains(abs)) {
					list.add(abs);
					set.add(abs);
				}
			}
		}
		return len == list.size();
	}

	// 正式方法
	// 时间复杂O(N)
	public static int finalLen2(int[] arr) {
		// 收集最大的数
		int max = 0;
		// 随便的一个非0的数，最后遇到的非0的数
		int gcd = 0;
		for (int num : arr) {
			max = Math.max(max, num);
			if (num != 0) {
				gcd = num;
			}
		}
		// 最大值有了，max
		// 最后遇到的非0的数, gcd
		if (gcd == 0) { // 如果数组中所有数都是0，数组长度不会变化的！
			return arr.length;
		}
		// 统计每一种数出现的次数
		// 求所有数的最大公约数
		HashMap<Integer, Integer> counts = new HashMap<>();
		for (int num : arr) {
			if (num != 0) {
				gcd = gcd(gcd, num);
			}
			counts.put(num, counts.getOrDefault(num, 0) + 1);
		}
		// 假设不考虑0、也不考虑有没有重复值
		// 经典的max / gcd，有几个数
		int ans = max / gcd;
		// 考虑0，考虑数组中原本有的0，而不是重复数值减出来的0
		// 长度变成多少
		ans += counts.getOrDefault(0, 0);
		boolean add = false;
		for (int key : counts.keySet()) {
			// 遍历每一种数的词频
			// 2 7个  + 6
			// 5 4个  + 3
			// 0 ?个
			if (key != 0) {
				ans += counts.get(key) - 1;
			}
			// 因为重复数字的出现，会不会减出来多一个0
			if (!add && counts.get(key) > 1 && !counts.containsKey(0)) {
				ans++;
				add = true;
			}
		}
		return ans;
	}

	public static int gcd(int m, int n) {
		return n == 0 ? m : gcd(n, m % n);
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v);
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 15;
		int V = 50;
		int testTime = 8000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n, V);
			int ans1 = finalLen1(arr);
			int ans2 = finalLen2(arr);
			if (ans1 != ans2) {
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println("出错了!");
			}
		}
		System.out.println("功能测试结束");
	}

}
