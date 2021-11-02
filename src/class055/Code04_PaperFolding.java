package class055;

public class Code04_PaperFolding {

	public static void printAllFolds(int N) {
		process(1, N, true);
		System.out.println();
	}

	// 想象中的节点，在第i层
	// N，最多有多少层，固定参数
	// 想象中的节点，是凸还是凹，down = true 凹 down = false 凸
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		process(i + 1, N, true);
		System.out.print(down ? "凹 " : "凸 ");
		process(i + 1, N, false);
	}

	public static void main(String[] args) {
		int N = 3;
		printAllFolds(N);
	}
}
