package class117;

// 测试链接 : https://leetcode.com/problems/candy/
public class Code05_CandyProblem {

	public static int candy(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int N = arr.length;
		int[] left = new int[N];
		for (int i = 1; i < N; i++) {
			if (arr[i - 1] < arr[i]) {
				left[i] = left[i - 1] + 1;
			}
		}
		int[] right = new int[N];
		for (int i = N - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				right[i] = right[i + 1] + 1;
			}
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			ans += Math.max(left[i], right[i]);
		}
		return ans + N;
	}
	
	public static int circleCandy(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return 1;
		}
		int n = arr.length;
		int minIndex = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] <= arr[lastIndex(i, n)] && arr[i] <= arr[nextIndex(i, n)]) {
				minIndex = i;
				break;
			}
		}
		int[] nums = new int[n + 1];
		for (int i = 0; i <= n; i++, minIndex = nextIndex(minIndex, n)) {
			nums[i] = arr[minIndex];
		}
		int[] left = new int[n + 1];
		left[0] = 1;
		for (int i = 1; i <= n; i++) {
			left[i] = nums[i] > nums[i - 1] ? (left[i - 1] + 1) : 1;
		}
		int[] right = new int[n + 1];
		right[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			right[i] = nums[i] > nums[i + 1] ? (right[i + 1] + 1) : 1;
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += Math.max(left[i], right[i]);
		}
		return ans;
	}

	public static int nextIndex(int i, int n) {
		return i == n - 1 ? 0 : (i + 1);
	}

	public static int lastIndex(int i, int n) {
		return i == 0 ? (n - 1) : (i - 1);
	}

}
