package class051;

public class Code01_LongestNoRepeatSubstring {

	// 返回str的最长无重复字符子串长度
	public static int maxLen1(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] s = str.toCharArray();
		int N = s.length;
		int[] dp = new int[N]; // 0 dp[0] 1 dp[1] ... dp max
		int[] map = new int[256];// a 上次出现的位置 b 0 ~ 255
		for (int i = 0; i < 256; i++) {
			// i 字符的asc码 map[i] == -1
			map[i] = -1;
		}
		int ans = 1;
		dp[0] = 1;
		map[s[0]] = 0;
		for (int i = 1; i < N; i++) {
			// i [i]上次出现的位置，i距离
			int p1 = i - map[s[i]];
			int p2 = dp[i - 1] + 1;
			dp[i] = Math.min(p1, p2);
			map[s[i]] = i;
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	public static int maxLen2(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] s = str.toCharArray();
		int N = s.length;
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		int ans = 1;
		int pre = 1;
		map[s[0]] = 0;
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[s[i]], pre + 1);
			map[s[i]] = i;
			ans = Math.max(ans, pre);
		}
		return ans;
	}

	/*
	 * 给定一个只由小写字母（a~z）组成的字符串str， 返回其中最长无重复字符的子串长度
	 * 
	 */
	public static int lnrs1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int max = 0;
		for (int i = 0; i < N; i++) {
			boolean[] set = new boolean[26];
			for (int j = i; j < N; j++) {
				if (set[str[j] - 'a']) {
					break;
				}
				set[str[j] - 'a'] = true;
				max = Math.max(max, j - i + 1);
			}
		}
		return max;
	}

	public static int lnrs2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[] last = new int[26];
		for (int i = 0; i < 26; i++) {
			last[i] = -1;
		}
		last[str[0] - 'a'] = 0;
		int max = 1;
		int preMaxLen = 1;
		for (int i = 1; i < N; i++) {
			preMaxLen = Math.min(i - last[str[i] - 'a'], preMaxLen + 1);
			max = Math.max(max, preMaxLen);
			last[str[i] - 'a'] = i;
		}
		return max;
	}

	// for test
	public static String getRandomString(int possibilities, int maxSize) {
		char[] ans = new char[(int) (Math.random() * maxSize) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 26;
		int strMaxSize = 100;
		int testTimes = 1000000;
		System.out.println("test begin, test time : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strMaxSize);
			int ans1 = lnrs1(str);
			int ans2 = maxLen2(str);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");

	}

}
