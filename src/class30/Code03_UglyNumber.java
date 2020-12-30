package class30;

// 测试链接：https://leetcode.com/problems/ugly-number-ii
public class Code03_UglyNumber {

	public static boolean isUgly(int num) {
		while (num % 2 == 0) {
			num /= 2;
		}
		while (num % 3 == 0) {
			num /= 3;
		}
		while (num % 5 == 0) {
			num /= 5;
		}
		return num == 1;
	}

	public static int nthUglyNumber1(int n) {
		int find = 0;
		int num = 1;
		for (; find < n; num++) {
			if (isUgly(num)) {
				find++;
			}
		}
		return num - 1;
	}

	public static int nthUglyNumber2(int n) {
		int[] ugly = new int[n + 1];
		ugly[1] = 1;
		int i2 = 1;
		int i3 = 1;
		int i5 = 1;
		for (int i = 2; i <= n; i++) {
			ugly[i] = Math.min(Math.min(ugly[i2] * 2, ugly[i3] * 3), ugly[i5] * 5);
			if (ugly[i] == ugly[i2] * 2) {
				i2++;
			}
			if (ugly[i] == ugly[i3] * 3) {
				i3++;
			}
			if (ugly[i] == ugly[i5] * 5) {
				i5++;
			}
		}
		return ugly[n];
	}

}
