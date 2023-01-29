package class119;

import java.util.ArrayList;
import java.util.HashMap;

// 设计一个类似堆栈的数据结构，将元素推入堆栈，并从堆栈中弹出出现频率最高的元素。
// 实现 FreqStack 类:
// FreqStack() 构造一个空的堆栈。
// void push(int val) 将一个整数 val 压入栈顶。
// int pop() 删除并返回堆栈中出现频率最高的元素。
// 如果出现频率最高的元素不只一个，则移除并返回最接近栈顶的元素。
// 测试链接 : https://leetcode.cn/problems/maximum-frequency-stack/
public class Code02_MaximumFrequencyStack {

	class FreqStack {

		// 出现的最大次数
		private int topTimes;
		
		// 每层节点
		// value部分也可以用LinkedList
		// 因为只是加入数字在尾巴上，或者从尾部拿数字
		private HashMap<Integer, ArrayList<Integer>>
		levelLists = new HashMap<>();
		
		// 每一个数出现了几次
		private HashMap<Integer, Integer> valueTopTime = new HashMap<>();

		public void push(int val) {
			// 当前数词频+1
			valueTopTime.put(val, valueTopTime.getOrDefault(val, 0) + 1);
			// 当前数是什么词频
			int curTopTimes = valueTopTime.get(val);
			// 5  13次
			// 13 -> {......5}
			if (!levelLists.containsKey(curTopTimes)) {
				levelLists.put(curTopTimes, new ArrayList<>());
			}
			ArrayList<Integer> curTimeValues = levelLists.get(curTopTimes);
			curTimeValues.add(val);
			topTimes = Math.max(topTimes, curTopTimes);
		}

		public int pop() {
			// 最大词频的那一层的链表(动态数组)
			ArrayList<Integer> topTimeValues = levelLists.get(topTimes);
			// 移除并且返回，链表尾部的数字
			int ans = topTimeValues.remove(topTimeValues.size() - 1);
			if (topTimeValues.size() == 0) {
				levelLists.remove(topTimes--);
			}
			int times = valueTopTime.get(ans);
			if (times == 1) {
				valueTopTime.remove(ans);
			} else {
				valueTopTime.put(ans, times - 1);
			}
			return ans;
		}
	}

}
