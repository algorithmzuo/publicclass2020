package class089;

// 测试链接 : https://leetcode.com/problems/reaching-points/
public class Code02_ReachingPoints {

	// 会超时，但是揭示了大思路
	public static boolean reachingPoints1(int sx, int sy, int tx, int ty) {
		while (tx != ty) {
			if (tx < ty) {
				ty -= tx;
			} else {
				tx -= ty;
			}
			if (sx == tx && sy == ty) {
				return true;
			}
		}
		return false;
	}

	// 对大体思路的优化
	// s ( 5, 10)
	// t (100, 65)
	public static boolean reachingPoints2(int sx, int sy, int tx, int ty) {
		// sx 出发点的x
		// sy 出发点的y
		// tx 目标点的x
		// ty 目标点的y
		// sx < tx 同时 sy < ty
		// x和y，出发点都小才跳！
		while (sx < tx && sy < ty) {
			// tx ty 谁大，谁去%，然后用余数替换大数！
			if (tx < ty) {
				ty %= tx;
			} else {
				tx %= ty;
			}
		}
		// 1) sx == tx 跳出来！
		// 2) sy == ty 跳出来！
		// 除此之外的所有，都认为返回false
		// 什么情况下，会返回true
		// 1) (sx == tx && sy <= ty && (ty - sy) % sx == 0)
		// 2) (sy == ty && sx <= tx && (tx - sx) % sy == 0)
		// false!
		return (sx == tx && sy <= ty && (ty - sy) % sx == 0) || (sy == ty && sx <= tx && (tx - sx) % sy == 0);
	}

}
