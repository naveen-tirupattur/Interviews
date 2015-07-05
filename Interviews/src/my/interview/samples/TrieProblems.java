package my.interview.samples;


public class TrieProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyTrie myTrie = new MyTrie();
		
		myTrie.insert("add");
		myTrie.insert("adding");
		myTrie.insert("adda");
		myTrie.insert("cat");
		myTrie.insert("cattle");
		
//		List<String> nodesList = new ArrayList<>();
//		myTrie.print(myTrie.root,String.valueOf(myTrie.root.getC()),nodesList);
//		
//		for(String s: nodesList)
//		{
//			System.out.println(s);
//		}
//		
//		
		System.out.println("Is present: "+myTrie.longestPrefix("addings"));
		
		
	}

}
