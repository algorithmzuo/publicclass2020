package class064;

import java.util.Arrays;
import java.util.HashMap;

// 来自小红书
// 有四种诗的韵律分别为: AABB、ABAB、ABBA、AAAA
// 比如 : 1 1 3 3就属于AABB型的韵律、6 6 6 6就属于AAAA型的韵律等等
// 一个数组arr，当然可以生成很多的子序列，如果某个子序列一直以韵律的方式连接起来，我们称这样的子序列是有效的
// 比如, arr = { 1, 1, 15, 1, 34, 1, 2, 67, 3, 3, 2, 4, 15, 3, 17, 4, 3, 7, 52, 7, 81, 9, 9 }
// arr的一个子序列为{1, 1, 1, 1, 2, 3, 3, 2, 4, 3, 4, 3, 7, 7, 9, 9}
// 其中1, 1, 1, 1是AAAA、2, 3, 3, 2是ABBA、4, 3, 4, 3是ABAB、7, 7, 9, 9是AABB
// 可以看到，整个子序列一直以韵律的方式连接起来，所以这个子序列是有效的
// 给定一个数组arr, 返回最长的有效子序列长度
// 题目限制 : arr长度 <= 4000, arr中的值<= 10^9
// 离散化之后，arr长度 <= 4000,  arr中的值<= 4000
public class Code04_PoemProblem {

	public static int maxLen1(int[] arr) {
		if (arr == null || arr.length < 4) {
			return 0;
		}
		int[] path = new int[arr.length];
		return process1(arr, 0, path, 0);
	}

	public static int process1(int[] arr, int index, int[] path, int size) {
		if (index == arr.length) {
			if (size % 4 != 0) {
				return 0;
			} else {
				for (int i = 0; i < size; i += 4) {
					if (!valid(path, i)) {
						return 0;
					}
				}
				return size;
			}
		} else {
			int p1 = process1(arr, index + 1, path, size);
			path[size] = arr[index];
			int p2 = process1(arr, index + 1, path, size + 1);
			return Math.max(p1, p2);
		}
	}

	public static boolean valid(int[] p, int i) {
		// AABB
		// ABAB
		// ABBA
		// AAAA
		return (p[i] == p[i + 1] && p[i + 2] == p[i + 3])
				|| (p[i] == p[i + 2] && p[i + 1] == p[i + 3] && p[i] != p[i + 1])
				|| (p[i] == p[i + 3] && p[i + 1] == p[i + 2] && p[i] != p[i + 1]);
	}

	// AABB
	// ABAB
	// ABBA
	// AAAA
	// 先看前三个规则：AABB、ABAB、ABBA
	// 首先A、A、B、B的全排列为:
	// AABB -> AABB
	// ABAB -> ABAB
	// ABBA -> ABBA
	// BBAA -> 等同于AABB，因为A和B谁在前、谁在后都算是 : AABB的范式
	// BABA -> 等同于ABAB，因为A和B谁在前、谁在后都算是 : ABAB的范式
	// BAAB -> 等同于ABBA，因为A和B谁在前、谁在后都算是 : ABBA的范式
	// 也就是说，AABB、ABAB、ABBA这三个规则，可以这么用：
	// 只要有两个不同的数，都出现2次，那么这一共4个数就一定符合韵律规则。
	// 所以：
	// 1) 当来到arr中的一个数字num的时候，
	// 如果num已经出现了2次了, 只要之前还有一个和num不同的数，
	// 也出现了两次，则一定符合了某个规则, 长度直接+4，然后清空所有的统计
	// 2) 当来到arr中的一个数字num的时候,
	// 如果num已经出现了4次了(规则四), 长度直接+4，然后清空所有的统计
	public static int maxLen2(int[] arr) {
		// 统计某个数(key)，出现的次数(value)
		HashMap<Integer, Integer> map = new HashMap<>();
		// tow代表目前有多少数出现了2次
		int two = 0;
		// ans代表目前符合韵律链接的子序列增长到了多长
		int ans = 0;
		// 当前的num出现了几次
		int numTimes = 0;
		for (int num : arr) {
			// 对当前的num，做次数统计
			map.put(num, map.getOrDefault(num, 0) + 1);
			// 把num出现的次数拿出来
			numTimes = map.get(num);
			// 如果num刚刚出现了2次, 那么目前出现了2次的数，的数量，需要增加1个
			two += numTimes == 2 ? 1 : 0;
			// 下面的if代表 :
			// 如果目前有2个数出现2次了，可以连接了
			// 如果目前有1个数出现4次了，可以连接了
			if (two == 2 || numTimes == 4) {
				ans += 4;
				map.clear();
				two = 0;
			}
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * value);
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		// 1111 2332 4343 7799
		int[] test = { 1, 1, 15, 1, 34, 1, 2, 67, 3, 3, 2, 4, 15, 3, 17, 4, 3, 7, 52, 7, 81, 9, 9 };
		System.out.println(maxLen1(test));
		System.out.println(maxLen2(test));
		System.out.println("===========");
		System.out.println("随机测试开始");
		int len = 20;
		int value = 6;
		int testTime = 1000;
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 1;
			int[] arr = randomArray(n, value);
			int[] arr1 = Arrays.copyOf(arr, arr.length);
			int[] arr2 = Arrays.copyOf(arr, arr.length);
			int ans1 = maxLen1(arr1);
			int ans2 = maxLen2(arr2);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("随机测试结束");
		System.out.println("===========");

		long start;
		long end;
		int[] longArr = randomArray(5000000, 20);
		start = System.currentTimeMillis();
		maxLen2(longArr);
		end = System.currentTimeMillis();
		System.out.println("大样本量运行时间(毫秒) : " + (end - start));
		System.out.println("===========");
	}

}
