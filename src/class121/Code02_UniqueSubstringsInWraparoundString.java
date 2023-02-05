package class121;

// 把字符串 s 看作 "abcdefghijklmnopqrstuvwxyz" 的无限环绕字符串，
// 所以 s 看起来是这样的：
// ...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....
// 现在给定另一个字符串 p 。返回 s 中 不同 的 p 的 非空子串 的数量
// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
public class Code02_UniqueSubstringsInWraparoundString {

	public int findSubstringInWraproundString(String pattern) {
		char[] p = pattern.toCharArray();
		int n = p.length;
		int ans = 0;
		int len = 1;
		// 256 0~255
		int[] max = new int[256];
		max[p[0]]++;
		for (int i = 1; i < n; i++) {
			char cur = p[i];
			char pre = p[i - 1];
			if ((pre == 'z' && cur == 'a') || pre + 1 == cur) {
				len++;
			} else {
				len = 1;
			}
			max[cur] = Math.max(max[cur], len);
		}
		for (int i = 0; i < 256; i++) {
			ans += max[i];
		}
		return ans;
	}

}
