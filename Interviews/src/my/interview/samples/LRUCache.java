package my.interview.samples;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	int cacheSize;

	public LRUCache(int cacheSize) {
		super(cacheSize + 1, 1.0f, true);
		this.cacheSize = cacheSize;
	}

	public boolean removeEldestEntry(Map.Entry e) {

		return (size() > this.cacheSize);
		// return false;
	}

}
