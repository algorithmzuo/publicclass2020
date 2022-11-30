package class111;

import java.util.HashMap;

// 来自亚马逊
// 给定一个字符串数组strs，其中每个字符串都是小写字母组成的
// 如果i < j，并且strs[i]和strs[j]所有的字符随意去排列能组成回文串
// 那么说(i,j)叫做一个互补对(complementary)
// 求strs中有多少个互补对
// strs长度 <= 3 * 10^5
// 单个字符串长度 <= 10^5
// strs里所有字符串总长度 <= 10^6
public class Code01_ComplementaryPairsInStringArray {

	public static int test(String[] strs) {
		// key : 一种字符串的状态
		// value : 这种状态出现了多少次
		HashMap<Integer, Integer> map = new HashMap<>();
		int ans = 0;
		for (String str : strs) {
			int status = 0;
			for (int i = 0; i < str.length(); i++) {
				status ^= 1 << (str.charAt(i) - 'a');
			}
			// 之前的字符串，和当前字符串一样的状态，有多少个？
			ans += map.get(status);
			// 每一位都允许和当前字符串状态不一样，但是剩下的状态得一样
			//              g f e d c b a
			// status :     0 0 1 1 0 0 1
			// a上捣乱 :     0 0 1 1 0 0 0
			// b上捣乱 :     0 0 1 1 0 1 1
			// 0  ~
			// ~  1  ~
			//   ~  2 ~
			for(int a = 0; a < 26; a++) {
				ans += map.get(status ^ (1 << a));
			}
			if (!map.containsKey(status)) {
				map.put(status, 1);
			} else {
				map.put(status, map.get(status) + 1);
			}
		}
		return ans;
	}

	// 暴力方法
	// 为了测试
	public static int num1(String[] strs) {
		int ans = 0;
		for (int i = 0; i < strs.length; i++) {
			for (int j = i + 1; j < strs.length; j++) {
				if (complementary(strs[i], strs[j])) {
					ans++;
				}
			}
		}
		return ans;
	}

	public static boolean complementary(String a, String b) {
		int[] cnt = new int[26];
		for (int i = 0; i < a.length(); i++) {
			cnt[a.charAt(i) - 'a']++;
		}
		for (int i = 0; i < b.length(); i++) {
			cnt[b.charAt(i) - 'a']++;
		}
		int odd = 0;
		for (int num : cnt) {
			if ((num & 1) != 0) {
				odd++;
			}
		}
		return odd < 2;
	}

	// 正式方法
	// O(N*M)，N字符串长，M字符串平均长度
	// 时间复杂度O(N) + O(M)，一共有多少个字符串N，一共有多少字符M
	public static int num2(String[] strs) {
		// key : 某一种状态(int类型，状态)
		// z..d c b a
		// 3 2 1 0
		// 1 0 1 1
		// value : 这样状态的字符串，有几个
		HashMap<Integer, Integer> status = new HashMap<>();
		int ans = 0;
		for (String str : strs) {
			// 当前str这个字符串
			// 它自己的状态，加工好
			// d c b a
			// 0 0 0 1
			int cur = 0;
			for (int i = 0; i < str.length(); i++) {
				cur ^= 1 << (str.charAt(i) - 'a');
			}
			// 一点点都不捣乱，cur，map有几个状态也是cur的字符串
			ans += status.getOrDefault(cur, 0);
			for (int i = 0; i < 26; i++) {
				// 每一位捣乱一下
				// a
				// b
				// c
				// z
				ans += status.getOrDefault(cur ^ (1 << i), 0);
			}
			status.put(cur, status.getOrDefault(cur, 0) + 1);
		}
		return ans;
	}

	// 为了验证
	public static String[] randomStringArray(int n, int m, int r) {
		String[] ans = new String[n];
		for (int i = 0; i < n; i++) {
			int len = (int) (Math.random() * m) + 1;
			char[] str = new char[len];
			for (int j = 0; j < len; j++) {
				str[j] = (char) ((int) (Math.random() * r) + 'a');
			}
			ans[i] = String.valueOf(str);
		}
		return ans;
	}

	public static void main(String[] args) {
		int N = 100;
		int M = 20;
		int R = 5;
		int testTime = 5000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			String[] strs = randomStringArray(n, M, R);
			int ans1 = num1(strs);
			int ans2 = num2(strs);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("测试结束");
	}

}
