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
	}

}
