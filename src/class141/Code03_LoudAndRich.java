package class141;

import java.util.ArrayList;

// 来自亚马逊
// 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱
// 以及不同程度的安静值，为了方便起见，我们将编号为 x 的人简称为 "person x "。
// 给你一个数组 richer ，其中 richer[i] = [ai, bi] 
// 表示 person ai 比 person bi 更有钱。另给你一个整数数组 quiet
// 其中 quiet[i] 是 person i 的安静值
// richer 中所给出的数据 逻辑自洽
// 也就是在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况
// 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，
// 在所有拥有的钱肯定不少于 person x 的人中
// person y 是最安静的人（也就是安静值 quiet[y] 最小的人）
// 测试链接 : https://leetcode.cn/problems/loud-and-rich/
public class Code03_LoudAndRich {

	public static int[] loudAndRich(int[][] richer, int[] quiet) {
		int n = quiet.length;
		ArrayList<ArrayList<Integer>> nexts = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			nexts.add(new ArrayList<>());
		}
		int[] degree = new int[n];
		for (int[] r : richer) {
			nexts.get(r[0]).add(r[1]);
			degree[r[1]]++;
		}
		int[] zeroQueue = new int[n];
		int l = 0;
		int r = 0;
		for (int i = 0; i < n; i++) {
			if (degree[i] == 0) {
				zeroQueue[r++] = i;
			}
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = i;
		}
		while (l < r) {
			int cur = zeroQueue[l++];
			for (int next : nexts.get(cur)) {
				if (quiet[ans[next]] > quiet[ans[cur]]) {
					ans[next] = ans[cur];
				}
				if (--degree[next] == 0) {
					zeroQueue[r++] = next;
				}
			}
		}
		return ans;
	}

}
