package class118;

// 来自字节跳动
// 牛客的测试链接：
// https://www.nowcoder.com/questionTerminal/8ecfe02124674e908b2aae65aad4efdf
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 把如下的全部代码拷贝进java编辑器
// 把文件大类名字改成Main，可以直接通过
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code03_CherryPickup {

	public static int maxCherry(int[][] grid) {
		return getMaxCherry(grid, 0, 0, 0, 0);
	}

	// A (a,b)
	// B (c,d)
	// 每一次A走一步，B也走一步，向下或者向右
	// 两个人，共同走到右下角，一起获得的最大樱桃数，返回
	// 注意 : A和B如果来到同一个地方，樱桃只算一份！
	public static int getMaxCherry(int[][] grid, int a, int b, int c, int d) {
		// 结束 : 共同来到右下角
		if (a == grid.length - 1 && b == grid[0].length - 1) {
			if (grid[grid.length - 1][grid[0].length - 1] == -1) {
				return -1;
			}
			if (grid[grid.length - 1][grid[0].length - 1] == 0) {
				return 0;
			}
			// 右下角是1
			return 1;
		}
		// A (a,b)
		// B (c,d)
		if (grid[a][b] == -1 || grid[c][d] == -1) {
			return -1;
		}
		// 当前A+B获得多少樱桃
		int cur = 0;
		if (a == c && b == d) {
			if (grid[a][b] == 1) {
				cur = 1;
			}
		} else {
			int aPick = grid[a][b] == 1 ? 1 : 0;
			int bPick = grid[c][d] == 1 ? 1 : 0;
			cur = aPick + bPick;
		}
		// 1) A下 B下
		int p1 = -1;
		if (a + 1 < grid.length && c + 1 < grid.length) {
			p1 = getMaxCherry(grid, a + 1, b, c + 1, d);
		}
		// 2) A 下 B右
		int p2 = -1;
		if (a + 1 < grid.length && d + 1 < grid[0].length) {
			p2 = getMaxCherry(grid, a + 1, b, c, d + 1);
		}
		// 3) A 右 B下
		int p3 = -1;
		// ...
		// 4) A 右 B右
		int p4 = -1;
		// ...
		// 最好的后续
		int next = Math.max(Math.max(p1, p2), Math.max(p3, p4));
		if (next == -1) {
			return -1;
		}
		return cur + next;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			int N = (int) in.nval;
			in.nextToken();
			int M = (int) in.nval;
			int[][] matrix = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					in.nextToken();
					matrix[i][j] = (int) in.nval;
				}
			}
			out.println(cherryPickup(matrix));
			out.flush();
		}
	}

	// 如下方法，在leetcode上提交也能通过
	// 测试链接 : https://leetcode.cn/problems/cherry-pickup/
	public static int cherryPickup(int[][] grid) {
		int N = grid.length;
		int M = grid[0].length;
		int[][][] dp = new int[N][M][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < N; k++) {
					dp[i][j][k] = Integer.MIN_VALUE;
				}
			}
		}
		int ans = process(grid, 0, 0, 0, dp);
		return ans < 0 ? 0 : ans;
	}

	public static int process(int[][] grid, int x1, int y1, int x2, int[][][] dp) {
		if (x1 == grid.length || y1 == grid[0].length || x2 == grid.length || x1 + y1 - x2 == grid[0].length) {
			return Integer.MIN_VALUE;
		}
		if (dp[x1][y1][x2] != Integer.MIN_VALUE) {
			return dp[x1][y1][x2];
		}
		if (x1 == grid.length - 1 && y1 == grid[0].length - 1) {
			dp[x1][y1][x2] = grid[x1][y1];
			return dp[x1][y1][x2];
		}
		int next = Integer.MIN_VALUE;
		// x1 + 1, y1 A下 x2 + 1 B下
		// x1 + 1, y1, A下 x2 y2+1 B右
		next = Math.max(next, process(grid, x1 + 1, y1, x2 + 1, dp));
		next = Math.max(next, process(grid, x1 + 1, y1, x2, dp));
		next = Math.max(next, process(grid, x1, y1 + 1, x2, dp));
		next = Math.max(next, process(grid, x1, y1 + 1, x2 + 1, dp));
		if (grid[x1][y1] == -1 || grid[x2][x1 + y1 - x2] == -1 || next == -1) {
			dp[x1][y1][x2] = -1;
			return dp[x1][y1][x2];
		}
		if (x1 == x2) {
			dp[x1][y1][x2] = grid[x1][y1] + next;
			return dp[x1][y1][x2];
		}
		dp[x1][y1][x2] = grid[x1][y1] + grid[x2][x1 + y1 - x2] + next;
		return dp[x1][y1][x2];
	}

}