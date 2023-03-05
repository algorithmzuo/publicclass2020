package class124;

// 假设数组长度为N，如果某一种数的出现次数大于 N/2 
// 那么说这个数叫做水王数
// 给定一个数组arr
// 如果有水王数，打印水王数是什么
// 如果没有水王数，打印水王数不存在
public class Code07_FindHalfMajority {

	public static void halfMajor(int[] arr) {
		int cand = 0;
		int HP = 0;
		for (int i = 0; i != arr.length; i++) {
			if (HP == 0) {
				cand = arr[i];
				HP = 1;
			} else if (arr[i] == cand) {
				HP++;
			} else {
				HP--;
			}
		}
		if (HP == 0) {
			System.out.println("水王数不存在!");
		}
		HP = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] == cand) {
				HP++;
			}
		}
		if (HP <= arr.length / 2) {
			System.out.println("水王数不存在!");
		}
		System.out.println("水王数是" + cand);
	}

}
