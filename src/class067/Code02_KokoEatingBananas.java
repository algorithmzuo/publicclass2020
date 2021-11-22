package class067;

// 测试链接 : https://leetcode.com/problems/koko-eating-bananas/
public class Code02_KokoEatingBananas {

	public static int minEatingSpeed(int[] piles, int h) {
		int L = 1;
		int R = 0;
		for (int pile : piles) {
			R = Math.max(R, pile);
		}
		// 只需要在L ~ R范围上，最小的达标速度
		// 1 ~ max
		int ans = 0;
		int M = 0;
		while (L <= R) {
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

	// 一堆香蕉都在piles数组里
	// 猩猩以speed的速度吃！
	// 但是，这一小时吃完，就躺！
	// 返回一共要几小时？
	public static int hours(int[] piles, int speed) {
		int ans = 0;
		for (int pile : piles) {
			ans += (pile + speed - 1) / speed;
		}
		return ans;
	}

}
