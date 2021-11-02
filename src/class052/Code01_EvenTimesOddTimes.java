package class052;

public class Code01_EvenTimesOddTimes {

	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	public static void printOddTimesHas2Nums(int[] arr) {
		int eor = 0;
		for (int num : arr) {
			eor ^= num;
		}
		// a和b，出现了奇数次 eor -> a ^ b
		// eor : 0..000101011000
		// 最右的1: 0..000000001000
		// ~num + 1 -> -num
		int mostRightOne = eor & (-eor);
		int eor2 = 0;
		for (int num : arr) {
			// 第3位上有1的数字，异或进 eor2里
			if ((num & mostRightOne) != 0) {
				eor2 ^= num;
			}
		}
		System.out.println("找到的第一个是 : " + eor2);
		System.out.println("找到的第二个是 : " + (eor ^ eor2));
	}

	public static void printOddTimesNum2(int[] arr) {
		// arr中，a和b出现了奇数次，其他数都是偶数次
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		// eor = a ^ b
		// eor != 0
		// eor : 001100100
		// rightOne : 000000100
		int rightOne = eor & (~eor + 1);
		int onlyOne = 0; // eor'
		for (int i = 0; i < arr.length; i++) {
			if ((arr[i] & rightOne) != 0) {
				onlyOne ^= arr[i];
			}
		}
		// eor' = a or b
		System.out.println(onlyOne + " " + (eor ^ onlyOne));
	}

	public static int bit1counts(int N) {
		int count = 0;
		while (N != 0) {
			int rightOne = N & ((~N) + 1);
			count++;
			N -= rightOne;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr1 = { 1500, 3, 1, 1500, 1, 1, 1500, 3, 1500 };
		// 3 3 1500 1500 1 1 1
		printOddTimesNum1(arr1);

		int[] arr = { 4, 3, 3, 4, 3, 3, 2, 5, 5, 5, 5, 2, 2, 1, 1, 5, 1, 2, 2, 2 };

		printOddTimesHas2Nums(arr);
	}

}
