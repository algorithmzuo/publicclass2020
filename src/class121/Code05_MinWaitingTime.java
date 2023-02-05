package class121;

import java.util.PriorityQueue;

// 来自谷歌
// 给定一个数组arr，长度为n
// 表示n个服务员，每个人服务一个人的时间
// 给定一个正数m，表示有m个人等位
// 如果你是刚来的人，请问你需要等多久？
// 假设：m远远大于n，比如n<=1000, m <= 10的9次方，该怎么做？
public class Code05_MinWaitingTime {

//	public static int slow(int[] arr, int level) {
//		int n = arr.length;
//		int max = arr[0];
//		for (int i = 1; i < n; i++) {
//			max = Math.max(max, arr[i]);
//		}
//		int l = 1;
//		int r = max;
//		int ans = -1;
//		// l .... r 答案
//		while (l <= r) {
//			int speed = (l + r) / 2;
//			if (finish(arr, speed, level)) {
//				ans = speed;
//				r = speed - 1;
//			} else {
//				l = speed + 1;
//			}
//		}
//		return ans;
//	}
//
//	// 速度是speed，离开level小时
//	// 是否能把香蕉吃完
//	public static boolean finish(int[] arr, int speed, int level) {
//		int all = 0;
//		for (int num : arr) {
//			all += (num + speed - 1) / speed;
//		}
//		return all <= level;
//	}
//
//	public static int fast(int[] arr, int k) {
//		int l = 0;
//		int r = 0;
//		for (int num : arr) {
//			r += num;
//		}
//		// l....r
//		int ans = -1;
//		while (l <= r) {
//			int deadLine = (l + r) / 2;
//			if (need(arr, deadLine) > k) { // 时间订的太紧迫！
//				l = deadLine + 1;
//			} else { // 当前画家够用
//				ans = deadLine;
//				r = deadLine - 1;
//			}
//		}
//		return ans;
//	}
//
//	// 所有画arr，必须在deadLine时间内完成
//	// 返回需要几个画家
//	public static int need(int[] arr, int deadLine) {
//		// 加油的逻辑！
//		
//	}

	public static int minWaitingTime1(int[] arr, int m) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			heap.add(new int[] { 0, arr[i] });
		}
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
		int best = Integer.MAX_VALUE;
		for (int num : arr) {
			best = Math.min(best, num);
		}
		int left = 0;
		int right = best * m;
		int time = 0;
		int ans = 0;
		while (left <= right) {
			// left ... right
			//       t
			time = (left + right) / 2;
			// 完成的 + 开始的，一共有多少客人
			int cover = 0;
			for (int num : arr) {
				cover += (time / num) + 1;
			}
			if (cover >= m + 1) {
				ans = time;
				right = time - 1;
			} else {
				left = time + 1;
			}
		}
		return ans;
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
