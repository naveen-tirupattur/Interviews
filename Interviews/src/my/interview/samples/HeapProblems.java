package my.interview.samples;

import java.util.PriorityQueue;

public class HeapProblems {

//	public static class MedianOfStream
//	{
//		PriorityQueue maxHeap,minHeap;
//
//		public MedianOfStream(int size)
//		{
//			maxHeap = new PriorityQueue(size, false);
//			minHeap = new PriorityQueue(size, true);
//		}
//
//		public int getSize()
//		{
//			return maxHeap.size + minHeap.size;
//		}
//
//		public void insert(int element) throws Exception
//		{
//			int sizeBefore = getSize();
//			maxHeap.add(element);
//			if(sizeBefore%2==0)
//			{
//				if(!minHeap.isEmpty() && maxHeap.top() > minHeap.top())
//				{
//					int temp = maxHeap.remove(), temp1 = minHeap.remove();
//					maxHeap.add(temp1);
//					minHeap.add(temp);
//				}
//			}else
//			{
//				minHeap.add(maxHeap.remove());
//			}
//		}
//
//		public double getMedian()
//		{
//			if(getSize()%2==0) 
//				return (maxHeap.top()+minHeap.top())/2;
//			else
//				return maxHeap.top();
//		}
//
//	}

	public static class ListNode implements Comparable<ListNode>
	{

		int value;
		int row;
		int nextColumn;
		
		ListNode(int value, int row,int nextColumn)
		{
			this.value = value;
			this.row = row;
			this.nextColumn = nextColumn;
		}

		@Override
		public int compareTo(ListNode o) {
			// TODO Auto-generated method stub
			if(this.value>o.value) return 1;
			else
				if(this.value <o.value) return -1;
				else
					return 0;
		}
		
		public String toString()
		{
			return String.valueOf(this.value);
		}

	}
	
	public static int[] mergeArrays(int[][] aList, int n, int k)
	{
		int[] a = new int[n*k];
		PriorityQueue<ListNode> p = new PriorityQueue<>(k);
		for(int i=0;i<k;i++)
		{
			ListNode lNode = new ListNode(aList[i][0], i, 1);
			p.add(lNode);
		}
		
		for(int i=0;i<n*k;i++)
		{
			int tempV=0;
			
			ListNode ln = p.poll();
			a[i] = ln.value;
			if(ln.nextColumn >= n) tempV = Integer.MAX_VALUE;
			else tempV =aList[ln.row][ln.nextColumn];
			
			ListNode ln1 = new ListNode(tempV, ln.row, ++ln.nextColumn);
			p.add(ln1);
		}
		return a;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] a = new int[]{9,6,-1,3,2,4,8,10,-11,12};
		int[][] l = new int[][]{{1,3,5,7,9},{2,4,8,11,13},{6,10,12,14,15}};

		a = mergeArrays(l, 5, 3);
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
		//		try {
		//			System.out.println(getMedianFromStream(a));
		//		} catch (Exception e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//
		//		PriorityQueue p = makeHeap(a, 10);
		//		//		
		//		//
		//		//		p.print();
		//		//
		//		System.out.println("Sorted Array");
		//		heapSort(p);

	}

//	public static double getMedianFromStream(int[] a) throws Exception
//	{
//		MedianOfStream m = new MedianOfStream(10);
//		for(int i=0;i<a.length;i++)
//		{
//			m.insert(a[i]);
//		}
//
//		return m.getMedian();
//	}
//
//	public static PriorityQueue makeHeap(int[] a, int size)
//	{
//		PriorityQueue p = new PriorityQueue(size,true);
//
//		for(int i=0;i<a.length;i++)
//		{
//			try
//			{
//				p.add(a[i]);
//			}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		return p;
//	}
//
//	public static void heapSort(PriorityQueue p)
//	{
//		try {
//			while(p.size!=0)
//				System.out.println(p.remove());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
