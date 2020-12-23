package class29;

public class Code01_AppleMinBags {

	public static int minBag(int apple) {
		if (apple < 0) {
			return -1;
		}
		if (apple == 0) {
			return 0;
		}
		// max 决定用多少个8号袋 100 / 8 = 12 11 10 ... 0
		for (int max = (apple / 8); max >= 0; max--) {
			// max 个 8号袋的时候，max * 8， 6号袋剩下的苹果数 = apple - max * 8
			int rest = apple - (max * 8);
			if (rest % 6 == 0) {
				return max + rest / 6;
			}
		}
		return -1;
	}

	// O(1)
	public static int test(int apple) {
		if(apple < 0) {
			return -1;
		}
		if(apple == 0) {
			return 0;
		}
		if(apple < 18) {
			if(apple == 6 || apple == 8) {
				return 1;
			}else if(apple == 12 || apple == 14 || apple == 16) {
				return 2;
			}else {
				return -1;
			}
		}
		// apple >= 18
		// apple 第几组？
		// (apple - 18) / 8
		// 18 ~ 25  0组  奇数 -1  偶数 3
		// 26 ~ 33  1组  奇数 -1  偶数 4
		// 34 ~ 41  2组  奇数 -1  偶数 5
		//   X >= 18  ->  X属于i组，  X是奇数  -1  偶数 i + 3
		return apple % 2 == 0 ? ((apple - 18) / 8 + 3) : -1;
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
		for (int apple = 0; apple < 100; apple++) {
			System.out.println(minBag(apple) == test(apple));
		}

	}

}
