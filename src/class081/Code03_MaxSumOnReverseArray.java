package class081;

// 来自美团
// 最大子段和是
// 一个经典问题，即对于一个数组找出其和最大的子数组。
// 现在允许你在求解该问题之前翻转这个数組的连续一段
// 如翻转(1,2,3,4,5,6)的第三个到第五个元素組成的子数组得到的是(1,2,5,4,3,6)，
// 则翻转后该数组的最大子段和最大能达到多少？
// 来自字节
// 几乎一样的题，来自字节笔试第4题
// 给定两个数組values和numbers，
// values[i]表示i号宝石的单品价值
// numbers[i]表示i号宝石的数量
// i号宝石的总价值 = values[i] * numbers[i]
// 如果有一种魔法，可以翻转任何区间L...R的宝石，也就是改变L..R的宝石排列，变成逆序的
// 求在允许用一次魔法的情况下，任取一段连续区间，能达到的最大价值
// 这两个问法解法都几乎一样，区别无非是:
// 美团的: 可进行一次翻转情况下，子数组最大累加和
// 字节的: 可进行一次翻转情况下，子数组最大价值和
public class Code03_MaxSumOnReverseArray {

	public static int maxSumReverse1(int[] arr) {
		int ans = Integer.MIN_VALUE;
		for (int L = 0; L < arr.length; L++) {
			for (int R = L; R < arr.length; R++) {
				reverse(arr, L, R);
				ans = Math.max(ans, maxSum(arr));
				reverse(arr, L, R);
			}
		}
		return ans;
	}

	public static void reverse(int[] arr, int L, int R) {
		while (L < R) {
			int tmp = arr[L];
			arr[L++] = arr[R];
			arr[R--] = tmp;
		}
	}

	public static int maxSum(int[] arr) {
		int pre = arr[0];
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			pre = Math.max(arr[i], arr[i] + pre);
			max = Math.max(max, pre);
		}
		return max;
	}

	public static int maxSumReverse2(int[] arr) {
		int n = arr.length;
		int[] prefix = new int[n];
		prefix[n - 1] = arr[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			prefix[i] = arr[i] + Math.max(0, prefix[i + 1]);
		}
		int ans = prefix[0];
		int suffix = arr[0];
		int maxSuffix = suffix;
		for (int i = 1; i < n; i++) {
			ans = Math.max(ans, maxSuffix + prefix[i]);
			suffix = arr[i] + Math.max(0, suffix);
			maxSuffix = Math.max(maxSuffix, suffix);
		}
		ans = Math.max(ans, maxSuffix);
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) - (int) (Math.random() * v);
		}
		return arr;
	}

	// 为了测试
	public static void main(String[] args) {
		int len = 50;
		int value = 20;
		int testTime = 20000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * len) + 1;
			int[] arr = randomArray(n, value);
			int ans1 = maxSumReverse1(arr);
			int ans2 = maxSumReverse2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
				for (int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
