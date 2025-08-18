package org.interviews.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Table {
  String name;
  Map<Integer, String[]> rowMap;
  Map<Integer, Map<String, Set<Integer>>> indexMap;
  int numColumns;

  public Table(String name, int numColumns) {
    this.name = name;
    this.rowMap = new HashMap<>();
    this.numColumns = numColumns;
    this.indexMap = new HashMap<>();
  }
}

public class DesignSQL {
  private final Map<String, Table> db;
  private int rowId;
  private final Lock writeLock;
  private final Lock readLock;

  public DesignSQL(String[] names, int[] cols) {
    this.db = new HashMap<>();
    for (int i = 0; i < names.length; i++) {
      Table t = new Table(names[i], cols[i]);
      db.put(names[i], t);
    }
    rowId = 1;
    ReadWriteLock lock = new ReentrantReadWriteLock();
    writeLock = lock.writeLock();
    readLock = lock.readLock();
  }

  public void insertRow(String name, String[] row) {
    writeLock.lock();
    try {
      Table t = db.get(name);
      t.rowMap.put(rowId, row);
      rowId++;
    } finally {
      writeLock.unlock();
    }
  }

  public void deleteRow(String name, int rowId) {
    writeLock.lock();
    try {
      Table t = db.get(name);
      t.rowMap.remove(rowId);
    } finally {
      writeLock.unlock();
    }
  }

  public String selectCell(String name, int rowId, int columnId) {
    readLock.lock();
    try {
      Table t = db.get(name);
      return t.rowMap.get(rowId)[columnId];
    } finally {
      readLock.unlock();
    }
  }
}
