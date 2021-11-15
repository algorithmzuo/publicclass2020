package class066;

// leetcode题目 : https://leetcode.com/problems/koko-eating-bananas/
public class Code05_KokoEatingBananas {

	public static int minEatingSpeed(int[] piles, int h) {
		int L = 1;
		int R = 0;
		for (int pile : piles) {
			R = Math.max(R, pile);
		}
		// L --- R
		// 去寻找最合适的速度！
		int ans = 0;
		int M = 0;
		while (L <= R) {
			// M = (L + R) / 2
			M = L + ((R - L) >> 1);
			if (hours(piles, M) <= h) {
				ans = M;
				R = M - 1;
			} else {
				L = M + 1;
			}
		}
		return ans;
	}

	// 一堆香蕉，piles
	// 给定一个速度，speed
	// 同一堆，吃完就躺平，
	// 请返回需要几小时？
	public static int hours(int[] piles, int speed) {
		int ans = 0;
		for (int pile : piles) {
			ans += (pile + speed - 1) / speed;
		}
		return ans;
	}
	
	
	public static void main(String[] args) {
		int a = 8;
		int b = 2;
		// 8 / 2   4   4
		int ans = (a + b - 1) / b;
		System.out.println(ans);
	}

}
