package class125;

import java.util.HashMap;

// 实现RandomizedSet 类：
// RandomizedSet() 初始化 RandomizedSet 对象
// bool insert(int val) 当元素 val 不存在时
//      向集合中插入该项，并返回 true
//      否则，返回 false
// bool remove(int val) 当元素 val 存在时
//      从集合中移除该项，并返回 true ；否则，返回 false 。
// int getRandom() 随机返回现有集合中的一项
// 测试用例保证调用此方法时集合中至少存在一个元素
// 每个元素应该有 相同的概率 被返回。
// 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 
// 测试链接 : https://leetcode.cn/problems/insert-delete-getrandom-o1/
public class Code02_InsertDeleteGetRandom {

	public class RandomizedSet {

		private HashMap<Integer, Integer> keyIndexMap;
		private HashMap<Integer, Integer> indexKeyMap;
		private int size;

		public RandomizedSet() {
			keyIndexMap = new HashMap<Integer, Integer>();
			indexKeyMap = new HashMap<Integer, Integer>();
			size = 0;
		}

		public boolean insert(int val) {
			if (!keyIndexMap.containsKey(val)) {
				keyIndexMap.put(val, size);
				indexKeyMap.put(size++, val);
				return true;
			}
			return false;
		}

		public boolean remove(int val) {
			if (keyIndexMap.containsKey(val)) {
				int deleteIndex = keyIndexMap.get(val);
				int lastIndex = --size;
				int lastKey = indexKeyMap.get(lastIndex);
				keyIndexMap.put(lastKey, deleteIndex);
				indexKeyMap.put(deleteIndex, lastKey);
				keyIndexMap.remove(val);
				indexKeyMap.remove(lastIndex);
				return true;
			}
			return false;
		}

		public int getRandom() {
			if (size == 0) {
				return -1;
			}
			int randomIndex = (int) (Math.random() * size);
			return indexKeyMap.get(randomIndex);
		}
	}

}
