package class15;

public class Code02_ColorLeftRight {

	// RGRGR -> RRRGG
	public static int minPaint(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int rAll = 0;
		for (int i = 0; i < N; i++) {
			rAll += str[i] == 'R' ? 1 : 0;
		}
		int ans = rAll; // 如果数组所有的范围，都是右侧范围，都变成G
		int left = 0;
		for (int i = 0; i < N - 1; i++) { // 0..i 左侧 n-1..N-1
			left += str[i] == 'G' ? 1 : 0;
			rAll -= str[i] == 'R' ? 1 : 0;
			ans = Math.min(ans, left + rAll);
		}
		// 0...N-1 左全部 右无
		ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
		return ans;
	}

	public static void main(String[] args) {
		String test = "GGGGGR";
		System.out.println(minPaint(test));
	}

}
