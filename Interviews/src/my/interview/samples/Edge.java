package my.interview.samples;

public class Edge implements Comparable<Edge> {

	public Node from, to;

	public double weight;

	public Edge(Node src, Node tgt, double weight) {
		this.from = src;
		this.to = tgt;
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Edge) obj).to.equals(this.to) && ((Edge) obj).from.equals(this.from);
	}

	@Override
	public int compareTo(Edge o) {

		if (this.equals(o))
			return 0;

		if (this.weight > o.weight)
			return 1;

		if (this.weight < o.weight)
			return -1;

		return 1;
	}

	public Node getTo() {
		return to;
	}

	public Node getFrom() {
		return from;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return this.from.data + " " + this.to.data + " " + this.weight;
	}
}
