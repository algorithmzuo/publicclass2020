package class29;

public class Code01_AppleMinBags {

	public static int minBag(int apple) {
		if (apple < 0) {
			return -1;
		}
		if (apple == 0) {
			return 0;
		}
		for (int max = (apple / 8); max >= 0; max--) {
			int rest = apple - (max * 8);
			if (rest % 6 == 0) {
				return max + rest / 6;
			}
		}
		return -1;
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
			System.out.println(minBag(apple));
		}

	}

}
