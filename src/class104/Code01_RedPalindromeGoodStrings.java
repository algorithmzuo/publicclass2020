package class104;

// 来自阿里
// 小红定义一个仅有r、e、d三种字符的字符串中
// 如果仅有一个长度不小于2的回文子串，那么这个字符串定义为"好串"
// 给定一个正整数n，输出长度为n的好串有多少个
// 结果对10^9 + 7取模， 1 <= n <= 10^9
// 示例：
// n = 1, 输出0
// n = 2, 输出3
// n = 3, 输出18
public class Code01_RedPalindromeGoodStrings {

	public static int cnt = 0;

	public static int num1(int n) {
		cnt = 0;
		process(new char[n], 0, n);
		return cnt;
	}

	// path = {r,r,e,d } .. n
	public static void process(char[] path, int i, int n) {
		if (i == n) {
			if (isGood(path)) {
				cnt++;
			}
		} else {
			path[i] = 'r';
			process(path, i + 1, n);
			path[i] = 'e';
			process(path, i + 1, n);
			path[i] = 'd';
			process(path, i + 1, n);
		}
	}

	// 验证path，是不是好串
	// 好串：只有一个回文子串长度>=2
	public static boolean isGood(char[] path) {
		int ans = 0;
		for (int l = 0; l < path.length; l++) {
			for (int r = l + 1; r < path.length; r++) {
				// path[l...r]是不是回文串！
				if (isP(path, l, r)) {
					ans++;
				}
				if (ans > 1) {
					return false;
				}
			}
		}
		return ans == 1;
	}

	// 暴力方法
	// 为了观察规律
	// 根据好串的定义
	// 长度为n的时候，返回好串的数量
	public static int goods(int n) {
		char[] path = new char[n];
		return process(0, n, path);
	}

	// 长度为n所有可能的字符串，请都得到！
	public static int process(int i, int n, char[] path) {
		if (i == n) {
			return ok(path) ? 1 : 0;
		} else {
			int ans = 0;
			path[i] = 'r';
			ans += process(i + 1, n, path);
			path[i] = 'e';
			ans += process(i + 1, n, path);
			path[i] = 'd';
			ans += process(i + 1, n, path);
			return ans;
		}
	}

	// 如果是好串，返回true；不是好串，返回false
	public static boolean ok(char[] path) {
		int count = 0;
		for (int l = 0; l < path.length; l++) {
			for (int r = l + 1; r < path.length; r++) {
				if (isP(path, l, r)) {
					count++;
				}
				if (count > 1) {
					return false;
				}
			}
		}
		return count == 1;
	}

	public static boolean isP(char[] path, int l, int r) {
		while (l < r) {
			if (path[l] != path[r]) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	// 正式方法
	// 观察规律之后，把规律变成代码
	public static int num2(int n) {
		if (n == 1) {
			return 0;
		}
		if (n == 2) {
			return 3;
		}
		if (n == 3) {
			return 18;
		}
		return 6 * (n + 1);
	}

	public static void main(String[] args) {
		System.out.println(num1(1));
		System.out.println(num1(2));
		System.out.println(num1(3));
		System.out.println(num1(4));
		System.out.println(num1(5));
		System.out.println(num1(6));
		System.out.println(num1(7));
		System.out.println(num1(8));
	}

}
