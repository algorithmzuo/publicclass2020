package class071;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code01_CoverMax {

	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p < max; p += 1) {
			int cur = 0;
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}

	// [ [4, 5], [3, 6] , [1, 3] ]
	// [ [1, 3] [3, 6] [4 ,5]
	public static int maxCover2(int[][] lines) {
		if (lines == null || lines.length == 0) {
			return 0;
		}
		// 排序：每个线段，根据开头位置排序，开头的位置如果一样，怎么排序无所谓！
		// java Arrays sort 比较
		// java lambda 表达式
		Arrays.sort(lines, (a, b) -> a[0] - b[0]);
		// 准备一个堆
		// 在这里，不去谈堆的实现，只是使用
		// 小根堆：小 到 大 组织
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (int[] line : lines) {
			int start = line[0];
			int end = line[1];
			// <= start的数字，都从小根堆里弹出！
			while (!heap.isEmpty() && heap.peek() <= start) {
				heap.poll();
			}
			heap.add(end);
			int cur = heap.size();
			max = Math.max(max, cur);
		}
		return max;
	}

	// for test
	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = maxCover2(lines);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
