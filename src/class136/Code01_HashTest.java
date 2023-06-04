package class136;

// 需要手动把javax.xml.bind的jar包，然后导入到项目
import javax.xml.bind.DatatypeConverter;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code01_HashTest {

	public static class Hash {

		private MessageDigest hash;

		public Hash(String algorithm) {
			try {
				hash = MessageDigest.getInstance(algorithm);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		public String hashCode(String input) {
			return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
		}
	}

	public static List<String> getPositionABs(int len) {
		List<String> ans = new ArrayList<>();
		process(0, len, "", ans);
		return ans;
	}

	public static void process(int i, int len, String path, List<String> ans) {
		if (i == len) {
			ans.add(path);
		} else {
			process(i + 1, len, path + "A", ans);
			process(i + 1, len, path + "B", ans);
		}
	}

	public static void main(String[] args) {
		System.out.println("支持如下算法的哈希函数");
		for (String str : Security.getAlgorithms("MessageDigest")) {
			System.out.println(str);
		}
		System.out.println("===============================");

		String algorithm = "SHA-512";
		Hash hash = new Hash(algorithm);
		System.out.println("设置了 " + algorithm + " 算法作为哈希函数");

		System.out.println("===============================");

		String test1 = "zuochengyunzuochengyunzuochengyun1";
		String test2 = "zuochengyunzuochengyunzuochengyuN1";
		String test3 = "zuochengyunzuochengyunzuochengyun2";
		String test4 = "zuochengyunZuochengyunzuochengyun2";
		String test5 = "zuochengyunzuochengyunzuochengyun3";
		String test6 = "zuochengyunzuochengyunzu0chengyun3";
		String test7 = "zuochengyunzuochengyunzuochengyun4";
		String test8 = "zuochengyunzuochengyunzuochengyun1";
		System.out.println("几乎相同的串，hashCode却有极大的不同");
		System.out.println(hash.hashCode(test1));
		System.out.println(hash.hashCode(test2));
		System.out.println(hash.hashCode(test3));
		System.out.println(hash.hashCode(test4));
		System.out.println(hash.hashCode(test5));
		System.out.println(hash.hashCode(test6));
		System.out.println(hash.hashCode(test7));
		System.out.println(hash.hashCode(test8));
		
		System.out.println("===============================");

		int len = 16;
		System.out.println("得到长度为 " + len + " , 用A和B拼成的所有字符串");
		List<String> strs = getPositionABs(len);
//		for(String s : strs) {
//			System.out.println(s);
//		}
		System.out.println("一共产生了 " + strs.size() + " 个 字符串");

		System.out.println("===============================");

		HashSet<String> set = new HashSet<>();
		for (String str : strs) {
			set.add(hash.hashCode(str));
		}
		System.out.println("一共产生了 " + set.size() + " 个 HashCode");

		System.out.println("===============================");

		
		String code = "534B4F419260563EA370762C735649AB9DDBF9E1932B897771FBEB80049E5FB8E3AA2414F1844A6C16811EEDAB14606EA2E15666B2B69A3C98788D76A18DB8DA";
		
		BigInteger convert = new BigInteger(code, 16);
		
		System.out.println(convert.toString());
		
		
		int m = 100;
		// % 10 -> 0 1 2 3 ... 9
		int[] cnts = new int[m];
		System.out.println("现在看看这些HashCode，% " + m + " 之后的分布情况");
		BigInteger mod = new BigInteger(String.valueOf(m));
		for (String hashCode : set) {
			BigInteger bigInt = new BigInteger(hashCode, 16);
			int ans = bigInt.mod(mod).intValue();
			cnts[ans]++;
		}
		for (int i = 0; i < m; i++) {
			System.out.println("余数 " + i + " 出现了 " + cnts[i] + " 次");
		}
	}

}
