package class067;

// 测试链接 : https://leetcode.com/problems/minimum-window-substring/
public class Code04_MinimumWindowSubstring {

	// 在s中，找到包含t的最短子串
	// 返回最短子串
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
		// [L,R)
		// [1,2) -> 1..1
		// [1,5) -> 1..4
		// [1,1) -> 窗口一个字符也没有了
		// 所有发现达标的窗口中，最短窗口的长度是多少minLen
		// ansl...ansr 到底是哪一段的子串，记一下！
		int minLen = Integer.MAX_VALUE;
		int ansl = -1;
		int ansr = -1;
		// 窗口右边界没到底的时候
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
