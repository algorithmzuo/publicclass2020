package class32;

public class Code03_PaperFolding {

	public static void printAllFolds(int N) {
		process(1, N, true);
		System.out.println();
	}

	// 假想中的当前节点，在i层，一共N层，
	// 假想中的当前节点凹还是凸？down决定！down = true 凹 down = false 凸
	// 打印以假想节点为头的整棵树，中序打印
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		process(i + 1, N, true);
		System.out.print(down ? "凹 " : "凸 ");
		process(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 4;
		printAllFolds(N);
	}
}
