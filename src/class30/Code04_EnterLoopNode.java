package class30;

// 测试链接：https://leetcode.com/problems/linked-list-cycle-ii
public class Code04_EnterLoopNode {

	// 这个类不用提交
	public static class ListNode {
		public int val;
		public ListNode next;
	}

	// 只提交以下的代码
	public static ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}
		ListNode slow = head.next;
		ListNode fast = head.next.next;
		while (slow != fast) {
			if (fast.next == null || fast.next.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
		}
		fast = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

}
