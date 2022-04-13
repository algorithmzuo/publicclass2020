package class083;

// 小红书
// 3.13 笔试
// 数组里有0和1，一定要翻转一个区间，翻转：0变1，1变0
// 请问翻转后可以使得1的个数最多是多少？
public class Code01_MaxOneNumbers {

	// arr中，子数组的最大累加和，返回
	public static int maxSum1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int[] dp = new int[n];
		dp[0] = arr[0];
		int max = dp[0];
		for (int i = 1; i < n; i++) {
			int p1 = arr[i];
			int p2 = dp[i - 1] + arr[i];
			dp[i] = Math.max(p1, p2);
			max = Math.max(max, dp[i]);
		}
		return max;
	}
	
	public static int maxSum2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		int n = arr.length;
		int pre = arr[0]; // pre -> dp[0];
		int max = pre;
		for (int i = 1; i < n; i++) {
			pre = Math.max(arr[i], pre + arr[i]);
			max = Math.max(max, pre);
		}
		return max;
	}
	
	
	

	public static int maxOneNumbers1(int[] arr) {
		int ans = 0;
		for (int l = 0; l < arr.length; l++) {
			for (int r = l; r < arr.length; r++) {
				reverse(arr, l, r);
				ans = Math.max(ans, oneNumbers(arr));
				reverse(arr, l, r);
			}
		}
		return ans;
	}

	public static void reverse(int[] arr, int l, int r) {
		for (int i = l; i <= r; i++) {
			arr[i] ^= 1;
		}
	}

	public static int oneNumbers(int[] arr) {
		int ans = 0;
		for (int num : arr) {
			ans += num;
		}
		return ans;
	}

	public static int maxOneNumbers2(int[] arr) {
		int ans = 0;
		for (int num : arr) {
			ans += num;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] == 0 ? 1 : -1;
		}
		int max = Integer.MIN_VALUE;
		int cur = 0;
		for (int i = 0; i < arr.length; i++) {
			cur += arr[i];
			max = Math.max(max, cur);
			cur = cur < 0 ? 0 : cur;
		}
		return ans + max;
	}

	// 为了测试
	public static int[] randomArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * 2);
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n);
			int ans1 = maxOneNumbers1(arr);
			int ans2 = maxOneNumbers2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
