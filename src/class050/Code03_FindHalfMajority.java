package class050;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Code03_FindHalfMajority {

	public static int superWater(int[] arr) {
		int candidate = 0;
		int hp = 0;
		for (int num : arr) {
			if (hp == 0) {
				candidate = num;
				hp = 1;
			} else if (num == candidate) {
				hp++;
			} else {
				hp--;
			}
		}
		if (hp == 0) {
			return -1;
		}
		// hp!=0 , candidate
		hp = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == candidate) {
				hp++;
			}
		}
		return hp > arr.length / 2 ? candidate : -1;
	}

	// for test
	public static int right(int[] arr) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int cur : arr) {
			if (!map.containsKey(cur)) {
				map.put(cur, 0);
			}
			map.put(cur, map.get(cur) + 1);
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > arr.length / 2) {
				return entry.getKey();
			}
		}
		return -1;
	}

	// for test
	public static int[] genareteRandomArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max) + 1;
		}
		return ans;
	}

	public static void main(String[] args) {
		int len = 100;
		int max = 10;
		int testTime = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = genareteRandomArray(len, max);
			int ans1 = superWater(arr);
			int ans2 = right(arr);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("test end");
	}

	public static void printKMajor(int[] arr, int K) {
		if (K < 2) {
			System.out.println("the value of K is invalid.");
			return;
		}
		HashMap<Integer, Integer> cands = new HashMap<Integer, Integer>();
		for (int i = 0; i != arr.length; i++) {
			if (cands.containsKey(arr[i])) {
				cands.put(arr[i], cands.get(arr[i]) + 1);
			} else {
				if (cands.size() == K - 1) {
					allCandsMinusOne(cands);
				} else {
					cands.put(arr[i], 1);
				}
			}
		}
		HashMap<Integer, Integer> reals = getReals(arr, cands);
		boolean hasPrint = false;
		for (Entry<Integer, Integer> set : cands.entrySet()) {
			Integer key = set.getKey();
			if (reals.get(key) > arr.length / K) {
				hasPrint = true;
				System.out.print(key + " ");
			}
		}
		System.out.println(hasPrint ? "" : "no such number.");
	}

	public static void allCandsMinusOne(HashMap<Integer, Integer> map) {
		List<Integer> removeList = new LinkedList<Integer>();
		for (Entry<Integer, Integer> set : map.entrySet()) {
			Integer key = set.getKey();
			Integer value = set.getValue();
			if (value == 1) {
				removeList.add(key);
			}
			map.put(key, value - 1);
		}
		for (Integer removeKey : removeList) {
			map.remove(removeKey);
		}
	}

	public static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
		HashMap<Integer, Integer> reals = new HashMap<Integer, Integer>();
		for (int i = 0; i != arr.length; i++) {
			int curNum = arr[i];
			if (cands.containsKey(curNum)) {
				if (reals.containsKey(curNum)) {
					reals.put(curNum, reals.get(curNum) + 1);
				} else {
					reals.put(curNum, 1);
				}
			}
		}
		return reals;
	}

}
