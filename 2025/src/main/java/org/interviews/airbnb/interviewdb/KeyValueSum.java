package org.interviews.airbnb.interviewdb;

import java.util.*;

class Value {
  List<String> values;
  List<String> dependents;
  int val;
  int computedVal;

  public Value() {
    this.values = new ArrayList<>();
    this.dependents = new ArrayList<>();
  }
}

public class KeyValueSum {
  Map<String, Value> valueMap = new HashMap<>();

  public void setValue(String key, int value) {
    Value v = valueMap.getOrDefault(key, new Value());
    v.val = value;
    v.values.clear();
    // Propagate the change to dependents
    updateAndPropagate(key);
  }

  public void setSum(String key, List<String> values) {
    Value v = valueMap.getOrDefault(key, new Value());
    v.values = new ArrayList<>(values);
    for (String val : values) {
      Value value = valueMap.getOrDefault(val, new Value());
      value.dependents.add(key);
      valueMap.put(val, value);
    }
    if (hasCycle(key, new HashSet<>(), new HashSet<>())) throw new IllegalArgumentException("Cycle detected");
    updateAndPropagate(key);
  }

  private boolean hasCycle(String node, Set<String> visited, Set<String> recStack) {
    if (recStack.contains(node)) return true; // back edge found → cycle
    if (visited.contains(node)) return false; // already processed safely

    visited.add(node);
    recStack.add(node);

    Value v = valueMap.get(node);
    for (String dep : v.values) {
      if (hasCycle(dep, visited, recStack)) return true;
    }

    recStack.remove(node);
    return false;
  }

  public void updateAndPropagate(String key) {
    Value v = valueMap.get(key);
    if (v.values.isEmpty()) {
      v.computedVal = v.val;
    } else {
      int total = 0;
      for (String keys : v.values) {
        total += valueMap.get(key).computedVal;
      }
      v.computedVal = total;
    }

    //Propagate to dependents
    for (String dep : v.dependents) {
      updateAndPropagate(dep);
    }
  }

  public int getValue(String key) {
    if (!valueMap.containsKey(key)) throw new NoSuchElementException();
    return getValueHelper(key, new HashSet<>()); // pass visited for cycle detection
  }

  private int getValueHelper(String key, Set<String> visited) {
    if (visited.contains(key)) throw new IllegalStateException("Cycle detected: " + key);
    visited.add(key);

    Value v = valueMap.get(key);
    if (v.values.isEmpty()) return v.val;

    int total = 0;
    for (String dep : v.values) {
      total += getValueHelper(dep, visited);
    }
    visited.remove(key);
    return total;
  }
}
