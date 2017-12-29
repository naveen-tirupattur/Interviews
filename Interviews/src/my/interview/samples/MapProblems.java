package my.interview.samples;

public class MapProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// MyHashMap<String,String> map = new MyHashMap<String, String>(10);
		//
		// map.put("1", "one");
		// map.put("2", "two");
		// map.put("5", "five");
		// map.put("8", "eight");
		// map.put("1", "2one");
		// map.put("5", "5two");
		// map.put(null, "null");
		//
		// System.out.println(map.get("1"));
		//
		LRUCache<String, String> lru = new LRUCache<String, String>(3);

		lru.put("1", "one");
		lru.put("2", "two");
		lru.put("5", "five");
		lru.put("8", "eight");
		lru.put("1", "1one");
		lru.put("2", "1two");

		System.out.println("\n Before access ");
		for (String s : lru.values()) {
			System.out.println(s);
		}

		System.out.println("\n" + lru.get("2"));
		System.out.println("\n" + lru.get("1"));

		System.out.println("\n After access \n");
		for (String s : lru.values()) {
			System.out.println(s);
		}

	}

}
