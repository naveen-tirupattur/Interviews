package my.interviews2017.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Skyline {

	public static void main(String[] args) {
		
		int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
		System.out.println(getSkyline(buildings));

	}

	public static List<int[]> getSkyline(int[][] buildings) {
		List<int[]> endPoints = new ArrayList<int[]>();
		if (buildings == null || buildings.length == 0
				|| buildings[0].length == 0) {
			return endPoints;
		}

		List<Point> points = new ArrayList<Point>();

		// add all left/right edges
		for (int[] building : buildings) {
			Point start = new Point(building[0], building[2], true);
			points.add(start);
			Point end = new Point(building[1], building[2], false);
			points.add(end);
		}

		Collections.sort(points); // Sort the points
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>(points.size(), Collections.reverseOrder()); 
		for (Point p:points) {
			if (p.isStart) { // Check if it is a starting point
				if (heap.isEmpty() || heap.peek() < p.height) { // If heap is empty or an element which is taller than this exists already which means this point will not be visible so don't add
					endPoints.add(new int[] {p.position, p.height});
				}
				heap.add(p.height); // Update the heap
			} else {
				heap.remove(p.height); // If it is an end point, remove the corresponding starting point from heap
				if (heap.isEmpty()) endPoints.add(new int[]{p.position, 0}); // If heap is empty which means you removed all points so close this out with new end point.
				else if (heap.peek() < p.height) endPoints.add(new int[]{p.position, heap.peek()}); // Check if a point smaller than this exists, if yes create a new end point with that height.
			}
		}

		return endPoints;
	}

	public static class Point implements Comparable<Point> {
		int position, height;
		boolean isStart;

		public Point(int position, int height, boolean isStart) {
			this.position = position;
			this.height = height;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Point o) {
			if (this.position == o.position) { // If both points have same position
				// Check if both are starting at that position, if yes then put the tallest one first
				if (this.isStart && o.isStart) { 
					return Integer.compare(o.height, this.height);
					/*
					 *  Check if both are ending at that position, if yes then put the smallest one first 
					 *  because this will not create a new end point in case of empty heap or if top of heap is greater than this point. 
					 *  The point that is after this ending at same position which is taller than this will take care of creating an end point.
					 *  For example if you have <2,7,15>, <2,7,10> if you place <7,10> before <7,15> 
					 *  then when you reach <7,10> you remove 10 from heap but 15 is still there so to avoid creating an end point it is important to place this point before point ending at 15
					 *    
					 */
				} else if (!this.isStart && !o.isStart) { 
					return Integer.compare(this.height, o.height);
				} else { // Place the point first which is a starting point among the two points
					return this.isStart ? -1:1;
				}
			} 
			return Integer.compare(this.position, o.position); // Place the points according to their position
		}
	}

}
