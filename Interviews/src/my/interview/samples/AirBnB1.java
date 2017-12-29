package my.interview.samples;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

/*
 * Implement an iterator for list of lists with the following methods:
 * hasNext
 * next
 * remove

 [[1,2],[3,4],[5,6]]
 [[1,2],[3,4],[],[],[5,6]]

 */

class ListIterator {

	// The data structure
	private List<List<Integer>> dList = new ArrayList<List<Integer>>();

	// Iterators for parent and child lists
	Iterator parentIterator;
	Iterator childIterator;

	public ListIterator(List<List<Integer>> dList) {

		this.dList = dList;
		this.parentIterator = dList.iterator();

	}

	public boolean hasNext() {
		// Check if child iterator is initialized
		if (childIterator != null)
			return childIterator.hasNext();

		if (parentIterator.hasNext()) {

			// Get the next child
			List l = (List) parentIterator.next();

			// if(childIterator== null)
			// childIterator = l.iterator();

			// If the parent has more children
			if (l != null)
				childIterator = l.iterator();
			else
				return false;

			return childIterator.hasNext();
		}

		return false;
	}

	public Object next() {
		// Check if child iterator is initialized
		if (childIterator != null && childIterator.hasNext())
			return childIterator.next();

		if (parentIterator.hasNext()) {

			// Get the next child
			List l = (List) parentIterator.next();

			// If the parent has more children
			if (l != null)
				childIterator = l.iterator();
			else
				return null;

			return childIterator.next();
		}

		return null;

	}

	public void remove() throws Exception {
		// Check if child iterator is initialized
		if (childIterator != null) {
			childIterator.remove();
			return;
		}

		if (parentIterator.hasNext()) {

			// Get the next child
			List l = (List) parentIterator.next();

			// If the parent has more children
			if (l != null)
				childIterator = l.iterator();
			else
				return;

			childIterator.remove();
			return;
		}

	}

}

class AirBnB1 {
	public static void main(String[] args) {

		List<List<Integer>> dataList = new ArrayList<List<Integer>>();

		// [[1,2],[3,4],[5,6]]
		// [[1,2],[3,4],[],[],[5,6]]

		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);

		List<Integer> list2 = new ArrayList<Integer>();
		list1.add(3);
		list1.add(4);

		List<Integer> list3 = new ArrayList<Integer>();
		list1.add(5);
		list1.add(6);

		List<Integer> list4 = new ArrayList<Integer>();
		List<Integer> list5 = new ArrayList<Integer>();

		dataList.add(list1);
		dataList.add(list2);
		dataList.add(list4);
		dataList.add(list5);
		dataList.add(list3);

		ListIterator i = new ListIterator(dataList);

		System.out.println(i.next());
		System.out.println(i.next());

		try {
			i.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(i.next());

		System.out.println("New Iterator");
		ListIterator newI = new ListIterator(dataList);
		while (newI.hasNext()) {
			System.out.println(newI.next());
		}

	}
}
