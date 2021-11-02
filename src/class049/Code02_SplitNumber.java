package class049;

public class Code02_SplitNumber {

	public static boolean isSplit1(int num) {
		if (num <= 2) {
			return false;
		}
		for (int start = 1; start < num; start++) { // 开始的最小数字 
			int ans = start;
			for (int next = start + 1; next < num; next++) {
				if (ans + next > num) {
					break;
				}
				if (ans + next == num) {
					return true;
				}
				ans += next;
			}
		}
		return false;
	}

	public static boolean isSplit2(int num) {
		// num > 0
		return num > 0 && (num & (num - 1)) != 0;
	}

	public static void printNumberBinary(int num) {
		for (int i = 31; i >= 0; i--) {
			int status = (num & (1 << i)) != 0 ? 1 : 0;
			System.out.print(status);
		}
		System.out.println();
	}

	public static void main(String[] args) {
//		int num = 128; // 0...0111
//		printNumberBinary(num);
//		
//		printNumberBinary(num - 1);

		for (int i = 0; i < 100; i++) {
			System.out.println(i + " : " + isSplit1(i));
		}
	}

}
