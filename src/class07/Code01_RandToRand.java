package class07;

public class Code01_RandToRand {

	// 这个结构是唯一的随机机制
	// 你只能初始化并使用，不可修改
	public static class RandomBox {
		private final int min;
		private final int max;

		public RandomBox(int mi, int ma) {
			min = mi;
			max = ma;
		}

		public int random() {
			return min + (int) (Math.random() * (max - min + 1));
		}

		public int min() {
			return min;
		}

		public int max() {
			return max;
		}
	}

	public static int rand01(RandomBox randomBox) {
		int min = randomBox.min();
		int max = randomBox.max();
		int size = max - min + 1;
		boolean odd = (size & 1) != 0;
		int mid = size / 2;
		int ans = 0;
		do {
			ans = randomBox.random() - min;
		} while (odd && ans == mid);
		return ans < mid ? 0 : 1;
	}

	// 给你一个RandomBox，这是唯一能借助的随机机制
	// 等概率返回from~to范围上任何一个数
	// 要求from<=to
	public static int random(RandomBox randomBox, int from, int to) {
		if (from == to) {
			return from;
		}
		int range = to - from;
		int num = 1;
		// 求0～range需要几个2进制位
		while ((1 << num) - 1 < range) {
			num++;
		}
		// 最终的累加和，首先+0位上是1还是0，1位上是1还是0，2位上是1还是0...
		int ans = 0;
		do {
			ans = 0;
			for (int i = 0; i < num; i++) {
				ans += (rand01(randomBox) << i);
			}
		} while (ans > range);
		return ans + from;
	}

	public static void main(String[] args) {
		RandomBox randomBox = new RandomBox(3, 9);
		int from = 17;
		int to = 29;
		int[] ans = new int[to + 1];
		int testTime1 = 1000000;
		for (int i = 0; i < testTime1; i++) {
			ans[random(randomBox, from, to)]++;
		}
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}

	}

}
