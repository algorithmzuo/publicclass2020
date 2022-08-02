package class095;

import java.util.Arrays;

// 来自字节
// 输入:
// 去重数组arr，里面的数只包含0~9
// limit，一个数字
// 返回:
// 要求比limit小的情况下，能够用arr拼出来的最大数字
public class Code02_MaxNumberUnderLimit {

	public static int tmp = 0;

	// 暴力尝试的方法
	public static int maxNumber1(int[] arr, int limit) {
		tmp = 0;
		Arrays.sort(arr);
		limit--;
		int offset = 1;
		while (offset <= limit / 10) {
			offset *= 10;
		}
		process1(arr, 0, offset, limit);
		if (tmp == 0) {
			int rest = 0;
			offset /= 10;
			while (offset > 0) {
				rest += arr[arr.length - 1] * offset;
				offset /= 10;
			}
			return rest;
		}
		return tmp;
	}

	public static void process1(int[] arr, int num, int offset, int limit) {
		if (offset == 0) {
			if (num <= limit) {
				tmp = Math.max(tmp, num);
			}
		} else {
			for (int cur : arr) {
				process1(arr, num * 10 + cur, offset / 10, limit);
			}
		}
	}

	// 正式方法
	public static int maxNumber2(int[] arr, int limit) {
		Arrays.sort(arr);
		limit--;
		int offset = 1;
		while (offset <= limit / 10) {
			offset *= 10;
		}
		int ans = process2(arr, limit, offset);
		if (ans != -1) {
			return ans;
		} else {
			offset /= 10;
			int rest = 0;
			while (offset > 0) {
				rest += arr[arr.length - 1] * offset;
				offset /= 10;
			}
			return rest;
		}
	}

	public static int process2(int[] arr, int limit, int offset) {
		if (offset == 0) {
			return limit;
		}
		int cur = (limit / offset) % 10;
		int near = near(arr, cur);
		if (near == -1) {
			return -1;
		} else if (arr[near] == cur) {
			int ans = process2(arr, limit, offset / 10);
			if (ans != -1) {
				return ans;
			} else if (near > 0) {
				near--;
				return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
			} else {
				return -1;
			}
		} else {
 			return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
		}
	}

	public static int rest(int[] arr, int offset) {
		int rest = 0;
		while (offset > 0) {
			rest += arr[arr.length - 1] * offset;
			offset /= 10;
		}
		return rest;
	}

	public static int near(int[] arr, int num) {
		int l = 0;
		int r = arr.length - 1;
		int m = 0;
		int near = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (arr[m] <= num) {
				near = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return near;
	}

	// 为了测试
	public static int[] randomArray() {
		int[] arr = new int[(int) (Math.random() * 10) + 1];
		boolean[] cnt = new boolean[10];
		for (int i = 0; i < arr.length; i++) {
			do {
				arr[i] = (int) (Math.random() * 10);
			} while (cnt[arr[i]]);
			cnt[arr[i]] = true;
		}
		return arr;
	}

	public static void main(String[] args) {
		int max = 3000;
		int testTime = 100;
		System.out.println("测试开始");
		for (int i = 0; i < max; i++) {
			int[] arr = randomArray();
			for (int j = 0; j < testTime; j++) {
				int ans1 = maxNumber1(arr, i);
				int ans2 = maxNumber2(arr, i);
				if (ans1 != ans2) {
					System.out.println("出错了!");
					System.out.println("数组为 ：");
					for (int num : arr) {
						System.out.print(num + " ");
					}
					System.out.println();
					System.out.println("数字为 ：" + i);
					System.out.println(ans1);
					System.out.println(ans2);
				}
			}
		}
		System.out.println("测试结束");

	}

}
