package class113;

// 来自微软
// 给定两个数组A和B，比如
// A = { 0, 1, 1 }
// B = { 1, 2, 3 }
// A[0] = 0, B[0] = 1，表示0到1有双向道路
// A[1] = 1, B[1] = 2，表示1到2有双向道路
// A[2] = 1, B[2] = 3，表示1到3有双向道路
// 给定数字N，编号从0~N，所以一共N+1个节点
// 题目输入一定保证所有节点都联通，并且一定没有环
// 默认办公室是0节点，其他1~N节点上，每个节点上都有一个居民
// 每天所有居民都去往0节点上班
// 所有的居民都有一辆5座的车，也都乐意和别人一起坐车
// 车不管负重是多少，只要走过一条路，就耗费1的汽油
// 比如A、B、C的居民，开着自己的车来到D居民的位置，一共耗费3的汽油
// D居民和E居民之间，假设有一条路
// 那么D居民可以接上A、B、C，4个人可以用一辆车，去往E的话，就再耗费1的汽油
// 求所有居民去办公室的路上，最少耗费多少汽油
import java.util.ArrayList;

public class Code01_TravelMinFuel {

	// a [ 2 ..
	// b [ 3 ..
	// n = 4，a、b数组的长度，a和b等长的！
	// 0 : {}
	// 1 : {}
	// 2 : {}
	// 3 : {}
	// 4 : {}
	public static int minFuel(int[] a, int[] b, int n) {
		// 先建图
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < a.length; i++) {
			graph.get(a[i]).add(b[i]);
			graph.get(b[i]).add(a[i]);
		}
		int[] size = new int[n+1];
		return cost(0, -1, graph, size);
	}

	
	
	// cur : 点的编号！
	// father : cur点的父节点！
	// 返回 : cur整棵子树上的所有节点汇聚到cur，需要多少油！
	public static int cost(int cur, int father, ArrayList<ArrayList<Integer>> graph, int[] size) {
		// cur的整棵子树上，包含cur自己的！
		size[cur] = 1;
		int cost = 0;
		for (int next : graph.get(cur)) {
			if (next != father) { // 不回到上级去！
				// 下级节点的子树所有节点汇聚在下级节点的总消耗！
				int nextDistance = cost(next, cur, graph, size);
				cost += nextDistance;
				cost += (size[next] + 4) / 5; // size[next] / 5向上取整！
				size[cur] += size[next];
			}
		}
		return cost;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 1 };
		int[] b1 = { 1, 2, 3 };
		int n1 = 3;
		System.out.println(minFuel(a1, b1, n1));

		int[] a2 = { 1, 1, 1, 9, 9, 9, 9, 7, 8 };
		int[] b2 = { 2, 0, 3, 1, 6, 5, 4, 0, 0 };
		int n2 = 9;
		System.out.println(minFuel(a2, b2, n2));
	}

}
