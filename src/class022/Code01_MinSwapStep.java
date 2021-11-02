package class022;

public class Code01_MinSwapStep {
	
	
	public static int minSteps1(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int step1 = 0;
		int gi = 0;
		// 想让G都放在左侧，请问至少需要交换几次，需要算出step1来。
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 'G') {
				step1 += i - (gi++);
			}
		}
		int step2 = 0;
		int bi = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 'B') {
				step2 += i - (bi++);
			}
		}
		return Math.min(step1, step2);
	}
	

	public static int minSteps(String s) {
		if (s == null || s.equals("")) {
			return 0;
		}
		char[] str = s.toCharArray();
		int step1 = 0;
		int step2 = 0;
		int gi = 0;
		int bi = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == 'G') {
				step1 += i - (gi++);
			} else {
				step2 += i - (bi++);
			}
		}
		return Math.min(step1, step2);
	}

	public static void main(String[] args) {
		String s = "BGGBB";
		System.out.println(minSteps(s));
	}
}