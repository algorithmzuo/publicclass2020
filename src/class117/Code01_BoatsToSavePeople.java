package class117;

import java.util.Arrays;

// 测试链接 : https://leetcode.com/problems/boats-to-save-people/
public class Code01_BoatsToSavePeople {
	
	// 数组里一定不会有大于limit的数值
	public static int numRescueBoats(int[] people, int limit) {
		Arrays.sort(people);
		// 最终的船数
		int ans = 0;
		// 左指针
		int l = 0;
		// 右指针
		int r = people.length - 1;
		int sum = 0;
		while (l <= r) {
			//    ?             ?
			//   17            39
			//   l             r
			sum = l == r ? people[l] : people[l] + people[r];
			if (sum > limit) {
				r--;
			} else {
				l++;
				r--;
			}
			ans++;
		}
		return ans;
	}

}
