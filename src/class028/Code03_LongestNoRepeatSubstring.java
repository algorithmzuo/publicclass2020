package class028;

import java.util.HashMap;
import java.util.HashSet;

public class Code03_LongestNoRepeatSubstring {

	/*
	 * 给定一个只由小写字母（a~z）组成的字符串str， 返回其中最长无重复字符的子串长度
	 * 
	 */

	public static int maxNoRepeatSubstringLen1(char[] str) {
		int len = 0;
		for (int L = 0; L < str.length; L++) {
			for (int R = L; R < str.length; R++) {
				HashSet<Character> set = new HashSet<>();
				boolean noRepeat = true;
				for (int i = L; i <= R; i++) {
					if (set.contains(str[i])) {
						noRepeat = false;
						break;
					}
					set.add(str[i]);
				}
				if (noRepeat) {
					int cur = R - L + 1;
					len = Math.max(len, cur);
				}
			}
		}
		return len;
	}

	public static int maxNoRepeatSubstringLen2(char[] str) {
		if (str == null || str.length == 0) {
			return 0;
		}
		int N = str.length;
		int[] dp = new int[N];
		HashMap<Character, Integer> lastMap = new HashMap<>();
		dp[0] = 1;
		lastMap.put(str[0], 0);
		int max = 1;
		for (int i = 1; i < N; i++) {
			int lastIndex = lastMap.containsKey(str[i]) ? lastMap.get(str[i]) : -1;
			int preNo = i - 1 - dp[i - 1];
			int no = Math.max(lastIndex, preNo);
			int curAns = i - no;
			dp[i] = curAns;
			lastMap.put(str[i], i);
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	// for test
	public static String getRandomString(int possibilities, int maxSize) {
		char[] ans = new char[(int) (Math.random() * maxSize) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 26;
		int strMaxSize = 100;
		int testTimes = 10000;
		System.out.println("test begin, test time : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strMaxSize);
			int ans1 = maxNoRepeatSubstringLen1(str.toCharArray());
			int ans2 = maxNoRepeatSubstringLen2(str.toCharArray());
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");

	}

}
