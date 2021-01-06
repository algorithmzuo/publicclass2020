package class31;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code04_MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		int l = -1;
		int r = arr.length;
		while (l + 1 != r) {
			if (arr[l + 1] == l + 2) {
				l++;
			} else if (arr[l + 1] <= l + 1 || arr[l + 1] > r || arr[arr[l + 1] - 1] == arr[l + 1]) {
				swap(arr, l + 1, --r);
			} else {
				swap(arr, l + 1, arr[l + 1] - 1);
			}
		}
		return l + 2;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
