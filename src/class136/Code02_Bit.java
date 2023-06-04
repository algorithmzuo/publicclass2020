package class136;

public class Code02_Bit {

	public static void main(String[] args) {
		// arr[0] : int 32 位 0 ~ 31
		// 10 * 32 : 320 bits
		// 10 * 4 Byte : 40 Bytes
		int[] arr = new int[10];
		// 把189位上，描黑!
		int i = 189;
		// int : 32位
		// 0 : 0 ~ 31
		// 1 : 32 ~ 63
		int x = i / 32;
		// arr[x] : 31 30 29 .... 2 1 0
		arr[x] |= 1 << (i % 32);

//		int num = 0;
//		// num : 000000....0000
//		// num : 000000....1000
//
//		num |= (1 << 3);

		// 不管i位之前是什么状态：现在，变成0
		// 1 : 000000000.....0000001
		// 1 << 2 : 000000000.....0000100
		arr[x] = arr[x] & (~(1 << (i % 32)));

		// 读出i位的状态 0 1
		int read = (arr[i / 32] & (1 << (i % 32))) == 0 ? 0 : 1;
		System.out.println(read);

	}

}
