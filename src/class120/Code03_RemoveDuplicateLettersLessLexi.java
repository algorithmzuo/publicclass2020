package class120;

// 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次
// 需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
// 注意 : 只有小写英文字母
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
public class Code03_RemoveDuplicateLettersLessLexi {

	public static String removeDuplicateLetters(String s) {
		char[] str = s.toCharArray();
		int[] map = new int[26];
		for (int i = 0; i < str.length; i++) {
			map[str[i] - 'a']++;
		}
		char[] res = new char[26];
		int index = 0;
		int L = 0;
		int R = 0;
		while (R != str.length) {
			if (map[str[R] - 'a'] == -1 || --map[str[R] - 'a'] > 0) {
				R++;
			} else {
				int pick = -1;
				for (int i = L; i <= R; i++) {
					if (map[str[i] - 'a'] != -1 && (pick == -1 || str[i] < str[pick])) {
						pick = i;
					}
				}
				res[index++] = str[pick];
				for (int i = pick + 1; i <= R; i++) {
					if (map[str[i] - 'a'] != -1) {
						map[str[i] - 'a']++;
					}
				}
				map[str[pick] - 'a'] = -1;
				L = pick + 1;
				R = L;
			}
		}
		return String.valueOf(res, 0, index);
	}

}
