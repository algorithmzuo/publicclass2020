package class092;

//    ()
// ()()
// (()())(())
// (()
// (())()......
// 
// cnt = 0
// ( ++
// ) --
//  ( ) ) (
//  1 0 -1 false
//  ( ( ( ) ) ) ( ) ( ( ( )
//  1 2 3 2 1 0 1 0 1 2 3 2  false
//  前缀 ： ( ( ) ( ( ) ) )
//  ())
public class Code01_ValidParentheses {

	// 检查有效性，整体有效性！
	public static boolean valid(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			if (count < 0) {
				return false;
			}
		}
		return count == 0;
	}

	// )(  -> 2个
	// ......   -> ? 能变合法！
	// 
	// cnt = 0
	// add = 3
	// ( ++
	// ) --
	// 遍历的过程中，cnt < 0时刻！
	// ( ) ) ) ( ( ) ) ) ( ( ( )
	// 1 0 0 0 1 2 1 0 0 1 2 3 2
	// cnt = -1 添加 ( , 使之平衡！cnt = 0
	// 到最后 add += cnt
	public static int needParentheses(String s) {
		char[] str = s.toCharArray();
		int count = 0;
		int add = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == '(') {
				count++;
			} else { // 遇到的是')'
				if (count == 0) {
					add++;
				} else {
					count--;
				}
			}
		}
		return count + add;
	}

	public static boolean isValid(char[] str) {
		if (str == null || str.length == 0) {
			return false;
		}
		int status = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] != ')' && str[i] != '(') {
				return false;
			}
			if (str[i] == ')' && --status < 0) {
				return false;
			}
			if (str[i] == '(') {
				status++;
			}
		}
		return status == 0;
	}

	
	
	// 4
	// ( () () ) ( ( ( ) ) ) ( ( ) ( ) ( ( ) ) ( )( ) ( ( ( ) ( ) ) ) ) 
	// cnt  ( ++
	// cnt  ) --
	public static int deep(String s) {
		char[] str = s.toCharArray();
		if (!isValid(str)) {
			return 0;
		}
		int count = 0;
		int max = 0;
		for (int i = 0; i < str.length; i++) {
			count += str[i] == '(' ? 1 : -1;
			max = Math.max(max, count);
		}
		return max;
	}

}
