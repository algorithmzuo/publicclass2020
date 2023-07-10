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
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		int[] indegree = new int[numCourses];
		for (int[] arr : prerequisites) {
			int to = arr[0];
			int from = arr[1];
			graph.get(from).add(to);
			indegree[to]++;
		}
		int[] zeroQueue = new int[numCourses];
		int l = 0;
		int r = 0;
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				zeroQueue[r++] = i;
			}
		}
		int count = 0;
		while (l < r) {
			int cur = zeroQueue[l++];
			count++;
			for (int next : graph.get(cur)) {
				if (--indegree[next] == 0) {
					zeroQueue[r++] = next;
				}
			}
		}
		return count == numCourses ? zeroQueue : new int[0];
	}

}
