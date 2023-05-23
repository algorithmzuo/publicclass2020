package class135;

// 测试链接 : https://leetcode.cn/problems/validate-stack-sequences/
public class Code04_ValidateStackSequences {

	public boolean validateStackSequences(int[] pushed, int[] popped) {
		int n = pushed.length;
		int size = 0;
		for (int i = 0, j = 0; i < n; i++) {
			pushed[size++] = pushed[i];
			while (size > 0 && j < n && pushed[size - 1] == popped[j]) {
				size--;
				j++;
			}
		}
		return size == 0;
	}

}
