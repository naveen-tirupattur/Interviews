package org.interviews.openai;

import java.util.*;
import java.util.concurrent.*;

public class MultiThreadedWebCrawler {

  private static final int MAX_PAGES_TO_CRAWL = 100;
  private static final int THREAD_POOL_SIZE = 5;

  // Use a thread-safe Set
  private final Set<String> visitedUrls = Collections.synchronizedSet(new HashSet<>());
  // A thread-safe queue for URLs to visit
  private final BlockingQueue<String> urlsToVisit = new LinkedBlockingQueue<>();
  private final String startUrl;
  private final Map<String, List<String>> mappedUrls = new HashMap<>();

  public MultiThreadedWebCrawler(String startUrl) {
    this.startUrl = startUrl;
    this.urlsToVisit.add(startUrl);

    
  }

  public Set<String> crawl() throws InterruptedException, ExecutionException {
    // Create a fixed-size thread pool
    ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    while (!urlsToVisit.isEmpty() && visitedUrls.size() < MAX_PAGES_TO_CRAWL) {
      String currentUrl = urlsToVisit.poll(100, TimeUnit.MILLISECONDS);
      if (currentUrl != null && !visitedUrls.contains(currentUrl)) {
        visitedUrls.add(currentUrl);
        executor.submit(() -> {
          try {
            for (String absoluteUrl : mappedUrls.get(currentUrl)) {
              if (isSameDomain(absoluteUrl) && !visitedUrls.contains(absoluteUrl)) {
                urlsToVisit.add(absoluteUrl);
              }
            }
          } catch (IllegalArgumentException e) {
            System.err.println("Error crawling " + currentUrl + ": " + e.getMessage());
          }
        });
      }
    }

    executor.shutdown();
    executor.awaitTermination(60, TimeUnit.SECONDS); // Wait for threads to finish

    return visitedUrls;
  }

  private boolean isSameDomain(String url) {
    return url.startsWith(startUrl.split("/")[0] + "//" + startUrl.split("/")[2]);
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    String url = "https://www.example.com";
    MultiThreadedWebCrawler crawler = new MultiThreadedWebCrawler(url);
    Set<String> discoveredLinks = crawler.crawl();
    System.out.println("Discovered " + discoveredLinks.size() + " links:");
    discoveredLinks.forEach(System.out::println);
  }
}
