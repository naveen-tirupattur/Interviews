package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RankByVotes {

  public static class CustomerAgent implements Comparable<CustomerAgent> {
    int id;
    List<Integer> ratings;
    float avgRating;

    public CustomerAgent(int id, List<Integer> ratings, float avgRating) {
      this.id = id;
      this.ratings = ratings;
      this.avgRating = avgRating;
    }

    @Override
    public int compareTo(CustomerAgent o) {
      return Float.compare(this.avgRating, o.avgRating);
    }

    @Override
    public String toString() {
      return this.id + " " + this.avgRating;
    }
  }

  public static void main(String[] args) {
    List<List<Integer>> input = new ArrayList<>();
    input.add(Arrays.asList(4, 5, 3));
    input.add(Arrays.asList(5, 5));
    input.add(Arrays.asList(3, 2, 4, 1));
    List<CustomerAgent> agents = new ArrayList<>();
    int i = 0;
    for (List<Integer> rating : input) {
      int totalRating = 0;
      for (Integer r : rating) {
        totalRating += r;
      }
      float avgRating = (float) totalRating / rating.size();
      agents.add(new CustomerAgent(i++, rating, avgRating));
    }
    Collections.sort(agents);
    System.out.println(agents);
  }
}
