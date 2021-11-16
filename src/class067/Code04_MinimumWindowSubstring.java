package class067;

// 测试链接 : https://leetcode.com/problems/minimum-window-substring/
public class Code04_MinimumWindowSubstring {

	public static String minWindow(String s, String t) {
		if (s.length() < t.length()) {
			return "";
		}
		char[] str = s.toCharArray();
		char[] target = t.toCharArray();
		int[] map = new int[256];
		for (char cha : target) {
			map[cha]++;
		}
		int all = target.length;
		int L = 0;
		int R = 0;
		int minLen = Integer.MAX_VALUE;
		int ansl = -1;
		int ansr = -1;
		while (R != str.length) {
			map[str[R]]--;
			if (map[str[R]] >= 0) {
				all--;
			}
			if (all == 0) {
				while (map[str[L]] < 0) {
					map[str[L++]]++;
				}
				if (minLen > R - L + 1) {
					minLen = R - L + 1;
					ansl = L;
					ansr = R;
				}
				all++;
				map[str[L++]]++;
			}
			R++;
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(ansl, ansr + 1);
	}

}
