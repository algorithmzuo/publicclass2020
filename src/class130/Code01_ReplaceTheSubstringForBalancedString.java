package class130;

// 来自华为
// 完美走位问题
// 给定一个由'W'、'A'、'S'、'D'四种字符组成的字符串，长度一定是4的倍数
// 你可以把任意连续的一段子串，变成'W'、'A'、'S'、'D'组成的随意状态
// 目的是让4种字符词频一样
// 返回需要修改的最短子串长度
// 找到了出处，是leetcode原题
// 测试链接 : https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
public class Code01_ReplaceTheSubstringForBalancedString {

	// 'Q', 'W', 'E', 'R'
	// Q : 0
	// W : 1
	// E : 2
	// R : 3
	public static int balancedString(String str) {
		int n = str.length();
		// "QQRREQQW"
		// 0, 0, 3, 3, 2, 0, 0, 1
		int[] arr = new int[n];
		int[] cnts = new int[4];
		for (int i = 0; i < n; i++) {
			char c = str.charAt(i);
			arr[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
			cnts[arr[i]]++;
		}
		// cnts[0~3]，整体的每种字符的词频统计
		// 划窗口的过程中，进入窗口的字符，的那词频，不用被cnts统计到！
		int ans = n;
		for (int l = 0, r = 0; l < n; l++) {
			// l = 0....
			// l = 1....
			// l = 2....
			// !ok(cnts, l, r) : l....r 还做不到，自由变换能让整体4种字符一样多
			// && r < n : 还能往右进行更大窗口的尝试
			while (!ok(cnts, l, r) && r < n) {
				cnts[arr[r++]]--;
			}
			// 1) !ok(cnts, l, r) r == n, 此时，不用努力了，窗口的右边界已经到底了，可以退出了
			// 2) ok(cnts, l, r)，此时，做到了!
			if (ok(cnts, l, r)) {
				ans = Math.min(ans, r - l);
			} else {
				break;
			}
			cnts[arr[l]]++;
		}
		return ans;
	}

	// 判断函数，当前可以自由支配的窗口 str[l....r)
	// int[] cnts , 4种字符，在窗口之外的词频
	// cnts[0]、cnts[1]、cnts[2]、cnts[3]
	// 返回，当前窗口能不能让整体4种字符一样多
	public static boolean ok(int[] cnts, int l, int r) {
		// maxCnt : 窗口之外，4种字符的最大词频
		int maxCnt = Math.max(Math.max(cnts[0], cnts[1]), Math.max(cnts[2], cnts[3]));
		// 先补齐短板 : maxCnt - cnts[0] 、maxCnt - cnts[1]、maxCnt - cnts[2]、maxCnt - cnts[3]
		int changes = maxCnt * 4 - cnts[0] - cnts[1] - cnts[2] - cnts[3];
		// 总共窗口
		// l = 3, r = 6
		// str[3....6)
		// 窗口长度 : r - l
		int rest = r - l - changes;
		return rest >= 0 && rest % 4 == 0;
	}

}
