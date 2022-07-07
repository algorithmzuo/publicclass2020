package class091;

// 给定一个正整数数组arr，把arr想象成一个直方图。
// 返回这个直方图如果装水，能装下几格水？
// 测试链接 : https://leetcode.cn/problems/trapping-rain-water
public class Code03_TrappingRainWater {

	public static int trap(int[] arr) {
		if (arr == null || arr.length < 3) {
			return 0;
		}
		int n = arr.length;
		int maxLeft = arr[0];
		int maxRight = arr[n - 1];
		int L = 1;
		int R = n - 2;
		int ans = 0;
		while (L <= R) {
			// 左边部分最大值 < 右边部分最大值，让左侧动
			// 左边部分最大值 == 右边部分最大值，也让左侧动
			if (maxLeft <= maxRight) {
				ans += Math.max(maxLeft - arr[L], 0);
				maxLeft = Math.max(maxLeft, arr[L++]);
			} else { // 左边部分最大值 > 右边部分最大值，让右侧动
				ans += Math.max(maxRight - arr[R], 0);
				maxRight = Math.max(maxRight, arr[R--]);
			}
		}
		return ans;
	}

	public static int trap1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int water = 0;
		for (int i = 1; i < N - 1; i++) {
			int leftMax = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				leftMax = Math.max(leftMax, arr[j]);
			}
			int rightMax = Integer.MIN_VALUE;
			for (int j = i + 1; j < N; j++) {
				rightMax = Math.max(rightMax, arr[j]);
			}
			water += Math.max(Math.min(leftMax, rightMax) - arr[i], 0);
		}
		return water;
	}

	public static int trap2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] leftMaxs = new int[N];
		leftMaxs[0] = arr[0];
		for (int i = 1; i < N; i++) {
			leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
		}

		int[] rightMaxs = new int[N];
		rightMaxs[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
		}
		int water = 0;
		for (int i = 1; i < N - 1; i++) {
			water += Math.max(Math.min(leftMaxs[i - 1], rightMaxs[i + 1]) - arr[i], 0);
		}
		return water;
	}

	public static int trap3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int[] rightMaxs = new int[N];
		rightMaxs[N - 1] = arr[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i]);
		}
		int water = 0;
		int leftMax = arr[0];
		for (int i = 1; i < N - 1; i++) {
			water += Math.max(Math.min(leftMax, rightMaxs[i + 1]) - arr[i], 0);
			leftMax = Math.max(leftMax, arr[i]);
		}
		return water;
	}

	public static int trap4(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int L = 1;
		int leftMax = arr[0];
		int R = N - 2;
		int rightMax = arr[N - 1];
		int water = 0;
		while (L <= R) {
			if (leftMax <= rightMax) {
				water += Math.max(0, leftMax - arr[L]);
				leftMax = Math.max(leftMax, arr[L++]);
			} else {
				water += Math.max(0, rightMax - arr[R]);
				rightMax = Math.max(rightMax, arr[R--]);
			}
		}
		return water;
	}

}
