package my.interview.samples;

public class Threads2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Long> bQueue = new BlockingQueue<Long>();
		Consumer<Long> consumer = new Consumer<Long>(bQueue);

		consumer.run();

	}
}
