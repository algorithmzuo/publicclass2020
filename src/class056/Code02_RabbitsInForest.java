package class056;

import java.util.Arrays;

public class Code02_RabbitsInForest {

	public static int numRabbits(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		Arrays.sort(arr);
		int x = arr[0];
		int k = 1;
		int ans = 0;
		for (int i = 1; i < arr.length; i++) {
			if (x != arr[i]) {
				// (k / (x + 1)) 上 * (x+1)
				// (k + x + 1 - 1) / (x + 1)
				// (k + x) / (x + 1) (x + 1)
				ans += ((k + x) / (x + 1)) * (x + 1);
				x = arr[i];
				k = 1;
			} else {
				k++;
			}
		}
		return ans + ((k + x) / (x + 1)) * (x + 1);
	}

}
