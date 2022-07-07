package class091;

import java.util.HashMap;

// 设计有一个带有setAll功能的哈希表，有如下功能
// 1) put(x, y) : 新增或者更新，key为x，value是y
// 2) get(x)    : 返回key为x的时候，value是多少
// 3) setAll(y) : 把调用这个方法之前所有的key，value都设置成y
// 要求三个方法的时间复杂度都是O(1)
public class Code01_SetAll {

	public static class MyValue<V> {
		public V value;
		public long time;

		public MyValue(V v, long t) {
			value = v;
			time = t;
		}
	}

	public static class MyHashMap<K, V> {
		// key : 原始的key
		// value : 封装好的一个MyValue结构：原始的value、时间戳
		private HashMap<K, MyValue<V>> map;
		// 全局时间点，每一次put、或者setAll都会让时间点 + 1
		private long time;
		// setAll区域：setAllValue，setAllTime
		private MyValue<V> setAll;

		public MyHashMap() {
			map = new HashMap<>();
			time = 0;
			setAll = new MyValue<V>(null, -1);
		}

		public void put(K key, V value) {
			map.put(key, new MyValue<V>(value, time++));
		}

		public void setAll(V value) {
			setAll = new MyValue<V>(value, time++);
		}

		public V get(K key) {
			if (!map.containsKey(key)) {
				return null;
			}
			if (map.get(key).time > setAll.time) {
				return map.get(key).value;
			} else {
				return setAll.value;
			}
		}
	}

}
