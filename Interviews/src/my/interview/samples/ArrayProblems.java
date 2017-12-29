package my.interview.samples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ArrayProblems {

	/**
	 * @param args
	 */

	public static int inversions = 0;
	public static int ways=0;
	public static void main(String[] args) {

		//int[] a = new int[]{1,0,1,0,0,1,1,0,1,1,0};
		//		int[] a = new int[]{5,2,1,3,4,6,9};
		//		insertionSort(a);
		//		for(int i=0;i<a.length;i++)
		//		{
		//			System.out.println(a[i]);
		//
		//		}
		//		int[][] matrix = new int[][]{{1,4,6,7},{3,5,8,9},{10,11,12,13},{13,14,15,16}};
		//		findKSmallestInMatrix(matrix, 6);

		//		int[] a = new int[]{11,12,14,14,16,16,7,7,7,7,8,9,10,10,10};
		//		System.out.println(findMaxElementInRotated(a));
		//		System.out.println(findMinElementInRotated(a));
		//		
		//int[] a = new int[]{1,3,6,8,9,10};
		//int[] b = new int[]{2,4,6,7,10,11};
		//System.out.println(findMedian(a, b, 0, a.length-1, 0, b.length-1));

		//		int[] v = new int[]{1,2,3,4,9,11};
		//		int[] w = new int[]{5,6,7,8};
		//		int x = 50;


		//System.out.println(reverse(x));
		//System.out.println(findKSmallest(v, w, 0, v.length-1, 0, w.length-1, 9));

		//int[] coins = new int[]{1,2,3};
		//makingChange(coins, 7);
		//findWays(coins,3,7);



		//		int[] v = new int[]{10,40,30,50};
		//		int[] w = new int[]{5,4,6,3};
		//		int W = 12;

		//System.out.println(knapsack(v, w, W, 4));

		//quickSelect(a);
		//		mergeSort(a, 0, a.length-1);


		//		int[] b = countingSort(a, 5);
		//		for(int i=0;i<b.length;i++)
		//		{
		//			System.out.println(b[i]);

		//		}

		//System.out.println(squareRoot(2));

		//int[] denom = new int[]{1,2,3};
		//solve(7, denom, 3);
		//reverse(2);
		//longestSubArray(a);
		//System.out.println(findMode(a));

		//		int[] input = {3,-5, 4};
		//		
		//		findCoefficients(input);
		//		int[] a = {1,2,3};
		//		printRandomSubset(a);
		//		int[] a = {3,4,5,6,9,10};
		//		int[] b = {6,7,8,11,15,17,20,25};
		//		System.out.println(findKSmallest(a, b, a.length, b.length, 8));

		//findDeepestPit();

		//findMinimalDifferenceUtil();

		//findMaximumSubArrayUtil();

		//findIntersectioUtil();
		//		String s = "naveen";
		//		System.out.println(s.intern());
		int[] a = {1,2,3,4};
		//permutations(a, 0);
	}

public static class Rectangle {

	int x,y;
	int length,breadth;

	public Rectangle(int x, int y, int length, int breadth) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.breadth = breadth;
	}

}

public static void findIntersectioUtil() {

	Rectangle r1 = new Rectangle(1, 1, 3, 3);
	Rectangle r2 = new Rectangle(2, 3, 3, 3);

	Rectangle intersectionRectangle = findIntersection(r1, r2);
	System.out.println("Found intersecting rectangle with co-ordinates: "+intersectionRectangle.x+","+intersectionRectangle.y
			+" with length: "+intersectionRectangle.length+" and breadth: "+intersectionRectangle.breadth);

}

public static Rectangle findIntersection(Rectangle r1, Rectangle r2) {

	if(r1.x <= r2.x && r2.x <= r1.x+r1.length && r1.y <= r2.y && r2.y <= r1.y + r1.breadth)
		return new Rectangle(Math.max(r1.x,r2.x), Math.max(r1.y, r2.y), Math.abs(r2.x+r2.length - (r1.x+r1.length)), Math.abs(r2.y+r2.breadth - (r1.y+r1.breadth)));
	else return new Rectangle(-1, -1, 0, 0);
}

public static void findMaximumSubArrayUtil() {

	int[] a = {1, 0, 0, 1, 1, 0, 0, 1, 1};
	findMaximumSubArray(a);
}

public static void findMaximumSubArray(int[] a) {

	Map<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
	int[] sumLeft = new int[a.length];
	int maxSize = -1, stIndex = 0;

	sumLeft[0] = (a[0]==0)?-1:1;
	positionMap.put(sumLeft[0], 0);
	for(int i=1;i<a.length;i++) {

		sumLeft[i] = sumLeft[i-1] + ((a[i]==0)?-1:1);

		// Case 1: Check the maximum size from first element, if first element starts with 0
		if(sumLeft[i] == 0) {
			int size = i+1;
			if(size > maxSize) {
				maxSize = size;
				stIndex = 0;

			}
		}

		// Case 2: Check the maximum size from any element.
		// Check if you have seen this count before, if not update the left most position
		if(!positionMap.containsKey(sumLeft[i])) {
			positionMap.put(sumLeft[i], i);
		} else {
			// If seen, get the left most position. The sub array would have started after the left most position. 
			// Check for the size of sub array and update the maximum size
			int leftPosition = positionMap.get(sumLeft[i])+1;
			int size = i-leftPosition+1;
			if(size > maxSize) {
				maxSize = size;
				stIndex = leftPosition;
			}
		}

	}

	if(maxSize == -1) 
		System.out.println("No subarray found");
	else
		System.out.println("Maximum sub array of size: "+ maxSize+ " from: "+stIndex+" to "+(maxSize+stIndex-1));
}

public static void findMinimalDifferenceUtil() {
	int[] a = {3,1,2,4,3};
	findMinimalDifference(a);
}

// Divide the array into 2 parts without rearrangement such that difference between these 2 parts is minimal
public static void findMinimalDifference(int[] a) {
	int sum = 0;
	for(int i=0;i<a.length;i++) {
		sum += a[i];
	}
	int min=Integer.MAX_VALUE,rsum = 0;
	for(int i=0;i<a.length;i++) {
		sum = sum-a[i];
		rsum = rsum+a[i];
		if( Math.abs(rsum-sum) < min)
			min = Math.abs(sum-rsum);
	}

	System.out.println("Minimum difference: "+min);
}


public static void findDeepestPit() {
	//		int[] a = {0,1,3,-2,0,1,0,-3,2,3};
	int[] a = {6,5,4,3,2,0,1,2,3,4,5,6};
	findDeepestPit(a);
}

public static void findDeepestPit(int[] a) {

	int maxDepth = 0, p = Integer.MIN_VALUE, r = Integer.MIN_VALUE;
	// Start from 2nd element till n-1
	for(int i = 1;i<a.length-1;i++) {
		// Check if condition satisfies on left side
		if(a[i-1]>a[i]) {
			// Check if left end is more than current left end
			if(a[i-1] > p)
				p = a[i-1];
		} else {
			p = Integer.MIN_VALUE;
		}

		// Check if condition satisfies on right side
		if(a[i+1]>a[i]) {
			// Check if right end is more than current right end
			if(a[i+1] > r)
				r = a[i+1];
		} else {
			r = Integer.MIN_VALUE;
		}

		// If both right and left end are valid, check the depth
		if(p != Integer.MIN_VALUE && r!= Integer.MIN_VALUE) {
			int d = Math.min(p-a[i], r-a[i]);
			if(d >= maxDepth) maxDepth = d;
		}
	}

	System.out.println("Deepest pit is of depth: "+maxDepth);
}

// Event class
public static class Event implements Comparable<Event> {
	long startTime;
	long endTime;

	public Event(long startTime, long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Event o) {

		if(o.startTime > this.startTime)
			return -1; 
		else if(o.startTime == this.startTime) 
			return 0;
		else
			return 1;
	}
}

public static void findMostBusiestTimeslot() {

	List<Event> events = new ArrayList<Event>();
	events.add(new Event(1,3));
	events.add(new Event(2,4));
	events.add(new Event(1,5));
	events.add(new Event(6,8));
	events.add(new Event(6,7));
	events.add(new Event(5,6));
	events.add(new Event(5,7));

	//Sort the events
	Collections.sort(events);
	findMostBusiestTimeslot(events);

}

public static void findMostBusiestTimeslot(List<Event> events) {

	// Keep track of most busiest slot seen so far
	int mostBusiest = 0, tempMostBusiest = 0;
	// This is the busiest slot so far
	Event overlappingSlot = new Event(Long.MIN_VALUE, Long.MAX_VALUE);
	// Most busiestSlot
	Event busiestSlot = new Event(0,0);

	for(int i=0;i<events.size();i++) {
		Event e = events.get(i);
		// Check if the events overlap
		if(e.startTime < overlappingSlot.endTime) {
			// Update the overlapping slot
			tempMostBusiest++;
			overlappingSlot.startTime = e.startTime;
			overlappingSlot.endTime = Math.min(overlappingSlot.endTime, e.endTime);

		} else{ //if not
			if(tempMostBusiest > mostBusiest) {
				// Check the busiest seen so far and update the new overlapping slot
				mostBusiest = tempMostBusiest;
				busiestSlot.startTime = overlappingSlot.startTime;
				busiestSlot.endTime = overlappingSlot.endTime;
				overlappingSlot.startTime = e.startTime;
				overlappingSlot.endTime = e.endTime;
			}
		}

	}

	System.out.println("Most busiest event: "+busiestSlot.startTime+" "+busiestSlot.endTime);
}


//TODO - Find k smallest element in 2 sorted arrays
public static int findKSmallest(int[] a, int[] b, int m, int n, int k) {

	if(m>n) return findKSmallest(b, a, n, m, k);

	// Reached end of smaller array
	if(m==0 && n > 0) return b[k-1];

	// Exit condition
	if(k==1) return Math.min(a[k-1], b[k-1]);

	// Pick the indexes to maintain the invariant i+j = k;
	int i = Math.min(m, k/2);
	int j = Math.min(n, k/2);

	// Check if element i is less than element j then we can ignore the left of A and right of B
	if(a[i-1] < b[j-1]) {
		int[] tempA = Arrays.copyOfRange(a,i,m);
		int[] tempB = Arrays.copyOfRange(b, 0,j);
		findKSmallest(tempA, tempB, tempA.length, tempB.length, k-i);
	}else { // Else ignore the right of A and left of B
		int[] tempA = Arrays.copyOfRange(a,0,i);
		int[] tempB = Arrays.copyOfRange(b,j,n);
		findKSmallest(tempA, tempB, tempA.length, tempB.length, k-j);
	}

	return -1;
}

public static void printArray(int[] array) {
	for(int i=0;i< array.length;i++) {
		System.out.print(array[i]+" ");
	}
}

// Print a random subset
public static void printRandomSubset(int[] a) {
	Random random = new Random();
	// TODO - For each position in subset, flip a coin to see if the element can be added or not at this position 
}

// Print a random permutation
public static int[] printRandomPermutation(int[] a) {
	// For each position in the array, find a random number in range 0 to n-1 
	// and swap it with the element at that position
	Random r = new Random();
	for(int i=0;i<a.length;i++) {
		int random = r.nextInt(a.length-1);
		int swap = a[i];
		a[i] = a[random];
		a[random] = swap;
	}	
	return a;
}

public static void findCoefficients(int[] input){
	int[] output = new int[input.length+1];

	output[0] = 1;

	for(int i = 0;i<input.length;i++) {

		calculateCoefficients(output, input[i]); 
	}

	printOutput(input, output);
}

public static void printOutput(int[] inputArr, int[] outputArr) {
	System.out.println("Input: " + java.util.Arrays.toString(inputArr));
	System.out.println("Output: " + java.util.Arrays.toString(outputArr));
	System.out.println("---------------------------------------");
}

public static void calculateCoefficients(int[] output, int coefficient) {
	int[] shiftedArray = shiftArray(output);
	int[] scaledArray = scaleArray(output, coefficient);

	for(int i=0;i<output.length;i++) {
		output[i] = shiftedArray[i];
		output[i] += scaledArray[i];
	}
}

public static int[] shiftArray(int[] output) {
	int[] shiftedArray = new int[output.length];
	for(int i=1;i<output.length;i++) {
		shiftedArray[i] = output[i-1];
	}
	return shiftedArray;
}

public static int[] scaleArray(int[] output, int coefficient) {
	int[] scaledArray = new int[output.length];
	for(int i=0;i<output.length;i++) {
		scaledArray[i] = output[i] * coefficient;
	}
	return scaledArray;
}

public static boolean findElementInSortedMatrix(int[][] a, int k)
{
	int i = 0, j=a[0].length-1;
	while(i<a.length && j>=0)
	{
		if(a[i][j] == k) return true;
		if(a[i][j] > k) j--;
		else i++;
	}

	return false;
}

public static void findSmallestElement(int[] a)
{

	Stack s = new Stack();
	int[] b = new int[a.length];
	for(int i=a.length-1;i>=0;i--)
	{
		if(s.isEmpty())
		{
			b[i] = 0;
			s.push(a[i]);
			continue;
		}

		while(!s.isEmpty() && s.peek() > a[i])
			s.pop();

		b[i] = (s.isEmpty())?0:s.peek();
		s.push(a[i]);
	}

	for(int i=0;i<b.length;i++)
	{
		System.out.println(b[i]);
	}
}

public static void longestSubArray(int[] a)
{
	int sum = 0,maxSt = -1, maxEnd = -1, maxLen = 0;
	Integer[] hash = new Integer[a.length*2+1];
	hash[a.length] = -1;
	//		for(int i=0;i<hash.length;i++)
	//		{
	//			hash[i] = -1;
	//		}

	for(int i=0;i<a.length;i++)
	{
		if(a[i]==0) sum--;
		else
			sum++;
		Integer prevIndex = hash[a.length+sum];
		if(prevIndex==null)
		{
			hash[a.length+sum] = i;
		}else
		{

			if(i-prevIndex > maxLen)
			{
				maxLen = i - prevIndex;
				maxSt = prevIndex+1;
				maxEnd = i;
			}
		}
	}

	System.out.println("longest sub array is of length: "+maxLen+" starting at index: "+maxSt+" ending at index: "+maxEnd);
}
public static int reverse(int x)
{
	System.out.println(Integer.toBinaryString(x));
	int b = 0;
	while (x!=0){
		b<<=1;
		b|=(x&1);
		x>>=1;
	}
	System.out.println(Integer.toBinaryString(b));
	return b;
}



public static void findWays(int[] denom, int count, int sum)
{
	int[][] ways = new int[sum+1][count];

	for(int i=0;i<=sum;i++)
	{
		for(int j=0;j<count;j++)
		{
			if(i==0){
				ways[i][j] = 1;
				continue;
			}
			int x = 0;
			if(i-denom[j]>=0)
			{
				x = ways[i-denom[j]][j];
			}

			int y = 0;
			if(j>=1)
			{
				y = ways[i][j-1];
			}

			ways[i][j] = x+y;
		}
	}

	System.out.println(ways[sum][count-1]);
}


public static float sqrt(float n) 
{ 
	float low=0,high=n; 
	float mid=(high+low)/2; 
	while(Math.abs(mid*mid-n)>0.00001)
	{ 
		if(mid*mid<n) 
			low=mid; 
		else if(mid*mid>n) 
			high=mid; 
		mid=(low+high)/2; 
	} 
	return mid; 
}

public static int reverseNum(int a)
{
	int reversed = 0;

	while (a > 0)
	{
		reversed = reversed * 10 + a % 10;
		a /= 10;
	}
	return reversed;


}

public static int findMissingElement(int[] a, int[] b)
{
	int num=0;
	for(int i=0;i<a.length;i++)
		num^=a[i];

	for(int j=0;j<b.length;j++)
	{
		num^=b[j];
	}
	return num;
}

public static double findMedian(int[] a,int[] b,int l1,int h1,int l2,int h2)
{
	//Check if both the arrays are of same size
	if(h1-l1+1 != h2-l2+1) return -1;

	//Exit condition
	if(h1<l1 || h2<l2) return -1;

	//one element each in the array
	if(l1==h1) return (double)(a[l1]+b[l2])/2;

	//When there are 2 elements each in sorted array
	if(h1-l1+1==2)
	{
		return (Math.max(a[l1],b[l2]) + Math.min(a[h1], b[h2]))/2.0;
	}

	double median1=0,median2=0;
	int m1 = l1+(h1-l1)/2;
	int m2 = l2+(h2-l2)/2;

	//if size of array is even
	if((h1-l1+1)%2==0)
	{
		median1 = (double)(a[m1]+a[m1+1])/2.0;
		median2 = (double)(b[m2]+b[m2+1])/2.0;
	}else
	{
		median1 = a[m1];
		median2 = b[m2];
	}

	if(median1 == median2) return median1;

	if(median1 > median2)
	{
		if((h1-l1+1)%2==0)
			return findMedian(a, b, l1, m1, m2+1, h2);
		else
			return findMedian(a,b,l1,m1,m2,h2);
	}
	else
	{
		if((h1-l1+1)%2==0)
			return findMedian(a, b, m1+1, h1, l2, m2);
		else
			return findMedian(a, b, m1, h1, l2, m2);
	}

}
public static int[] countingSort(int[] a, int k)
{
	int[] b = new int[a.length];
	int[] c = new int[k+1];

	for(int i=0;i<a.length;i++)
	{
		c[a[i]]++;
	}

	int j = 0;
	for(int i=0;i<=k;i++)
	{
		while(c[i]!=0)
		{
			b[j++] = i;
			c[i]--;
		}
	}
	return b;
}

public static void findKSmallestInMatrix(int[][] a, int k)
{
	for(int i=0;i<k-1;i++)
	{
		findKSmallestInMatrix(a,0,0);
	}

	System.out.println(a[0][0]);
}

public static void findKSmallestInMatrix(int[][] a, int row, int col)
{
	int newRow = 0,newCol=0;
	if(row==a.length-1 && col == a[0].length-1)
	{
		a[row][col] = Integer.MAX_VALUE;
		return;

	}else if(row==a.length-1)
	{
		newRow = row;
		newCol = col+1;
	}else if(col == a[0].length-1)
	{
		newRow = row+1;
		newCol = col;
	}else if(a[row][col+1] <= a[row+1][col])
	{
		newRow = row;
		newCol = col+1;
	}else
	{
		newRow = row+1;
		newCol = col;
	}

	a[row][col] = a[newRow][newCol];
	findKSmallestInMatrix(a,newRow,newCol);
}


public static int[] add(int[] a, int[] b)
{
	int carry = 0;
	int[] c = new int[a.length+1];
	int j =0;
	for(int i=0;i<a.length;i++)
	{	
		int sum = a[i]+b[i]+carry;
		c[j] = sum%2;
		carry = sum/2;
		j++;
	}
	c[j] = carry;

	return c;
}

public static void quickSelect(int[] a)
{
	System.out.println(quickSelect(a, 0, a.length-1,11));		
}

public static int quickSelect(int[] a, int low,int high,int k)
{
	if(low>=high) return a[low];

	int pivotIndex = low+(high-low)/2;
	int pivot = partition(a,low,high,pivotIndex);

	if(pivot == k-1)
	{
		return a[pivot];
	}

	if(pivot > k-1)
	{
		return quickSelect(a, low, pivot-1, k);
	}else
	{
		return quickSelect(a,pivot+1,high,k);
	}
}


public static int power(int a, int b)
{
	boolean isNeg=false;
	if(b == 0 )return 1;

	if(b == 1) return a;

	if(b < 0) {
		isNeg = true;
		b *= -1;
	}
	int result = 1;
	while(b>0)
	{
		if((b&1)==1)
			result *= a;
		a*= a;
		b = b>>1;
	}

	if (isNeg) result = 1/result;
	return result;
}

public static double getMedian(int[] a,int n)
{
	if(n%2==0){
		return (double)(a[n/2]+a[n/2-1])/2;
	}
	else
		return (double)a[n/2];

}

public static int findMaxElementInRotated(int[] a)
{
	int low = 0,high = a.length-1;

	if(a[low]<a[high]) return a[high];

	while(low <= high)
	{
		if(low==high) return a[low];

		int mid = low + (high-low)/2;

		if(a[mid]>a[mid+1] && mid < high) return a[mid];

		if(a[mid]<a[mid-1] && mid > low) return a[mid-1];

		if(a[mid]<a[high]) high = mid - 1;

		else low = mid + 1;
	}
	return -1;
}

public static int findMinElementInRotated(int[] a)
{
	int low =0,high=a.length-1;

	while(low <=high)
	{
		if(low == high) return a[low];

		int mid = low + (high-low)/2;

		if(a[mid] > a[mid+1]) return a[mid+1];

		if(a[mid] < a[mid-1]) return a[mid];

		if(a[mid] < a[high]) high = mid-1;
		else
			low = mid+1;
	}
	return -1;
}


public static int findMode(int[] a)
{
	int i = a.length-1,max_so_far = 1,tempMode=a[i],mode=a[i],max_count=0;
	while(i > 0)
	{
		if(a[i-1]==a[i])
		{
			max_so_far++;
		}else
		{
			max_so_far=1;
			tempMode = a[i-1];
		}

		if(max_so_far > max_count)
		{
			max_count = max_so_far;
			mode = tempMode;
		}
		i--;
	}
	System.out.println(mode);
	return max_count;
}

public static int findCount(int[] a, int k)
{
	int high = findRight(a,0,a.length-1,k);

	int low = findLeft(a, 0, a.length-1, k);

	if(low == -1 || high == -1) return 0;

	return high-low+1;
}

public static int findRight(int[] a,int low,int high,int k)
{
	while(low<=high)
	{
		int mid = low + (high-low)/2;

		if((mid==a.length-1 || k < a[mid+1]) && a[mid]==k) return mid;

		if(k<a[mid]) high = mid - 1;

		else low = mid +1;
	}
	return -1;

}

public static int findLeft(int[] a,int low,int high,int k)
{
	while(low<=high)
	{
		int mid = low + (high-low)/2;

		if((mid==0 || a[mid-1]<k) && a[mid]==k) return mid;

		if(a[mid]<k) low = mid + 1;

		else high = mid -1;
	}
	return -1;

}



public static int findElementI(int[] a)
{
	int low = 0,high = a.length-1;
	while(low <= high)
	{
		int mid = low + (high - low)/2;
		if(a[mid]==mid) return mid;
		if(a[mid] < mid) low = mid +1;
		else high = mid -1;

	}
	return -1;
}

public static int findMajorityElement(int[] a)
{
	int majIndex = 0,count=0;

	for(int i=0;i<a.length;i++)
	{
		if(count == 0) majIndex = i;

		if(a[i]==a[majIndex]) 
			count++;
		else
			count--;
	}

	count = 0;
	for(int i=0;i<a.length;i++)
	{
		if(a[i]==a[majIndex]) count++;

		if(count > a.length/2) return a[majIndex];
	}

	return -1;
}

public static int binarySearch(int[] a,int low, int high,int k)
{
	int mid=0;

	if(low > high) return -1;

	mid = low + (high-low)/2;

	if(a[mid] == k) return mid;

	if(a[mid] < k) 
		return binarySearch(a,mid+1,high,k);
	else 
		return binarySearch(a,low,mid-1,k);
}

public static int[] findIndexes(int[] a,int x)
{
	int[] k = new int[2];

	int index = binarySearch(a,0,a.length-1,x);

	int stIndex = index-1,endIndex = index+1;

	while(stIndex >=0 && (index = binarySearch(a,0,stIndex,x) )!=-1)
	{
		stIndex = index-1;
	}

	while(endIndex <a.length-1 && (index = binarySearch(a,endIndex,a.length-1,x)) !=-1)
	{
		endIndex = index+1;
	}

	k[0] = stIndex+1;
	k[1] = endIndex - 1;

	return k;
}

public static void swap(int[] a,int x,int y)
{
	int temp = a[x];
	a[x] = a[y];
	a[y] = temp;
}

public static void bubbleSort(int[] a)
{
	int n=a.length;
	for(int i=0;i<n;i++)
	{
		for(int j=1;j<n-i;j++)
		{
			if(a[j-1] > a[j]) swap(a, j-1, j);
		}
	}
}
public static void insertionSort(int[] a){
	for(int i=1;i<a.length;i++)
	{
		int temp = a[i];
		while(i > 0 && temp < a[i-1])
		{
			a[i] = a[i-1];
			i = i-1;
		}
		a[i] = temp;
	}
}

public static void selectionSort(int[] a)
{

	for(int i=0;i<a.length-1;i++)
	{
		for(int j=i+1;j<a.length;j++)
		{
			if(a[i]>a[j]){

				swap(a,i,j);

			}
		}
	}
}

public static void mergeSort(int[] a, int low, int high){

	int mid = 0;
	if(low < high)
	{
		mid = low + (high - low)/2;
		mergeSort(a, low, mid);
		mergeSort(a, mid+1, high);
		merge(a,low,mid,high);
	}
}
public static void merge(int[] a,int low,int mid,int high)
{

	// Copy both parts into the helper array
	int[] b = new int[a.length];
	for (int i = low; i <= high; i++) {
		b[i] = a[i];
	}
	int i=low,j=mid+1,k=low;
	while(i<=mid && j <=high)
	{
		if (b[i] <= b[j]){
			a[k] = b[i];
			i++;
		}else
		{
			a[k]= b[j];
			j++;
			inversions += mid-i+1;
		}
		k++;
	}

	while( i <= mid)
	{
		a[k]=b[i];
		k++;
		i++;
	}

	while(j<=high)
	{
		a[k++] = b[j++];
	}


}

public static int partition(int[] a,int low, int high,int pivotIndex)
{
	int pivotValue = a[pivotIndex];
	swap(a,pivotIndex,high);
	int storeIndex = low;
	for(int i=low;i<high;i++)
	{
		if(a[i]< pivotValue) 
		{
			swap(a,i,storeIndex);
			storeIndex++;
		}
	}
	swap(a,storeIndex,high);

	return storeIndex;
}



public static void quickSort(int[] a,int low,int high)
{
	if(low>=high) return;

	int pivotIndex = partition(a,low,high,low);
	quickSort(a,low,pivotIndex);
	quickSort(a,pivotIndex+1,high);
}

public static int findElementInRotated(int[] a,int k)
{
	int l = 0,h = a.length-1;
	while(l <= h)
	{
		int m = l+(h-l)/2;

		if (a[m]==k) return m;

		//If first half of array is sorted
		if(a[l] < a[m])
		{
			//check if k is in between low and middle
			if(a[l] <= k && k < a[m])
			{
				h = m-1;
			}else
			{
				l = m+1;
			}
		}
		else
		{
			if(a[m] < k && k <= a[h])
			{
				l = m+1;
			}else
			{
				h = m-1;
			}
		}
	}
	return -1;
}

public static void rotateBy90(int[][] matrix)
{
	//Do a transpose of the matrix
	for(int i=0;i<matrix.length;i++)
	{
		for (int j=0;j<matrix[0].length;j++)
		{
			if(i<j){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}

	//Reverse each row
	for(int i=0;i<matrix.length;i++)
	{

		int low = 0,high = matrix[0].length-1;
		while( low < high)
		{
			int temp = matrix[i][low];
			matrix[i][low] = matrix[i][high];
			matrix[i][high] = temp;
			low++;
			high--;
		}
	}

	for(int i=0;i<matrix.length;i++)
	{
		for (int j=0;j<matrix[0].length;j++)
		{
			System.out.print(" "+matrix[i][j]+" ");
		}
		System.out.println("\n");
	}
}

}