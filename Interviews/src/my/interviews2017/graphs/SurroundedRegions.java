package my.interviews2017.graphs;

public class SurroundedRegions {

	public static void main(String[] args) {
		
		char[][] grid = {{'X', 'X', 'X',  'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X',  'O', 'X', 'X'}};
		
		char[][] grid1 = {{'O', 'O', 'O'},{'O', 'O', 'O'},{'O', 'O', 'O'}};
		solve(grid1);
		
		System.out.println(grid1);
		
	}
	
	public static void solve(char[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		// Mark the boundary nodes as safe
		for (int i=0;i<grid.length;i++) { // Row wise
			int left = 0, right = grid[0].length-1;
			if (grid[i][left] == 'O' && !visited[i][left]) {
				solveUtil(grid, visited, i, left); // Mark their neighbors also safe
			}
			if (grid[i][right] == 'O' && !visited[i][right]) {
				solveUtil(grid, visited, i, right);
			}
		}
		
		for (int i=0;i<grid[0].length;i++) { // Column wise
			int left = 0, right = grid.length-1;
			if (grid[left][i] == 'O' && !visited[left][i]) {
				solveUtil(grid, visited, left, i); // Mark their neighbors also safe
			}
			if (grid[right][i] == 'O' && !visited[right][i]) {
				solveUtil(grid, visited, right,i);
			}
		}
		
		// Update all the non boundary nodes
		for (int i=1;i<grid.length;i++) {
			for (int j=1;j<grid[0].length;j++) {
				if (grid[i][j] == 'O' && !visited[i][j]) {
					visited[i][j] = true;
					grid[i][j] = 'X';
					
				}
			}
		}
	}
	
	public static void solveUtil(char[][] grid, boolean[][] visited, int x, int y) {
		
		// Check if it is a valid coordinate
		if (x < 0 || x>= grid.length || y <0 || y >=grid[0].length || visited[x][y] || (grid[x][y] == 'X')) return ;
		
		visited[x][y] = true;
		
		// Recursively check it's neighbors
		solveUtil(grid, visited, x+1, y);
		solveUtil(grid, visited, x-1, y);
		solveUtil(grid, visited, x, y+1);
		solveUtil(grid, visited, x, y-1);
		
	}

}
