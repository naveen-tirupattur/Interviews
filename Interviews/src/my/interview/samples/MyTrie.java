package my.interview.samples;


import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class MyTrie {

	public TrieNode root;

	public MyTrie()
	{
		this.root = new TrieNode((char)0);
	}


	public void print(TrieNode current, String key,List<String> nodesList)
	{
		if(current == null) return;

		if(current.isEnd())
		{
			nodesList.add(key);

		}

		for(Entry e: current.getChildren().entrySet())
		{
			String s = key + (Character)e.getKey();
			print((TrieNode)e.getValue(),s,nodesList);
		}

	}

	public boolean search(String s)
	{
		TrieNode current = root;
		

		for(int i=0;i<s.length();i++)
		{
			TreeMap<Character,TrieNode> childrenMap = (TreeMap<Character,TrieNode>)current.getChildren();
			char c = s.charAt(i);
			if(!childrenMap.containsKey((Character)c))
			{
				return false;
			}else
			{
				current = childrenMap.get((Character)c);
			}
		}
		
		if(current.isEnd()) return true;
		
		return false;
	}


	public void insert(String s)
	{
		//Start from root
		TrieNode current = root;
		int i=0;
		//Till end of string
		while(i < s.length())
		{
			//Get the next character
			char c = s.charAt(i++);
			TreeMap<Character,TrieNode> childrenMap = (TreeMap)current.getChildren();

			//Check if the current node has it in its children
			if(childrenMap.containsKey((Character)c))
			{
				//Get the child node matching the current character
				current = childrenMap.get((Character)c);

			}else
			{
				//Create a temporary node and add it to current nodes children
				TrieNode n = new TrieNode(c);
				childrenMap.put((Character)c,n);
				current = n;
			}
		}

		current.setEnd(true);
	}
	
	public String longestPrefix(String s)
	{
		if(root==null) return "";
		TrieNode current = root;
		String p= new String("");
		int prevWordEnd = 0;
		
		for(int i=0;i<s.length();i++)
		{	
			TreeMap<Character,TrieNode> childrenMap = (TreeMap<Character,TrieNode>)current.getChildren();
			char c = s.charAt(i);
			if(childrenMap.containsKey((Character)c))
			{
				p+=c;
				current = childrenMap.get((Character)c);
				if(current.isEnd())
				{
					prevWordEnd = i+1;
				}
				
			}else
			{
				break;
			}
		}
		
		if(current.isEnd())
		{
			return p;
		}else
		{
			return s.substring(0,prevWordEnd);
		}
		
		
				
	}

}
