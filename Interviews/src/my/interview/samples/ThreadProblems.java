package my.interview.samples;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class ThreadProblems {

	public static class pObject {
		public int count;
		public int n;
		public Map<String, Integer> countMap = new HashMap<String, Integer>();
		public pObject(int n) {
			this.n = n;
		}

		public void critical() throws InterruptedException {
			int count = 0;
			
			if(this.count < n) wait();
			
			String threadName = Thread.currentThread().getName();
			if(countMap.containsKey(threadName)) {
				count = countMap.get(threadName);
			}
			countMap.put(threadName, count+1);
			
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
					synchronized (p) {
						p.rendezvous();
						p.count++;
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
