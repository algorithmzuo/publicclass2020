package class135;

// 来自亚马逊、谷歌、字节跳动、华为、蔚来 NIO
// 给你一个字符串 s ，请你去除字符串中重复的字母
// 使得每个字母只出现一次
// 需保证 返回结果的字典序最小(要求不能打乱其他字符的相对位置)
// 测试链接 : https://leetcode.cn/problems/remove-duplicate-letters/
public class Code04_RemoveDuplicateLetters {

	public static String removeDuplicateLetters(String s) {
		// 建立词频表，为了判断后面还有没有某种字符
		int[] cnts = new int[26];
		for (int i = 0; i < s.length(); i++) {
			// a : cnts[0]
			// b : cnts[1]
			// c : cnts[2]
			// z : cnts[25]
			cnts[s.charAt(i) - 'a']++;
		}
		// 判断某种字符是不是在栈里
		boolean[] enter = new boolean[26];
		char[] stack = new char[26];
		int size = 0;
		for (int i = 0; i < s.length(); i++) {
			// 从左往右遍历字符
			// 当前的字符，是cur
			char cur = s.charAt(i);
			if (!enter[cur - 'a']) {
				enter[cur - 'a'] = true;
				while (size > 0 && stack[size - 1] > cur && cnts[stack[size - 1] - 'a'] > 0) {
					enter[stack[size - 1] - 'a'] = false;
					size--;
				}
				stack[size++] = cur;
			}
			cnts[cur - 'a']--;
		}
		// 最后把栈里留着的字符，转成字符串
		return String.valueOf(stack, 0, size);
	}

}
