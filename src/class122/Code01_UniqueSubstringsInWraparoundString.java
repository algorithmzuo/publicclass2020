package class122;

// 把字符串 s 看作 "abcdefghijklmnopqrstuvwxyz" 的无限环绕字符串，
// 所以 s 看起来是这样的：
// ...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....
// 现在给定另一个字符串 p 。返回 s 中 不同 的 p 的 非空子串 的数量
// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
public class Code01_UniqueSubstringsInWraparoundString {

	// 省去了想象中的串
	// a...za....za...z......
	public int findSubstringInWraproundString(String str) {
		char[] s = str.toCharArray();
		int n = s.length;
		// ascii, 0~255
		// a...z  0~255
		// a -> 96
		// a -> 107
		// max[a -> 96] = 107
		int[] max = new int[256];
		max[s[0]] = 1;
		// 前一个字符成长的长度
		int len = 1;
		for (int i = 1; i < n; i++) {
			char cur = s[i];
			char pre = s[i - 1];
			// cur == a -> 前一个z
			// cur == d -> 前一个（d-1） -> c
			if ((pre == 'z' && cur == 'a') || pre + 1 == cur) {
				len++;
			} else {
				len = 1;
			}
			max[cur] = Math.max(max[cur], len);
		}
		// 答案，s中有多少不同的非空子串，也是想象串的子串
		int ans = 0;
		for (int i = 0; i < 256; i++) {
			ans += max[i];
		}
		return ans;
	}

}
