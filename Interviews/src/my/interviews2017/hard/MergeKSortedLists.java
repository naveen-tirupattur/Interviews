package my.interviews2017.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

	public static void main(String[] args) {
		
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(6);
		ListNode l7 = new ListNode(7);
		ListNode l8 = new ListNode(8);
		ListNode l9 = new ListNode(9);
		ListNode l10 = new ListNode(10);
		
		l1.next=l4;
		l4.next=l6;
		
		l2.next=l3;
		l3.next=l5;
		
		l7.next=l8;
		l8.next=l9;
		l9.next=l10;
		
		ListNode[] lists = {l1,l2,l7};
		
		System.out.println(mergeKLists(lists));
		
		
	}

	/**
	 * Definition for singly-linked list.
	 */
	public static class ListNode {
		@Override
		public String toString() {
			return "ListNode [val=" + val + ", next=" + next + "]";
		}
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
	
	public static class ListComparator implements Comparator<ListNode> {
		@Override
		public int compare(ListNode o1, ListNode o2) {
			return Integer.compare(o1.val, o2.val);
		}
	}
	
	public static ListNode mergeKLists(ListNode[] lists) {
    
		if (lists.length == 0) return null;
    if (lists.length == 1) return lists[0];
		//Use a min heap to store the elements of linkedlists
    PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length, new ListComparator());
  
    //Add all first elements of the linkedlists
    ListNode head = null, prev = new ListNode(-1), curr = null;
    for (int i=0;i<lists.length;i++) {
    	 if (lists[i] != null) minHeap.add(lists[i]);
    }
    
    while (!minHeap.isEmpty()) {
    	// Remove the top of min heap and fetch the next one to removed element
    	if (head == null) head = curr = minHeap.remove();
    	else curr = minHeap.remove();
    	// Update the heap
    	if (curr.next != null) minHeap.add(curr.next);
    	prev.next = curr;
    	prev = prev.next;
    }
    
    return head;
  }


}
