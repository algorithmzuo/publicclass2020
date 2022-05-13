package class085;

// 来自字节跳动
// 给定一个数组arr，其中的值有可能正、负、0
// 给定一个正数k
// 返回累加和>=k的所有子数组中，最短的子数组长度
// 本题测试链接 : https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class Code02_ShortestSubarrayWithSumAtLeastK {

//	public static int shortestLenSumNolessK(int[] arr, int k) {
//		int ans = Integer.MAX_VALUE;
//		Box box = new Box(arr.length);
//		box.add(0, -1);
//		int sum = 0;
//		for (int i = 0; i < arr.length; i++) {
//			sum += arr[i];
//			// sum : 0...i 整体累加和！
//			int find = sum - k;
//			// box中 <= find 的最右位置！
//			int right = box.find(find);
//			if(right != -2) {
//				ans = Math.min(ans, i - right);
//			}
//			box.add(sum, i);
//		}
//		return ans == Integer.MAX_VALUE ? -1 : ans;
//	}
//
//	public static class Box {
//
//		public Node[] help;// 搞长一些，n长度
//		int size;
//		public Box(int n) {
//			help = new Node[n];
//			size = 0;
//		}
//
//		// 0...right sum
//		// 保持单调性去更新
//		public void add(int sum, int right) {
//
//		}
//		
//		// 在box中<=aim的最右位置，返回
//		// 如果不存在，返回-2
//		// 二分去找！
//		public int find(int aim) {
//			
//		}
//
//	}
//	
//	public static class Node{
//		public int sum;
//		public int right;
//	}

	public static int shortestSubarray1(int[] arr, int k) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		int n = arr.length + 1;
		long[] sum = new long[n];
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + arr[i - 1];
		}
		int[] stack = new int[n];
		int size = 1;
		int ans = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			int mostRight = mostRight(sum, stack, size, sum[i] - k);
			ans = Math.min(ans, mostRight == -1 ? Integer.MAX_VALUE : (i - mostRight));
			while (size > 0 && sum[stack[size - 1]] >= sum[i]) {
				size--;
			}
			stack[size++] = i;
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int mostRight(long[] sum, int[] stack, int size, long aim) {
		int l = 0;
		int r = size - 1;
		int m = 0;
		int ans = -1;
		while (l <= r) {
			m = (l + r) / 2;
			if (sum[stack[m]] <= aim) {
				ans = stack[m];
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ans;
	}

	public static int shortestSubarray2(int[] arr, int K) {
		int N = arr.length;
		long[] preSums = new long[N + 1];
		for (int i = 0; i < N; i++) {
			preSums[i + 1] = preSums[i] + arr[i];
		}
		int ans = Integer.MAX_VALUE;
		int[] dq = new int[N + 1];
		int l = 0;
		int r = 0;
		for (int i = 0; i < N + 1; i++) {
			// 从尾巴干掉，box里的记录！
			while (l != r && preSums[dq[r - 1]] >= preSums[i]) {
				r--;
			}
			// 从头部干掉，box里的记录
			while (l != r && preSums[i] - preSums[dq[l]] >= K) {
				ans = Math.min(ans, i - dq[l++]);
			}
			dq[r++] = i;
		}
		return ans != Integer.MAX_VALUE ? ans : -1;
	}

}
