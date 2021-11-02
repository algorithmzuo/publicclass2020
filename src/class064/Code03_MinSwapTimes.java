package class064;

// 来自小红书
// 一个无序数组长度为n，所有数字都不一样，并且值都在[0...n-1]范围上
// 返回让这个无序数组变成有序数组的最小交换次数
public class Code03_MinSwapTimes {

	// 纯暴力，arr长度大一点都会超时
	// 但是绝对正确
	public static int minSwap1(int[] arr) {
		return process1(arr, 0);
	}

	// 让arr变有序，最少的交换次数是多少！返回
	// times, 之前已经做了多少次交换
	public static int process1(int[] arr, int times) {
		boolean sorted = true;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] > arr[i]) {
				sorted = false;
				break;
			}
		}
		if (sorted) {
			return times;
		}
		// 数组现在是无序的状态！
		if (times >= arr.length - 1) {
			return Integer.MAX_VALUE;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				swap(arr, i, j);
				ans = Math.min(ans, process1(arr, times + 1));
				swap(arr, i, j);
			}
		}
		return ans;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// 已知arr中，只有0~n-1这些值，并且都出现1次
	public static int minSwap2(int[] arr) {
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {
			// 来到0、1、2、3、。。。。i
			// 0 -> 0 跳过
			// 7 -> 7 跳过
			// 6 -> 13 不能跳过！
			// 6 != 13  6位置，13位置的数，交换
			// [6] = 13 -> 正确归为
			// [13] = ? -> 来到6位置
			while (i != arr[i]) {
				swap(arr, i, arr[i]);
				ans++;
			}
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int len) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < len; i++) {
			swap(arr, i, (int) (Math.random() * len));
		}
		return arr;
	}

	public static void main(String[] args) {
		int n = 6;
		int testTime = 2000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * n) + 1;
			int[] arr = randomArray(len);
			int ans1 = minSwap1(arr);
			int ans2 = minSwap2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
