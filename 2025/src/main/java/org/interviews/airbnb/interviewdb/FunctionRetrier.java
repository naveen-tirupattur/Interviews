package org.interviews.airbnb.interviewdb;

import java.util.function.Supplier;

public class FunctionRetrier {

  public <T> T execute(Supplier<T> callback) {
    try {
      return callback.get();
    } catch (Exception e) {
      return callback.get();
    }
  }

  public <T> T executeWithDelay(Supplier<T> callback, long delayInMillis) {
    try {
      return callback.get();
    } catch (Exception e) {
      try {
        Thread.sleep(delayInMillis);
      } catch (InterruptedException interruptedExceptione) {
        Thread.currentThread().interrupt();
      }
      return callback.get();
    }
  }

  // Retry with exponential backoff
  public <T> T executeWithBackoff(Supplier<T> callback, int maxRetries, long initialDelayMillis) {
    int attempt = 0;
    long delay = initialDelayMillis;

    while (true) {
      try {
        return callback.get();
      } catch (Exception e) {
        attempt++;
        if (attempt > maxRetries) {
          throw e; // give up after max retries
        }
        try {
          Thread.sleep(delay);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
        delay *= 2; // exponential increase
      }
    }
  }
}
