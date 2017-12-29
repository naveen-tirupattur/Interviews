package my.interview.samples;

public class Dequeue {

	public LinkedListNode first, last;

	public void addEnd(int x) {
		if (first == null) {
			first = new LinkedListNode(x, null);
			last = first;
		} else {
			LinkedListNode temp = new LinkedListNode(x, null);
			last.next = temp;
			last = temp;
		}
	}

	public int removeFront() {
		if (first == null)
			return -1;
		LinkedListNode temp = first;
		first = first.next;
		return temp.value;
	}

	public void addFront(int x) {
		LinkedListNode temp = new LinkedListNode(x, null);
		temp.next = first;
		if (first == null) {
			last = temp;

		}
		first = temp;
	}

	public int removeEnd() {
		if (last == null)
			return -1;
		LinkedListNode temp = first;

		while (temp.next != null && temp.next != last) {
			temp = temp.next;
		}

		LinkedListNode temp1 = last;
		temp.next = null;
		last = temp;
		return temp1.value;

	}

	public void print() {
		LinkedListNode temp = first;
		while (temp != null) {
			System.out.println(temp.value);
			temp = temp.next;
		}
	}

}
