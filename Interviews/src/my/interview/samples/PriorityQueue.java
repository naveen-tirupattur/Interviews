package my.interview.samples;

public class PriorityQueue {

	int[] items;
	int size;
	boolean isMinHeap;

	public PriorityQueue(int count,boolean isMinHeap) {
		// TODO Auto-generated constructor stub
		items = new int[count];
		size = 0;
		this.isMinHeap = isMinHeap;
	}

	public void add(int x) throws Exception
	{
		if(items.length == size) throw new Exception("Priority Queue Full");
		else
		{
			items[size++] = x;
			makeHeap(size-1);
		}

	}

	public void makeHeap(int elementIndex)
	{
		//Get the parent element
		int p = getParent(elementIndex);

		//If it is not the first element
		if(p != -1)
		{
			if(isMinHeap)
			{
				//Check if element's parent is greater than element 
				if(items[p] > items[elementIndex])
				{
					//Swap the parent and element
					items[elementIndex] = items[elementIndex]^items[p];
					items[p] = items[elementIndex]^items[p];
					items[elementIndex] = items[elementIndex]^items[p];

					//Now check for the parent and it's parent
					makeHeap(p);
				}
			}else
			{
				//Check if element's parent is greater than element 
				if(items[p] < items[elementIndex])
				{
					//Swap the parent and element
					items[elementIndex] = items[elementIndex]^items[p];
					items[p] = items[elementIndex]^items[p];
					items[elementIndex] = items[elementIndex]^items[p];

					//Now check for the parent and it's parent
					makeHeap(p);
				}
			}
		}
	}

	public int remove() throws Exception
	{
		if(size==0) throw new Exception("Priority Queue empty");
		else
		{
			//Return the first element of the array
			int x = items[0];

			//Copy the last element of the array to first position and reduce the size
			items[0] = items[--size];

			heapify(0);

			return x;
		}
	}
	
	public int top()
	{
		if(size!=0) return items[0];
		
		if(isMinHeap) return Integer.MIN_VALUE;
		
		return Integer.MAX_VALUE;
	}
	
	public boolean isEmpty()
	{
		return (size==0);
	}

	public int getLeftChildIndex(int elementIndex)
	{
		return 2*elementIndex+1;
	}

	public int getRightChildIndex(int elementIndex)
	{
		return 2*elementIndex+2;
	}

	public void heapify(int elementIndex)
	{
		//Check if you have reached leaf level. If yes don't do anything
		if(!isLeaf(elementIndex))
		{
			int lc = getLeftChildIndex(elementIndex), rc= getRightChildIndex(elementIndex), elem_index = elementIndex;

			if(isMinHeap)
			{
				//Find the smallest element among the root and it's children
				if(items[elementIndex] > items[lc] || items[elementIndex] > items[rc])
				{
					if(items[elementIndex] > items[lc]) elem_index = lc;

					if(items[elem_index] > items[rc]) elem_index = rc;

					//Swap the element with the elem_index element
					items[elementIndex] = items[elementIndex]^items[elem_index];
					items[elem_index] = items[elementIndex]^items[elem_index];
					items[elementIndex] = items[elementIndex]^items[elem_index];

					heapify(elem_index);
				}
			}else
			{
				//Find the largest element among the root and it's children
				if(items[elementIndex] < items[lc] || items[elementIndex] < items[rc])
				{
					if(items[elementIndex] < items[lc]) elem_index = lc;

					if(items[elem_index] < items[rc]) elem_index = rc;

					//Swap the element with the elem_index element
					items[elementIndex] = items[elementIndex]^items[elem_index];
					items[elem_index] = items[elementIndex]^items[elem_index];
					items[elementIndex] = items[elementIndex]^items[elem_index];

					heapify(elem_index);
				}
			}
		}
	}

	public boolean isLeaf(int elementIndex)
	{
		return 2*elementIndex+1 >= size;
	}

	public int getParent(int elementIndex)
	{
		if(elementIndex == 0) return -1;

		return (elementIndex-1)/2;
	}
	
	public boolean isFull()
	{
		return (items.length==size);
	}

	public void print()
	{
		for(int i=0;i<size;i++)
		{
			System.out.println(items[i]);
		}
	}

}
