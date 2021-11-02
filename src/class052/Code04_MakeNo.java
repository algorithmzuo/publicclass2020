package class052;

public class Code04_MakeNo {

	public static int[] makeNo(int N) {
		if (N == 1) {
			return new int[] { 1 };
		}
		// N -> 一半
		// N = 7  ->  4
		int halfSize = (N + 1) / 2;
		int[] base = makeNo(halfSize);
		int[] ans = new int[N];
		int index = 0;
		for (; index < halfSize; index++) {
			ans[index] = base[index] * 2 + 1;
		}
		for (int i = 0; index < N; index++, i++) {
			ans[index] = base[i] * 2;
		}
		return ans;
	}

	// 检验函数
	public static boolean isValid(int[] arr) {
		int N = arr.length;
		for (int i = 0; i < N; i++) {
			for (int k = i + 1; k < N; k++) {
				for (int j = k + 1; j < N; j++) {
					if (arr[i] + arr[j] == 2 * arr[k]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("test begin");
		for (int N = 1; N < 1000; N++) {
			int[] arr = makeNo(N);
			if (!isValid(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
		System.out.println(isValid(makeNo(1042)));
		System.out.println(isValid(makeNo(2981)));
	}

}
