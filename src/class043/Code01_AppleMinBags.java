package class043;

public class Code01_AppleMinBags {

	public static int minBag1(int apple) {
		if (apple < 0) {
			return -1;
		}
		if (apple == 0) {
			return 0;
		}
		// 最多的8号袋开始试，apple / 8
		for (int max = (apple / 8); max >= 0; max--) {
			int rest = apple - (max * 8);
			if (rest % 6 == 0) {
				return max + rest / 6;
			}
		}
		return -1;
	}

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
		} else { // apple >= 18 且 是偶数
			return (apple - 18) / 8 + 3;
		}
	}

	public static void main(String[] args) {
		for (int apple = 0; apple <= 100; apple++) {
			System.out.println(apple + " : " + minBag1(apple));
		}

	}

}
