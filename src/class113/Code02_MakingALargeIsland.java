package class113;

import java.util.ArrayList;

// 来自亚马逊、谷歌、微软、Facebook、Bloomberg
// 给你一个大小为 n x n 二进制矩阵 grid 。最多 只能将一格 0 变成 1 。
// 返回执行此操作后，grid 中最大的岛屿面积是多少？
// 岛屿 由一组上、下、左、右四个方向相连的 1 形成。
// 测试链接 : https://leetcode.cn/problems/making-a-large-island/
public class Code02_MakingALargeIsland {

	public static int largestIsland(int[][] grid) {
		int n = grid.length;
		int m = grid[0].length;
		ArrayList<Integer> sizes = new ArrayList<>();
		sizes.add(0);
		sizes.add(0);
		int id = 2;
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 1) {
					int curSize = infect(grid, i, j, id, n, m);
					ans = Math.max(ans, curSize);
					sizes.add(id++, curSize);
				}
			}
		}
		boolean[] visited = new boolean[id];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 0) {
					int up = i - 1 >= 0 ? grid[i - 1][j] : 0;
					int down = i + 1 < n ? grid[i + 1][j] : 0;
					int left = j - 1 >= 0 ? grid[i][j - 1] : 0;
					int right = j + 1 < m ? grid[i][j + 1] : 0;
					int merge = 1 + sizes.get(up);
					visited[up] = true;
					if (!visited[down]) {
						merge += sizes.get(down);
						visited[down] = true;
					}
					if (!visited[left]) {
						merge += sizes.get(left);
						visited[left] = true;
					}
					if (!visited[right]) {
						merge += sizes.get(right);
						visited[right] = true;
					}
					ans = Math.max(ans, merge);
					visited[up] = false;
					visited[down] = false;
					visited[left] = false;
					visited[right] = false;
				}
			}
		}
		return ans;
	}

	public static int infect(int[][] grid, int i, int j, int v, int n, int m) {
		if (i < 0 || i == n || j < 0 || j == m || grid[i][j] != 1) {
			return 0;
		}
		int ans = 1;
		grid[i][j] = v;
		ans += infect(grid, i - 1, j, v, n, m);
		ans += infect(grid, i + 1, j, v, n, m);
		ans += infect(grid, i, j - 1, v, n, m);
		ans += infect(grid, i, j + 1, v, n, m);
		return ans;
	}

	// O(N * M)
	public static int[] infect(int[][] map) {
		int cnt = 2;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) { // 一个新的岛！
					zuo(map, i, j, cnt++);
				}
			}
		}
		int[] size = new int[cnt];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 1) {
					size[map[i][j]]++;
				}
			}
		}
		return size;
	}

	public static void zuo(int[][] map, int i, int j, int th) {
		if (i < 0 || i == map.length || j < 0 || j == map[0].length || map[i][j] != 1) {
			return;
		}
		// i,j 不越界 map[i][j] == 1
		map[i][j] = th;
		zuo(map, i - 1, j, th);
		zuo(map, i + 1, j, th);
		zuo(map, i, j - 1, th);
		zuo(map, i, j + 1, th);
	}

	public static void main(String[] args) {
		int[][] map = { { 0, 1, 0, 0, 1, 1 }, { 1, 1, 1, 0, 0, 0 }, { 1, 1, 0, 1, 1, 0 }, { 1, 1, 0, 0, 1, 0 }, };
		int[] size = infect(map);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

		for (int i = 2; i < size.length; i++) {
			System.out.println("编号" + i + ", 大小" + size[i]);
		}

	}

}
