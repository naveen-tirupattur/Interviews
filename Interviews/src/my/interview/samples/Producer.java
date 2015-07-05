/**
 * 
 */
package my.interview.samples;

/**
 * @author naveenit7sep
 *
 */
public class Producer<K> implements Runnable {

	private BlockingQueue<K> queue;

	public Producer(BlockingQueue<K> bQueue)
	{
		this.queue = bQueue;
	}

	@Override
	public void run() {

		try {

			for(int i=0;i<10;i++)
			{
				queue.enQueue(Long.valueOf(System.currentTimeMillis()));
				System.out.println("produced");
				Thread.sleep(10000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

