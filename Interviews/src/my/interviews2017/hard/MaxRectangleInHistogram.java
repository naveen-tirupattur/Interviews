package my.interviews2017.hard;

import java.util.Stack;

public class MaxRectangleInHistogram {

	public static void main(String[] args) {
		int hist[] = { 3, 2, 4, 2, 1, 4, 3 };
    System.out.println("Maximum area is " + getMaximum(hist));

	}
	
	public static int getMaximum(int[] heights) {
		int maximum = 0;
		Stack<Integer> stack = new Stack<Integer>();  // Store the bars of histogram in stack in way that elements are always in sorted order
		int i=0;
		while (i < heights.length) { // Iterate over all bars in histogram
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) stack.push(i++); // If stack is empty or top of stack is less than current element add it to stack
			else { // Because there could be more tall elements after this and they can contribute to larger rectangle
				int top = stack.pop(); // If not the current max cannot contribute anymore to larger rectangle so remove it
				int area = heights[top] * (stack.isEmpty()?i:i-stack.peek()-1); // Get the starting and ending elements and compute the area between them and check if it's maximum
				maximum = Math.max(area, maximum);
			}
		}
		
		while(!stack.isEmpty()) { // Compute the array for remaining elements
			int top = stack.pop();
			int area = heights[top] * (stack.isEmpty()?i:i-stack.peek()-1); // If no element exists in stack it means this element is smallest and this should extend all the way till "i"
			maximum = Math.max(area, maximum);
		}
		
		return maximum;
		
	}

}
