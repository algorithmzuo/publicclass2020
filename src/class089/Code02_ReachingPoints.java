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
		while (sx < tx && sy < ty) {
			if (tx < ty) {
				ty %= tx;
			} else {
				tx %= ty;
			}
		}
		// 1) startx >= tx
		// 2) starty >= ty
		return (sx == tx && sy <= ty && (ty - sy) % sx == 0) 
				|| (sy == ty && sx <= tx && (tx - sx) % sy == 0);
	}

}
