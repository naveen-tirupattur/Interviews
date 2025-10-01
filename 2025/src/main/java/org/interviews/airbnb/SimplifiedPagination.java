package org.interviews.airbnb;

import java.util.*;

public class SimplifiedPagination {

  public static List<String> paginate(List<String> input, int pageSize) {
    LinkedList<String> items = new LinkedList<>(input);
    List<String> pages = new ArrayList<>();

    while (!items.isEmpty()) {
      Set<String> seenHosts = new HashSet<>();
      List<String> page = new ArrayList<>();
      List<String> skipped = new ArrayList<>();

      while (!items.isEmpty() && page.size() < pageSize) {
        String record = items.poll();
        String hostId = record.split(",")[0];

        if (!seenHosts.contains(hostId)) {
          page.add(record);
          seenHosts.add(hostId);
        } else {
          skipped.add(record);
        }
      }

      // If page not full, fill from skipped
      while (page.size() < pageSize && !skipped.isEmpty()) {
        page.add(skipped.removeFirst());
      }

      // Remaining skipped back to queue
      items.addAll(0, skipped);
      if (!page.isEmpty()) page.add(" ");
      pages.addAll(page);

    }

    return pages;
  }

  public static void main(String[] args) {
    List<String> results = new ArrayList<>();
    results.add("1,28,300.1,San Francisco");
    results.add("4,5,209.1,San Francisco");
    results.add("20,7,208.1,San Francisco");
    results.add("23,2,207.1,San Francisco");
    results.add("16,1,206.1,San Francisco");
    results.add("1,2,205.1,San Francisco");
    results.add("1,3,204.1,San Francisco");
    results.add("1,4,203.1,San Francisco");
    results.add("6,8,202.1,San Francisco");
    results.add("7,9,201.1,San Francisco");
    results.add("8,10,200.1,San Francisco");
    results.add("9,11,199.1,San Francisco");
    results.add("10,12,198.1,San Francisco");
    results.add("1,5,197.1,San Francisco");

    List<String> reorderedResults = paginate(results, 5);
    for (String line : reorderedResults) {
      if (line.equals(" ")) {
        System.out.println("---- Page Separator ----");
      } else {
        System.out.println(line);
      }
    }
  }
}