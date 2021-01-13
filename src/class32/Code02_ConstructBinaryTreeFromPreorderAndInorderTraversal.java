package class32;

import java.util.HashMap;

// 测试链接：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
public class Code02_ConstructBinaryTreeFromPreorderAndInorderTraversal {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int val) {
			this.val = val;
		}
	}

	public static TreeNode buildTree1(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		return process1(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	// 只使用pre[L1...R1]这一段，作为这棵树先序遍历的结果
	// 只使用in [L2...R2]这一段，作为这棵树中序遍历的结果
	// 建立好这棵树，然后把头节点返回
	public static TreeNode process1(int[] pre, int L1, int R1, int[] in, int L2, int R2) {
		if (L1 > R1) {
			return null;
		}
		if (L1 == R1) {
			return new TreeNode(pre[L1]);
		}
		TreeNode head = new TreeNode(pre[L1]);
		int find = L2;
		while (in[find] != pre[L1]) {
			find++;
		}
		head.left = process1(pre, L1 + 1, L1 + find - L2, in, L2, find - 1);
		head.right = process1(pre, L1 + find - L2 + 1, R1, in, find + 1, R2);
		return head;
	}

	public static TreeNode buildTree2(int[] pre, int[] in) {
		if (pre == null || in == null || pre.length != in.length) {
			return null;
		}
		HashMap<Integer, Integer> valueIndexMap = new HashMap<>();
		for (int i = 0; i < in.length; i++) {
			valueIndexMap.put(in[i], i);
		}
		return process2(pre, 0, pre.length - 1, in, 0, in.length - 1, valueIndexMap);
	}

	// 只使用pre[L1...R1]这一段，作为这棵树先序遍历的结果
	// 只使用in [L2...R2]这一段，作为这棵树中序遍历的结果
	// 建立好这棵树，然后把头节点返回
	public static TreeNode process2(int[] pre, int L1, int R1, int[] in, int L2, int R2,
			HashMap<Integer, Integer> valueIndexMap) {
		if (L1 > R1) {
			return null;
		}
		if (L1 == R1) {
			return new TreeNode(pre[L1]);
		}
		TreeNode head = new TreeNode(pre[L1]);
		int find = valueIndexMap.get(pre[L1]);
		head.left = process2(pre, L1 + 1, L1 + find - L2, in, L2, find - 1, valueIndexMap);
		head.right = process2(pre, L1 + find - L2 + 1, R1, in, find + 1, R2, valueIndexMap);
		return head;
	}

}
