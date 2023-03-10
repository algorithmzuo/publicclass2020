package class125;

// 给定一个大小为 n 的数组 nums ，返回其中的多数元素
// 多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
// 测试链接 : https://leetcode.cn/problems/majority-element/
public class Code01_WaterKing {

	public static int majorityElement(int[] arr) {
		int cand = 0;
		int hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (hp == 0) {
				cand = arr[i];
				hp = 1;
			} else if (arr[i] == cand) {
				hp++;
			} else {
				hp--;
			}
		}
		if (hp == 0) {
			return -1;
		}
		hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == cand) {
				hp++;
			}
		}
		return hp > arr.length / 2 ? cand : -1;
	}

}
