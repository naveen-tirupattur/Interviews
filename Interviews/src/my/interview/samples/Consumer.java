/**
 * 
 */
package my.interview.samples;

/**
 * @author naveenit7sep
 *
 */
public class Consumer<K> implements Runnable {

	private BlockingQueue<K> queue;

	public Consumer(BlockingQueue<K> bQueue)
	{
		this.queue = bQueue;
	}

	@Override
	public void run() {

		try {
			for(int i=0;i<10;i++)
			{
				System.out.println("Consumed: "+queue.dequeue());
				Thread.sleep(10000);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
