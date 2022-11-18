package class109;

import java.util.HashMap;

public class Code02_LongestSumSubArrayLength {

	// 该代码不得分！
//	public static int targetSumMaxLen(int[] arr, int target) {
//		int ans = 0;
//		for(int 开头  = 0; 开头 < arr.length;开头++) {
//			for(int 结尾 = 开头; 结尾 <= arr.length; 结尾++) {
//				// arr[开头....结尾]
//				// 统计一下arr[开头....结尾]累加和
//				// == target
//				int cur = 结尾 - 开头 + 1;
//				ans = Math.max(ans, cur);
//			}
//		}
//		return ans;
//	}

	public static int maxLength(int[] arr, int target) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// key:前缀和
		// value : 0~value这个前缀和是最早出现key这个值的
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, -1); // important
		int len = 0;
		int sum = 0;
		// O(N)
		for (int i = 0; i < arr.length; i++) {
			// 0...i整体前缀和
			sum += arr[i];
			if (map.containsKey(sum - target)) {
				// 0.....17 1000 target == 300
				// sum - target
				// 0...4 700
				// 5.....17 300
				// 17 - 4
				len = Math.max(i - map.get(sum - target), len);
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			}
		}
		return len;
	}

	// for test
	public static int right(int[] arr, int K) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (valid(arr, i, j, K)) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}

	// for test
	public static boolean valid(int[] arr, int L, int R, int K) {
		int sum = 0;
		for (int i = L; i <= R; i++) {
			sum += arr[i];
		}
		return sum == K;
	}

	// for test
	public static int[] generateRandomArray(int size, int value) {
		int[] ans = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 500000;

		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len, value);
			int K = (int) (Math.random() * value) - (int) (Math.random() * value);
			int ans1 = maxLength(arr, K);
			int ans2 = right(arr, K);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println("K : " + K);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("test end");

	}

}
