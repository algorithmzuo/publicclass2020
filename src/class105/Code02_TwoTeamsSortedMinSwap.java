package class105;

import java.util.HashMap;

// 来自美团
// 两种颜色的球，蓝色和红色，都按1～n编号，共计2n个
// 为方便放在一个数组中，红球编号取负，篮球不变，并打乱顺序，
// 要求同一种颜色的球按编号升序排列，可以进行如下操作：
// 交换相邻两个球，求最少操作次数。
// [3,-3,1,-4,2,-2,-1,4]
// 最终交换结果为
// [1,2,3,-1,-2,-3,-4,4]
// 最少交换次数为10
// n <= 1000
public class Code02_TwoTeamsSortedMinSwap {

//	public static int zuo(int lastA, int lastB, int[] arr) {
//		if (lastA == 0 && lastB == 0) {
//			return 0;
//		}
//		// lastA != 0 || lastB != 0
//		if (lastA == 0) {
//			// lastB
//			int curCost = lastB来到此时的终止位置的代价;
//			int next = zuo(lastA, lastB - 1, arr);
//			return curCost + next;
//		}
//		if(lastB == 0) {
//			int curCost = lastA来到此时的终止位置的代价;
//			int next = zuo(lastA - 1, lastB, arr);
//			return curCost + next;
//		}
//		// lastA, lastB
//		// 让lastA来到最后
//		int p1 = Integer.MAX_VALUE;
//		int lastAComeCost = lastA来到此时的终止位置的代价;
//		int next1 = zuo(lastA - 1, lastB, arr);
//		p1 = lastAComeCost + next1;
//		// 让lastB来到最后
//		int p2 = Integer.MAX_VALUE;
//		int lastBComeCost = lastB来到此时的终止位置的代价;
//		int next2 = zuo(lastA, lastB - 1, arr);
//		p2 = lastBComeCost + next2;
//		return Math.min(p1, p2);
//	}

	// [3,-3,1,-4,2,-2,-1,4]
	// -3 -4 -2 -1 -> -1 -2 -3 -4
	// 3 1 2 4 -> 1 2 3 4

	// 这个题写对数器太麻烦了
	// 所以这就是正式解
	public static int minSwaps(int[] arr) {
		int n = arr.length;
		HashMap<Integer, Integer> map = new HashMap<>();
		int topA = 0;
		int topB = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] > 0) {
				topA = Math.max(topA, arr[i]);
			} else {
				topB = Math.max(topB, Math.abs(arr[i]));
			}
			map.put(arr[i], i);
		}
		IndexTree it = new IndexTree(n);
		for (int i = 0; i < n; i++) {
			it.add(i, 1);
		}
		return f(topA, topB, it, n - 1, map);
	}

	// 可以改二维动态规划！
	// 因为it的状态，只由topA和topB决定
	// 所以it的状态不用作为可变参数！
	public static int f(int lastA, int lastB,
			IndexTree it, // 支持快速的距离计算
			int end, // 不变！永远n-1！
			HashMap<Integer, Integer> map // 位置表
			) {
		if (lastA == 0 && lastB == 0) {
			return 0;
		}
		int p1 = Integer.MAX_VALUE;
		int p2 = Integer.MAX_VALUE;
		int index, cost, next;
		if (lastA != 0) {
			// 查出原数组中，lastA在哪？
			index = map.get(lastA);
			// 
			cost = it.sum(index, end) - 1;
			// 既然搞定了lastA，indexTree
			it.add(index, -1);
			next = f(lastA - 1, lastB, it, end, map);
			it.add(index, 1);
			p1 = cost + next;
		}
		if (lastB != 0) {
			index = map.get(-lastB);
			cost = it.sum(index, end) - 1;
			it.add(index, -1);
			next = f(lastA, lastB - 1, it, end, map);
			it.add(index, 1);
			p2 = cost + next;
		}
		return Math.min(p1, p2);
	}

	public static class IndexTree {
		private int[] tree;
		private int N;

		public IndexTree(int size) {
			N = size;
			tree = new int[N + 1];
		}

		public void add(int i, int v) {
			i++;
			while (i <= N) {
				tree[i] += v;
				i += i & -i;
			}
		}

		public int sum(int l, int r) {
			return l == 0 ? sum(r + 1) : (sum(r + 1) - sum(l));
		}

		private int sum(int index) {
			int ans = 0;
			while (index > 0) {
				ans += tree[index];
				index -= index & -index;
			}
			return ans;
		}

	}

	public static void main(String[] args) {
		int[] arr = { 3, -3, 1, -4, 2, -2, -1, 4 };
		System.out.println(minSwaps(arr));
	}

}
