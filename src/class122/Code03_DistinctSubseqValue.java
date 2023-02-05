package class122;

// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符
// 但不改变剩余字符相对位置的一个新字符串。
// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Code03_DistinctSubseqValue {

	public static int distinctSubseqII(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int m = 1000000007;
		char[] str = s.toCharArray();
		int[] count = new int[26];
		int all = 1;
		for (char x : str) {
			int newAdd = (all - count[x - 'a'] + m) % m;
			all = (all + newAdd) % m;
			count[x - 'a'] = (count[x - 'a'] + newAdd) % m;
		}
		return (all - 1 + m) % m;
	}

}
