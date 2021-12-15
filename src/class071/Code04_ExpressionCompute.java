package class071;

import java.util.LinkedList;

// 本题测试链接 : https://leetcode.com/problems/basic-calculator-iii/
public class Code04_ExpressionCompute {

	public static int calculate(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组 int[] ans
	// ans[0] 负责的这一段的结果是多少
	// ans[1] 负责的这一段，算到了哪个位置停止的
	public static int[] f(char[] str, int i) {
		// 12 +  345 -   5 * 
		LinkedList<String> que = new LinkedList<String>();
		int cur = 0;
		int[] bra = null;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			if (str[i] >= '0' && str[i] <= '9') { //遇到数字字符
				cur = cur * 10 + str[i++] - '0';
			} else if (str[i] != '(') { // 遇到的是运算符号
				// cur = 34 str[i] = '+'
				addNum(que, cur);
				que.addLast(String.valueOf(str[i++]));
				cur = 0;
			} else { // 遇到左括号了
				//   (
				//   i i+1
				bra = f(str, i + 1);
				cur = bra[0];
				i = bra[1] + 1;
			}
		}
		// 1）终止了！2）遇到右括号了
		addNum(que, cur);
		return new int[] { getNum(que), i };
	}

	public static void addNum(LinkedList<String> que, int num) {
		if (!que.isEmpty()) {
			int cur = 0;
			String top = que.pollLast();
			if (top.equals("+") || top.equals("-")) {
				que.addLast(top);
			} else {
				cur = Integer.valueOf(que.pollLast());
				num = top.equals("*") ? (cur * num) : (cur / num);
			}
		}
		que.addLast(String.valueOf(num));
	}

	public static int getNum(LinkedList<String> que) {
		int res = 0;
		boolean add = true;
		String cur = null;
		int num = 0;
		while (!que.isEmpty()) {
			cur = que.pollFirst();
			if (cur.equals("+")) {
				add = true;
			} else if (cur.equals("-")) {
				add = false;
			} else {
				num = Integer.valueOf(cur);
				res += add ? num : (-num);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String test = "52+13*(5+2-(5*(23-4)))-40+((3*2)+(5/2))";
		System.out.println(calculate(test));
	}

}
