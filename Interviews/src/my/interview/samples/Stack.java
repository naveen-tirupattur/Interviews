package my.interview.samples;

public class Stack {
	
	public LinkedListNode top;
	int count=0;
	int MAX_SIZE=5;
	
	public void push(int v)
	{
		if(count == MAX_SIZE) return;
		LinkedListNode n = new LinkedListNode(v,null);
		n.next = top;
		top = n;
		count++;
	}
	
	public int pop()
	{
		if(top == null || count == 0) return -1;
		int v = top.value;
		top = top.next;
		count--;
		return v;
		
	}

	public int peek()
	{
		return top.value;
	}
	
	public boolean isEmpty()
	{
		if(count == 0) return true;
		
		return false;
	}
	
	public boolean isFull()
	{
		if(count == MAX_SIZE) return true;
		
		return false;
	}
}
