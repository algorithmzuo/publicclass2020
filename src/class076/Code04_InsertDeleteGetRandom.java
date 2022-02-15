package class076;

import java.util.HashMap;

public class Code04_InsertDeleteGetRandom {

	public class RandomizedSet {

		// 600 -> 0
		private HashMap<Integer, Integer> keyIndexMap;
		// 0 -> 600
		private HashMap<Integer, Integer> indexKeyMap;
		private int size;

		public RandomizedSet() {
			keyIndexMap = new HashMap<Integer, Integer>();
			indexKeyMap = new HashMap<Integer, Integer>();
			size = 0;
		}

		public boolean insert(int key) {
			if (!keyIndexMap.containsKey(key)) {
				keyIndexMap.put(key, size);
				indexKeyMap.put(size++, key);
				return true;
			}
			return false;
		}

		public boolean remove(int val) {
			
			// c -> 2

			// remove c
			// 最后的位置：9 - 1
			// deleteIndex = 2;
			// lastIndex = 8
			// 8 -> z
			// z -> 2
			// 2 -> c(z)
			// c -> X
			// 8 -> z X
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
