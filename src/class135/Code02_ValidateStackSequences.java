package class135;

// 来自美团
// 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复
// 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时
// 返回 true；否则，返回 false
// 测试链接 : https://leetcode.cn/problems/validate-stack-sequences/
public class Code02_ValidateStackSequences {

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		int n = pushed.length;
		// 栈的大小
		// 利用pushed[...前面size个空间...]做栈
		int size = 0;
		for (int i = 0, j = 0; i < n; i++) {
			// i : 哪个位置的数字，即将入栈
			// j : 在弹出数组中，要比对的下标
			pushed[size++] = pushed[i];
			while (size > 0 && j < n && pushed[size - 1] == popped[j]) {
				size--;
				j++;
			}
		}
		return size == 0;
	}

}
