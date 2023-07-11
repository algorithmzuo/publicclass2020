package class141;

import java.util.ArrayList;

// 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1
// 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi]
// 表示在选修课程 ai 前 必须 先选修 bi
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1]
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序
// 你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组
// 测试链接 : https://leetcode.cn/problems/course-schedule-ii/
public class Code01_CourseScheduleII {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// numCourses : 一共有几个点，7，
		// 点的编号： 0 、1 2 3 4 5 6
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		int[] indegree = new int[numCourses];
		for (int[] edge : prerequisites) {
			// [3, 1] : 1 -> 3
			int to = edge[0];
			int from = edge[1];
			graph.get(from).add(to);
			indegree[to]++;
		}
		int[] zeroQueue = new int[numCourses];
		int l = 0;
		int r = 0;
		// 所有节点，过一遍，入度为0的点，先进去，zeroQueue
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				zeroQueue[r++] = i;
			}
		}
		// 统计一下，拓扑排序的过程中，收集了多少入度为0的点！
		int count = 0;
		while (l < r) {
			int cur = zeroQueue[l++];
			count++;
			// 消除影响，当前的点cur，
			for (int next : graph.get(cur)) {
				if (--indegree[next] == 0) {
					zeroQueue[r++] = next;
				}
			}
		}
		return count == numCourses ? zeroQueue : new int[0];
	}

}
