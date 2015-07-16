package my.interview.samples;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


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

	public static void runThreads() {
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

	// Class to store the counter. 

	/** I tried by just using an Integer object, but Integer is immutable.
	 *  Every time I made a change to the object, a new object is created and the lock would still be with old object. 
	 *  This caused IllegalMonitorStateException when ever I invoked wait() and notify().
	 */
	public static class Counter {

		public int value;

		public Counter(int value) {
			this.value = value;
		}

	}

	public static class evenThread implements Runnable {

		public Counter counter;

		public evenThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			try {
				synchronized (counter) { // Get a lock on the counter object
					while(counter.value <=100) { // Check if condition matches
						if(counter.value%2==0){ // Check if it is even or odd
							System.out.println(counter.value++); // Print and update the value
							counter.notifyAll(); // Notify the other waiting threads
						} else {
							counter.wait(); // Wait for the lock
						}
					}
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static class oddThread implements Runnable {

		public Counter counter;

		public oddThread(Counter counter) {
			this.counter = counter;
		}

		@Override
		public void run() {
			try {
				synchronized (counter) { // Get a lock on the counter object
					while(counter.value <=100) { // Check if condition matches
						if(counter.value%2!=0){ // Check if it is even or odd
							System.out.println(counter.value++); // Print and update the value
							counter.notifyAll(); // Notify the other waiting threads
						} else {
							counter.wait(); // Wait for the lock
						}
					}
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// Print 1 to 100, using 2 threads. One thread should print even numbers and other thread should print odd numbers
	public static void printEvenOdd() {

		Counter c = new Counter(1);

		Thread t1 = new Thread(new oddThread(c));
		Thread t2 = new Thread(new evenThread(c));

		t1.start(); t2.start();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		printEvenOdd();
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
