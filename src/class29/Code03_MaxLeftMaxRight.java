package class29;

public class Code03_MaxLeftMaxRight {

	// 笨办法，但是好想
	public static int solution1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int ans = Integer.MIN_VALUE;
		for (int leftEnd = 0; leftEnd < N - 1; leftEnd++) {
			int leftMax = arr[0];
			for (int i = 1; i <= leftEnd; i++) {
				leftMax = Math.max(leftMax, arr[i]);
			}
			int rightMax = arr[leftEnd + 1];
			for (int i = leftEnd + 2; i < N; i++) {
				rightMax = Math.max(rightMax, arr[i]);
			}
			ans = Math.max(ans, Math.abs(leftMax - rightMax));
		}
		return ans;
	}

	// 好办法，是我们真正想测试的
	public static int solution2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int N = arr.length;
		int max = arr[0];
		for (int i = 1; i < N; i++) {
			max = Math.max(max, arr[i]);
		}
		return max - Math.min(arr[0], arr[N - 1]);
	}

	public static int[] randomArray(int maxLen, int maxValue) {
		int len = (int) (Math.random() * (maxLen + 1));
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		// 随机数组的最大长度
		int maxLen = 6;
		// 随机数组值的范围
		int maxValue = 30;
		// 测试次数
		int testTime = 3000000;
		System.out.println("如果没有错误信息打印，说明所有测试通过");
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			// 随机得到一个数组，长度也随机，每个值也随机
			int[] arr = randomArray(maxLen, maxValue);
			// 用方法1跑出答案1
			int ans1 = solution1(arr);
			// 用方法2跑出答案2
			int ans2 = solution2(arr);
			// 如果答案1和答案2不一样，提示出错了
			if (ans1 != ans2) {
				System.out.println("出错了！");
				System.out.println(ans1 + " , " + ans2);

				for (int k = 0; k < arr.length; k++) {
					System.out.print(arr[k] + " ");
				}
				System.out.println();

				break;
			}
		}
		System.out.println("测试结束");
	}

}
