package class143;

// 一共有n个项目，每个项目都有两个信息
// projects[i] = {a, b}
// 表示i号项目做完要a天，但是当你投入b个资源，它就会缩短1天的时间
// 你一共有k个资源，你的目标是完成所有的项目，但是希望总天数尽可能缩短
// 在所有项目同时开工的情况下，返回尽可能少的天数
// 1 <= n <= 10^5
// 1 <= k <= 10^7
public class Code01_MinDaysDoneAllProjects {

	// 这是二分答案法几乎最简单的题了
	public static int minDays(int[][] projects, int k) {
		int l = 0;
		int r = 0;
		for (int[] project : projects) {
			r = Math.max(r, project[0]);
		}
		// l........r
		int m, ans = r;
		while (l <= r) {
			// 0.......100
			//     50天
			m = (l + r) / 2;
			if (yeah(projects, m) <= k) {
				ans = m;
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return ans;
	}

	// 所有的项目，要在days天内结束！返回需要多少资源
	public static int yeah(int[][] projects, int days) {
		int ans = 0;
		for (int[] p : projects) {
			// 28天 20天要完成
			// 8份 
			if (p[0] > days) {
				ans += (p[0] - days) * p[1];
			}
		}
		return ans;
	}

}
