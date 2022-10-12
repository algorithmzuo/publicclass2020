package class105;

import java.util.HashMap;
import java.util.HashSet;

// 来自字节
// 给定正数N，表示用户数量，用户编号从0~N-1
// 给定正数M，表示实验数量，实验编号从0~M-1
// 给定长度为N的二维数组A，
// A[i] = { a, b, c }表示，用户i报名参加了a号、b号、c号实验
// 给定正数Q，表示查询的条数
// 给定长度为Q的二维数组B，
// B[i] = { e, f }表示，第i条查询想知道e号、f号实验，一共有多少人(去重统计)
// 返回每一条查询的结果数组
// 数据描述 : 
// 1 <= N <= 10^5
// 1 <= M <= 10^2
// 所有查询所列出的所有实验编号数量 <= 10^5
public class Code01_EveryQueryUsers {

//	public static void main(String[] args) {
////		int a = 11;
////		int b = 10;
////		// a / b向上取整 ：
////		System.out.println((a + b - 1) / b);
////
////		System.out.println(100000 / 32);
////		// 0 ~ 49999
////		int n = 50000;
////		int[] sets = new int[(n + 31) / 32];
////		int i = 34601;
////		// 34601 / 32
////		// sets[0] : 0 ~ 31
////		// sets[1] : 32 ~ 63
////		// sets[2] : 64 ~
////		int from = i / 32;
////		// 34601 % 32
////		sets[from] |= 1 << (i % 32);
//
//		int n = 100000;
//		int m = 100;
//		int parts = (n + 31) / 32;
//		int[][] sets = new int[m][parts];
//		int[][] A = { { 3, 6, 9 }, // 0
//				{ 5, 17, 23 }, // 1
//				{ 89, 73, 13 }, // 2
//
//		};
//
//		for (int i = 0; i < A.length; i++) {
//			for (int exp : A[i]) {
//				int[] set = sets[exp];
//				set[i / 32] |= 1 << (1 % 32);
//			}
//		}
//
//		int[][] B = { 
//				{ 4, 17, 23 }, // 第0条查询
//				{ 3, 4, 41 }, // 第1条查询
//		};
//
//		int[] ans = new int[B.length];
//
//		for (int i = 0; i < B.length; i++) {
//			int ones = 0;
//			// 4 : 0 1 ... parts-1
//			//17 : 0 1 ... parts-1
//			//23 : 0 1 ... parts-1
//			for (int j = 0; j < parts; j++) {
//				int count = 0;
//				for(int exp : B[i]) {
//					count |= sets[exp][j];
//				}
//				ones += countOnes(count);
//			}
//			ans[i] = ones;
//		}
//
////		System.out.println(countOnes(set));
//
//	}

	// 暴力方法
	// 为了验证
	public static int[] record1(int n, int m, int q, int[][] A, int[][] B) {
		HashMap<Integer, HashSet<Integer>> expUsersMap = new HashMap<>();
		for (int i = 0; i < m; i++) {
			expUsersMap.put(i, new HashSet<>());
		}
		for (int i = 0; i < n; i++) {
			for (int exp : A[i]) {
				expUsersMap.get(exp).add(i);
			}
		}
		int[] ans = new int[q];
		HashSet<Integer> help = new HashSet<>();
		for (int i = 0; i < q; i++) {
			help.clear();
			for (int exp : B[i]) {
				for (int user : expUsersMap.get(exp)) {
					help.add(user);
				}
			}
			ans[i] = help.size();
		}
		return ans;
	}

	// 正式方法
	public static int[] record2(int n, int m, int q, int[][] A, int[][] B) {
		// n 一共有多少人
		// 任何一个实验，需要几个整数，能表示所有人谁出现谁没出现？
		int parts = (n + 31) / 32;
		// m 0 ~ m -1
		// [i] [.........]
		int[][] bitMap = new int[m][parts];
		for (int i = 0; i < n; i++) {
			// i 人的编号 : a b c
			for (int exp : A[i]) {
				bitMap[exp][i / 32] |= 1 << (i % 32);
			}
		}
		int[] ans = new int[q];
		for (int i = 0; i < q; i++) {
			// i号查询 ： a、c、e，一共有多少去重的人
			// a[0] | c[0] | e[0] -> 几个1
			// a[1] | c[1] | e[1] -> 几个1
			int all = 0;
			for (int j = 0; j < parts; j++) {
				int status = 0;
				for (int exp : B[i]) {
					status |= bitMap[exp][j];
				}
				all += countOnes(status);
			}
			ans[i] = all;
		}
		return ans;
	}

	// 大厂刷题班，32节，
	// leetcode专题 : https://leetcode.com/problems/number-of-1-bits/
	// 一个32位整数，求里面有几个1
	public static int countOnes(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}

	// 为了测试
	public static int[][] randomMatrix(int n, int m, int v) {
		int[][] ans = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ans[i][j] = (int) (Math.random() * v);
			}
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 100;
		int M = 20;
		int Q = 50;
		int testTime = 5000;
		System.out.println("功能测试开始");
		for (int i = 0; i < testTime; i++) {
			int n = (int) (Math.random() * N) + 1;
			int m = (int) (Math.random() * M) + 1;
			int[][] A = randomMatrix(n, (int) (Math.random() * m) + 1, m);
			int q = (int) (Math.random() * Q) + 1;
			int[][] B = randomMatrix(q, (int) (Math.random() * m) + 1, m);
			int[] ans1 = record1(n, m, q, A, B);
			int[] ans2 = record2(n, m, q, A, B);
			boolean pass = true;
			for (int j = 0; j < q; j++) {
				if (ans1[j] != ans2[j]) {
					pass = false;
					break;
				}
			}
			if (!pass) {
				System.out.println("出错了!");
				break;
			}
		}
		System.out.println("功能测试结束");

		System.out.println("性能测试开始");
		int n = 100000;
		int m = 100;
		int[][] A = randomMatrix(n, m, m);
		int q = 1000;
		int c = 100;
		int[][] B = randomMatrix(q, c, m);
		System.out.println("用户数量 : " + n);
		System.out.println("实验数量 : " + m);
		System.out.println("用户参加的实验数量总和 : " + n * m);
		System.out.println("查询条数 : " + q);
		System.out.println("每条查询的实验数量 : " + c);
		System.out.println("所有查询所列出的所有实验编号数量 : " + q * c);
		long start = System.currentTimeMillis();
		record2(n, m, q, A, B);
		long end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
