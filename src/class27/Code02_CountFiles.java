package class27;

import java.io.File;
import java.util.Stack;

public class Code02_CountFiles {

	// 注意这个函数也会统计隐藏文件
	public static int getFileNumber(String folderPath) {
		File root = new File(folderPath);
		if (!root.isDirectory() && !root.isFile()) {
			return 0;
		}
		if (root.isFile()) {
			return 1;
		}
		// File分为文件夹和文件
		// 而stack只放文件夹
		Stack<File> stack = new Stack<>();
		stack.add(root);
		int files = 0;
		while (!stack.isEmpty()) {
			File folder = stack.pop();
			for (File next : folder.listFiles()) {
				if (next.isFile()) {
					files++;
				}
				if (next.isDirectory()) {
					stack.push(next);
				}
			}
		}
		return files;
	}

	public static void main(String[] args) {
		// 你可以自己更改目录来查看这个目录下的文件个数
		String path = "/Users/zuochengyun/Desktop";
		System.out.println(getFileNumber(path));
	}

}
