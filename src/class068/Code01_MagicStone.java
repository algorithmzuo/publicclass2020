package class068;

import java.util.Arrays;

// 来自小红书
// [0,4,7] ： 0表示这里石头没有颜色，如果变红代价是4，如果变蓝代价是7
// [1,X,X] ： 1表示这里石头已经是红，而且不能改颜色，所以后两个数X无意义
// [2,X,X] ： 2表示这里石头已经是蓝，而且不能改颜色，所以后两个数X无意义
// 颜色只可能是0、1、2，代价一定>=0
// 给你一批这样的小数组，要求最后必须所有石头都有颜色，且红色和蓝色一样多，返回最小代价
// 如果怎么都无法做到所有石头都有颜色、且红色和蓝色一样多，返回-1
public class Code01_MagicStone {

	//
	// [0, 5 , 10]
	// [1, X , X]
	// [0, 10, 6]
	// ..
	// N stones N * 3
	public static int minCost(int[][] stones) {
		int n = stones.length;
		// n : 0011100010
		// 偶数
		// n : 0011110111
		// 奇数
		// 1 : 0000000001
		if ((n & 1) != 0) {
			return -1;
		}
		// O（N * logN）
		Arrays.sort(stones, (a, b) -> a[0] == 0 && b[0] == 0 ? (b[1] - b[2] - a[1] + a[2]) : (a[0] - b[0]));
		int zero = 0;
		int red = 0;
		int blue = 0;
		int cost = 0;
		for (int i = 0; i < n; i++) {
			if (stones[i][0] == 0) {
				zero++;
				cost += stones[i][1];
			} else if (stones[i][0] == 1) {
				red++;
			} else {
				blue++;
			}
		}
		// 红色如果超过了一半  或者  蓝色如果超过了一半
		// 返回-1
		// red > n /2 || blue > n / 2
		// n / 2  ->  n >> 1
		if (red > (n >> 1) || blue > (n >> 1)) {
			return -1;
		}
		// 所有无色的时候，都给红色了
		// 要把适当的石头，从红色，叛逃到蓝色
		// 叛逃的数量，blue
		blue = zero - ((n >> 1) - red);
		// stones: 0 1 2 3 12   13块
		for (int i = 0; i < blue; i++) {
			cost += stones[i][2] - stones[i][1];
		}
		return cost;
	}

	public static class Student {
		public int age;
		public int classNo;

		public Student(int a, int c) {
			age = a;
			classNo = c;
		}
	}

	public static void main(String[] args) {
		Student s1 = new Student(17, 2);
		Student s2 = new Student(21, 1);
		Student s3 = new Student(70, 3);
		Student s4 = new Student(35, 1);
		Student s5 = new Student(21, 3);
		Student[] arr = { s1, s2, s3, s4, s5 };

//		for (Student s : arr) {
//			System.out.println(s.age + " , " + s.classNo);
//		}

		// 大 -> 小
		// 只要返回负：第一个参数放前面
		// 只要返回正：第二个参数放前面
		// 整体按照班级从小到大排序，但是班级一样的！请按照年龄从小到大排序？
		Arrays.sort(arr, (a, b) -> a.classNo != b.classNo ? (a.classNo - b.classNo) : (a.age - b.age));

		for (Student s : arr) {
			System.out.println(s.age + " , " + s.classNo);
		}

//		int[][] stones = { { 1, 5, 3 }, { 2, 7, 9 }, { 0, 6, 4 }, { 0, 7, 9 }, { 0, 2, 1 }, { 0, 5, 9 } };
//		System.out.println(minCost(stones));
	}

}
