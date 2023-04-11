package class129;

import java.util.PriorityQueue;

// 来自学员问题，来自真实笔试
// 景区里有m个项目，也就是项目数组为int[][] game，这是一个m*2的二维数组
// 景区的第i个项目有如下两个参数：
// game[i] = { Ki, Bi }
// Ki一定是负数，Bi一定是正数
// 举个例子 : 
// Ki = -2, Bi = 10
// 如果只有1个人买票，单张门票的价格为 : Ki * 1 + Bi = 8
// 所以这1个人游玩该项目要花8元
// 如果有2个人买票，单张门票的价格为 : Ki * 2 + Bi = 6
// 所以这2个人游玩该项目要花6 * 2 = 12元
// 如果有5个人买票，单张门票的价格为 : Ki * 2 + Bi = 0
// 所以这5个人游玩该项目要花0 * 5 = 0元
// 如果有更多人买票，都认为花0元(因为你让项目倒贴钱实在是太操蛋了)
// 于是可以认为，如果有x个人买票，单张门票的价格为 : Ki * x + Bi
// x个人游玩这个项目的总花费是 : max { (Ki * x + Bi) * x , 0 }
// 你作为领导，单位一共有n个人，每个人最多可以选1个项目来游玩，也可以不选任何项目
// 所有员工将在明晚提交选择，然后由你去按照上面的规则，统一花钱，统一购票
// 但是现在，你想知道自己需要准备多少钱，就可以应付可能的各种情况，
// 支持各种可能下的开销，返回这个最保险的钱数
// 数据量描述 : 
// 1 <= N、M、Bi <= 10^5
// -(10^5) <= Ki < 0
public class Code01_GroupBuyTickets {

	public static int enoughMoney(int n, int[][] games) {
		PriorityQueue<Game> heap = new PriorityQueue<>((a, b) -> b.earn() - a.earn());
		for (int[] g : games) {
			heap.add(new Game(g[0], g[1]));
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (heap.peek().earn() <= 0) {
				break;
			}
			Game cur = heap.poll();
			ans += cur.earn();
			cur.people++;
			heap.add(cur);
		}
		return ans;
	}

	public static class Game {
		public int Ki;
		public int Bi;
		public int people;

		public Game(int k, int b) {
			Ki = k;
			Bi = b;
			people = 0;
		}

		public int earn() {
//			return  (Ki * (people + 1) + Bi) + Ki * people;
			return (2 * people + 1) * Ki + Bi;
		}

	}

}
