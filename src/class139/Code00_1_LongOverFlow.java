package class139;

import java.math.BigInteger;

public class Code00_1_LongOverFlow {

	public static void main(String[] args) {
		BigInteger z = new BigInteger("3000000000000000000000000000000000000000000");
		System.out.println(z.longValue());

		BigInteger a = new BigInteger("1000000000000000000000000000000000000000000");
		BigInteger b = new BigInteger("2000000000000000000000000000000000000000000");
		System.out.println(a.longValue() + b.longValue());

		BigInteger c = new BigInteger("5000000000000000000000000000000000000000000");
		BigInteger d = new BigInteger("2000000000000000000000000000000000000000000");
		System.out.println(c.longValue() - d.longValue());

		BigInteger e = new BigInteger("3000000000000000000000");
		BigInteger f = new BigInteger("1000000000000000000000");
		System.out.println(e.longValue() * f.longValue());

		// 注意，除法不行！除法的同余，需要乘法逆元，这里不做展开
	}

	public static void test(String str) {
		// b c a b c c
		// 0 1 2 3 4 5
		// 任意子串的哈希值！
		// 还快，生成一个预处理结构！
		// hash数组
		// hash[0] = 0...0 "b"
		// hash[1] = 0...1 "bc"
		// hash[2] = 0...2 "bca"
		// hash[3] = 0...3 "bcab"
		// hash[5] = 0...5 "bcabcc"
		// "c c"
		// 4 5
		// "bcabcc"
		// "bcab00"
		// hash[0] = 0...0 哈希值 : 2 * k^0
		// hash[1] = 0...1 哈希值 : 2 * k^1 + 3 * k^0
		// hash[2] = 0...2 哈希值 : 2 * k^2 + 3 * k^1 + 1 * k^0
		// hash[i] = hash[i-1] * k + (str[i] - 'a' + 1) * 1
	}

}
