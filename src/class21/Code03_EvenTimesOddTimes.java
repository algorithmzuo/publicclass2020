package class21;

public class Code03_EvenTimesOddTimes {

	// arr中，只有一种数，出现奇数次
	public static void printOddTimesNum1(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		System.out.println(eor);
	}

	// arr中，有两种数，出现奇数次
	public static void printOddTimesNum2(int[] arr) {
		int eor = 0;
		for (int i = 0; i < arr.length; i++) {
			eor ^= arr[i];
		}
		// eor = a ^ b
		// eor != 0
		// eor必然有一个位置上是1
		// 0110010000
		// 0000010000
		int rightOne = eor & (~eor + 1); // 提取出最右的1
		int onlyOne = 0; // e'
		for (int i = 0; i < arr.length; i++) {
			// arr[1] = 111100011110000
			// rightOne= 000000000010000
			if ((arr[i] & rightOne) != 0) {
				onlyOne ^= arr[i];
			}
		}
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

	public static int add(int a, int b) {
		int t = 0;
		while (b != 0) {
			t = a;
			a = a ^ b;
			b = ((t & b) << 1);
		}
		return a;
	}

	public static int minus(int a, int b) {
		// -b ~b + 1
		return add(a, add(~b, 1));
	}

	public static void main(String[] args) {
		int[] arr1 = { 3, 3, 4, 2, 3, 1, 1, 2, 1, 3, 4, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum1(arr1);

		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);

	}

}
