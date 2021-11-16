package class068;

import java.util.LinkedList;

// 测试链接 : https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class Code04_ShortestSubarrayWithSumAtLeastK {

	public static int shortestSubarray1(int[] arr, int K) {
		int N = arr.length;
		long[] preSums = new long[N + 1];
		for (int i = 0; i < N; i++) {
			preSums[i + 1] = preSums[i] + arr[i];
		}
		int ans = Integer.MAX_VALUE;
		LinkedList<Integer> dq = new LinkedList<>();
		for (int i = 0; i < N + 1; i++) {
			while (!dq.isEmpty() && preSums[dq.getLast()] >= preSums[i]) {
				dq.pollLast();
			}
			while (!dq.isEmpty() && preSums[i] - preSums[dq.getFirst()] >= K) {
				ans = Math.min(ans, i - dq.pollFirst());
			}
			dq.addLast(i);
		}
		return ans != Integer.MAX_VALUE ? ans : -1;
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
			while (l != r && preSums[dq[r - 1]] >= preSums[i]) {
				r--;
			}
			while (l != r && preSums[i] - preSums[dq[l]] >= K) {
				ans = Math.min(ans, i - dq[l++]);
			}
			dq[r++] = i;
		}
		return ans != Integer.MAX_VALUE ? ans : -1;
	}

}
