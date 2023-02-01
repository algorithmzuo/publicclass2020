package class120;

import java.util.TreeMap;

// 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数
// 在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃
// 而第 2、4、6... 次跳跃称为偶数跳跃
// 你可以按以下方式从索引 i 向后跳转到索引 j（其中 i < j）：
// 在进行奇数跳跃时（如，第 1，3，5... 次跳跃），
// 你将会跳到索引 j，使得 A[i] <= A[j]，A[j] 是可能的最小值
// 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
// 在进行偶数跳跃时（如，第 2，4，6... 次跳跃)
// 你将会跳到索引 j，使得 A[i] >= A[j]，A[j] 是可能的最大值
// 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
//（对于某些索引 i，可能无法进行合乎要求的跳跃。）
// 如果从某一索引开始跳跃一定次数（可能是 0 次或多次），
// 就可以到达数组的末尾（索引 A.length - 1），
// 那么该索引就会被认为是好的起始索引。
// 返回好的起始索引的数量。
// 测试链接 : https://leetcode.cn/problems/odd-even-jump/
public class Code01_OddEvenJump {

	public static void main(String[] args) {
		// 在有序表里，key是有序组织的
		TreeMap<Integer, String> orderMap = new TreeMap<>();
		// 有序表中，以下所有操作
		// 时间复杂度O(logN)！
		// 非常快的！
		orderMap.put(5, "我是5");
		orderMap.put(10, "我是10");
		orderMap.put(15, "我是15");
		orderMap.put(-5, "我是-5");
		orderMap.put(-15, "我是-15");
		orderMap.put(50, "我是50");
		
		int num = 40;
		// >= 40 记录
		System.out.println(orderMap.ceilingKey(num));
		

//		System.out.println(orderMap.firstKey());
//		
//		System.out.println(orderMap.lastKey());
//		
//		int num = 7;
//		// <= 7 且尽量大的key是多少
//		System.out.println(orderMap.floorKey(num));
//		// >= 7 且尽量小的key是多少
//		System.out.println(orderMap.ceilingKey(num));
//		
//		System.out.println(orderMap.containsKey(5));
//		
//		System.out.println(orderMap.containsKey(7));
		

	}

	public static int oddEvenJumps(int[] arr) {
		
		//       ? ? ?
		// arr[0....n-1]
		
		
		int n = arr.length;
		// odd[i] : 如果来到i位置，处在奇数规则下，该去哪个位置！
		int[] odd = new int[n];
		// odd[i] : 如果来到i位置，处在偶数规则下，该去哪个位置！
		int[] even = new int[n];
		// 准备有序表！
		// key : 值！
		// value : 这个值出现的最左下标！
		TreeMap<Integer, Integer> orderMap = new TreeMap<>();
		for (int i = n - 1; i >= 0; i--) {
			// 从右往左遍历
			// 右边，有没有 >= arr[i]的值
			Integer to = orderMap.ceilingKey(arr[i]);
			odd[i] = to == null ? -1 : orderMap.get(to);
			// 右边，有没有 <= arr[i]的值
			to = orderMap.floorKey(arr[i]);
			even[i] = to == null ? -1 : orderMap.get(to);
			orderMap.put(arr[i], i);
		}
		
		// dp[i][0] : 如果来到i位置，并且是奇数规则，能不能去往数组最终位置
		// dp[i][1] : 如果来到i位置，并且是偶数规则，能不能去往数组最终位置
		boolean[][] dp = new boolean[n][2];
		dp[n - 1][0] = true;
		dp[n - 1][1] = true;
		// dp[n-2][0] dp[n-2][1]
		// dp[n-3][0] dp[n-3][1]
		// dp[n-4][0] dp[n-4][1]
		int ans = 1; // n-1位置一定良好的！ans = 1
		for (int i = n - 2; i >= 0; i--) {
			dp[i][0] = odd[i] != -1 && dp[odd[i]][1];
			dp[i][1] = even[i] != -1 && dp[even[i]][0];
			ans += dp[i][0] ? 1 : 0;
		}
		return ans;
	}

}
