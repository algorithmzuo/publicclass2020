package class096;

// 给定一个只由小写字母和数字字符组成的字符串str
// 要求子串必须只含有一个小写字母，数字字符数量随意
// 求这样的子串最大长度是多少
public class Code04_LongestOneLetterManyNumberString {

	// 一个绝对正确的暴力方法
	public static int right(String s) {
		char[] str = s.toCharArray();
		int ans = 0;
		for (int i = 0; i < str.length; i++) {
			for (int j = i; j < str.length; j++) {
				if (check(str, i, j)) {
					ans = Math.max(ans, j - i + 1);
				}
			}
		}
		return ans;
	}

	public static boolean check(char[] str, int l, int r) {
		int letterNumber = 0;
		for (int i = l; i <= r; i++) {
			if (str[i] >= 'a' && str[i] <= 'z') {
				letterNumber++;
			}
		}
		return letterNumber == 1;
	}

	// 用窗口
	// 时间复杂度O(N)
	public static int zuo(String s) {
		char[] str = s.toCharArray();
		int n = str.length;
		// 窗口内小写字母的数量
		int letters = 0;
		// 右边界
		// 0.....5 6(x)
		// [Left, right)
		// [Left, right-1]
		// [0,0) -> 代表窗口一个数也没有
		int right = 0;
		int ans = 0;
		// 窗口开始的位置left
		// 枚举了窗口每一个开始的位置
		for (int left = 0; left < n; left++) {
			// left......right(停！)
			while (right < n) { // right不能越界
				if (letters == 1 
						&& str[right] >= 'a' 
						&& str[right] <= 'z') {
					break;
				}
				// right往右扩!
				if (str[right] >= 'a' && str[right] <= 'z') {
					letters++;
				}
				right++;
			}
			// right已经来到X的位置
			// left......... X
			if (letters == 1) {
				ans = Math.max(ans, right - left);
			}
			// left 往右 吐出一个字符
			if (str[left] >= 'a' && str[left] <= 'z') {
				letters--;
			}
		}
		return ans;
	}

	// 为了测试
	public static char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	// 为了测试
	public static String randomString(int n) {
		char[] str = new char[n];
		for (int i = 0; i < n; i++) {
			str[i] = chars[(int) (Math.random() * chars.length)];
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int testTimes = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			int n = (int) (Math.random() * N) + 1;
			String str = randomString(n);
			int ans1 = right(str);
			int ans2 = zuo(str);
			if (ans1 != ans2) {
				System.out.println(str);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
