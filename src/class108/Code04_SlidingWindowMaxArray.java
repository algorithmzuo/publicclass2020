package class108;

import java.util.LinkedList;

// 给你一个整数数组 nums，有一个大小为 k 的滑动窗口
// 从数组的最左侧移动到数组的最右侧
// 你只可以看到在滑动窗口内的 k 个数字
// 滑动窗口每次只向右移动一位。
// 返回每一步 滑动窗口中的最大值
// 测试链接 : https://leetcode.cn/problems/sliding-window-maximum/
public class Code04_SlidingWindowMaxArray {

//	public static class Window {
//
//		// 一些结构
//		
//		public int[] help;
//
//		public Window(int[] arr) {
//			help = arr;
//		}
//
//		public void addNumberFromRight() {
//
//		}
//
//		public void deleteNumberFromLeft() {
//
//		}
//
//		public int max() {
//
//		}
//
//	}
//
//	public static void main(String[] args) {
//		int[] arr = { 3, 5, 7, 2, 4, 2, 6 };
//		//                [ 7, 2]
//		Window w = new Window(arr);
//		w.addNumberFromRight();
//		w.addNumberFromRight();
//		w.addNumberFromRight();
//		System.out.println(w.max());
//		w.deleteNumberFromLeft();
//		w.deleteNumberFromLeft();
//		w.addNumberFromRight();
//		System.out.println(w.max());
//	}

	

	// 暴力的对数器方法
	public static int[] right(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int N = arr.length;
		int[] res = new int[N - w + 1];
		int index = 0;
		int L = 0;
		int R = w - 1;
		while (R < N) {
			int max = arr[L];
			for (int i = L + 1; i <= R; i++) {
				max = Math.max(max, arr[i]);

			}
			res[index++] = max;
			L++;
			R++;
		}
		return res;
	}

	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		// qmax 窗口最大值的更新结构
		// 放下标
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		int[] res = new int[arr.length - w + 1];
		int index = 0;
		for (int R = 0; R < arr.length; R++) {
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
				qmax.pollLast();
			}
			qmax.addLast(R);
			if (qmax.peekFirst() == R - w) {
				qmax.pollFirst();
			}
			if (R >= w - 1) {
				res[index++] = arr[qmax.peekFirst()];
			}
		}
		return res;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 100;
		int maxValue = 100;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int w = (int) (Math.random() * (arr.length + 1));
			int[] ans1 = getMaxWindow(arr, w);
			int[] ans2 = right(arr, w);
			if (!isEqual(ans1, ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
