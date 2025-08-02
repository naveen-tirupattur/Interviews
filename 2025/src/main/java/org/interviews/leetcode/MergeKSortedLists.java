package org.interviews.leetcode;

import java.util.PriorityQueue;

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

public class MergeKSortedLists {
  public ListNode mergeKLists(ListNode[] lists) {
    if (lists.length == 0) return null;
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    PriorityQueue<ListNode> heap = new PriorityQueue<>((o1, o2) -> {
      return Integer.compare(o1.val, o2.val);
    });
    for (ListNode listNode : lists) {
      if (listNode != null) heap.add(listNode);
    }
    while (!heap.isEmpty()) {
      ListNode node = heap.poll();
      head.next = node;
      head = head.next;
      if (node.next != null) heap.add(node.next);
    }
    return dummy.next;
  }
}
