/**
 * 
 */
package my.interview.samples;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author naveenit7sep
 *
 */
public class TrieNode {
	
	private char value;
	private Map<Character,TrieNode> children;
	private boolean isEnd;
	
	public char getC() {
		return value;
	}
	
	public Map<Character, TrieNode> getChildren() {
		return children;
	}
	
	public boolean isEnd() {
		return isEnd;
	}
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}
	
	public TrieNode(char c)
	{
		this.value = c;
		this.children = new TreeMap<>();
	}

}
