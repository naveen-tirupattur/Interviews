package my.interviews2017;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RenderCalendar {

	public static void main(String[] args) {
		
		List<Event> eventsList = new ArrayList<Event>();
		eventsList.add(new Event(1,3));
		eventsList.add(new Event(2,4));
		eventsList.add(new Event(0,3));
		eventsList.add(new Event(5,7));
		eventsList.add(new Event(4,8));
		eventsList.add(new Event(7,10));
		eventsList.add(new Event(8,10));
		eventsList.add(new Event(6,10));
		eventsList.add(new Event(8,11));
		eventsList.add(new Event(11,12));

		System.out.println(maximumEvents(eventsList));
	}


	public static int maximumEvents(List<Event> events) {
		int maximum = 0, count=0;
		List<Point> points = new ArrayList<Point>();
		for (Event e: events) { // Create points in time with isStart identifier
			points.add(new Point(e.start, true));
			points.add(new Point(e.end, false));
		}
		
		Collections.sort(points); // Sort the points such that all start points come before end points if time is same
		for (Point p:points) {
			if (p.isStart) count++; // Keep updating the running count if point is a start point
			else {
				if (count > maximum) maximum = count; // Check if you have reached the maximum, if yes update the global maximum
				count--;
			}
		}
		return maximum;
	}
	
	public static class Event {
		int start,end;
		public Event(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	
	public static class Point implements Comparable<Point>{
		int time;
		boolean isStart;
		
		public Point(int time, boolean isStart) {
			this.time = time;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Point o) {
			if (this.time == o.time) { // If 2 points have same time pick the one which is a starting point
				if (this.isStart) return -1;
				else
					return 1;
			} else {
				return (o.time > this.time)?-1:1;
			}
		}
	}
}
