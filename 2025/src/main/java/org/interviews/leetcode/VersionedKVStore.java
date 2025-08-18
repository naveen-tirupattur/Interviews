package org.interviews.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class KeyData {
  final List<Value> values = new ArrayList<>();
  final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
}

class Value {
  int timestamp;
  String value;

  public Value(String value, int timestamp) {
    this.value = value;
    this.timestamp = timestamp;
  }
}

class TimeMap {

  Map<String, KeyData> db;

  public TimeMap() {
    this.db = new ConcurrentHashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    KeyData data = db.computeIfAbsent(key, k -> new KeyData());
    // Acquire the write lock for this specific key
    data.lock.writeLock().lock();
    try {
      data.values.add(new Value(value, timestamp));
    } finally {
      data.lock.writeLock().unlock();
    }
  }

  public String get(String key, int timestamp) {
    KeyData data = db.get(key);
    if (data == null) {
      return "";
    }

    // Acquire the read lock for this specific key
    data.lock.readLock().lock();
    try {
      List<Value> values = data.values;
      int index = find(values, timestamp);
      if (index == -1) return "";
      return values.get(index).value;
    } finally {
      data.lock.readLock().unlock();
    }
  }

  public int find(List<Value> values, int timestamp) {
    int low = 0, high = values.size() - 1;
    int highestIndex = -1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      int midTimestamp = values.get(mid).timestamp;
      if (midTimestamp == timestamp) return mid;
      if (midTimestamp > timestamp) high = mid - 1;
      else {
        low = mid + 1;
        highestIndex = mid;
      }
    }
    return highestIndex;
  }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
public class VersionedKVStore {
}
