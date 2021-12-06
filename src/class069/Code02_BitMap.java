package class069;

public class Code02_BitMap {

	// 位图
	public static class BitMap {

		private long[] bits;

		public BitMap(int max) {
			bits = new long[(max + 64) >> 6];
		}

		public void add(int num) {
			bits[num >> 6] |= (1L << (num & 63));
		}

		public void delete(int num) {
			bits[num >> 6] &= ~(1L << (num & 63));
		}

		public boolean contains(int num) {
			return (bits[num >> 6] & (1L << (num & 63))) != 0;
		}

	}

	public static void main(String[] args) {

		// 表示 0~ 31 谁出现了，谁没出现
//		int a = 0;
//		int num = 7;
//		// 请把7位描黑！
//		//   0000000000010000000
//		//   0000000000010000000
//		a |= 1 << 7;
//		a |= 1 << 13;
//		a |= 1 << 29;
//		// 7  13  29
//		// 请告诉我，7有没有进去
//		boolean has =( a & (1 << 7)) != 0;
//		
//		
		int[] set = new int[10];
		// set : 10个数
		// 每个数，32位
		// 0~319
		int num = 176;
		// set[0] : 0~31
		// set[1] : 32~
		int team = num / 32;
		set[team] |= 1 << (num % 32);
		
		
		
		

//		System.out.println("测试开始！");
//		int max = 2000000;
//		BitMap bitMap = new BitMap(max);
//		HashSet<Integer> set = new HashSet<>();
//		int testTime = 6000000;
//		for (int i = 0; i < testTime; i++) {
//			int num = (int) (Math.random() * (max + 1));
//			double decide = Math.random();
//			if (decide < 0.333) {
//				bitMap.add(num);
//				set.add(num);
//			} else if (decide < 0.666) {
//				bitMap.delete(num);
//				set.remove(num);
//			} else {
//				if (bitMap.contains(num) != set.contains(num)) {
//					System.out.println("Oops!");
//					break;
//				}
//			}
//		}
//		for (int num = 0; num <= max; num++) {
//			if (bitMap.contains(num) != set.contains(num)) {
//				System.out.println("Oops!");
//			}
//		}
//		System.out.println("测试结束！");
	}

}
