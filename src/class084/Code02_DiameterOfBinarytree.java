package class084;

// 来自学员反馈
// 最近字节的面试题
// 二叉树直径
// 测试链接 : https://leetcode.com/problems/diameter-of-binary-tree/
public class Code02_DiameterOfBinarytree {

	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;
	}

	public static int diameterOfBinaryTree(TreeNode root) {
		return process(root).maxDistance;
	}

	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int m, int h) {
			maxDistance = m;
			height = h;
		}
	}

	public static Info process(TreeNode x) {
		if (x == null) {
			return new Info(0, 0);
		}
		Info leftInfo = process(x.left);
		Info rightInfo = process(x.right);
		int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
				leftInfo.height + rightInfo.height);
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		return new Info(maxDistance, height);
	}

}
