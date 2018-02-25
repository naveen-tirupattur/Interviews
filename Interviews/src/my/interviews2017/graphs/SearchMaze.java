package my.interviews2017.graphs;

import java.util.ArrayList;
import java.util.List;

public class SearchMaze {

	public static void main(String[] args) {

		int[][] graph = {{0,0,0},{1,1,0}, {0,0,0}};

		System.out.println(findPath(new Pair(2,0), new Pair(0,2), graph));

	}

	public static List<Pair> findPath(Pair start, Pair end, int[][] graph) {
		List<Pair> paths = new ArrayList<Pair>();
		find(start, end, graph, paths);
		return paths;
	}

	public static boolean find(Pair start, Pair end, int[][] graph, List<Pair> paths) {

		// Check if you are in a valid position
		if (start.x < 0 || start.x >= graph.length || start.y <0 || start.y >= graph[0].length) return false;

		// If you are in an already visited position
		if (graph[start.x][start.y]==1) return false;

		// If you have reached the destination then return
		if (start.x == end.x && start.y == end.y) { paths.add(start); return true;}
		
		// Add current position to traversed path so far
		paths.add(start);

		int x = start.x, y = start.y;
		
		// Mark current position as visited
		graph[x][y] = 1;

		// Check if you can reach via your neighbors
		if ( find(new Pair(x-1, y), end, graph, paths) || find(new Pair(x+1, y), end, graph, paths)
				|| find(new Pair(x, y+1), end, graph, paths) ||find(new Pair(x, y-1), end, graph, paths)) return true;

		// Remove this path as it is not leading to destination and return false;
		paths.remove(paths.size()-1);
		return false;

	}

	public static class Pair {

		int x,y;

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}

		public Pair (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
