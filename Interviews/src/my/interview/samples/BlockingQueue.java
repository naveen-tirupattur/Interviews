/**
 * 
 */
package my.interview.samples;

import java.util.LinkedList;

/**
 * @author naveenit7sep
 * @param <K>
 *
 */
public class BlockingQueue<K> {

	public LinkedList<K> queue;
	public final int MAX_SIZE = 5;

	public BlockingQueue() {
		queue = new LinkedList<K>();

	}

	public synchronized void enQueue(Object o) {
		try {

			while (queue.size() == MAX_SIZE) {
				wait();
			}

			if (queue.size() == 0) {
				notifyAll();
			}
			queue.addLast((K) o);
			System.out.println("added: " + o);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized Object dequeue() {
		try {
			while (queue.size() == 0) {
				wait();
			}

			if (queue.size() == MAX_SIZE) {
				notifyAll();
			}
			System.out.println("removed: " + queue.peekFirst());
			return queue.remove();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
