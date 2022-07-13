package class092;

// s只由(和)组成
// 求最长有效括号子串长度
// 本题测试链接 : https://leetcode.com/problems/longest-valid-parentheses/
public class Code02_LongestParentheses {

	//  ( ) ) ) ( ( ) ) ( ) ( (
	//  0 1 2 3 4 5 6 7 8 9 10 11
	// 最长有效括号子串
	// dp[] 长度11，
	// dp[i] : 子串必须以i位置结尾的情况下！向左最长能延伸多长！是有效的串！
	//    ( ) ) ) ( ( ) ) ( ) ( (
	//    0 1 2 3 4 5 6 7 8 9 10 11
	// dp 0 2 0 0 .................
	//    0 1 2 3
	//
	// dp[i] :  重点关注  dp[i-1]
	// 1) [i] == (, dp[i] = 0
	//                        (
	//                        i
	// 2) [i] == )
	//        )   (  )  (  )     )
	//                    i-1    i
	//                     4     0
	
	//        (   (  )  (  )     )
	//                    i-1    i
	//                     4     至少6 
	// 
	//    ( ) ( ) ( ( ) ( ) )
	//    0 1 2 3 4 5 6 7 8 9
	//                    4 10
	//    ( ) ( ) ( ) ( ( ) ) ( ( ( ) ) )
	//    a b c d e f g h i j k l m n o p
	//    0 2 0 4 0 6 0 0 2 10
	public static int longestValidParentheses(String s) {
		if (s == null || s.length() < 2) {
			return 0;
		}
		char[] str = s.toCharArray();
		int[] dp = new int[str.length];
		int pre = 0;
		int ans = 0;
		// dp[0]  = 0
		for (int i = 1; i < str.length; i++) {
			if (str[i] == ')') {
				// pre 找到和当前 ) 括号配的位置！
				//             ? ( ( ) ) )
				//             4 5 6 7 8 9(i)
				//                     4
				//
				//                 ? ( ) )
				//             4 5 6 7 8 9(i)
				//                     2
				//
				//
				//                     ( )
				//             4 5 6 7 8 9(i)
				//                     0
				//            ?           )
				//           pre          i
				pre = i - dp[i - 1] - 1;
				// pre < 0
				// ( ) ( ) )
				// 0 1 2 3 4
				//         0
				//            (           )
				//           pre          i
				//                        0
				if (pre >= 0 && str[pre] == '(') {
					//           ( ........ )
					//  pre-1   pre         i
					dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
				}
				// dp[i] = 0
			}
			// ( dp[i] = 0
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

}
