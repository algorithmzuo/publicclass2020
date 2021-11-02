package class052;

import java.util.HashMap;
import java.util.HashSet;

public class Code02_KM {

	public static int test(int[] arr, int k, int m) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int num : arr) {
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}
		for (int num : map.keySet()) {
			if (map.get(num) == k) {
				return num;
			}
		}
		return -1;
	}

	// 其他所有的数字都出现了M次，只有一种数，出现了K次
	// 请打印出现了K次的数，K < M
	public static void find(int[] arr, int K, int M) {
		// 1） 先遍历一遍数组，看看0，是不是出现了K次的数
		// 如果是，直接打印0

		int[] tmp = new int[32];
		for (int num : arr) {
			// 我要提取出，num的每一位
			for (int i = 31; i >= 0; i--) {
				// 第i位如果是1，curbit = 1；第i位如果是0，curbit = 0；
				int curbit = (num & (1 << i)) != 0 ? 1 : 0;
				tmp[i] += curbit;
				tmp[i] %= M;
			}
		}
		// tmp 得到了！
		int ans = 0;
		for(int i = 31; i >=0 ;i--) {
			// tmp[i]有没有数字剩下来！
			// i位上，有数剩下来，has = 1
			// i位上，没有数剩下来，has = 0
			ans |= ((tmp[i] != 0 ? 1 : 0) << i);
		}
		System.out.println(ans);
		// 打印ans
		
		
		

	}

	public static HashMap<Integer, Integer> map = new HashMap<>();

	// 请保证arr中，只有一种数出现了K次，其他数都出现了M次
	public static int onlyKTimes(int[] arr, int k, int m) {
		if (map.size() == 0) {
			mapCreater(map);
		}
		int[] t = new int[32];
		// t[0] 0位置的1出现了几个
		// t[i] i位置的1出现了几个
		for (int num : arr) {
			while (num != 0) {
				int rightOne = num & (-num);
				t[map.get(rightOne)]++;
				num ^= rightOne;
			}
		}
		int ans = 0;
		for (int i = 0; i < 32; i++) {
			if (t[i] % m != 0) {
				if (t[i] % m == k) {
					ans |= (1 << i);
				} else {
					return -1;
				}
			}
		}
		if (ans == 0) {
			int count = 0;
			for (int num : arr) {
				if (num == 0) {
					count++;
				}
			}
			if (count != k) {
				return -1;
			}
		}
		return ans;
	}

	public static void mapCreater(HashMap<Integer, Integer> map) {
		int value = 1;
		for (int i = 0; i < 32; i++) {
			map.put(value, i);
			value <<= 1;
		}
	}

	public static int[] randomArray(int maxKinds, int range, int k, int m) {
		int ktimeNum = randomNumber(range);
		// 真命天子出现的次数
		int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
		// 2
		int numKinds = (int) (Math.random() * maxKinds) + 2;
		// k * 1 + (numKinds - 1) * m
		int[] arr = new int[times + (numKinds - 1) * m];
		int index = 0;
		for (; index < times; index++) {
			arr[index] = ktimeNum;
		}
		numKinds--;
		HashSet<Integer> set = new HashSet<>();
		set.add(ktimeNum);
		while (numKinds != 0) {
			int curNum = 0;
			do {
				curNum = randomNumber(range);
			} while (set.contains(curNum));
			set.add(curNum);
			numKinds--;
			for (int i = 0; i < m; i++) {
				arr[index++] = curNum;
			}
		}
		// arr 填好了
		for (int i = 0; i < arr.length; i++) {
			// i 位置的数，我想随机和j位置的数做交换
			int j = (int) (Math.random() * arr.length);// 0 ~ N-1
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}
		return arr;
	}

	// [-range, +range]
	public static int randomNumber(int range) {
		return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
	}

	public static void main(String[] args) {
		int kinds = 5;
		int range = 30;
		int testTime = 100000;
		int max = 9;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
			int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
			int k = Math.min(a, b);
			int m = Math.max(a, b);
			// k < m
			if (k == m) {
				m++;
			}
			int[] arr = randomArray(kinds, range, k, m);
			int ans1 = test(arr, k, m);
			int ans2 = onlyKTimes(arr, k, m);
			if (ans1 != ans2) {
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");

	}

}
