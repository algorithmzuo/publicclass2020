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

	// 暴力尝试的方法 limit -1  -2 -3 -4
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
	// 用arr中的数字去拼，< limit ，尽量大
	// 能拼出来尽量大的数字，返回
	public static int maxNumber2(int[] arr, int limit) {
		// [6,2,8] [8,2,6] [2,6,8]
		Arrays.sort(arr);
		// limit - 1
		limit--;
		// <= limit，尽量大即可
		// limit : 657321
		// offset: 100000
		// 当前数 : (limit / offset) % 10 -> 6
		// 下一个 :
		// limit : 657321
		// offset:  10000
		// 当前数 : (limit / offset) % 10 -> 5
		// 下一个 :
		// limit : 657321
		// offset:   1000
		// 当前数 : (limit / offset) % 10 -> 7
		int offset = 1;
		while (offset <= limit / 10) {
			offset *= 10;
		}
		
		// 不要这么写，可能溢出！
//		while(offset <= limit) {
//			offset *=10;
//		}
//		offset /=10;
		
		
		// limit : 65431098
		// offset: 10000000
		// arr中的数字，<=limit, offset方便我提取数字用的！
		// limit : 65431098
		// process2 : 拼出来的数字，和limit位数一定要一样长！！！！
		// 返回尽量大的数字！
		// 如果拼出来的数字，无法和limit位数一样长，返回-1
		int ans = process2(arr, limit, offset);
		if (ans != -1) {
			return ans;
		} else {
			// limit : 65431098
			// offset:  1000000
			// arr[5]
			//          5000000
//			             500000
//			              50000
			offset /= 10;
			int rest = 0;
			while (offset > 0) {
				rest += arr[arr.length - 1] * offset;
				offset /= 10;
			}
			return rest;
		}
	}

	// 可以用arr中的数字！
	// 去拼<=limit，尽量大！位数一定要和limit一样长
	// offset是用来取数字的！
	// limit = 876530
	// offset=   1000
	// 87这两位，一定做的决定是：追平！而且真的追平了！
	public static int process2(int[] arr, int limit, int offset) {
		// limit = 876530
		// offset=      0
		if (offset == 0) {
			return limit;
		}
		// limit = 876530
		// offset=   1000
		int cur = (limit / offset) % 10;
		// 当前数字已经知道了cur
		// 去arr中拿数字试图追平！
		// 6
		// 1) 拿到了能追平的数字
		// 2) 没拿到能追平的数字，但又较小的数字
		// 3) <=当前想追平的数字，都不存在  -1
		int near = near(arr, cur);
		if (near == -1) {
			return -1;
		} else if (arr[near] == cur) { // 1) 拿到了能追平的数字
			// 当前位达成了！
			int ans = process2(arr, limit, offset / 10);
			if (ans != -1) { // 后续计算出了最优结果！
				return ans;
			} else if (near > 0) {
				near--;
				return (limit / (offset * 10)) * offset * 10 + (arr[near] * offset) + rest(arr, offset / 10);
			} else { // 后续搞不定！当前位也没有办法再下降了
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
