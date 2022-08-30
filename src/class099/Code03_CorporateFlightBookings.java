package class099;

// 这里有 n 个航班，它们分别从 1 到 n 进行编号。
// 有一份航班预订表 bookings ，
// 表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi]
// 意味着在从 firsti 到 lasti 
//（包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
// 测试链接 : https://leetcode.cn/problems/corporate-flight-bookings/
public class Code03_CorporateFlightBookings {

	public static int[] corpFlightBookings(int[][] bookings, int n) {
		// 1 2 3 4 n
		// 0 1 2 3 .. n n+1
		int[] cnt = new int[n + 2];
		for (int[] book : bookings) {
			// start book[0]
			// end   book[1]
			// 票    book[2]
			cnt[book[0]] += book[2];
			cnt[book[1] + 1] -= book[2];
		}
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = cnt[i + 1];
		}
		return ans;
	}

}
