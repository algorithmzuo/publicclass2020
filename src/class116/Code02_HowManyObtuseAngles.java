package class116;

import java.util.Arrays;

// 来自hulu
// 有一个以原点为圆心，半径为1的圆
// 在这个圆的圆周上，有一些点
// 因为所有的点都在圆周上，所以每个点可以有很简练的表达
// 比如：用0来表示一个圆周上的点，这个点就在(1,0)位置
// 比如：用6000来表示一个点，这个点是(1,0)点沿着圆周逆时针转60.00度之后所在的位置
// 比如：用18034来表示一个点，这个点是(1,0)点沿着圆周逆时针转180.34度之后所在的位置
// 这样一来，所有的点都可以用[0, 36000)范围上的数字来表示
// 那么任意三个点都可以组成一个三角形，返回能组成钝角三角形的数量
public class Code02_HowManyObtuseAngles {

	public static long obtuseAngles(int[] arr) {
		// n长度的排序，O(N * logN)
		// O(N)
		int n = arr.length;
		int m = n << 1;
		int[] enlarge = new int[m];
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			enlarge[i] = arr[i];
			enlarge[i + n] = arr[i] + 36000;
		}
		long ans = 0;
		// 这里不用二分查找(太慢)，能做一个不回退的优化
		for (int L = 0, R = 0; L < n; L++) {
			while (R < m && enlarge[R] - enlarge[L] < 18000) {
				R++;
			}
			ans += num(R - L - 1);
		}
		return ans;
	}

	public static long num(long nodes) {
		return nodes < 2 ? 0 : ((nodes - 1) * nodes) >> 1;
	}

}
