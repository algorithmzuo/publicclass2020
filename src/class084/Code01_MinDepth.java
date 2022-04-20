package class084;

// 美团面试题
// 来自学员反馈的一面题目
// 求二叉树最小深度
// 本题测试链接 : https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
public class Code01_MinDepth {

	// 不提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int x) {
			val = x;
		}
	}

	// head为头的二叉树，最小深度返回
	public static int ans = Integer.MAX_VALUE;

	public static int minDepth(TreeNode head) {
		if(head == null) {
			return 0;
		}
		ans = Integer.MAX_VALUE;
		zuo(head, 1);
		return ans;
	}

	public static void zuo(TreeNode x, int level) {
		if (x.left == null && x.right == null) {
			ans = Math.min(ans, level);
		} else {
			if (x.left != null) {
				zuo(x.left, level + 1);
			}
			if (x.right != null) {
				zuo(x.right, level + 1);
			}
		}
	}

	// 下面的方法是一般解
	public static int minDepth1(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return p(head);
	}

	// 返回x为头的树，最小深度是多少
	public static int p(TreeNode x) {
		if (x.left == null && x.right == null) {
			return 1;
		}
		// 左右子树起码有一个不为空
		int leftH = Integer.MAX_VALUE;
		if (x.left != null) {
			leftH = p(x.left);
		}
		int rightH = Integer.MAX_VALUE;
		if (x.right != null) {
			rightH = p(x.right);
		}
		return 1 + Math.min(leftH, rightH);
	}

	// 下面的方法是morris遍历的解
	public static int minDepth2(TreeNode head) {
		if (head == null) {
			return 0;
		}
		TreeNode cur = head;
		TreeNode mostRight = null;
		int curLevel = 0;
		int minHeight = Integer.MAX_VALUE;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				int rightBoardSize = 1;
				while (mostRight.right != null && mostRight.right != cur) {
					rightBoardSize++;
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) { // 第一次到达
					curLevel++;
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else { // 第二次到达
					if (mostRight.left == null) {
						minHeight = Math.min(minHeight, curLevel);
					}
					curLevel -= rightBoardSize;
					mostRight.right = null;
				}
			} else { // 只有一次到达
				curLevel++;
			}
			cur = cur.right;
		}
		int finalRight = 1;
		cur = head;
		while (cur.right != null) {
			finalRight++;
			cur = cur.right;
		}
		if (cur.left == null && cur.right == null) {
			minHeight = Math.min(minHeight, finalRight);
		}
		return minHeight;
	}

}
