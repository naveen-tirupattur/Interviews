package my.interview.samples;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class LinkedListProblems {

	/**
	 * @param args
	 * 
	 */
	public static LinkedListNode head;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//head = add();

		LinkedListNode n1 = new LinkedListNode(1,null);
		LinkedListNode n2 = new LinkedListNode(2,null);
		LinkedListNode n3 = new LinkedListNode(3,null);
		LinkedListNode n4 = new LinkedListNode(4,null);
		LinkedListNode n5 = new LinkedListNode(5,null);
		LinkedListNode n6 = new LinkedListNode(6,null);
		LinkedListNode n7 = new LinkedListNode(7,null);
		LinkedListNode n8 = new LinkedListNode(8,null);
		
		
		n1.next = n2;
		n2.next = n3;
		n3.next = n6;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		
		print(n1);
		
//		n1 = new LinkedListNode(,n1);
//		n1 = new LinkedListNode(1,n1);
//		n1 = new LinkedListNode(4,n1);
//		n1 = new LinkedListNode(3, n1);
//		n1 = new LinkedListNode(2, n1);
//		n1 = new LinkedListNode(1, n1);
//		System.out.println("LinkedList 1");
//		print(n1);
//		
//		
//		System.out.println("LinkedList 2");
//		print(n4);
//		
//		System.out.println("Common Node: "+findConvergence(n1, n4).value);
//		//head = n1;

		//		LinkedListNode n2 = new LinkedListNode(1,null);
		//		n2 = new LinkedListNode(5,n2);
		//		n2 = new LinkedListNode(9,n2);
		//		n2 = new LinkedListNode(2,n2);

		//head = add(n1,n2,0);

//		System.out.println("Before folding");
//		print(head);
//
//		head = foldLinkedList(head);
//		
//		System.out.println("After folding");
//		print(head);
//		head = remove(head,5);
//
//		System.out.println("After deletion");
//		print(head);

		//head = reverse(head);

		//print(head);

		//deQueueTest();
		
		//Reverse in pairs
		printReverseP(n1);
	}
	
	public static void printReverseP(LinkedListNode head) {
		print(head);
		LinkedListNode newHead = reverseP(head);
		print(newHead);
	}
	
	public static LinkedListNode findConvergence(LinkedListNode n1,LinkedListNode n2)
	{
		
		if(n1== null || n2 == null) return null;
		
		int ct1=0,ct2=0;
		LinkedListNode tN1 = n1, tN2 = n2;
		while(n1.next != null) { n1 = n1.next; ct1++;}
		while(n2.next != null) { n2 = n2.next; ct2++;}
		
		if(n1==n2)
		{
			int diff = Math.abs(ct1-ct2);
			if(ct1 > ct2)
			{
				while(tN1!=null && diff!=0)
				{
					tN1 = tN1.next;
					diff--;
				}
			}else
			{
				while(tN2!=null && diff!=0)
				{
					tN2 = tN2.next;
					diff--;
				}
			}
			
			while(tN1 != null && tN2!=null)
			{
				if(tN1 == tN2) break;
				
				tN1 = tN1.next;
				tN2 = tN2.next;
			}
			
			return tN1;
		}
		
		return null;
	}
	
	public static LinkedListNode foldLinkedList(LinkedListNode head)
	{
		
		LinkedListNode fp = head, sp = head;
		
		while(fp!= null && fp.next != null)
		{
			fp = fp.next.next;
			sp = sp.next;
		}
		
		
		LinkedListNode current = sp, nxt = current.next, lpNode = null;
		current.next = null;
		while(nxt != null)
		{
			lpNode = nxt.next;
			nxt.next = current;
			current = nxt;
			nxt = lpNode;
		}
		
		LinkedListNode n1 = head, n2 = current;
		
		while(n1 != sp && n2 != null)
		{
			LinkedListNode t1 = n1.next;
			LinkedListNode t2 = n2.next;
			n1.next = n2;
			n2.next = t1;
			n1 = t1;
			n2 = t2;
		}
		
		return head;
	}
	
	public static LinkedListNode reversePairs(LinkedListNode head)
    {
        if(head == null || head.next == null) return head;

        LinkedListNode currentNode = head,nextNode= head.next,tempNode = null,holder = head.next;

        while(currentNode != null && nextNode != null)
        {
            tempNode = currentNode.next.next;
            nextNode.next = currentNode;
            if(tempNode == null)
            {
                currentNode.next = null;
                break;
            }else if(tempNode.next == null)
            {
                currentNode.next = tempNode;
                break;
            }
            else
            {
                currentNode.next = tempNode.next;          
                currentNode = tempNode;
                nextNode = currentNode.next;
            }

        }

        return holder;

    }
	
	// Reverse linked list in pairs
	public static LinkedListNode reverseP(LinkedListNode head) {
		if(head == null || head.next == null) return head;
		LinkedListNode current = head, temp = current.next, next = current.next, loopNode = null;
		while(next != null && current.next != null) {
			loopNode = next.next;
			next.next = current;
			current.next = loopNode;
			current = loopNode;
			next = current.next;
		}
		return temp;
	}

	public static LinkedListNode remove(LinkedListNode head, int k)
	{
		if(head==null) return null;

		LinkedListNode current = head,prev=null;
		while(current!=null)
		{
			if(current.value==k)
			{
				if(current != head)
				{
					prev.next=current.next;
					current = current.next;
				}else
				{
					head = head.next; 
					current = head;
				}
			}else
			{
				prev = current;
				current = current.next;
			}
		}	
		
		return head;
	}

	public static void deQueueTest()
	{
		Dequeue d = new Dequeue();
		d.addEnd(1);
		d.addEnd(2);
		d.addFront(3);
		d.addEnd(4);
		d.addFront(5);
		//d.print();

		d.removeEnd();
		d.removeFront();
		d.removeEnd();
		d.print();

	}

	public static LinkedListNode add(LinkedListNode n1,LinkedListNode n2,int carry)
	{
		if(n1==null&&n2==null&&carry==0 ) return null;


		int v = carry;

		if(n1!=null)
		{
			v+=n1.value;
		}

		if(n2!=null)
		{
			v+=n2.value;
		}

		LinkedListNode newNode = new LinkedListNode(v%10,null);

		//		if(n1!=null || n2!=null)
		//		{
		LinkedListNode n = add(n1!=null?n1.next:null,n2!=null?n2.next:null,v >=10?1:0);

		newNode.next = n;
		//		}

		return newNode;
	}
	public static boolean isPalindrome(LinkedListNode head)
	{

		LinkedListNode fp = head;
		LinkedListNode sp = head;
		Stack<Integer> s = new Stack<Integer>();
		while(fp != null && fp.next != null)
		{
			s.push(sp.value);
			sp = sp.next;
			fp = fp.next.next;
		}
		//if linkedlist is odd
		if(fp != null)
		{
			sp = sp.next;
		}

		while(sp != null)
		{
			if(s.pop() != Integer.valueOf(sp.value)) return false;
			sp = sp.next;
		}

		return true;
	}


	public static void removeLoop(LinkedListNode head)
	{
		LinkedListNode cycleNode = findNode(head);

		LinkedListNode fp = cycleNode;
		while(fp != null)
		{
			if (fp.next == cycleNode) break;
			fp = fp.next;

		}

		if(fp == null) return;

		fp.next = null;

	}

	public static LinkedListNode findNode(LinkedListNode head)
	{
		LinkedListNode fp = head;
		LinkedListNode sp = head;

		//find the cycle
		while(fp!= null)
		{
			fp = fp.next.next;
			sp = sp.next;
			if(fp == sp) break;
		}

		if(fp == null) return null;

		//move one pointer to head
		sp = head;

		//keep moving both the pointers, they will meet at beginning of the loop

		while(fp != null)
		{
			fp = fp.next;
			sp = sp.next;
			if(fp == sp) break;
		}

		return sp;
	}

	public static void makePartition()
	{
		head = new LinkedListNode(6, null);
		head = new LinkedListNode(2, head);
		head = new LinkedListNode(3, head);
		LinkedListNode k = head;
		head = new LinkedListNode(5, head);
		head = new LinkedListNode(1, head);
		head = new LinkedListNode(4, head);

		head = partitionNode(k);
	}

	public static LinkedListNode add()
	{
		LinkedListNode head = new LinkedListNode(1,null);

		LinkedListNode temp = head;
		int i=2;
		while(i <= 4)
		{
			temp = new LinkedListNode(i,temp);

			i++;

		}

		return temp;
	}

	public static void print(LinkedListNode head)
	{
		LinkedListNode temp = head;
		while(temp != null)
		{
			System.out.println(temp.value);
			temp = temp.next;
		}
	}

	public static LinkedListNode reverse(LinkedListNode head)
	{
		LinkedListNode currentNode = head,nextNode = head.next,loopNode = null;
		head.next = null;
		while(nextNode != null)
		{
			loopNode = nextNode.next;
			nextNode.next = currentNode;
			//currentNode.next = null;
			currentNode = nextNode;
			nextNode = loopNode;
		}

		return currentNode;
	}

	public static LinkedListNode findMiddle(LinkedListNode head)
	{

		LinkedListNode fastPointer = head,slowPointer=head;

		int k=0;
		while(fastPointer != null)
		{
			fastPointer = fastPointer.next;
			k++;

			if(k%2==0) 
				slowPointer = slowPointer.next;
		}

		return slowPointer;
	}

	public static void reverseRecursion(LinkedListNode node)
	{
		if(node==null) return;

		if(node.next == null){
			head = node;
			return;
		}
		reverseRecursion(node.next);
		node.next.next = node;
		node.next = null;
	}

	public static LinkedListNode makeCycle(LinkedListNode head)
	{
		LinkedListNode temp = head;

		while(temp.next != null) temp = temp.next;

		temp.next = head;

		head = new LinkedListNode(5, head);
		head = new LinkedListNode(6, head);

		return head;
	}

	public static boolean isCycle(LinkedListNode head)
	{
		LinkedListNode fp  = head,sp = head;
		while(fp != null)
		{
			fp = fp.next.next;
			sp = sp.next;

			if(sp == fp)
			{
				return true;
			}
		}

		return false;
	}


	public static void removeCycle(LinkedListNode head)
	{
		LinkedListNode fp  = head,sp = head;
		//1. Find if cycle exists
		while(fp != null)
		{
			fp = fp.next.next;
			sp = sp.next;

			if(sp == fp)
			{
				removeCycle(sp,head); break;
			}
		}
	}

	public static void removeCycle(LinkedListNode node,LinkedListNode head)
	{
		//2. Find the length of the cycle
		LinkedListNode temp = node,temp1 = head; int k=1;
		while(temp.next != node){
			temp = temp.next;
			k++;
		}

		//3. Move a pointer to distance k from head
		temp = head;
		while(k!=0)
		{
			temp = temp.next;
			k--;
		}

		//4. start moving both the pointers until they meet. When they meet they are at beginning of the loop 
		while(temp != temp1){
			temp = temp.next;
			temp1 = temp1.next;
		}

		//5. Find the last node of the loop and make its next as null
		while(temp1.next != temp)
		{
			temp1 = temp1.next;
		}
		temp1.next = null;
	}


	public static LinkedListNode deleteDupsExtraSpace(LinkedListNode head)
	{
		LinkedListNode temp = head,prev = null;
		Map<Integer,Boolean> map = new HashMap<Integer,Boolean>();
		while(temp != null)
		{
			if(map.containsKey(Integer.valueOf(temp.value)))
			{
				prev.next = temp.next;
			}else
			{
				map.put(Integer.valueOf(temp.value), true);
				prev = temp;
			}
			temp = temp.next;
		}


		return head;
	}

	public static LinkedListNode deleteDupsNoExtraSpace(LinkedListNode head)
	{
		LinkedListNode current = head;
		LinkedListNode runner = head;

		while(current != null)
		{
			while(runner.next != null)
			{
				if(runner.next.value==current.value)
				{
					runner.next = runner.next.next;
				}
				else
				{
					runner = runner.next;
				}
			}

			current = current.next;
			runner = current;
		}
		return head;
	}



	public static LinkedListNode findKLastNode(LinkedListNode head,int k)
	{
		LinkedListNode temp = head;
		LinkedListNode temp1 = head;
		while(k!=0 && temp1 != null)
		{
			temp1 = temp1.next;
			k--;
		}

		if(temp1 == null) return null;

		while(temp1 != null)
		{
			temp = temp.next;
			temp1 = temp1.next;
		}

		return temp;
	}

	public static LinkedListNode partitionNode(LinkedListNode k)
	{
		LinkedListNode p = head;
		LinkedListNode less = null;
		LinkedListNode more = null;
		while(p!= null)
		{
			if(p.value < k.value)
			{
				less = new LinkedListNode(p.value, less);
			}
			else if(p.value > k.value)
			{
				more = new LinkedListNode(p.value,more);
			}
			p = p.next;

		}

		p = less;
		while(p.next != null)
		{
			p = p.next;
		}
		p.next = k;
		k.next = more;

		head = less;

		return head;

	}

	public static void swap(LinkedListNode a, LinkedListNode b)
	{
		int temp = a.value;
		a.value = b.value;
		b.value = temp;
	}


}

