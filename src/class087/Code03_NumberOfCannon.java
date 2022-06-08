package class087;

import java.util.TreeMap;

// 来自学员问题
// 给定一个数组arr，表示从早到晚，依次会出现的导弹的高度
// 大炮打导弹的时候，如果一旦大炮定了某个高度去打，那么这个大炮每次打的高度都必须下降一点
// 1) 如果只有一个大炮，返回最多能拦截多少导弹
// 2) 如果所有的导弹都必须拦截，返回最少的大炮数量
public class Code03_NumberOfCannon {

	public static int numOfCannon(int[] arr) {
		// key : 某个大炮打的结尾数值
		// value : 有多少个大炮有同样的结尾数值
		// 比如：
		// 一共有A、B、C三个大炮
		// 如果A大炮此时打的高度是17，B大炮此时打的高度是7，C大炮此时打的高度是13
		// 那么在表中：
		// 7, 1
		// 13, 1
		// 17, 1
		// 如果A大炮此时打的高度是13，B大炮此时打的高度是7，C大炮此时打的高度是13
		// 那么在表中：
		// 7, 1
		// 13, 2
		TreeMap<Integer, Integer> ends = new TreeMap<>();
		for (int num : arr) {
			if (ends.ceilingKey(num + 1) == null) {
				ends.put(Integer.MAX_VALUE, 1);
			}
			int ceilKey = ends.ceilingKey(num + 1);
			if (ends.get(ceilKey) > 1) {
				ends.put(ceilKey, ends.get(ceilKey) - 1);
			} else {
				ends.remove(ceilKey);
			}
			ends.put(num, ends.getOrDefault(num, 0) + 1);
		}
		int ans = 0;
		for (int value : ends.values()) {
			ans += value;
		}
		return ans;
	}

	public static void main(String[] args) {

		// orderedMap C++
		TreeMap<Integer, String> map = new TreeMap<>();
		map.put(1, "我是1");
		map.put(5, "我是5");
		map.put(50, "我是50");
		map.remove(5);

		System.out.println(map.get(5));

		// 查 >= 15 , 并且离15最近的key
		System.out.println(map.ceilingKey(55));

		// 查 <= 15
		System.out.println(map.floorKey(15));

		// put、get、remove、ceilingKey、floorKey
		// 时间复杂度O(log N)
		// TreeMap，key是Integer，去重的！

		// key : 大炮来到的高度，<=key以下高度的导弹
		// value : 这样高度的大炮有几门
		TreeMap<Integer, Integer> cannonMap = new TreeMap<Integer, Integer>();

		// A ： 80 B ：80 C ： 80 D ： 20
		// 80 : 3
		// 20 : 1
		// 假设map中有一些大炮，
		int h = 100;
		// >= 100，的key存在？
		if (cannonMap.ceilingKey(h) == null) { // 没有能打100高度的
			// 新开一门炮，100消灭！99
			if (cannonMap.containsKey(h - 1)) {
				cannonMap.put(h - 1, cannonMap.get(h - 1) + 1);
			} else {
				cannonMap.put(h - 1, 1);
			}
		} else { // 有能打100高度的
			// 100   110  99
			int cannonH = cannonMap.ceilingKey(h);
			if(cannonMap.get(cannonH) == 1) {
				cannonMap.remove(cannonH);
			}else {
				cannonMap.put(cannonH , cannonMap.get(cannonH) - 1);
			}
			if (cannonMap.containsKey(h - 1)) {
				cannonMap.put(h - 1, cannonMap.get(h - 1) + 1);
			} else {
				cannonMap.put(h - 1, 1);
			}
		}
		
		
		// map ,values 累加 返回~

	}

}
