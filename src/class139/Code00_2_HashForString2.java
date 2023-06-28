package class139;

public class Code00_2_HashForString2 {

	// 哈希方法检测
	public static int MAXN = 100005;

	public static long[] pow = new long[MAXN];

	public static int base = 499;

	static {
		pow[0] = 1;
		for (int j = 1; j < MAXN; j++) {
			pow[j] = pow[j - 1] * base;
		}
	}

	public static void build(String str, long[] hash, int n) {
		hash[0] = str.charAt(0) - 'a' + 1;
		for (int j = 1; j < n; j++) {
			hash[j] = hash[j - 1] * base + str.charAt(j) - 'a' + 1;
		}
	}

	public static long hash(long[] hash, int l, int r) {
		long ans = hash[r];
		ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l + 1]);
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		String str1 = "bbaabbccabccbbaatttbbbcccpppfaafafafafappppfafpaf";
		System.out.println(str1.length());
		// --------89
		long[] hash1 = new long[str1.length()];

		build(str1, hash1, str1.length());

		System.out.println(str1.substring(25, 49));
		
		System.out.println(hash(hash1, 25, 48));

		String str2 = "babccbbcccbbaabbccaatttbbpppfaafafafafappppfafpaf";
		//             ----------------------
		long[] hash2 = new long[str2.length()];
		
		System.out.println(str2.substring(25, 49));

		build(str2, hash2, str2.length());
		
		System.out.println(hash(hash2, 25, 48));

	}

}
