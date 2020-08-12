package class15;

public class Code02_ColorLeftRight {

	// RGRGR -> RRRGG
	public static int minPaint(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int rAll = 0;
		for (int i = 0; i < N; i++) {
			rAll += str[i] == 'R' ? 1 : 0;
		}
		int ans = rAll; // 如果数组所有的范围，都是右侧范围，都变成G
		int left = 0;
		for (int i = 0; i < N - 1; i++) { // 0..i 左侧 n-1..N-1
			left += str[i] == 'G' ? 1 : 0;
			rAll -= str[i] == 'R' ? 1 : 0;
			ans = Math.min(ans, left + rAll);
		}
		// 0...N-1 左全部 右无
		ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
		return ans;
	}

	// RGRGR -> RRRGG
	public static int test(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int ans = Integer.MAX_VALUE;
		for (int leftEnd = -1; leftEnd < str.length; leftEnd++) {
			int left = 0;
			for (int i = 0; i <= leftEnd; i++) {
				left += str[i] == 'G' ? 1 : 0;
			}
			int right = 0;
			for (int i = leftEnd + 1; i < str.length; i++) {
				right += str[i] == 'R' ? 1 : 0;
			}
			ans = Math.min(ans, left + right);
		}
		return ans;
	}

	public static String generateString(int len) {
		char[] str = new char[(int) (Math.random() * len) + 1];
		for (int i = 0; i < str.length; i++) {
			str[i] = Math.random() < 0.5 ? 'R' : 'G';
		}
		return String.valueOf(str);
	}

	public static void main(String[] args) {
		int len = 100;
		int testTime = 100000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			String str = generateString(len);
			int ans1 = minPaint(str);
			int ans2 = test(str);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束");
	}

}
