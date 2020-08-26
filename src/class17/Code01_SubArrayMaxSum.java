package class17;

public class Code01_SubArrayMaxSum {

	public static int maxSum1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			pre = arr[i] + (pre > 0 ? pre : 0);
			max = Math.max(max, pre);
		}
		return max;
	}

	public static int maxSum2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return max;
	}

	public static int[] generateArray(int N, int V) {
		int n = (int) (Math.random() * N) + 1;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * V) - (int) (Math.random() * V);
		}
		return arr;
	}

	public static void main(String[] args) {
		int N = 100;
		int V = 100;
		int testTime = 1000000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateArray(N, V);
			int ans1 = maxSum1(arr);
			int ans2 = maxSum2(arr);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
