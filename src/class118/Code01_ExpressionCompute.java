package class118;

import java.util.LinkedList;

// 给定一个字符串表达式str，str表示一个公式，
// 公式里可能有整数、加减乘除符号和左右括号
// 返回公式的计算结果
// 难点在于括号可能嵌套很多层，
// str="48*((70-65)-43)+8*1"，返回-1816
// str="3+1*4"，返回7。str="3+(1*4)"，返回7。
// 1，可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
// 2，如果是负数，就需要用括号括起来，
// 比如"4*(-3)"但如果负数作为公式的开头或括号部分的开头则可以没有括号，
// 比如"-3*4"和"(-3*4)"都是合法的
// 3，不用考虑计算过程中会发生溢出的情况。
// 测试链接 : https://leetcode.cn/problems/basic-calculator-iii/
public class Code01_ExpressionCompute {

	public static int calculate(String str) {
		return f(str.toCharArray(), 0)[0];
	}

	// 请从str[i...]往下算，遇到字符串终止位置或者右括号，就停止
	// 返回两个值，长度为2的数组
	// 0) 负责的这一段的结果是多少
	// 1) 负责的这一段计算到了哪个位置
	public static int[] f(char[] str, int i) {
		// 当前从i.....
		LinkedList<String> stack = new LinkedList<String>();
		// 当前收集到的数字
		int number = 0;
		int[] bra = null;
		// 从i出发，开始撸串
		while (i < str.length && str[i] != ')') {
			// str[i] != ')'
			// str[i] -> 0~9
			//        -> +-*/
			//        -> (
			if (str[i] >= '0' && str[i] <= '9') {
				number = number * 10 + str[i++] - '0';
			} else if (str[i] != '(') { 
				// 遇到的是运算符号
				// number = 350 +
				addNum(stack, number, str[i++]);
				number = 0;
			} else { 
				// 遇到左括号了
				// 开始.......( ...... )
				//           i i+1
				bra = f(str, i + 1);
				number = bra[0];
				i = bra[1] + 1;
			}
		}
		addNum(stack, number, '+');
		return new int[] { getAns(stack), i };
	}

	public static void addNum(LinkedList<String> queue, int num, char op) {
		if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
			String top = queue.pollLast();
			int pre = Integer.valueOf(queue.pollLast());
			num = top.equals("*") ? (pre * num) : (pre / num);
		}
		queue.addLast(String.valueOf(num));
		queue.addLast(String.valueOf(op));
	}

	public static int getAns(LinkedList<String> queue) {
		int ans = Integer.valueOf(queue.pollFirst());
		while (queue.size() > 1) {
			String op = queue.pollFirst();
			int num = Integer.valueOf(queue.pollFirst());
			ans += op.equals("+") ? num : -num;
		}
		return ans;
	}

}
