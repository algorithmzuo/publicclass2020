package class060;

// 来自百度
// 给定一个字符串str，和一个正数k
// str子序列的字符种数必须是k种，返回有多少子序列满足这个条件
// 已知str中都是小写字母
public class Code04_SequenceKDifferentKinds {

//	public static int waysAll(String str, int k) {
//
//		int[] arr = 通过str生成;
//		// arr[0....]自由选择，一定要选出k中字符来！不同的子序列有多少个
//		return ways(arr, 0, k);
//
//	}

	// "aababddfeeef"
	// a 3个
	// b 2个
	// c 0个
	// d 2个
	// e 3个
	// f 2个
	// g 0个
	// ...
	// z 0个
	// int[] arr = { 3 2 0 2 3 }
	// 0 1 2 3 4...
	// 原始的字符串，变成了arr的形式来表达！
	// restKinds : 还剩下几种字符，需要去选！
	// "....." K = 3
	// "....." -> int[] arr
	// return ways(arr, 3);
	// arr[i....]桶，自由选择，一定要选出restKinds种来
	public static int zuo(int[] arr, int i, int restKinds) {
		if (i == arr.length) { // 结束了
			return restKinds == 0 ? 1 : 0;
		} else { // 没结束，还有字符，可供选择

			// 就是不考虑i位置的字符
			int p1 = zuo(arr, i + 1, restKinds);

			// 一定要选择，i位置的字符
			int p2 = 0;
			if (arr[i] != 0) {

//				选i字符的方法 = C n 1 + C n 2 + C n 3 + ..... + C n n
//						
//				
//				p2 = 选i字符的方法  *  ways(arr, i+1,  restKinds-1);
//				
//				p2 = (2的arr[i]次方  - 1)  *  ways(arr, i+1,  restKinds-1);

				p2 = ((1 << arr[i]) - 1) * zuo(arr, i + 1, restKinds - 1);
			}
			return p1 + p2;
		}
	}

	// bu -> {6,7,0,0,6,3}
	// 0 1 2 3 4 5
	// a b c d e f
	// 在桶数组bu[index....] 一定要凑出rest种来！请问几种方法！
	public static int f(int[] bu, int index, int rest) {
		if (index == bu.length) {
			return rest == 0 ? 1 : 0;
		}
		// 最后形成的子序列，一个index代表的字符也没有!
		int p1 = f(bu, index + 1, rest);
		// 最后形成的子序列，一定要包含index代表的字符，几个呢？(所有可能性都要算上！)
		int p2 = 0;
		if (rest > 0) { // 剩余的种数，没耗尽，可以包含当前桶的字符
			p2 = (1 << bu[index] - 1) * f(bu, index + 1, rest - 1);
		}
		return p1 + p2;
	}

	public static int nums(String s, int k) {
		char[] str = s.toCharArray();
		int[] counts = new int[26];
		for (char c : str) {
			counts[c - 97]++;
		}
		return ways(counts, 0, k);
	}

	public static int ways(int[] c, int i, int r) {
		if (r == 0) {
			return 1;
		}
		if (i == c.length) {
			return 0;
		}
		// math(n) -> 2 ^ n -1
		return math(c[i]) * ways(c, i + 1, r - 1) + ways(c, i + 1, r);
	}

	// n个不同的球
	// 挑出1个的方法数 + 挑出2个的方法数 + ... + 挑出n个的方法数为:
	// C(n,1) + C(n,2) + ... + C(n,n) == (2 ^ n) -1
	public static int math(int n) {
		return (1 << n) - 1;
	}

	public static void main(String[] args) {
		String str = "acbbca";
		int k = 3;
		System.out.println(nums(str, k));
	}

}
