package class112;

import java.util.Arrays;

// 来自谷歌
// 给定一个长度为N的数组，值一定在0~N-1范围，且每个值不重复
// 比如，arr = [4, 2, 0, 3, 1]
//             0  1  2  3  4
// 把0想象成洞，任何非0数字都可以来到这个洞里，然后在原本的位置留下洞
// 比如4这个数字，来到0所代表的洞里，那么数组变成 : 
// arr = [0, 2, 4, 3, 1]
// 也就是原来的洞被4填满，4走后留下了洞
// 任何数字只能搬家到洞里，并且走后留下洞
// 通过搬家的方式，想变成有序的，有序有两种形式
// 比如arr = [4, 2, 0, 3, 1]，变成
// [0, 1, 2, 3, 4]或者[1, 2, 3, 4, 0]都叫有序
// 返回变成任何一种有序的情况都可以，最少的数字搬动次数
// 测试链接 : https://leetcode.cn/problems/sort-array-by-moving-items-to-empty-space/
public class Code01_SortArrayByMovingItemsToEmptySpace {

	public static int sortArray(int[] nums) {
		// 1) 0 1 2 3 4 .... 这个样子，至少交换几次 ans1
		// 2) 1 2 3 4 .... 0 这个样子，至少交换几次 ans2
		int n = nums.length, ans1 = 0, ans2 = 0, m, next;
		// 标记，i位置，在之前的环里的话！touched[i] == true
		// 如果i位置不在之前的环里的话！touched[i] == false
		boolean[] touched = new boolean[n];
		// 0 1 2 3 4 ....方案1 -> ans1
		for (int i = 0; i < n; i++) {
			if (!touched[i]) {
				// 4 6 0
				// 0(i) -> 4 6
				// y y y
				touched[i] = true;
				m = 1;
				next = nums[i];
				while (next != i) {
					m++;
					touched[next] = true;
					next = nums[next];
				}
				// m个 -> 几次？
				if (m > 1) {
					ans1 += i == 0 ? (m - 1) : (m + 1);
				}
			}
		}
		Arrays.fill(touched, false);
		// 1 2 3 4 ... 0 方案2 -> ans2
		for (int i = n - 1; i >= 0; i--) {
			if (!touched[i]) {
				touched[i] = true;
				m = 1;
				// n = 7 : 下标 0 ~ 6
				// nums[i]
				// i
				// next = nums[next]
				next = nums[i] == 0 ? (n - 1) : (nums[i] - 1);
				while (next != i) {
					m++;
					touched[next] = true;
					next = nums[next] == 0 ? (n - 1) : (nums[next] - 1);
				}
				if (m > 1) {
					ans2 += i == n - 1 ? (m - 1) : (m + 1);
				}
			}
		}
		return Math.min(ans1, ans2);
	}

	public static int sortYours(int[] arr) {
		// .....
		return 100;
	}

	public static int[] randomArray(int len) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = i;
		}
		for (int i = len - 1; i >= 0; i--) {
			int swap = (int) (Math.random() * (i + 1));
			// 0 1 2 3 4 5(i)
			// 0 ~ 5 5
			int tmp = arr[i];
			arr[i] = arr[swap];
			arr[swap] = tmp;
		}
		return arr;
	}

//	public static void main(String[] args) {
//		int testTimes = 10000;
//		int Len = 10;
//		for(int i = 0; i < testTimes;i++) {
//			int[] arr = randomArray(10);
//			int ans1 = my();
//			int ans2 = yours();
//			if(ans1!=ans2) {
//				打印错误
//			}
//		}
//	}

}
