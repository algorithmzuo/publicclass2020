package class070;

public class SuperWaterKing {

	// int[] arr = {1 , 1, 3, 3, 1}
	// 长度5，长度是N
	// 水王：某个数，出现次数，大于 N / 2 (必须大于！不包括等于！)
	// 1 1 3次 N = 5 N/2 = 2
	// arr = {1,1,2,2}
	// n =4 n/2 2次
	// arr中的数，都不是负数！返回水王数！
	// 如果没有水王数！返回-1
	// 只能使用有限几个变量，就完成寻找水王数的功能
	// 时间复杂度O(N)
	public static int king(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		if (arr.length == 1) {
			return arr[0];
		}
		int cand = 0;
		int hp = 0;
		for (int num : arr) {
			if (hp == 0) {
				cand = num;
				hp = 1;
			} else if (num == cand) {
				hp++;
			} else {
				hp--;
			}
		}
		if (hp == 0) {
			return -1;
		}
		int times = 0;
		for (int num : arr) {
			if (num == cand) {
				times++;
			}
		}
		if (times > arr.length / 2) {
			return cand;
		} else {
			return -1;
		}
	}

}
