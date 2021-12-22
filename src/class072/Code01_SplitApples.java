package class072;

// 有m个同样的苹果，认为苹果之间无差别
// 有n个同样的盘子，认为盘子之间也无差别
// 还有，比如5个苹果如果放进3个盘子，
// 那么1、3、1和1、1、3和3、1、1的放置方法，也认为是一种方法
// 如上的设定下，返回有多少种放置方法
// 测试链接 : https://www.nowcoder.com/practice/bfd8234bb5e84be0b493656e390bdebf
// 提交以下的code，提交时请把类名改成"Main"
import java.util.Arrays;
import java.util.Scanner;

public class Code01_SplitApples {

	public static int ways1(int apples, int plates) {
		if (apples == 0) { // 没有苹果了！摆法有一种，“什么也不用摆”
			return 1;
		}
		if (plates == 0) {
			return 0; // 还剩下苹果，但是盘子没了！0种方法
		}
		// 苹果也有，盘子也有
		if (apples < plates) {
			// 多余的盘子都敲碎，因为没有，不会影响分布的种数
			return ways1(apples, apples);
		}
		// apples >= plates
		// 1) 所有的盘子全部都要使用，方法数是a
		int a = ways1(apples - plates, plates);
		// 2) 不全使用！所有的盘子不都使用，方法数是b
		int b = ways1(apples, plates - 1);
		return a + b;
	}

	// 苹果数量，初始时候，不超过100个
	// 盘子数量，初始视乎，不超过100个
	// 参数apples，0~100之间！
	// 参数plates，0~100之间！
	public static int[][] map = null;
	// int apples, int plates -> map

	// map[7][5] -> 苹果有7个，盘子有5个，有几种摆放方法！

	// map[7][5] == 0, 苹果有7个，盘子有5个，有0种摆放方法！
	
	// map[7][5] == -1，苹果有7个，盘子有5个，这个过程，还没有计算过！

	public static int ways2(int apples, int plates) {
		if(map == null) {
			map = new int[101][101];
			for (int i = 0; i <= apples; i++) {
				for (int j = 0; j <= plates; j++) {
					map[i][j] = -1;
				}
			}
		}
		return process(apples, plates);
	}
	
	// map可以用
	
	public static int process(int apples, int plates) {
		if(map[apples][plates] != -1) {
			return map[apples][plates];
		}
		int ans = 0;
		if (apples == 0) { // 没有苹果了！摆法有一种，“什么也不用摆”
			ans =  1;
		} else if (plates == 0) {
			ans =  0; // 还剩下苹果，但是盘子没了！0种方法
		} else if (apples < plates) {
			ans =  ways1(apples, apples);
		}else {
			// apples >= plates
			// 1) 所有的盘子全部都要使用，方法数是a
			int a = ways1(apples - plates, plates);
			// 2) 不全使用！所有的盘子不都使用，方法数是b
			int b = ways1(apples, plates - 1);
			ans =  a + b;
		}
		map[apples][plates] = ans;
		return ans;
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int m = sc.nextInt();
			int n = sc.nextInt();
			int ways = f1(m, n);
			System.out.println(ways);
		}
		sc.close();
	}

	// 苹果有apples个，盘子有plates个
	// 返回有几种摆法
	// 如果苹果数为0，有1种摆法：什么也不摆
	// 如果苹果数不为0，但是盘子数为0，有0种摆法（做不到）
	// 如果苹果数不为0，盘子数也不为0，进行如下的情况讨论：
	// 假设苹果数为apples，盘子数为plates
	// 可能性 1) apples < plates
	// 这种情况下，一定有多余的盘子，这些盘子完全没用，所以砍掉
	// 后续是f(apples, apples)
	// 可能性 2) apples >= plates
	// 在可能性2)下，讨论摆法，有如下两种选择
	// 选择a) 不是所有的盘子都使用
	// 选择b) 就是所有的盘子都使用
	// 对于选择a)，既然不是所有盘子都使用，那么后续就是f(apples, plates - 1)
	// 意思是：既然不是所有盘子都使用，那盘子减少一个，然后继续讨论吧！
	// 对于选择b)，既然就是所有的盘子都使用，那么先把所有盘子都摆上1个苹果。
	// 剩余苹果数 = apples - plates
	// 然后继续讨论，剩下的这些苹果，怎么摆进plates个盘子里，
	// 所以后续是f(apples - plates, plates)
	public static int f1(int apples, int plates) {
		if (apples == 0) {
			return 1;
		}
		if (plates == 0) {
			return 0;
		}
		if (plates > apples) {
			return f1(apples, apples);
		} else { // apples >= plates;
			return f1(apples, plates - 1) + f1(apples - plates, plates);
		}
	}

	// 下面的方法就是上面方法的记忆化搜索版本
	public static int[][] dp = new int[20][20];

	public static int f2(int apples, int plates) {
		for (int i = 0; i <= apples; i++) {
			Arrays.fill(dp[i], -1);
		}
		return g(apples, plates, dp);
	}

	public static int g(int apples, int plates, int[][] dp) {
		if (dp[apples][plates] != -1) {
			return dp[apples][plates];
		}
		int ans = 0;
		if (apples == 0) {
			ans = 1;
		} else if (plates == 0) {
			ans = 0;
		} else if (plates > apples) {
			ans = g(apples, apples, dp);
		} else {
			ans = g(apples, plates - 1, dp) + g(apples - plates, plates, dp);
		}
		dp[apples][plates] = ans;
		return ans;
	}

}