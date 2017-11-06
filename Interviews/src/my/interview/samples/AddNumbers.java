package my.interview.samples;

public class AddNumbers {

  public static void main(String[] args) {

	//	ListNode l1 = new ListNode(2);
	//	l1.next = new ListNode(4);
	//	l1.next.next = new ListNode(3);
	//	
	//	ListNode l2 = new ListNode(5);
	//	l2.next = new ListNode(6);
	//	l2.next.next = new ListNode(4);
	//	
	ListNode l1 = new ListNode(5);
	ListNode l2 = new ListNode(5);


	System.out.println(addTwoNumbers(l1, l2));

  }
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode prev = null,first = null;
	int carry = 0;
	while (l1 != null && l2 != null) {
	  int sum = l1.val + l2.val+carry;
	  ListNode current = new ListNode(sum%10);
	  //System.out.println(current);
	  //System.out.println(prev);
	  carry = sum/10;
	  if (prev != null) {
		prev.next = current;    
	  } else {
		first = current;
	  }
	  prev = current;
	  l1 = l1.next;
	  l2 = l2.next;
	}

	while(l1 != null) {
	  int sum = l1.val +carry;
	  ListNode current = new ListNode(sum%10);
	  carry = sum/10;
	  prev.next = current;
	  prev = current;
	  l1 = l1.next;
	}
	
	while(l2 != null) {
	  int sum = l2.val +carry;
	  ListNode current = new ListNode(sum%10);
	  carry = sum/10;
	  prev.next = current;
	  prev = current;
	  l2 = l2.next;
	}

	if (carry !=0) {
	  ListNode current = new ListNode(carry);
	  prev.next = current;
	  prev = current;

	}
	return first;
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
