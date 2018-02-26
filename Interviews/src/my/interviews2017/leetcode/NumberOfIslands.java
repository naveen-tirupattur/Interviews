package my.interviews2017.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfIslands {

	public static void main(String[] args) {
		char[][] grid = {{'1', '1', '0', '0', '0'},
        {'0', '1', '0', '0', '1'},
        {'1', '0', '0', '1', '1'},
        {'0', '0', '0', '0', '0'},
        {'1', '0', '1', '0', '1'}}; 
		//System.out.println(numIslands(grid));
		System.out.println(numIslands1(grid));
	}
	 
	public static class Pair {
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		int x,y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// Use BFS to update the neighbors
	public static void update(char[][] grid, int x, int y) {
		
		Queue<Pair> queue = new ArrayDeque<Pair>();
		queue.add(new Pair(x,y));
		while (!queue.isEmpty()) {
			Pair p = queue.remove();
			
			// If the coordinate has been visited or invalid then skip
			if (p.x < 0 || p.x>=grid.length || p.y <0 || p.y>=grid[0].length || grid[p.x][p.y] != '1') continue;
			
			// Mark the coordinate to visited
			grid[p.x][p.y] = '0';
			
			// Check it's neighbors
			queue.add(new Pair(p.x+1,p.y));
			queue.add(new Pair(p.x-1,p.y));
			queue.add(new Pair(p.x,p.y+1));
			queue.add(new Pair(p.x,p.y-1));

		}
	}
	
	public static int numIslands1(char[][] grid) {
		int count = 0;
		// Check all positions in the graph and update the count;
		for (int i=0;i<grid.length;i++) {
			for (int j=0;j<grid[0].length;j++) {
				if (grid[i][j] == '1') {
					count++;
					update(grid, i, j);
				}
			}
		}
		return count;
	}
	
	public static int numIslands(char[][] grid) {
		int count = 0;
		for (int i=0;i<grid.length;i++) {
			for (int j=0;j<grid[0].length;j++) {
				if (grid[i][j] == '1') {
					count++;
					traverse(grid,i,j);
				}
			}
		}
		return count;
	}
	
	
	public static void traverse(char[][] grid, int x, int y) {
		
		if (x < 0 || x>=grid.length || y <0 || y>=grid[0].length || grid[x][y] != '1') return;
		
		grid[x][y] = '0';		
		
		traverse(grid, x+1, y);
		traverse(grid, x-1, y);
		traverse(grid, x, y+1);
		traverse(grid, x, y-1);
		
	}

}
