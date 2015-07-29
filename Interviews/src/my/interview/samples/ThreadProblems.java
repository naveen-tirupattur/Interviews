package my.interview.samples;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeMap;


public class ThreadProblems {

	/**
	 * Implement a timer class.
	 * A method which accepts a String (name) and a Thread(Object) to store the tasks to manage 
	 * A start method which starts a thread with the matching name
	 * A cancel method to remove a thread with the matching name if it is'nt started already
	 */

	public static void testTimer() {

		final Timer t = new Timer();

		Runnable timerThread = new Runnable() {

			@Override
			public void run() {
				final long startTime = System.currentTimeMillis();
				while(true) {
					synchronized (t) {
						try {
							if(t.getObjHeap().isEmpty())
								t.wait();
							else {
								Obj o = t.getObjHeap().peek();
								long diff = (System.currentTimeMillis() - startTime - o.startTime);
								if(diff > 0) 
									Thread.sleep(diff);
								else if(diff == 0) {
									new Thread(o).start();
									t.getObjHeap().remove();
								}
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
				}
			}
		};

		new Thread(timerThread).start();

		Runnable task = new Runnable() {

			@Override
			public void run() {
				synchronized (t) {
					t.add("t1", new Obj("t1",10));
					t.add("t2", new Obj("t2", 5));
					t.add("t3", new Obj("t3", 13));
					t.add("t4", new Obj("t4", 8));
					t.notifyAll();
				}
			}

		};

		new Thread(task).start();

	}

	public static class Timer {

		public Map<String, Obj> objMap;
		public java.util.PriorityQueue<Obj> objHeap;

		public java.util.PriorityQueue<Obj> getObjHeap() {
			return objHeap;
		}

		public Map<String, Obj> getObjMap() {
			return objMap;
		}

		public Timer() {
			objMap = new TreeMap<String, Obj>();
			objHeap = new PriorityQueue<Obj>();
		}

		public void add(String name, Obj ob) {
			objMap.put(name, ob);
			objHeap.add(ob);
		}

		public void start(String name) {
			if(objMap.containsKey(name)) {
				Obj o = objMap.get(name);
				new Thread(o).start();
			}
		}

		public void cancel(String name) {
			if(objMap.containsKey(name)) {
				Obj o = objMap.get(name);
				if(!new Thread(o).isAlive()) {
					objMap.remove(name);
				}
			}
		}
	}

	public static class Obj implements Runnable, Comparable<Obj> {

		String name;
		int startTime;

		public Obj(String name, int startTime) {
			this.name = name;
			this.startTime = startTime;
		}

		@Override
		public void run() {
			System.out.println("Running thread: "+name+" at: "+System.currentTimeMillis());
		}

		@Override
		public int compareTo(Obj o) {
			return Integer.compare(this.startTime, o.startTime);
		}
	}

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

	// Thread to print even values
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

	// Thread to print odd values
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
	 * Implement asynchronous callbacks without timeout
	 * EPI : 20.5
	 */

	public static class Requestor {

		public String request;
		public long timeOut;
		public String response;

		public String getResponse() {
			return response;
		}

		public void setResponse(String response) {
			this.response = response;
		}

		public Requestor(String request, long timeOut) {
			this.request = request;
			this.timeOut = timeOut;
		}

		public long getTimeOut() {
			return timeOut;
		}

		public void setTimeOut(long timeOut) {
			this.timeOut = timeOut;
		}

		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}

		public void dispatch(){
			// Main thread which will accept the request and spawns a new thread
			Thread t = new Thread(new Runnable() {
				// Child thread which calls the execute()
				Thread childThread = new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							// Set a timeout on the execute and get response.
							setResponse(execute(getRequest(), getTimeOut()+1000)); // For testing purposes the make this thread sleep more than timeout period
						} catch (Exception e) {
							e.printStackTrace();
							return;
						}

						// Process the response
						processResponse(getResponse());
					}
				});

				@Override
				public void run() {
					// Start the child thread which does actual work
					childThread.start();
					try {
						// Make this thread sleep for time out period
						Thread.sleep(getTimeOut());

						// Child thread will be interrupted if it still alive
						childThread.interrupt();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}); 

			t.start();
		}

		public void processResponse(String response) {
			System.out.println("Processed response: "+response);
		}

		public String execute(String request, long timeout) throws Exception {
			try {
				// Sleep this thread
				Thread.sleep(timeout);
			} catch (InterruptedException e) {
				error(); // If interrupted throw an exception
			}

			// Else do the execute.
			return "execute completed";
		}

		public void error() throws Exception {

			throw new Exception("Failed to process the request. Execute thread interrupted");
		}

	}


	public static void testCallback() {

		Requestor r = new Requestor("Hello", 10000);
		r.dispatch();
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {

		testTimer();
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
