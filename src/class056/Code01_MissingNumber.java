package class056;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code01_MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		int l = 0; // l的左边，每一个位置i，上面都放着i+1这个数
		// 最好预期:1~r每个数字收集全，且只收集一次
		// 垃圾区：r....
		int r = arr.length;
		while (l != r) {
			if (arr[l] == l + 1) {
				l++;
			} else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
				// [l] = 83
				// 83应该放在82位置
				// arr [ arr[l] - 1 ] = arr[l]
				// arr[82] = 83
				// 此时的arr[l]是垃圾
				swap(arr, l, --r);
			} else { // l上面的数不是l+1 l上面的数也不是垃圾
				swap(arr, l, arr[l] - 1);
			}
		}
		return l + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
