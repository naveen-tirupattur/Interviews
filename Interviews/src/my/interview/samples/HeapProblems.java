package my.interview.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class HeapProblems {

	public static void printSkylineUtil() {
		
	}
	
	// Classes required for Sky line Problem
	public static class Building implements Comparable<Building>{
		
		int start, end, height;
		
		public Building(int start, int end, int height) {
			this.start = start;
			this.end = end;
			this.height = height;
		}

		@Override
		public int compareTo(Building o) {
			return Integer.compare(this.height, o.height);
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		
		int position, height;
		boolean isStart;
		Building building;
		
		public Edge (int position, int height, boolean isStart, Building building) {
			this.position = position;
			this.isStart = isStart;
			this.height = height;
			this.building = building;
		}
		
		public Edge (int position, int height) {
			this.position = position;
			this.height = height;
		}
		
		@Override
		public int compareTo(Edge o) {
			
			// If both the edges don't start at same position
			if(this.position != o.position) return Integer.compare(this.position, o.position);
			
			// If both the edges are starting at same position, the taller building goes first -- why ?
			// Always only the taller building shows up in the sky line, which is not possible if we add smaller building first
			// Imagine the heap is empty and if we add the smaller building first then we print the smaller building which is wrong
			if(this.isStart && o.isStart) return Integer.compare(o.height, this.height);
			
			// If both the edges are ending at same position, the smaller building goes first -- why ?
			// It does'nt matter, if there are no overlapping edges at this position, then we print this position
			// If there are overlapping positions then we check for tallest edge height in the remove, so we print the correct height at this position
			if(!this.isStart && !o.isStart) return Integer.compare(this.height, o.height);
			
			// If one is starting and other is ending, the starting building goes first -- why ?
			// Because we remove the building from heap when we hit the ending edge and if heap is empty we print that edge 
			// but if the starting edge at that position is higher than the ending edge, we should'nt be printing the ending edge.
			return this.isStart ? -1:1;
		}	
	}
	
	// Print the sky line
	public static void printSkyline(List<Building> buildings) {
		// List of edges
		List<Edge> edgesList = new ArrayList<Edge>();
		// Heap to store edge heights
		PriorityQueue<Building> heap = new PriorityQueue<Building>(10, Collections.reverseOrder());
		for(Building b:buildings) {
			edgesList.add(new Edge(b.start,b.height,false, b));
			edgesList.add(new Edge(b.end,b.height,true,b));
		}
		
		// Sort the edges
		Collections.sort(edgesList);
		
		//Do a sweep line algorithm
		for(Edge e:edgesList) {
			if(e.isStart) {
				// Check if heap is empty or the top of heap is smaller than current edge
				if(heap.isEmpty() || heap.peek().height < e.height) {
					System.out.println(e.position+","+e.height);
				}
				// Add the building to the heap
				heap.add(e.building);
			} else {
				heap.remove(e.building);
				// If the heap is empty then print the ending edge
				if(heap.isEmpty()) {
					System.out.println(e.position+", 0");
				} else { //If not empty then check if the current top of the heap has height less than the edge being removed
					// If the top of the heap is taller than current edge, this position will not be visible in skyline
					if(e.height < heap.peek().height) {
						System.out.println(e.position+","+heap.peek().height);
					}
				}
			}
		}
	}
	
		
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
