package class138;

// 来自华为OD
// 完美走位问题
// 给定一个由'W'、'A'、'S'、'D'四种字符组成的字符串，长度一定是4的倍数
// 你可以把任意连续的一段子串，变成'W'、'A'、'S'、'D'组成的随意状态
// 目的是让4种字符词频一样
// 返回需要修改的最短子串长度
// 找到了出处，是leetcode原题
// 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Code02_ReplaceTheSubstringForBalancedString {

	// Q W E R
	// 0 1 2 3
	public static int balancedString(String str) {
		int n = str.length();
		// str : Q W W E E R ...
		// arr : 0 1 1 2 2 3 ...
		//
		int[] arr = new int[n];
		int[] cnts = new int[4];
		for (int i = 0; i < n; i++) {
			char c = str.charAt(i);
			arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
			cnts[arr[i]]++;
		}
		int ans = n;
		// L = 0......
		// 1.....
		// 2....r
		// 3......
		for (int l = 0, r = 0; l < n; l++) {
			// [l...r)
			// 3...17 X
			// 3.....18 X
			// 3.......19
			while (!ok(cnts, l, r) && r < n) {
				// r++ 窗口扩了
				// 原本r位置的数，词频减少1点，因为进了窗口
				cnts[arr[r++]]--;
			}
			// 1) [l....r) ok
			// 2) l......最后位置了！还是不ok！
			if (ok(cnts, l, r)) {
				ans = Math.min(ans, r - l);
			} else {
				break;
			}
			// l位置的字符，要出窗口了，所以词频统计的表，要返还1点
			cnts[arr[l]]++;
		}
		return ans;
	}

	// l , r
	// l....r-1
	// [l.....r) 字符串这个范围上，自由变化！r - l
	// 请问能不能让4种字符整体一样多
	// cnts : l....r 外部的四种字符，词频统计
	// A : cnts[0]
	// B : cnts[1]
	// C : cnts[2]
	// D : cnts[3]
	public static boolean ok(int[] cnts, int l, int r) {
		// 看一下最大词频是多少
		// A : 20
		// B : 16
		// C : 20
		// D : 10
		int maxCnt = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
		// l...r中，要拿出多少个空间，去把所有不够最大词频的字符，填上来
		// maxCnt - A数量
		// maxCnt - B数量
		// maxCnt - C数量
		// maxCnt - D数量
		// 4 * maxCnt - A - B - C - D
		int changes = maxCnt * 4 - cnts[0] - cnts[1] - cnts[2] - cnts[3];
		// 还剩多少空间rest
		int rest = r - l - changes;
		return rest >= 0 && rest % 4 == 0;
	}

}
