package my.interviews2017.leetcode;

public class RemoveNFromLastNode {

	public static void main(String[] args) {

		ListNode head = new ListNode(1);
		head.next = new ListNode(2);

		System.out.println(delete(head, 2));
	}

	public static ListNode delete(ListNode head, int n) {
		if (head == null) return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode n1 = head;

		while (n > 0 && n1 != null) {
			n1 = n1.next;
			n--;
		}
		ListNode n2 = dummy;
		while (n1 != null) {
			n1 = n1.next;
			n2 = n2.next;
		}
		n2.next = n2.next.next;
		return dummy.next;
	}
	
	public static class ListNode {
		@Override
		public String toString() {
			return "ListNode [val=" + val + ", next=" + next + "]";
		}
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

}
