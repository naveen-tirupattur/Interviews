package my.interviews2017.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRooms {

	public static void main(String[] args) {
		
		Interval[] intervals = new Interval[3];
		intervals[0] = new Interval(0,30);
		intervals[1] = new Interval(5,10);
		intervals[2] = new Interval(15,20);
		System.out.println(minMeetingRooms(intervals));
	}

	

	public static class Interval {
		public int start, end;
		Interval() { start = 0; end = 0; }
		public Interval(int s, int e) { start = s; end = e; }
	}
	
	public static class Point implements Comparable<Point> {
		public int time;
		public boolean isStart;
		
		public Point(int time, boolean isStart) {
			this.time = time;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Point o) { // Sort the points
			if (this.time != o.time) return Integer.compare(this.time, o.time); // If they occur at different times then sort based on time
			return (this.isStart && !o.isStart)?1:((!this.isStart && o.isStart)?-1:0); // If they occur at same time then starting point comes after end point
		}
	}
	
	public static int minMeetingRooms(Interval[] intervals) {
		int maximum = 0;
		List<Point> points = new ArrayList<Point>();
		for (int i=0;i<intervals.length;i++) {
			points.add(new Point(intervals[i].start, true));
			points.add(new Point(intervals[i].end, false));
		}
		int numCollisions = 0;
		Collections.sort(points);
		for (Point p: points) {
			if (p.isStart) { // Increment the counter
				numCollisions++;
				maximum = Math.max(numCollisions, maximum); // Compare it with global maximum
			} else {
				numCollisions--; // Decrement the counter
			}
		}
		return maximum;
	}
}
