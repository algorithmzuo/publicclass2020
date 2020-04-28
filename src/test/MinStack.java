package test;

import java.util.Stack;

public class MinStack {
	/*
	 * 如何不用辅助栈，就能实现返回栈中最小值的操作
	 * 
	 * 不用跟我扯范围，有范围太弱鸡了，我就不限定范围，还不用辅助栈。
	 * 
	 * 但本质上毫无意义，多用一个栈怎么了？哎，现在的面试官啊
	 * 
	 */

	// 课上讲的结构，用辅助栈，但这不是我们要实现的东西，只是做对数器使用。
	// 真正想说的方法，看MinStack2
	public static class MinStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MinStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	// 想实现的东西
	public static class MinStack2 {
		private Stack<Long> stack;
		private final long offset;

		public MinStack2() {
			offset = 4294967295L;
			stack = new Stack<>();
		}

		public boolean isEmpty() {
			return stack.isEmpty();
		}

		public void push(int num) {
			long left = ((long)(num)  << 32)   ;
			long right = 0;
			if (isEmpty()) {
				right = ((long)(num) & offset);
			} else {
				int min = Math.min(num, getMin());
				right = ((long)(min) & offset);
			}
			stack.push((left | right));
		}

		public int pop() {
			long out = stack.pop();
			int ans = (int) (out >>> 32);
			return ans;
		}

		public int peek() {
			long peek = stack.peek();
			int ans = (int) (peek >>> 32);
			return ans;
		}

		public int getMin() {
			long peek = stack.peek();
			return (int) (peek & offset);
		}

	}

	// 在Integer.MIN_VALUE ~ Integer.MAX_VALUE范围上，
	// 等概率随机返回一个数
	public static int randomNumber() {
		int min = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		long t = (long) (Math.random() * ((long) max - (long) min + 1L));
		return (int) (min + t);
	}

	public static void main(String[] args) {
		int testTimes = 10000000;
		MinStack1 s1 = new MinStack1();
		MinStack2 s2 = new MinStack2();
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			double decide = Math.random();
			if (decide < 0.333) {
				int r = randomNumber();
				s1.push(r);
				s2.push(r);
			} else if (decide < 0.666) {
				if (!s2.isEmpty()) {			
					int p1 = s1.pop();
					int p2 = s2.pop();
					if (p1 != p2) {
						System.out.println("pop Oops!");
						break;
					}
				}
			} else {
				if (!s2.isEmpty()) {
					int min1 = s1.getmin();
					int min2 = s2.getMin();
					if (min1 != min2) {
						System.out.println("min Oops!");
						break;
					}
				}
			}

		}
		System.out.println("test end");
	}

}
