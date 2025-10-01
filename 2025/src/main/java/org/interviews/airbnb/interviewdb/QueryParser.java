package org.interviews.airbnb.interviewdb;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryParser {
  public static Map<String, Object> parse(String url) {
    Map<String, Object> result = new HashMap<>();
    int index = url.indexOf('?');
    if (index == -1 || index == url.length() - 1) return result;
    String queryParams = url.substring(index + 1);
    String[] parts = queryParams.split("&");
    for (String part : parts) {
      String[] pairs = part.split("=", 2);
      String key = pairs[0];
      String value = null;
      // 3. Handle keys with and without explicit values.
      if (pairs.length > 1) {
        value = pairs[1];
      }
      // 4. Decode URL-encoded characters in both the key and the value.
      try {
        key = URLDecoder.decode(key, StandardCharsets.UTF_8.toString());
        if (value != null) {
          value = URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        }
      } catch (Exception e) {
        // In a real-world scenario, you might want to log this error.
        System.err.println("Error decoding URL part: " + e.getMessage());
        continue; // Skip this pair if decoding fails.
      }
      if (result.containsKey(key)) {
        Object val = result.get(key);
        List<Object> values;
        if (val instanceof List) {
          values = (ArrayList<Object>) val;
        } else {
          values = new ArrayList<>();
          values.add(val);
          result.put(key, values);
        }

        if (value == null) {
          values.add(true);
        } else {
          values.add(value);
        }
      } else {
        if (value == null) {
          result.put(key, true);
        } else {
          result.put(key, value);
        }
      }

    }
    return result;
  }

  public static void main(String[] args) {
    // Example Usage
    String url1 = "http://www/api/v1/movie?year=100&type=action";
    String url2 = "http://www/api/v1/movie?book";
    String url3 = "http://www/api/v1/movie?type=scifi&type=action";
    String url4 = "http://www/api/v1/movie?type%26view=scifi&area%3Dcity=action";

    System.out.println("Input: " + url1);
    System.out.println("Output: " + parse(url1)); // Expected: {year=100, type=action}
    System.out.println("--------------------");
    System.out.println("Input: " + url2);
    System.out.println("Output: " + parse(url2)); // Expected: {book=true}
    System.out.println("--------------------");
    System.out.println("Input: " + url3);
    System.out.println("Output: " + parse(url3)); // Expected: {type=[scifi, action]}
    System.out.println("--------------------");
    System.out.println("Input: " + url4);
    System.out.println("Output: " + parse(url4)); // Expected: {type&view=scifi, area=city=action}
  }
}
