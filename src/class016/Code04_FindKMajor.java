package class016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class Code04_FindKMajor {

	public static List<Integer> kMajor(int[] arr, int K) {
		List<Integer> ans = new ArrayList<>();
		if (K < 2) {
			return ans;
		}
		// 候选表，记录数一定是O(K) > N/K    k-1
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
		for (Entry<Integer, Integer> set : cands.entrySet()) {
			Integer key = set.getKey();
			if (reals.get(key) > arr.length / K) {
				ans.add(key);
			}
		}
		return ans;
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

	// for test
	public static List<Integer> right(int[] arr, int K) {
		List<Integer> ans = new ArrayList<>();
		if (K < 2) {
			return ans;
		}
		HashMap<Integer, Integer> times = new HashMap<>();
		for (int num : arr) {
			if (!times.containsKey(num)) {
				times.put(num, 1);
			} else {
				times.put(num, times.get(num) + 1);
			}
		}
		for (Entry<Integer, Integer> entry : times.entrySet()) {
			if (entry.getValue() > arr.length / K) {
				ans.add(entry.getKey());
			}
		}
		return ans;
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

	public static int[] genareteRandomArray(int len, int max) {
		int[] ans = new int[(int) (Math.random() * len) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * max) + 1;
		}
		return ans;
	}

	public static boolean isEqual(List<Integer> ans1, List<Integer> ans2) {
		if (ans1.size() != ans2.size()) {
			return false;
		}
		HashSet<Integer> set1 = new HashSet<>();
		for (Integer num : ans1) {
			set1.add(num);
		}
		if (set1.size() != ans1.size()) {
			return false;
		}
		for (Integer num : ans2) {
			set1.remove(num);
		}
		return set1.size() == 0;
	}

	public static void main(String[] args) {
		int len = 100;
		int max = 10;
		int K = 5;
		int testTime = 100000;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = genareteRandomArray(len, max);
			List<Integer> ans1 = kMajor(arr, K);
			List<Integer> ans2 = right(arr, K);
			if (!isEqual(ans1, ans2)) {
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("test end");
	}

}
