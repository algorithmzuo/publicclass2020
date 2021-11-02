package class043;

public class Code04_FindOddTimesNumber {

	public static int oddTimesNumber(int[] arr) {
		int xor = 0;
		for (int num : arr) {
			xor ^= num;
		}
		return xor;
	}

	public static void main(String[] args) {
		int[] arr = { 3, 4, 3, 4, 2, 1, 4, 3, 3, 2, 4, 2, 3, 6, 2, 3, 6, 1, 6 };
		System.out.println(oddTimesNumber(arr));
	}

}
