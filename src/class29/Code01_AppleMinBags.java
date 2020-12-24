package class29;

public class Code01_AppleMinBags {

	public static int minBag1(int apple) {
		if (apple < 0) {
			return -1;
		}
		if (apple == 0) {
			return 0;
		}
		// 最多的8号袋开始试，
		for (int max = (apple / 8); max >= 0; max--) {
			int rest = apple - (max * 8);
			if (rest % 6 == 0) {
				return max + rest / 6;
			}
		}
		return -1;
	}

	// 18 ~
	// 18 ~ 25 0组 奇数 -1 偶数 3
	// 26 ~ 33 1组 奇数 -1 偶数 4
	// 34 ~ 41 2组 奇数 -1 偶数 5
	// X
	// i组 奇数 -1 偶数 i+3

	// 规律解 通过
	public static int minBag2(int apple) {
		if (apple < 18) {
			if (apple < 0) {
				return -1;
			}
			if (apple == 0) {
				return 0;
			}
			if (apple == 6 || apple == 8) {
				return 1;
			}
			if (apple == 12 || apple == 14 || apple == 16) {
				return 2;
			}
			return -1;
		}
		int team = (apple - 18) / 8;
		return (apple & 1) == 0 ? (team + 3) : -1;
	}

	public static int minBagAwesome(int apple) {
		if (apple < 0 || (apple & 1) != 0) {
			return -1;
		}
		if (apple == 0) {
			return 0;
		}
		if (apple < 18) {
			if (apple == 6 || apple == 8) {
				return 1;
			} else if (apple == 12 || apple == 14 || apple == 16) {
				return 2;
			} else {
				return -1;
			}
		} else {
			return (apple - 18) / 8 + 3;
		}
	}

	public static void main(String[] args) {
		for (int apple = 0; apple <= 100; apple++) {
			System.out.println(minBag1(apple) == minBag2(apple));
		}

	}

}
