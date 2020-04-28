package test;

import java.util.Arrays;

public class KthMinOddNum {

	public static int getKthMinOddNum1(int[] arr, int K) {
		if (arr == null || arr.length == 0 || K > arr.length) {
			return -1; // 无效
		}
		Arrays.sort(arr);
		int kth = 0;
		int ans = -1;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & 1) != 0) {
				kth++;
				if (kth == K) {
					return arr[i];
				}
			}
		}
		return ans;
	}

	public static int getKthMinOddNum2(int[] arr, int K) {
		if (arr == null || arr.length == 0|| K > arr.length) {
			return -1; // 无效
		}
		int oddNum = 0;
		for (int i = 0; i < arr.length; i++) {
			oddNum += (arr[i] & 1) != 0 ? 1 : 0;
		}
		if (oddNum == 0 || K > oddNum) {
			return -1;
		}
		int[] oddArr = new int[oddNum];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & 1) != 0) {
				oddArr[index++] = arr[i];
			}
		}
		return getMinKthByBFPRT(oddArr, K);
	}

	// 利用bfprt算法求解，是O(N)的过程
	// 在arr上，找到第K小的数，并返回。K范围是[1,N]，范围不是[0,N-1]
	public static int getMinKthByBFPRT(int[] arr, int K) {
		return select(arr, 0, arr.length - 1, K - 1);
	}

	public static int select(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		int pivot = medianOfMedians(arr, begin, end);
		int[] pivotRange = partition(arr, begin, end, pivot);
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		} else if (i < pivotRange[0]) {
			return select(arr, begin, pivotRange[0] - 1, i);
		} else {
			return select(arr, pivotRange[1] + 1, end, i);
		}
	}

	public static int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		return select(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		int[] range = new int[2];
		range[0] = small + 1;
		range[1] = big - 1;
		return range;
	}

	public static int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	public static void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
	}

	public static void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	// 为了测试，生成值也随机，长度也随机的随机数组
	public static int[] getRandomArray(int max, int len) {
		int[] arr = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// 为了测试
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// 随机测试了百万组，保证两种方法都是对的
	public static void main(String[] args) {
		int max = 100;
		int len = 300;
		int testTimes = 100000;
		System.out.println("test bagin, time times : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			int[] arr = getRandomArray(max, len);
			int[] arr1 = copyArray(arr);
			int[] arr2 = copyArray(arr);
			int N = arr.length;
			int k = (int) (Math.random() * N) + 1;
			int ans1 = getKthMinOddNum1(arr1, k);
			int ans2 = getKthMinOddNum2(arr2, k);
			if (ans1 != ans2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
