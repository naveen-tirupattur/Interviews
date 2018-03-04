package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TaskScheduler {

	public static void main(String[] args) {
		
		//char[] tasks = {'A','A','A','A','A','A','B','C','D','E'};
		char[] tasks = {'A','A','A','B','B','B'};
		System.out.println(leastInterval(tasks, 2));
	}
	
	public static int leastInterval(char[] tasks, int n) {
		int[] map = new int[26];
		int timer = 0;
		List<Integer> temp;
		// Store the tasks in a queue ordered by most occuring one first
		PriorityQueue<Integer> heap = new PriorityQueue<>(26, Collections.reverseOrder());  
		
		for (int i=0;i<tasks.length;i++) {
			map[tasks[i]-'A']++;
		}
		
		for (int i=0;i<map.length;i++) {
			if (map[i] > 0) heap.add(map[i]);
		}
		
		while (!heap.isEmpty()) {
			int i=0; temp = new ArrayList<>();
			while (i<=n) { // Keep doing till limit expires
				if (!heap.isEmpty()) { // If heap is not empty 
					if (heap.peek() > 1) { // Has more than 1 TODO then decrement 1 and add remaining to temporary list
						temp.add(heap.poll()-1);
					} else {
						heap.poll(); // only one left, finish it
					}
				}
				timer++; // heap is empty which means put an idle task
				if (heap.isEmpty() && temp.size() == 0) break; // Nothing to process in heap or in temp storage
				i++;
			}
			for (Integer e: temp) heap.add(e);
		}
		
		return timer;
		
	}

}
