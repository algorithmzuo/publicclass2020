package class094;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 二叉树右视图，
// 有Leetcode测试链接 : https://leetcode.cn/problems/binary-tree-right-side-view/
// 提交如下rightSideView方法，可以直接通过
public class Code03_RightView {

	// 不要提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int v) {
			val = v;
		}
	}

	public static void bfs(TreeNode head) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	public static void bfs2(TreeNode head) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size - 1; i++) {
				TreeNode cur = queue.poll();
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}
			
			TreeNode cur = queue.poll();
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
			
		}
	}

	// 只提交下面的方法
	public static List<Integer> rightSideView(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		if (root == null) {
			return ans;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			TreeNode cur = queue.poll();
			ans.add(cur.val);
			if (cur.right != null) {
				queue.add(cur.right);
			}
			if (cur.left != null) {
				queue.add(cur.left);
			}
			size--;
			while (size > 0) {
				cur = queue.poll();
				if (cur.right != null) {
					queue.add(cur.right);
				}
				if (cur.left != null) {
					queue.add(cur.left);
				}
				size--;
			}
		}
		return ans;
	}

}
