package class074;

// 给定两个数组A和B，长度都是N
// A[i]不可以在A中和其他数交换，只可以选择和B[i]交换(0<=i<n)
// 你的目的是让A有序，返回你能不能做到
public class Code02_LetASorted {

	public static boolean canSorted(int[] A, int[] B) {
		return process(A, B, 0, Integer.MIN_VALUE);
	}

	// 已经扫过的部分：A[0...index-1]已经过去了！在扫过的过程中，A[0...index-1]能保证有序！
	// A[index....最后]能否也保证有序！
	// index : 1) 不交换！A[index] ... 2)交换！A[index] 和 B[index]交换
	// ....pre A[index]
	// 要么不交换！
	// 返回能不能让A整体变有序！
	public static boolean process(int[] A, int[] B, int index, int pre) {
		if (index == A.length) {
			return true;
		}
		// index还没到终止位置！
		// 1) 不交换 A[index]
		boolean p1 = pre > A[index] ? false : (process(A, B, index + 1, A[index]));
		// 2) 交换
		boolean p2 = pre > B[index] ? false : (process(A, B, index + 1, B[index]));
		return p1 | p2;
	}

	public static boolean canSorted2(int[] A, int[] B) {
		if (A == null || A.length < 2) {
			return true;
		}
		// 长度>=2
		// 0 1..
		return zuo(A, B, 1, false) || zuo(A, B, 1, true);
	}

	public static boolean zuo(int[] A, int[] B, int index, boolean swap) {
		if (index == A.length) {
			return true;
		}
		int pre = swap ? B[index - 1] : A[index - 1];
		// index还没到终止位置！
		// 1) 不交换 A[index]
		boolean p1 = pre > A[index] ? false : (zuo(A, B, index + 1, false));
		// 2) 交换
		boolean p2 = pre > B[index] ? false : (zuo(A, B, index + 1, true));
		return p1 | p2;

	}

//
//	public static boolean letASorted(int[] A, int[] B) {
//		return process(A, B, 0, Integer.MIN_VALUE);
//	}
//
//	// 当前推进到了i位置，对于A和B都是i位置
//	// A[i]前一个数字，lastA
//	// 能否通过题意中的操作，A[i] B[i] 让A整体有序
//	public static boolean process(int[] A, int[] B, int i, int lastA) {
//		if (i == A.length) {
//			return true;
//		}
//		// 第一种选择 : A[i]不和B[i]交换
//		if (A[i] >= lastA && process(A, B, i + 1, A[i])) {
//			return true;
//		}
//		// 第一种选择 : A[i]和B[i]交换
//		if (B[i] >= lastA && process(A, B, i + 1, B[i])) {
//			return true;
//		}
//		return false;
//	}
//
//	public static boolean process2(int[] A, int[] B, int i, int lastA) {
//		if (i == A.length) {
//			return true;
//		}
//		// 第一种选择 : A[i]不和B[i]交换
//		if (A[i] <= lastA && process2(A, B, i + 1, A[i])) {
//			return true;
//		}
//		// 第一种选择 : A[i]和B[i]交换
//		if (B[i] <= lastA && process2(A, B, i + 1, B[i])) {
//			return true;
//		}
//		return false;
//	}

	// A B 操作 : A[i] 与 B[i] 交换！
	// 目的 : 让A和B都有序，能不能做到
//	public static boolean process3(int[] A, int[] B, int i, int lastA, int lastB) {
//
//	}

}
