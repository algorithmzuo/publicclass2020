package class20;

public class Code01_RotateString {

	public static String rotate1(String s, int leftSize) {
		if (leftSize <= 0 || leftSize >= s.length()) {
			return s;
		}
		return process1(s.toCharArray(), 0, leftSize - 1, s.length() - 1);
	}

	public static String process1(char[] str, int L, int M, int R) {
		reverse(str, L, M);
		reverse(str, M + 1, R);
		reverse(str, L, R);
		return String.valueOf(str);
	}

	public static void reverse(char[] str, int L, int R) {
		while (L < R) {
			char tmp = str[L];
			str[L++] = str[R];
			str[R--] = tmp;
		}
	}

	public static String rotate2(String s, int leftSize) {
		if (leftSize <= 0 || leftSize >= s.length()) {
			return s;
		}
		char[] str = s.toCharArray();
		int L = 0;
		int R = str.length - 1;
		int lpart = leftSize;
		int rpart = str.length - leftSize;
		int same = Math.min(lpart, rpart);
		int diff = lpart - rpart;
		exchange(str, L, R, same);
		while (diff != 0) {
			if (diff > 0) {
				L += same;
				lpart = diff;
			} else {
				R -= same;
				rpart = -diff;
			}
			same = Math.min(lpart, rpart);
			diff = lpart - rpart;
			exchange(str, L, R, same);
		}
		return String.valueOf(str);
	}

	public static void exchange(char[] chas, int start, int end, int size) {
		int i = end - size + 1;
		char tmp = 0;
		while (size-- != 0) {
			tmp = chas[start];
			chas[start] = chas[i];
			chas[i] = tmp;
			start++;
			i++;
		}
	}

	// for test
	public static String getRandomString(int possibilities, int strMaxSize) {
		char[] ans = new char[(int) (Math.random() * strMaxSize) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strMaxSize = 10;
		int testTimes = 5000000;
		System.out.println("test begin, test time : " + testTimes);
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strMaxSize);
			int leftSize = (int) (Math.random() * (str.length() + 1));
			String ans1 = rotate1(str, leftSize);
			String ans2 = rotate2(str, leftSize);
			if (!ans1.equals(ans2)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");

	}

}
