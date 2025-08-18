package org.interviews.openai;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SerDeserKVStore {
  private static String testFileName;
  private static Path tempFilePath;
  Map<String, String> kvStore;

  public SerDeserKVStore() {
    this.kvStore = new HashMap<>();
  }

  public void set(String key, String value) {
    kvStore.put(key, value);
  }

  public String get(String key) {
    return kvStore.getOrDefault(key, "");
  }

  public void saveToFile(String fileName) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
      for (Map.Entry<String, String> entry : kvStore.entrySet()) {
        String line = encode(entry.getKey()) + "=" + encode(entry.getValue());
        writer.write(line);
        writer.newLine();
      }
    }
  }

  public void loadFromFile(String fileName) throws IOException {
    kvStore.clear();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // find first unescaped '='
        int index = findFirstUnescapedEquals(line);
        if (index == -1) continue;
        String key = decode(line.substring(0, index));
        String value = decode(line.substring(index + 1));
        kvStore.put(key, value);
      }
    }
  }

  private int findFirstUnescapedEquals(String line) {
    int index = 0;
    while (index < line.length()) {
      if (line.charAt(index) == '=' && (index == 0 || line.charAt(index - 1) != '\\'))
        return index;
      index++;
    }
    return -1;
  }

  private String encode(String value) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < value.length(); i++) {
      char c = value.charAt(i);
      if (c == '=') {
        s.append("\\=");
      } else if (c == '\\') {
        s.append("\\\\");
      } else if (c == '\n') {
        s.append("\\\n");
      } else {
        s.append(c);
      }
    }
    return s.toString();
  }

  private String decode(String value) {
    StringBuilder s = new StringBuilder();
    int index = 0;
    while (index < value.length()) {
      char c = value.charAt(index);
      if (c == '\\' && index + 1 < value.length()) {
        char nextChar = value.charAt(index + 1);
        if (nextChar == '\n') {
          s.append("\n");
        } else if (nextChar == '=') {
          s.append("=");
        } else if (nextChar == '\\') {
          s.append("\\");
        } else {
          s.append(nextChar);
        }
        index = index + 2;
      } else {
        s.append(c);
        index++;
      }
    }
    return s.toString();
  }

  public static void main(String[] args) throws IOException {

    System.out.println("Starting SerDeserKVStore manual tests...");

    // Run all test cases
    testSetAndGet();
    testSaveAndLoadSimpleData();
    testSpecialCharacters();
    testEmptyKeysAndValues();
    testOverwritingKeys();
    testLoadFromFileWithMalformedData();
    testLoadFromNonExistentFile();

    System.out.println("\nAll tests finished.");
  }

  private static void setup() throws IOException {
    testFileName = "test_kv_store_" + UUID.randomUUID() + ".txt";
    tempFilePath = Paths.get(testFileName);
    Files.deleteIfExists(tempFilePath); // Clean up any previous test file
  }

  private static void teardown() throws IOException {
    Files.deleteIfExists(tempFilePath);
  }

  private static void testSetAndGet() throws IOException {
    setup();
    try {
      SerDeserKVStore kvStore = new SerDeserKVStore();
      kvStore.set("key1", "value1");
      kvStore.set("key2", "value2");

      assert "value1".equals(kvStore.get("key1")) : "Test 'set and get' failed for key1";
      assert "value2".equals(kvStore.get("key2")) : "Test 'set and get' failed for key2";
      assert "".equals(kvStore.get("nonexistent_key")) : "Test 'set and get' failed for nonexistent key";

      System.out.println("✅ Test 'set and get' passed.");
    } finally {
      teardown();
    }
  }

  private static void testSaveAndLoadSimpleData() throws IOException {
    setup();
    try {
      SerDeserKVStore kvStore = new SerDeserKVStore();
      kvStore.set("name", "John Doe");
      kvStore.set("age", "30");
      kvStore.saveToFile(testFileName);

      SerDeserKVStore newKvStore = new SerDeserKVStore();
      newKvStore.loadFromFile(testFileName);

      assert "John Doe".equals(newKvStore.get("name")) : "Test 'save and load simple data' failed for name";
      assert "30".equals(newKvStore.get("age")) : "Test 'save and load simple data' failed for age";

      System.out.println("✅ Test 'save and load simple data' passed.");
    } finally {
      teardown();
    }
  }

  private static void testSpecialCharacters() throws IOException {
    setup();
    try {
      SerDeserKVStore kvStore = new SerDeserKVStore();
      kvStore.set("key=with=equals", "value=with=equals");
      kvStore.set("key\\with\\backslash", "value\\with\\backslash");
      kvStore.set("key\nwith\nnewline", "value\nwith\nnewline");
      kvStore.set("combined\\key=with\nchars", "combined\\value=with\nchars");
      kvStore.saveToFile(testFileName);

      SerDeserKVStore newKvStore = new SerDeserKVStore();
      newKvStore.loadFromFile(testFileName);

      assert "value=with=equals".equals(newKvStore.get("key=with=equals")) : "Test 'special characters' failed for equals sign";
      assert "value\\with\\backslash".equals(newKvStore.get("key\\with\\backslash")) : "Test 'special characters' failed for backslash";
      assert "value\nwith\nnewline".equals(newKvStore.get("key\nwith\nnewline")) : "Test 'special characters' failed for newline";
      assert "combined\\value=with\nchars".equals(newKvStore.get("combined\\key=with\nchars")) : "Test 'special characters' failed for combined chars";

      System.out.println("✅ Test 'special characters' passed.");
    } finally {
      teardown();
    }
  }

  private static void testEmptyKeysAndValues() throws IOException {
    setup();
    try {
      SerDeserKVStore kvStore = new SerDeserKVStore();
      kvStore.set("", "value1");
      kvStore.set("key2", "");
      kvStore.set("", ""); // This will overwrite the first empty key

      kvStore.saveToFile(testFileName);

      SerDeserKVStore newKvStore = new SerDeserKVStore();
      newKvStore.loadFromFile(testFileName);

      assert "".equals(newKvStore.get("")) : "Test 'empty keys and values' failed for empty key";
      assert "".equals(newKvStore.get("key2")) : "Test 'empty keys and values' failed for empty value";

      System.out.println("✅ Test 'empty keys and values' passed.");
    } finally {
      teardown();
    }
  }

  private static void testOverwritingKeys() throws IOException {
    setup();
    try {
      SerDeserKVStore kvStore = new SerDeserKVStore();
      kvStore.set("key1", "value1");
      kvStore.set("key1", "new_value");
      assert "new_value".equals(kvStore.get("key1")) : "Test 'overwriting keys' failed for direct set";

      kvStore.saveToFile(testFileName);
      SerDeserKVStore newKvStore = new SerDeserKVStore();
      newKvStore.loadFromFile(testFileName);

      assert "new_value".equals(newKvStore.get("key1")) : "Test 'overwriting keys' failed after save/load";

      System.out.println("✅ Test 'overwriting keys' passed.");
    } finally {
      teardown();
    }
  }

  private static void testLoadFromFileWithMalformedData() throws IOException {
    setup();
    try {
      // Create a file with malformed data manually
      String malformedContent = "key1=value1\n" +
        "malformed_line_no_equals\n" +
        "key2=value2";
      Files.write(tempFilePath, malformedContent.getBytes());

      SerDeserKVStore newKvStore = new SerDeserKVStore();
      newKvStore.loadFromFile(testFileName);

      assert "value1".equals(newKvStore.get("key1")) : "Malformed data test failed for key1";
      assert "value2".equals(newKvStore.get("key2")) : "Malformed data test failed for key2";
      assert "".equals(newKvStore.get("malformed_line_no_equals")) : "Malformed data test failed to skip malformed line";

      System.out.println("✅ Test 'load with malformed data' passed.");
    } finally {
      teardown();
    }
  }

  private static void testLoadFromNonExistentFile() {
    try {
      new SerDeserKVStore().loadFromFile("non_existent_file.txt");
      System.err.println("❌ Test 'load from non-existent file' failed: Did not throw IOException.");
    } catch (IOException e) {
      System.out.println("✅ Test 'load from non-existent file' passed. (Caught expected IOException)");
    }
  }
}
