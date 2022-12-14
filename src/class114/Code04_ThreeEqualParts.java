package class114;

// 给定一个由 0 和 1 组成的数组 arr ，将数组分成  3 个非空的部分
// 使得所有这些部分表示相同的二进制值。
// 如果可以做到，请返回任何 [i, j]，其中 i+1 < j，这样一来
// arr[0], arr[1], ..., arr[i] 为第一部分
// arr[i + 1], arr[i + 2], ..., arr[j - 1] 为第二部分
// arr[j], arr[j + 1], ..., arr[arr.length - 1] 为第三部分
// 这三个部分所表示的二进制值相等
// 如果无法做到，就返回 [-1, -1]
// 注意，在考虑每个部分所表示的二进制时，应当将其看作一个整体
// 例如，[1,1,0] 表示十进制中的 6，而不会是 3。此外，前导零也是被允许的
// 所以 [0,1,1] 和 [1,1] 表示相同的值。
// 测试链接 : https://leetcode.cn/problems/three-equal-parts/
public class Code04_ThreeEqualParts {

	public static int[] threeEqualParts(int[] arr) {
		// 计算arr中1的数量
		int ones = 0;
		for (int num : arr) {
			ones += num == 1 ? 1 : 0;
		}
		// 如果1的数量不能被3整除，肯定不存在方案
		if (ones % 3 != 0) {
			return new int[] { -1, -1 };
		}
		int n = arr.length;
		// 如果1的数量是0，怎么划分都可以了，因为全是0
		if (ones == 0) {
			return new int[] { 0, n - 1 };
		}
		// 接下来的过程
		// 因为1的数量能被3整除，比如一共有12个1
		// 那么第一段肯定含有第1个1~第4个1
		// 那么第二段肯定含有第5个1~第8个1
		// 那么第三段肯定含有第9个1~第12个1
		// 所以把第1个1，当做第一段的开头，start1
		// 所以把第5个1，当做第二段的开头，start2
		// 所以把第9个1，当做第三段的开头，start3
		int part = ones / 3;
		int start1 = -1;
		int start2 = -1;
		int start3 = -1;
		int cnt = 0;
		// 1个数21个
		// part = 7
		// 1    8    
		for (int i = 0; i < n; i++) {
			if (arr[i] == 1) {
				cnt++;
				if (start1 == -1 && cnt == 1) {
					start1 = i;
				}
				if (start2 == -1 && cnt == part + 1) {
					start2 = i;
				}
				if (start3 == -1 && cnt == 2 * part + 1) {
					start3 = i;
				}
			}
		}
		// 第一段的开头往下的部分
		// 第二段的开头往下的部分
		// 第三段的开头往下的部分
		// 要都一样，这三段的状态才是一样的
		// 所以接下来就验证这一点，是不是每一步都一样
		while (start3 < n) {
			if (arr[start1] != arr[start2] || arr[start1] != arr[start3]) {
				// 一旦不一样，肯定没方案了
				return new int[] { -1, -1 };
			}
			start1++;
			start2++;
			start3++;
		}
		// 如果验证通过，返回断点即可
		return new int[] { start1 - 1, start2 };
	}

}
