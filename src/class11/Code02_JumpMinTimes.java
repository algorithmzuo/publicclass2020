package class11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code02_JumpMinTimes {

	public static int jumpMinTimes1(int N, int start, int end, int[] arr) {
		boolean[] map = new boolean[N + 1];

		return f1(N, start, end, 0, arr, map);
	}

	// 一共有N个位置, 每个位置如何跳,记录在arr中
	// 之前一共走了步数是step步
	// 当前来到cur位置，最终想去aim位置，
	// 返回从start开始到aim，最少的步数
	// 如果map[i] == true 表示i位置，之前来过
	// 如果map[i] == false 表示i位置，之前没来过
	public static int f1(int N, int cur, int aim, int step, int[] arr, boolean[] map) {
		if (cur < 1 || cur > N) {
			return -1;
		}
		if (map[cur]) {
			return -1;
		}
		// 有效的位置，又没来过
		if (cur == aim) {
			return step;
		}
		map[cur] = true;
		int ans1 = f1(N, cur + arr[cur - 1], aim, step + 1, arr, map);
		int ans2 = f1(N, cur - arr[cur - 1], aim, step + 1, arr, map);
		map[cur] = false;
		if (ans1 != -1 && ans2 != -1) {
			return Math.min(ans1, ans2);
		}
		if (ans1 != -1 && ans2 == -1) {
			return ans1;
		}
		if (ans1 == -1 && ans2 != -1) {
			return ans2;
		}
		return -1;
	}

	// 一共有N个位置
	// 最终要去aim位置
	// arr中，描述怎么跳
	// 当前，来到了i位置
	// 已经走了k步
	// 最后到达aim，至少几步？
	public static int process(int N, int aim, int[] arr, int i, int k) {
		if (i < 1 || i > N || k > N - 1) {
			return -1;
		}
		if (i == aim) {
			return k;
		}
		// 请注意，arr的下标是从0开始的，但是题目规定的下标从1开始
		// 所以，拿出i位置能跳的距离，需要拿arr[i-1]位置的值
		int ans1 = process(N, aim, arr, i + arr[i - 1], k + 1);
		int ans2 = process(N, aim, arr, i - arr[i - 1], k + 1);
		int ans = -1;
		if (ans1 != -1 && ans2 != -1) {
			ans = Math.min(ans1, ans2);
		}
		if (ans1 != -1 && ans2 == -1) {
			ans = ans1;
		}
		if (ans1 == -1 && ans2 != -1) {
			ans = ans2;
		}
		return ans;
	}

	public static int jumpMinTimes2(int N, int start, int end, int[] arr) {
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				dp[i][j] = -2;
			}
		}
		// dp[i][k] == -2表示这个过程没算过
		// dp[i][k] != -2表示这个过程算过了
		return f2(N, end, arr, start, 0, dp);
	}

	// 一共有N个位置，跳的过程中，如果你又跳回到某个位置，其实这已经说明不是最优步数了
	// 也就是说，如果存在最优的跳法，那么这个最优跳法一定不会大于N-1步
	// 所以，增加了一个参数k，表示已经跳了多少步
	// 整个函数的含义：
	// 一共有1~N个位置，目标是aim位置
	// 所有位置能跳的距离都记录在arr中，并且对任意的arr[i] > 0
	// 当前来到的位置是i, 之前已经跳过了k步，
	// 返回最后到达aim位置，跳的最少的步数
	// 如果返回-1表示怎么也无法到达
	public static int f2(int N, int aim, int[] arr, int i, int k, int[][] dp) {
		if (i < 1 || i > N || k > N - 1) {
			return -1;
		}
		if (dp[i][k] != -2) {
			return dp[i][k];
		}
		if (i == aim) {
			dp[i][k] = k;
			return k;
		}
		// 请注意，arr的下标是从0开始的，但是题目规定的下标从1开始
		// 所以，拿出i位置能跳的距离，需要拿arr[i-1]位置的值
		int ans1 = f2(N, aim, arr, i + arr[i - 1], k + 1, dp);
		int ans2 = f2(N, aim, arr, i - arr[i - 1], k + 1, dp);
		int ans = -1;
		if (ans1 != -1 && ans2 != -1) {
			ans = Math.min(ans1, ans2);
		}
		if (ans1 != -1 && ans2 == -1) {
			ans = ans1;
		}
		if (ans1 == -1 && ans2 != -1) {
			ans = ans2;
		}
		dp[i][k] = ans;
		return ans;
	}

	// bfs
	public static int jumpMinTimes3(int N, int start, int end, int[] arr) {
		if (start < 1 || start > N || end < 1 || end > N) {
			return -1;
		}
		Queue<Integer> queue = new LinkedList<>();
		HashMap<Integer, Integer> levelMap = new HashMap<>();
		queue.add(start);
		levelMap.put(start, 0);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int level = levelMap.get(cur);
			if (cur == end) {
				return level;
			} else {
				int left = cur - arr[cur - 1];
				int right = cur + arr[cur - 1];
				if (left > 0 && !levelMap.containsKey(left)) {
					queue.add(left);
					levelMap.put(left, level + 1);
				}
				if (right <= N && !levelMap.containsKey(right)) {
					queue.add(right);
					levelMap.put(right, level + 1);
				}
			}
		}
		return -1;
	}

	// for test
	public static int[] gerRandomArray(int N, int R) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = (int) (Math.random() * R);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxN = 20;
		int maxV = 10;
		int testTimes = 200000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			int[] arr = gerRandomArray(maxN, maxV);
			int N = arr.length;
			int start = (int) (Math.random() * N) + 1;
			int end = (int) (Math.random() * N) + 1;
			int ans1 = jumpMinTimes1(N, start, end, arr);
			int ans2 = jumpMinTimes2(N, start, end, arr);
			int ans3 = jumpMinTimes3(N, start, end, arr);
			if (ans1 != ans2 || ans2 != ans3) {
				printArray(arr);
				System.out.println("start : " + start);
				System.out.println("end : " + end);
				System.out.println("ans1 : " + ans1);
				System.out.println("ans2 : " + ans2);
				System.out.println("ans3 : " + ans2);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("test end");
	}

}
