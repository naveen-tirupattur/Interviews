package my.interviews2017.graphs;

public class FlipColor {

	public static void main(String[] args) {
		int[][] grid = {{1,0,0}, {0,1,1}, {0,0,1}};
		flipColor(1, 1, grid);
		System.out.println(grid);
	}
	

	// If entry is 1 and it's neighbors are 1 flip them to 0, do it recursively 
	public static void flipColor(int x, int y, int[][] grid) {
		
		if (x<0 || x>=grid.length || y <0 || y >=grid[0].length || grid[x][y] == 0) return;
		
		grid[x][y] = 0;
		
		flipColor(x+1,y,grid);
		flipColor(x-1,y,grid);
		flipColor(x,y+1,grid);
		flipColor(x,y-1,grid);
		
	}

}
