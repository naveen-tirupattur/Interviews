package my.interview.samples;



public class Threads {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BlockingQueue<Long> bQueue = new BlockingQueue<Long>();
		Producer<Long> producer = new Producer<Long>(bQueue);
		Consumer<Long> consumer = new Consumer<Long>(bQueue);
		
		try {
			producer.run();
			Thread.sleep(1000);
			consumer.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void test()
	{
		try
		{
			Thread.sleep(1000);
			testSync();
		}catch(InterruptedException i)
		{

		}

	}

	public static void testSync()
	{
		String s="new";
		synchronized (String.class) {
			try {
				Thread.sleep(1000);
				System.out.println(s+"updated"+Thread.currentThread());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
