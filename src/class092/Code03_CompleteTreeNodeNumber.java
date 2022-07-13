package class092;

// 本题测试链接 : https://leetcode.cn/problems/count-complete-tree-nodes/
public class Code03_CompleteTreeNodeNumber {

	// 提交时不要提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	//      1
	//   2     3
	// 4  5  6  7
	// ....
	// 遍历做法！不行！所有节点要走一遍！O(N), N是节点个数！
	//                      1                             1
	//            2                   3                   2
	//      4           5        6        7               3
	//   8     9     a     b   c   d    e   f             4
	// g  h  i  j   k  l                                  5
	// 
	// 先从1出发，看1的右树的最左节点，到了哪一层！
	// c 没到 最深！  说明，1的右树是满的！高度是3，节点数： 7个
	// 1自己，1个节点！   1 + 1的右树 = 8个
	// 整棵树的所有节点： 8个 + 1的左树节点数(递归！)
	// 
	// 再从2出发，看2的右树的最左节点，到了哪一层！
	// k 到了最深！  说明，2的左树是满的！左树高度3，2的左树 = 7个
	// 2自己，1个节点！   2 + 2的左树 = 8个
	// 2这棵树所有的节点 = 8个节点 + 2的右树节点个数(递归！)
	// 
	// 继续从5出发，看5的右树的最左节点，到了哪一层！
	// b 没到 最深！说明，5的右树是满的！高度是1，节点数： 1个
	// 5自己，1个节点！   5 + 5的右树 = 2个
	// 5这棵树所有的节点 = 2个 + 5的左树节点数(递归！)
	//
	// 从a出发！看a的右树的最左节点，到了哪一层！
	//
	
	
	
	// 最深到了4层！这个结论记录！
	// 
	// 满二叉树，高度是h，满二叉树的节点数 2^h - 1
	// 提交如下的方法
	public static int countNodes(TreeNode head) {
		if (head == null) {
			return 0;
		}
		//        当前 几层的节点  最深层是几层
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	// 当前来到node节点，node节点在level层，总层数是h
	// 返回node为头的子树(必是完全二叉树)，有多少个节点
	public static int bs(TreeNode node, int Level, int h) {
		if (Level == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, Level + 1) == h) {
			// node的右树的最左节点，到了最后一层！
			// 说明node的左树，是满的！
			// 左树的节点数 + 1个（node自己） + 右树去递归！
			// 2^(h - level)  -> 1 << (h - level)
			//      node        5(level)
			//                  6
			//                  7
			//                  8（最深h）
			return (1 << (h - Level)) + bs(node.right, Level + 1, h);
		} else {
			// node的右树的最左节点，没到最后一层！
			// 说明node的右树，是满的！-1
			return (1 << (h - Level - 1)) + bs(node.left, Level + 1, h);
		}
	}

	// 时间复杂度
	// 完全二叉树的节点数是N, 完全二叉树的高度 h -> log N
	// 
	//             x      去x右树上走个高度
	//       a            去a右树上走个高度
	//          d         去d右树上走个高度
	//            f       去f右树上走个高度
	//           y        去y右树上走个高度
	// h O(h^2) h logN
	//   O(  (logN)平方   )   < O(N)
	// 如果node在第level层，
	// 求以node为头的子树，最大深度是多少
	// node为头的子树，一定是完全二叉树
	//                 x    19
	//              a       20
	//            b         21
	//          c           22
	//       null           23
	public static int mostLeftLevel(TreeNode node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

}
