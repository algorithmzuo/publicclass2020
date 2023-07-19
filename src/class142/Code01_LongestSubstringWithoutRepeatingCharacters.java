package class142;

import java.util.Arrays;

// 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
// 测试链接 : https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class Code01_LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		String test = "abcabcbb";
		maxLen(test);
	}

	public static int maxLen(String str) {
		if (str == null || str.equals("")) {
			return 0;
		}
		char[] s = str.toCharArray();
		int[] map = new int[256];
		Arrays.fill(map, -1);
		int n = s.length;
		int[] dp = new int[n];
		dp[0] = 1;
		map[s[0]] = 0;
		int ans = 1;
		for (int i = 1; i < n; i++) {
			int p1 = i - map[s[i]];
			int p2 = dp[i - 1] + 1;
			dp[i] = Math.min(p1, p2);
			ans = Math.max(ans, dp[i]);
			map[s[i]] = i;
		}
		return ans;
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] map = new int[256];
		for (int i = 0; i < 256; i++) {
			map[i] = -1;
		}
		map[str[0]] = 0;
		int N = str.length;
		int ans = 1;
		int pre = 1;
		for (int i = 1; i < N; i++) {
			pre = Math.min(i - map[str[i]], pre + 1);
			ans = Math.max(ans, pre);
			map[str[i]] = i;
		}
		return ans;
	}

}
