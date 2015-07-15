package my.interview.samples;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ThreadProblems {

	/**
	 * Threads 1 to N execute a method critical. Before they
	 * can execute this they have to execute another method rendezvous. Only when all
	 * the threads have executed rendezvous, any thread can execute critical
	 * and they can call critical again for k+1th time only after all the
	 * threads have called it k times.
	*/
	
	// Object to store the count of each thread and number of threads
	public static class pObject {
		public int count;
		public int n;
		public Map<String, Integer> countMap = new HashMap<String, Integer>();
		public pObject(int n) {
			this.n = n;
		}

		// Check if the all the threads have called rendezvous 
		// and if other threads have called critical before making call again for current thread.
		public void critical() throws InterruptedException {
			int count = 0;
			
			// If other threads have not called rendezvous
			if(this.count < n) wait();
			
			// Get the count for the current thread's calls
			String threadName = Thread.currentThread().getName();
			if(countMap.containsKey(threadName)) {
				count = countMap.get(threadName);
			}
			
			// Update the count
			countMap.put(threadName, count+1);
			
			// Check the count of other thread's calls and wait
			for(String s: countMap.keySet()) {
				if(countMap.get(s) != count+1) wait();
			}
			
			System.out.println(threadName+" count in critical() "+ (count+1));
			notify();
		}

		public void rendezvous() {
			System.out.println(Thread.currentThread().getName()+" is in rendezvous()");
		}
	}

	public static class pThread implements Runnable {
		public pObject p; 
		public pThread(pObject p) {
			this.p = p;
		}

		@Override
		public void run() {
			try {
				while(true) {
					synchronized (p) { // Get a lock on the object
						p.rendezvous();
						p.count++; // Update the count of threads who made the rendezvous call
						p.critical();
					}
					Thread.sleep(new Random().nextInt(10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

		public static void testProducerConsumer() {
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
		
		/**
		 * @param args
		 */
		public static void main(String[] args) {

			pObject p = new pObject(4);
			Thread p1 = new Thread(new pThread(p));
			Thread p2 = new Thread(new pThread(p));
			Thread p3 = new Thread(new pThread(p));
			Thread p4 = new Thread(new pThread(p));
			
			p1.setName("p1");
			p2.setName("p2");
			p3.setName("p3");
			p4.setName("p4");

			p1.start();p2.start();p3.start();p4.start();
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
