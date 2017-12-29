package my.interview.samples;

public class Queue {

	public LinkedListNode first, last;

	public void enQueue(int v) {
		if (first == null) {
			last = new LinkedListNode(v, null);

			first = last;
		} else {

			LinkedListNode n = new LinkedListNode(v, null);
			last.next = n;
			last = n;
		}
	}

	public int deQueue() {
		if (isEmpty())
			return -1;

		int v = first.value;

		first = first.next;

		return v;

	}

	public boolean isEmpty() {
		return (first == last && first == null);
	}

}
