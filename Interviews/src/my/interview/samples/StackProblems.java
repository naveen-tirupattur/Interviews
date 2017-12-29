package my.interview.samples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StackProblems {

	public static int size = 10;
	public static int[] array = new int[3 * size];
	public static int[] ptrs = new int[] { -1, -1, -1 };
	public static List<Stack> stackOfStacks = new ArrayList<Stack>();
	public static Stack s1 = new Stack();
	public static Stack s2 = new Stack();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// sort();
		//
		// System.out.println(s2.peek());

		StackFromQueue s = new StackFromQueue();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);

		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());

	}

	public static class StackFromQueue {
		java.util.Queue<Object> q1, q2;

		public StackFromQueue() {
			q1 = new LinkedList<>();
			q2 = new LinkedList<>();
		}

		public void push(int x) {
			q2.add(x);
			while (!q1.isEmpty()) {
				q2.add(q1.remove());
			}
			Queue<Object> tempQ = q1;
			q1 = q2;
			q2 = tempQ;
		}

		public int pop() {
			return (int) q1.remove();
		}

	}

	public static void sort() {
		s1.push(10);
		s1.push(30);
		s1.push(20);
		s1.push(40);
		s1.push(5);

		while (!s1.isEmpty()) {
			int v = s1.pop();

			while (!s2.isEmpty() && s2.peek() > v) {
				s1.push(s2.pop());
			}

			s2.push(v);
		}

	}

	public static void myQueue() {
		myQPush(10);
		myQPush(20);
		myQPush(30);

		System.out.println(myQPop());

		myQPush(40);
		myQPush(50);

		System.out.println(myQPop());

	}

	public static void myQPush(int v) {
		s1.push(v);
	}

	public static int myQPop() {
		if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		return s2.pop();
	}

	public static void minStack() {
		minPush(10);
		minPush(3);
		minPush(1);
		minPop();
		minPush(2);
		minPop();

		System.out.println("Minimum: " + findMin());
	}

	public static int findMin() {
		if (s2.isEmpty())
			return Integer.MAX_VALUE;

		return s2.peek();
	}

	public static void minPush(int v) {

		if (v <= findMin()) {
			s2.push(v);
		}
		s1.push(v);

	}

	public static int minPop() {

		int v = s1.pop();

		if (findMin() == v)
			s2.pop();

		return v;

	}

	public static void stackOfStacks() {
		sofsPush(10);
		sofsPush(20);
		sofsPush(30);
		sofsPush(40);
		sofsPush(50);
		sofsPush(60);
		sofsPush(70);
		sofsPush(80);
		sofsPush(90);
		sofsPop();
		sofsPop();
		sofsPop();
		sofsPop();
		sofsPop();

		System.out.println("Stack of Stacks Size: " + stackOfStacks.size());

	}

	public static void sofsPush(int v) {
		Stack s = getLastStack();

		if (s == null || s.isFull()) {

			Stack t = new Stack();
			t.push(v);
			stackOfStacks.add(t);
		} else {
			s.push(v);
		}

	}

	public static Stack getLastStack() {
		if (stackOfStacks.size() == 0)
			return null;

		return stackOfStacks.get(stackOfStacks.size() - 1);
	}

	public static int sofsPop() {
		Stack s = getLastStack();
		if (s == null)
			return -1;
		int v = s.pop();

		if (s.isEmpty()) {
			stackOfStacks.remove(s);
		}

		return v;
	}

	public static void array3Stacks() {
		push(0, 10);
		push(1, 10);
		push(2, 20);
		push(1, 20);
		pop(1);
		pop(0);
		push(0, 30);
		pop(2);

		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
	}

	public static void push(int stackNum, int v) {
		if (ptrs[stackNum] + 1 >= size)
			return;

		ptrs[stackNum]++;

		int pos = findPos(stackNum);
		array[pos] = v;

	}

	public static int pop(int stackNum) {
		if (ptrs[stackNum] == -1)
			return -1;

		int pos = findPos(stackNum);

		ptrs[stackNum]--;

		return array[pos];
	}

	public static int findPos(int stackNum) {

		return stackNum * size + ptrs[stackNum];
	}

}
