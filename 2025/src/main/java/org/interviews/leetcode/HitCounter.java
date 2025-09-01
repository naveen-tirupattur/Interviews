package org.interviews.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {

  private Queue<Integer> hits;
  private final int TIME_WINDOW_IN_SECONDS = 300;

  public HitCounter() {
    this.hits = new LinkedList<>();
  }

  public void hit(int timestamp) {
    while (!hits.isEmpty() && hits.peek() > timestamp - TIME_WINDOW_IN_SECONDS) hits.poll();
    hits.add(timestamp);
  }

  public int getHits(int timestamp) {
    while (!hits.isEmpty() && hits.peek() > timestamp - TIME_WINDOW_IN_SECONDS) hits.poll();
    return hits.size();
  }

  public static void main(String[] args) {
    System.out.println("--- Running HitCounter Tests ---");

    // Helper method for assertions
    Runnable runTest = () -> {
    }; // Placeholder for lambda type

    // Test 1: Basic Hits
    runTest = () -> {
      System.out.println("\nTest 1: Basic Hits");
      HitCounter counter = new HitCounter();
      counter.hit(1);
      counter.hit(2);
      counter.hit(3);

      int result1 = counter.getHits(3);
      System.out.println("  At timestamp 3: " + result1 + " (Expected: 3)");
      assert result1 == 3 : "Test 1 Failed: Should count all 3 hits at timestamp 3.";

      int result2 = counter.getHits(4);
      System.out.println("  At timestamp 4: " + result2 + " (Expected: 3)");
      assert result2 == 3 : "Test 1 Failed: Should still count all 3 hits at timestamp 4.";

      int result3 = counter.getHits(300);
      System.out.println("  At timestamp 300: " + result3 + " (Expected: 3)");
      assert result3 == 3 : "Test 1 Failed: All hits should still be within window at timestamp 300.";
    };
    runTest.run();

    // Test 2: Time Window Expiration
    runTest = () -> {
      System.out.println("\nTest 2: Time Window Expiration");
      HitCounter counter = new HitCounter();
      counter.hit(1); // Hit at 1 second
      counter.hit(2); // Hit at 2 seconds
      counter.hit(300); // Hit at 300 seconds

      int result1 = counter.getHits(301);
      System.out.println("  At timestamp 301: " + result1 + " (Expected: 2)");
      assert result1 == 2 : "Test 2 Failed: Hit at timestamp 1 should expire at 301.";

      int result2 = counter.getHits(302);
      System.out.println("  At timestamp 302: " + result2 + " (Expected: 2)");
      assert result2 == 2 : "Test 2 Failed: Hit at timestamp 2 should expire at 302.";

      int result3 = counter.getHits(303);
      System.out.println("  At timestamp 303: " + result3 + " (Expected: 1)");
      assert result3 == 1 : "Test 2 Failed: Hit at timestamp 3 should expire at 303.";

      int result4 = counter.getHits(599);
      System.out.println("  At timestamp 599: " + result4 + " (Expected: 1)");
      assert result4 == 1 : "Test 2 Failed: Only hit at 300 should remain at 599.";

      int result5 = counter.getHits(600);
      System.out.println("  At timestamp 600: " + result5 + " (Expected: 0)");
      assert result5 == 0 : "Test 2 Failed: All hits should expire at 600 or later.";
    };
    runTest.run();

    // Test 3: Multiple Hits At Same Timestamp
    runTest = () -> {
      System.out.println("\nTest 3: Multiple Hits At Same Timestamp");
      HitCounter counter = new HitCounter();
      counter.hit(10);
      counter.hit(10);
      counter.hit(10);

      int result1 = counter.getHits(10);
      System.out.println("  At timestamp 10: " + result1 + " (Expected: 3)");
      assert result1 == 3 : "Test 3 Failed: Should count all 3 hits at timestamp 10.";

      int result2 = counter.getHits(310);
      System.out.println("  At timestamp 310: " + result2 + " (Expected: 3)");
      assert result2 == 3 : "Test 3 Failed: All 3 hits should expire at 310 or later.";

      int result3 = counter.getHits(311);
      System.out.println("  At timestamp 311: " + result3 + " (Expected: 0)");
      assert result3 == 0 : "Test 3 Failed: All 3 hits should be gone at 311.";
    };
    runTest.run();

    // Test 4: No Hits Recorded
    runTest = () -> {
      System.out.println("\nTest 4: No Hits Recorded");
      HitCounter counter = new HitCounter();
      int result = counter.getHits(100);
      System.out.println("  At timestamp 100 (no hits): " + result + " (Expected: 0)");
      assert result == 0 : "Test 4 Failed: Should return 0 when no hits are recorded.";
    };
    runTest.run();

    // Test 5: Future Timestamp Query
    runTest = () -> {
      System.out.println("\nTest 5: Future Timestamp Query");
      HitCounter counter = new HitCounter();
      counter.hit(10);
      counter.hit(20);
      counter.hit(30);

      int result = counter.getHits(1000);
      System.out.println("  At timestamp 1000: " + result + " (Expected: 0)");
      assert result == 0 : "Test 5 Failed: Querying a far future timestamp should result in 0 hits.";
    };
    runTest.run();

    // Test 6: Large Number of Hits
    runTest = () -> {
      System.out.println("\nTest 6: Large Number of Hits");
      HitCounter counter = new HitCounter();
      for (int i = 1; i <= 500; i++) {
        counter.hit(i);
      }

      int result1 = counter.getHits(500);
      System.out.println("  At timestamp 500: " + result1 + " (Expected: 300)");
      assert result1 == 300 : "Test 6 Failed: Should count 300 hits in the 5-minute window at 500.";

      int result2 = counter.getHits(501);
      System.out.println("  At timestamp 501: " + result2 + " (Expected: 300)");
      assert result2 == 300 : "Test 6 Failed: Should count 300 hits in the 5-minute window at 501.";
    };
    runTest.run();

    // Test 7: Edge Case Zero Timestamp
    runTest = () -> {
      System.out.println("\nTest 7: Edge Case Zero Timestamp");
      HitCounter counter = new HitCounter();
      counter.hit(0);
      counter.hit(1);

      int result1 = counter.getHits(0);
      System.out.println("  At timestamp 0: " + result1 + " (Expected: 2)");
      assert result1 == 2 : "Test 7 Failed: Should count hits at timestamp 0.";

      int result2 = counter.getHits(299);
      System.out.println("  At timestamp 299: " + result2 + " (Expected: 2)");
      assert result2 == 2 : "Test 7 Failed: Should count 2 hits at timestamp 299.";

      int result3 = counter.getHits(300);
      System.out.println("  At timestamp 300: " + result3 + " (Expected: 1)");
      assert result3 == 1 : "Test 7 Failed: Hit at 0 should expire at 300.";

      int result4 = counter.getHits(301);
      System.out.println("  At timestamp 301: " + result4 + " (Expected: 0)");
      assert result4 == 0 : "Test 7 Failed: Hit at 1 should expire at 301.";
    };
    runTest.run();

    // Test 8: Chronological Order Assumption (Complex Sequence)
    runTest = () -> {
      System.out.println("\nTest 8: Chronological Order Assumption");
      HitCounter counter = new HitCounter();
      counter.hit(100);
      counter.hit(101);
      counter.hit(102);
      int result1 = counter.getHits(102);
      System.out.println("  At timestamp 102: " + result1 + " (Expected: 3)");
      assert result1 == 3 : "Test 8 Failed: Initial hits.";

      counter.hit(400); // This will cause 100, 101, 102 to expire (400 - 300 = 100)
      int result2 = counter.getHits(400);
      System.out.println("  At timestamp 400: " + result2 + " (Expected: 1)");
      assert result2 == 1 : "Test 8 Failed: After hit at 400.";

      counter.hit(401);
      int result3 = counter.getHits(401);
      System.out.println("  At timestamp 401: " + result3 + " (Expected: 2)");
      assert result3 == 2 : "Test 8 Failed: After hit at 401.";

      counter.hit(402);
      int result4 = counter.getHits(402);
      System.out.println("  At timestamp 402: " + result4 + " (Expected: 3)");
      assert result4 == 3 : "Test 8 Failed: After hit at 402.";

      int result5 = counter.getHits(403);
      System.out.println("  At timestamp 403: " + result5 + " (Expected: 2)");
      assert result5 == 2 : "Test 8 Failed: After query at 403, 400 should be out.";
    };
    runTest.run();

    System.out.println("\n--- All tests completed. Review console output for any 'Assertion Failed' messages. ---");

  }

}
