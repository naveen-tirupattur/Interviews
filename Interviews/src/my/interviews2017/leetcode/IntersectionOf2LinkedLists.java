package my.interviews2017.leetcode;

public class IntersectionOf2LinkedLists {

	public static void main(String[] args) {
		
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		
		
		ListNode A = new ListNode(5);
		ListNode B = new ListNode(6);
		ListNode C = new ListNode(7);
	
		ListNode D = new ListNode(4);
		
		a.next = b;
		b.next = c;
		c.next = D;
		
		A.next = B;
		B.next = C;
		C.next = D;
		
		System.out.println(getIntersectionNode(a, A));
		

	}
	
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode intersection = null;
    int n1 = length(headA);
    int n2 = length(headB);
    if (n1 > n2) {
    	headA = move(headA, n1-n2);
    } else {
    	headB = move(headB, n2-n1);
    }
    while (headA != null && headB != null) {
    	if (headA.val == headB.val) return headA;
    	headA = headA.next;
    	headB = headB.next;
    }
    return intersection;
  }
	
	public static ListNode move(ListNode head, int n) {
		while(n>0 && head != null) {head = head.next;n--;}
		return head;
	}
	
	public static int length(ListNode head) {
		int n = 0;
		while(head != null) { n++; head = head.next;}
		return n;
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
