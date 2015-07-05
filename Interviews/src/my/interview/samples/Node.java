package my.interview.samples;

public class Node implements Comparable<Node>{

	public String data;
	public boolean isVisited;


	public Node(String data)
	{
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", isVisited=" + isVisited + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Node)obj).data.equals(this.data);
	}

	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.data.compareTo(o.data);
	}
}
