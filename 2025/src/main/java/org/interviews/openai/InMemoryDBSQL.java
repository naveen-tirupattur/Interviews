package org.interviews.openai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Enum for comparison operators
enum Operator {
  EQUAL, GREATER_THAN, LESS_THAN
}

/**
 * Represents a single condition in a WHERE clause.
 */
class WhereCondition {
  String column;
  Object value;
  Operator operator;

  public WhereCondition(String column, Object value, Operator operator) {
    this.column = column;
    this.value = value;
    this.operator = operator;
  }
}

/**
 * Represents a logical group of WHERE conditions.
 */
class WhereClause {
  List<WhereCondition> conditions;
  String logicalOperator; // "&&" or "||"

  public WhereClause() {
    this.conditions = new ArrayList<>();
    this.logicalOperator = "&&"; // Default to AND
  }
}

class Record {
  Map<String, Object> values;

  public Record() {
    this.values = new HashMap<>();
  }

  public void addValue(String column, Object value) {
    values.put(column, value);
  }

  public Object getValue(String column) {
    return values.get(column);
  }

  @Override
  public String toString() {
    return values.toString();
  }

}

class Table {
  List<String> schema;
  List<Record> records;

  public Table(List<String> schema) {
    this.schema = schema;
    this.records = new ArrayList<>();
  }

  public List<String> getSchema() {
    return new ArrayList<>(schema);
  }

  public void insert(List<Object> values) {
    if (values.size() != schema.size()) {
      throw new IllegalArgumentException("Number of values must match the number of columns in the schema.");
    }
    Record newRecord = new Record();
    for (int i = 0; i < schema.size(); i++) {
      newRecord.addValue(schema.get(i), values.get(i));
    }
    records.add(newRecord);
  }

  public List<Record> getRecords() {
    return new ArrayList<>(records);
  }
}

public class InMemoryDBSQL {
  Table table;

  public void createTable(List<String> schema) {
    if (table != null) throw new IllegalStateException("Table already exists");
    table = new Table(schema);
  }

  public void insert(List<Object> values) {
    if (table == null) throw new IllegalStateException("Table doesn't exist");
    table.insert(values);
  }

  public List<Record> select(List<String> columnsToSelect) {
    if (table == null) throw new IllegalStateException("Table doesn't exist");
    // Basic select without a where clause.
    // We'll filter the output to show only the selected columns.
    List<Record> allRecords = table.getRecords();
    List<Record> selectedRecords = new ArrayList<>();
    for (Record record : allRecords) {
      Record newRecord = new Record();
      for (String col : columnsToSelect) {
        if (table.getSchema().contains(col)) {
          newRecord.addValue(col, record.getValue(col));
        }
      }
      selectedRecords.add(newRecord);
    }
    return selectedRecords;
  }


  public List<Record> select(List<String> columnsToSelect, WhereClause whereClause) {
    if (table == null) {
      throw new IllegalStateException("Table has not been created yet.");
    }

    List<Record> allRecords = table.getRecords();
    List<Record> filteredRecords = new ArrayList<>();

    for (Record record : allRecords) {
      if (matchesWhereClause(record, whereClause)) {
        filteredRecords.add(record);
      }
    }
    return filteredRecords;
  }

  private boolean matchesCondition(Record record, WhereCondition condition) {
    Object recordValue = record.getValue(condition.column);
    if (recordValue == null) return false;
    String colName = condition.column;
    Object val = condition.value;
    switch (condition.operator) {
      case EQUAL:
        return recordValue.equals(val);
      case GREATER_THAN:
        if (recordValue instanceof Comparable && val instanceof Comparable) {
          return ((Comparable) recordValue).compareTo(val) > 0;
        }
        return false;
      case LESS_THAN:
        if (recordValue instanceof Comparable && condition.value instanceof Comparable) {
          return ((Comparable) recordValue).compareTo(condition.value) < 0;
        }
        return false;
      default:
        return false;
    }
  }

  private boolean matchesWhereClause(Record record, WhereClause whereClause) {
    if (whereClause == null || whereClause.conditions.isEmpty()) return true;
    if (whereClause.logicalOperator.equals("||")) {
      for (WhereCondition condition : whereClause.conditions) {
        if (matchesCondition(record, condition)) {
          return true;
        }
      }
      return false;
    } else {
      for (WhereCondition condition : whereClause.conditions) {
        if (!matchesCondition(record, condition)) {
          return false;
        }
      }
      return true;
    }
  }

  private boolean matchesWhereClause(Record record, String whereColumn, Object whereValue) {
    if (whereColumn == null || whereValue == null) {
      return true; // No where clause, so all records match
    }
    Object recordValue = record.getValue(whereColumn);
    return recordValue != null && recordValue.equals(whereValue);
  }

  public static void main(String[] args) {
    InMemoryDBSQL db = new InMemoryDBSQL();

    // Part A: Create a table and insert records
    List<String> schema = List.of("id", "name", "age", "city");
    db.createTable(schema);

    System.out.println("--- Part A: Inserting Records ---");
    db.insert(List.of(1, "Alice", 30, "New York"));
    db.insert(List.of(2, "Bob", 25, "Los Angeles"));
    db.insert(List.of(3, "Charlie", 35, "New York"));
    db.insert(List.of(4, "David", 30, "Chicago"));
    db.insert(List.of(5, "Eve", 25, "Los Angeles"));
    System.out.println("Inserted 5 records.");

    // Part B & C: Select with a complex WHERE clause
    System.out.println("\n--- Part B & C: Selecting with WHERE Clause (age > 25 && city = New York) ---");
    WhereClause whereClause = new WhereClause();
    whereClause.conditions.add(new WhereCondition("age", 25, Operator.GREATER_THAN));
    whereClause.conditions.add(new WhereCondition("city", "New York", Operator.EQUAL));
    whereClause.logicalOperator = "&&"; // Use AND logic

    List<String> columnsToSelect = List.of("name", "age");
    List<Record> results = db.select(columnsToSelect, whereClause);
    for (Record record : results) {
      System.out.println(record);
    }

//    // Part D: Select with WHERE and ORDER BY
//    System.out.println("\n--- Part D: Selecting with WHERE and ORDER BY (age > 25 order by city ASC, age DESC) ---");
//    WhereClause complexWhereClause = new WhereClause();
//    complexWhereClause.conditions.add(new WhereCondition("age", 25, Operator.GREATER_THAN));
//    complexWhereClause.logicalOperator = "||"; // Although there's only one condition, let's keep it consistent
//
//    OrderByClause orderByClause = new OrderByClause();
//    orderByClause.columns.add(new OrderByColumn("city", Order.ASC));
//    orderByClause.columns.add(new OrderByColumn("age", Order.DESC));
//
//    List<Record> orderedResults = db.select(List.of("name", "age", "city"), complexWhereClause, orderByClause);
//    for (Record record : orderedResults) {
//      System.out.println(record);
//    }
//
//    // Example with no where clause, just order by
//    System.out.println("\n--- Selecting All Records, Ordered by Name DESC ---");
//    OrderByClause simpleOrderBy = new OrderByClause();
//    simpleOrderBy.columns.add(new OrderByColumn("name", Order.DESC));
//
//    List<Record> allOrdered = db.select(List.of("name", "age"), null, simpleOrderBy);
//    for (Record record : allOrdered) {
//      System.out.println(record);
//    }
//  }
  }
}
