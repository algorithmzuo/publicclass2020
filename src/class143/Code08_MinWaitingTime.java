package class143;

import java.util.PriorityQueue;

// 来自谷歌
// 给定一个数组arr，长度为n
// 表示n个服务员，每个人服务一个人的时间
// 给定一个正数m，表示有m个人等位
// 每个等位的人都遵循有空位就上的原则
// 如果你是刚来的人，请问你需要等多久？
// 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
public class Code08_MinWaitingTime {

	public static int minWaitingTime1(int[] arr, int m) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		// [0,5]
		// [0,3]
		// [0,1]
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		int n = arr.length;
		// n -> n * logn
		for (int i = 0; i < n; i++) {
			heap.add(new int[] { 0, arr[i] });
		}
		// m * logn
		for (int i = 0; i < m; i++) {
			int[] cur = heap.poll();
			cur[0] += cur[1];
			heap.add(cur);
		}
		return heap.peek()[0];
	}

	public static int minWaitingTime2(int[] arr, int m) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		// 工作效率最好的服务员
		// 3 5 7 1
		// 1
		int best = Integer.MAX_VALUE;
		for (int num : arr) {
			best = Math.min(best, num);
		}
		// 0 ~ best*m
		int left = 0;
		int right = best * m;
		int t = 0;
		int near = 0;
		// left .... right
		// 二分
		// 0 ~ best * m;二分，
		while (left <= right) {
			t = (left + right) / 2;
			int cover = 0;
			for (int num : arr) {
				cover += (t / num) + 1;
			}
			if (cover >= m + 1) {
				near = t;
				right = t - 1;
			} else {
				left = t + 1;
			}
		}
		return near;
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int len = 50;
		int value = 30;
		int mMax = 3000;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 1;
			int[] arr = randomArray(n, value);
			int m = (int) (Math.random() * mMax);
			int ans1 = minWaitingTime1(arr, m);
			int ans2 = minWaitingTime2(arr, m);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println("m : " + m);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
