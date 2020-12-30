package class30;

// 测试链接：https://leetcode.com/problems/ugly-number-ii
public class Code04_UglyNumber {

	public static int nthUglyNumber(int n) {
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
