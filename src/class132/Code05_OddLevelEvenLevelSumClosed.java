package class132;

// 来自腾讯音乐
// 给定一棵树，一共有n个点
// 每个点上没有值，请把1~n这些数字，不重复的分配到二叉树上
// 做到 : 奇数层节点的值总和 与 偶数层节点的值总和 相差不超过1
// 返回奇数层节点分配值的一个方案
// 2 <= n <= 10^5 
// 所以课上会讲怎么转化，然后如下的代码实现
public class Code05_OddLevelEvenLevelSumClosed {

	// 一共n个节点，奇数层的节点是k个
	// 偶数层的节点就是n - k个
	// 1 ~ n ，哪k个数字，作为奇数层节点的分配
	// 能让：奇数层累加和，和，偶数层累加和，最接近！
	public static int[] team(int n, int k) {
		// 1 + 2 + 3 + ... + n = sum
		// sum = 10 奇数层累加和 = 5 偶数层累加和 = 5 无其他选择
		// sum = 15 奇数层累加和 = 8 或者 7
		int sum = (n + 1) * n / 2;
		// sum = 10
		// p1 = 5
		// p2 = 5
		// sum = 15
		// p1 = 7
		// p2 = 8
		int p1 = sum / 2;
		int p2 = (sum + 1) / 2;
		int[] ans = generate(p1, n, k);
		if (ans == null && p1 != p2) {
			ans = generate(p2, n, k);
		}
		return ans != null ? ans : new int[] { -1 };
	}

	// 1 ~ n 一共有这么多数字
	// 一定要从中选k个！累加和正好凑到x
	// 返回是哪k个数
	public static int[] generate(int x, int n, int k) {
		// 1 + 2+ 3 +.. + k
		int sumMinK = (k + 1) * k / 2;
		// 一个数字能提升的最大幅度
		int range = n - k;
		if (x < sumMinK || x > sumMinK + k * range) {
			return null;
		}
		// 整体需要提升的总幅度
		int add = x - sumMinK;
		// 需要整体搬家过去的数的个数
		int rightSize = add / range;
		// 中间的一个数，单独跳多少呢？
		int midIndex = (k - rightSize) + (add % range);
		int leftSize = k - rightSize - (add % range == 0 ? 0 : 1);
		int[] ans = new int[k];
		for (int i = 0; i < leftSize; i++) {
			ans[i] = i + 1;
		}
		if (add % range != 0) {
			ans[leftSize] = midIndex;
		}
		for (int i = k - 1, j = 0; j < rightSize; i--, j++) {
			ans[i] = n - j;
		}
		return ans;
	}

	public static void main(String[] args) {
		// n是最大值，1~n这些数字都有
		int n = 100;
		// k是个数
		int k = 33;
		// 1~n这些数字，选k个，能不能求和逼近一半
		// 返回方案
		int[] ans = team(n, k);
		System.out.println("总和 : " + (n + 1) * n / 2);
		System.out.println("长度 : " + ans.length);
		int sum = 0;
		System.out.print("数字 : ");
		for (int num : ans) {
			System.out.print(num + " ");
			sum += num;
		}
		System.out.println();
		System.out.println("求和 : " + sum);
		System.out.println("剩余 : " + ((n + 1) * n / 2 - sum));
	}

}
