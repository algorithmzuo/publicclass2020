package class122;

// 给定一个字符串 s，计算 s 的 不同非空子序列 的个数
// 因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
// 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符
// 但不改变剩余字符相对位置的一个新字符串。
// 本题测试链接 : https://leetcode.com/problems/distinct-subsequences-ii/
public class Code02_DistinctSubseqValue {

	// 提交版本，根据要求要取mod
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

	// 原始code，不取mod
	public static int distinctSubseqII2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		// a -> 0个
		// b -> 0个
		// c -> 0个
		int[] count = new int[26];
		// 空集
		int all = 1;
		for (char x : str) {
			// 纯新增 = 之前的集合数 - 上一个x字符加入后，x结尾的集合总数
			int newAdd = all - count[x - 'a'];
			// 当前的集合，去重数量
			all = all + newAdd;
			// 关于x的记录，也只算新增的部分
			// 'a' -> 97
			// 'a' -> 0
			// ..
			// 'z' -> 25
			// 'a' -'a' = 0
			// 'b' -'a' = 1
			// 'z' -'a' = 25
			count[x - 'a'] = count[x - 'a'] + newAdd;
		}
		// 题目要求，不能把空集算作一个结果，所以减去
		return all - 1;
	}

}
