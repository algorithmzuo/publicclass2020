package class142;

// 给两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
// 可以对一个单词进行如下三种操作：
// 插入一个字符，代价icost
// 删除一个字符，代价dcost
// 替换一个字符，代价rcost
// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class Code03_EditCost {

	public int minDistance(String word1, String word2) {
		return minDistance(word1, word2, 1, 1, 1);
	}

	public static int minDistance(String str1, String str2, int icost, int dcost, int rcost) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		char[] longs = chs1.length >= chs2.length ? chs1 : chs2;
		char[] shorts = chs1.length < chs2.length ? chs1 : chs2;
		if (chs1.length < chs2.length) {
			int tmp = icost;
			icost = dcost;
			dcost = tmp;
		}
		int[] dp = new int[shorts.length + 1];
		for (int i = 1; i <= shorts.length; i++) {
			dp[i] = icost * i;
		}
		for (int i = 1; i <= longs.length; i++) {
			int pre = dp[0];
			dp[0] = dcost * i;
			for (int j = 1; j <= shorts.length; j++) {
				int tmp = dp[j];
				if (longs[i - 1] == shorts[j - 1]) {
					dp[j] = pre;
				} else {
					dp[j] = pre + rcost;
				}
				dp[j] = Math.min(dp[j], dp[j - 1] + icost);
				dp[j] = Math.min(dp[j], tmp + dcost);
				pre = tmp;
			}
		}
		return dp[shorts.length];
	}

}
