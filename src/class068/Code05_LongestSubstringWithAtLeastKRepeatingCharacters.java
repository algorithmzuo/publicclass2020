package class068;

// 测试链接 : https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
public class Code05_LongestSubstringWithAtLeastKRepeatingCharacters {

	public static int longestSubstring(String s, int k) {
		char[] str = s.toCharArray();
		int N = str.length;
		int max = 0;
		for (int require = 1; require <= 26; require++) {
			int[] count = new int[26];
			int collect = 0;
			int satisfy = 0;
			int R = -1;
			for (int L = 0; L < N; L++) {
				while (R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) {
					R++;
					if (count[str[R] - 'a'] == 0) {
						collect++;
					}
					if (count[str[R] - 'a'] == k - 1) {
						satisfy++;
					}
					count[str[R] - 'a']++;
				}
				if (satisfy == require) {
					max = Math.max(max, R - L + 1);
				}
				if (count[str[L] - 'a'] == 1) {
					collect--;
				}
				if (count[str[L] - 'a'] == k) {
					satisfy--;
				}
				count[str[L] - 'a']--;
			}
		}
		return max;
	}

}
