package class054;

// 测试页面：https://leetcode.com/problems/jump-game-ii/
public class Code04_JumpGameII {

	public static int jump(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		// 一开始，人在0位置，一步都没有跳过
		int jump = 0;
		int cur = 0;
		int next = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if(cur < i) {
				jump++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return jump;
	}

}
