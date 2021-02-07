package class34;

public class Code04_EvenTimesOddTimes {

	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	public static void printOddTimesNum2(int[] arr) {
		// arr中，a和b出现了奇数次，其他数都是偶数次
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		// eor = a ^ b
		// eor != 0
		// eor       : 001100100
		// rightOne  : 000000100
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
		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);
	}

}
