package my.interviews2017;

import java.util.Collections;
import java.util.PriorityQueue;

public class OnlineMedian {

	// Use 2 heaps to store the elements. The idea is to keep middle elements at top of either heap which would become the median. 
	// Min heap should always have the median element but if num of elements is odd then tops of both heaps will be the median
	public static PriorityQueue<Integer> minQueue = new PriorityQueue<Integer>(); // Store all large elements
	public static PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(16, Collections.reverseOrder()); // Store small elements
	
	
	public static void main(String[] args) {
		add(1);
		System.out.println(median());
		add(0);
		add(3);
		System.out.println(median());
		add(5);
		System.out.println(median());
		add(2);
		System.out.println(median());
		add(0);
		System.out.println(median());
		add(1);
		System.out.println(median());
	}
	
	public static void add(int x) {
		if(minQueue.isEmpty()) { // If min heap is empty add to it
			minQueue.add(x);
		} else {
			if (minQueue.peek() <=x ) { // If element is greater than top of min heap add to it else add to max heap
				minQueue.add(x);
			} else {
				maxQueue.add(x);
			}
		}
		
		if (minQueue.size() > maxQueue.size()+1) { // If size of min heap is more than max heap by 1, move one element to max heap to balance the heaps
			maxQueue.add(minQueue.remove());
		} else if (maxQueue.size() > minQueue.size()) { // If max heap has more than min heap, move one element to other heap
			minQueue.add(maxQueue.remove());
		}
	}
	
	public static double median() {
		if (minQueue.size() == maxQueue.size() && !minQueue.isEmpty()) { // If size is equal then median is avg of both the tops
			return  (minQueue.peek() + maxQueue.peek()) * 0.5;
		} else {
			return  minQueue.peek(); // Else return top of min heap
		}
	}
	
	
}