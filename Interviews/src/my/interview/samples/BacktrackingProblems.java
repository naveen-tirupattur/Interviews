package my.interview.samples;

import java.util.ArrayList;
import java.util.List;

import my.interview.samples.BacktrackingProblems.Board.BoardType;

public class BacktrackingProblems {

	public static void main(String[] args) {
		doKCombinations(2);
	}
	
	public static void doKCombinations(int k) {
		int[] a = new int[k];
		int[] s = {1,2,3,4};
		printKCombinations(a,0,k,s);
	}
	
	public static void printKCombinations(int[] a, int cnt, int k, int[] s) {
		if(cnt == k) { printKCombs(a,s); return;}
		List<Integer> c = findKCandidates(a, cnt, s, k);
		for(int i=0;i<c.size();i++) {
			a[cnt] = c.get(i);
			printKCombinations(a,cnt+1,k,s);
		}
	}
	
	public static List<Integer> findKCandidates(int[] a, int cnt, int[] s, int k)
	{	List<Integer> c = new ArrayList<Integer>();
		for (int i=cnt; i<s.length ; i++) {
			c.add(i);
		}
		return c;
	}
	
	public static void printKCombs(int[] a , int[] s) {
		for(int i=0;i<a.length;i++) {
			System.out.println(s[a[i]]);
		}
		System.out.println("\n");
	}
	
	public static void doPermutations() {
		int[] a = new int[4];
		int[] s = {1,1,2,2};
		printPermutations(a, 0, s);
	}
	
	public static void printPermutations(int[] a, int k, int[] s) {
		if(k==s.length) { printPerms(a, k, s); return; }
		List<Integer> c = findCandidates(a,k, s.length, s);
		for(int i=0;i<c.size();i++) {
			// For priting all derangements 
			//if(k+1==s[i]) continue;
			//For generating all permutations of multi-set, 
			//if you have seen similar element in this position before then don't go down this path
			a[k] = c.get(i);
			printPermutations(a, k+1, s);
		}
		
	}
	
	public static void doCombinations() {
		boolean[] a = new boolean[3];
		int[] s = {1,2,3};
		printCombinations(a,0,s,3);
	}
	
	public static void printCombinations(boolean[] a, int k, int[] s, int m) {
		if(k == s.length) { printCombs(a,k,s); return ;}
		boolean[] c = findCandidates();
		for(int i=0;i<c.length;i++) {
			a[k] = c[i];
			printCombinations(a,k+1,s,m);
		}
	}
	
	public static boolean[] findCandidates() {
		return new boolean[] {true, false};
	}
	public static void printCombs(boolean[] a, int k, int[] s) {
		for(int i=0;i<a.length;i++) {
			if(a[i]) System.out.println(s[i]);
		}
		System.out.println("\n");
	}
	
	public static List<Integer> findCandidates(int[] a, int k, int n, int[] s) {
		//int[] c= new int[n-k];
		List<Integer> c = new ArrayList<Integer>();
		boolean[] in_perm = new boolean[n];
		//To print only unique permutations
		boolean[] is_seen = new boolean[n+1];
		for(int i=0;i<k;i++) {
			in_perm[a[i]] = true;
		}
		
		int count=0;
		for(int i=0;i<n;i++) {
			if(!in_perm[i] && !is_seen[s[i]]) {
				//c[count++]=i;
				c.add(i);
				is_seen[s[i]] = true;
				
			}
		}
		return c;
	}

	public static void printPerms(int[] a, int k, int[] s)
	{
		for(int i=0;i<k;i++) {
			System.out.println(s[a[i]]);
		}
		System.out.println("\n");
	}
	
	public static void solveChess() {
		Board b = initChessBoard();
		int[] a = new int[b.dimension];
		solveNQueens(a, 0, b);
	}
	
	public static void solveSudoku() {
		Board b = initSudokuBoard();
		int[] a = new int[b.dimension*b.dimension];
		sudoku(a,0,b);
	}

	public static class Board {
		int dimension;
		int[][] elements;
		int freeElements;
		Point[] moves;
		boolean finished = false;
		boolean[][] isTaken;
		static enum BoardType {
			sudoku,chess
		}

		public Board(int dimension, BoardType bType) {
			this.dimension = dimension;
			if(bType.equals(BoardType.sudoku)) {
				elements = new int[dimension][dimension];
			} else if(bType.equals(BoardType.chess)) {
				isTaken = new boolean[dimension][dimension];
			}
			freeElements = dimension*dimension;
			moves = new Point[dimension*dimension];
			for(int i=0;i<dimension*dimension;i++) {
				moves[i] = new Point(0,0);
			}
		}
	}

	public static class Point {
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static Board initChessBoard() {
		Board b = new Board(8,BoardType.chess);
		return b;
	}

	public static Board initSudokuBoard() {
		int [][] elements = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
				{5, 2, 0, 0, 0, 0, 0, 0, 0},
				{0, 8, 7, 0, 0, 0, 0, 3, 1},
				{0, 0, 3, 0, 1, 0, 0, 8, 0},
				{9, 0, 0, 8, 6, 3, 0, 0, 5},
				{0, 5, 0, 0, 9, 0, 6, 0, 0},
				{1, 3, 0, 0, 0, 0, 2, 5, 0},
				{0, 0, 0, 0, 0, 0, 0, 7, 4},
				{0, 0, 5, 2, 0, 6, 3, 0, 0}};

		Board b = new Board(9,BoardType.sudoku);
		b.elements = elements;
		b.freeElements = 49;
		return b;
	}

	public static void solveNQueens(int[] a, int column, Board b) {
		//Check if all columns have been visited
		if(column==b.dimension) { printChessBoard(b); b.finished=true;}

		//Find all possibleRows
		List<Integer> rows = findRows(a, column, b);
		if(rows == null) return;

		//Try each row and recurse
		for(int i=0;i<rows.size();i++) {
			a[column] = rows.get(i);
			placeQueen(a, column, b);
			solveNQueens(a,column+1,b);
			removeQueen(a,column, b);
			if(b.finished) return;
		}
	}

	public static void removeQueen(int[] a,int column, Board b) {
		b.isTaken[a[column]][column] = false;
	}

	public static void placeQueen(int[] a, int column, Board b) {
		b.isTaken[a[column]][column] = true;
	}

	//Find all possible rows where the queen can be placed
	public static List<Integer> findRows(int[] a, int column, Board b) {
		List<Integer> rows = new ArrayList<Integer>();
		for(int i=0;i<b.dimension;i++) {
			if(isRowSafe(i,b) && isDiagonalSafe(i,column, b)) {
				rows.add(i);
			}
		}
		return rows;
	}

	//Check if there is a queen in this row
	public static boolean isRowSafe(int row, Board b) {
		for(int i=0;i<b.dimension;i++) {
			if(b.isTaken[row][i]) return false;
		}
		return true;
	}

	//Check to left and top of the current row,column
	public static boolean isUpperDiagonalSafe(int row, int column, Board b) {
		for(int i = row,j=column; i>=0 && j>=0 ;i--,j--) {
			if(b.isTaken[i][j]) return false;
		}
		return true;
	}

	//Check to left and bottom of the current row,column
	public static boolean isLowerDiagonalSafe(int row, int column, Board b) {
		for(int i = row,j=column; i<b.dimension && j>=0 ;i++,j--) {
			if(b.isTaken[i][j]) return false;
		}
		return true;
	}

	//Check if there is queen in the diagonal to the left of current column (since we are filling column by column)
	public static boolean isDiagonalSafe(int row, int column, Board b) {
		return isUpperDiagonalSafe(row, column, b) && isLowerDiagonalSafe(row, column, b);
	}

	public static void printChessBoard(Board b) {
		
		for(int i=0;i<b.dimension;i++) {
			String s="";
			for(int j=0;j<b.dimension;j++) {
				if(b.isTaken[i][j]) s+=" 1 ";
				else s+=" 0 ";
			}
			System.out.println(s);
		}
	}

	public static void sudoku(int[] a, int k, Board b) {
		if(b.freeElements==0) {
			printSudokuBoard(b);
			b.finished = true;
		}

		List<Integer> c = findCandidates(a,k, b);
		if(c == null) return;
		for(int i =0;i<c.size();i++) {
			a[k] = c.get(i);
			fillSquare(b.moves[k].x, b.moves[k].y, a[k], b);
			sudoku(a,k+1,b);
			freeSquare(b.moves[k].x,b.moves[k].y,b);
			if(b.finished) return;
		}
	}


	public static void fillSquare(int x, int y, int value, Board b) {
		b.elements[x][y] = value;
		b.freeElements--;
	}

	public static void freeSquare(int x, int y, Board b) {
		b.elements[x][y] = 0;
		b.freeElements++;
	}

	public static List<Integer> findCandidates(int[] a, int k, Board b) {
		List<Integer> c = new ArrayList<Integer>();

		Point p = findFreeSquare(b);
		if(p == null) return null;

		b.moves[k].x = p.x;
		b.moves[k].y = p.y;

		for(int i = 1;i<b.dimension+1;i++) {
			if(isValidPosition(p.x,p.y,i,b)) c.add(i);
		}

		return c;
	}

	public static boolean isValidPosition(int x, int y, int value, Board b) {
		return isValidRow(x,value,b) && isValidColumn(y, value,b) && isValidSquare(x-x%3, y-y%3, value,b);
	}

	public static boolean isValidRow(int row, int value, Board b) {
		for(int j=0;j<b.dimension;j++) {
			if(b.elements[row][j] == value) return false;
		}
		return true;
	}

	public static boolean isValidColumn(int column, int value, Board b) {
		for(int i=0;i<b.dimension;i++) {
			if(b.elements[i][column] == value) return false;
		}
		return true;
	}

	public static boolean isValidSquare(int stRow, int stColumn, int value, Board b) {
		for(int i=stRow;i<stRow+3;i++) {
			for(int j=stColumn;j<stColumn+3;j++) {
				if(b.elements[i][j] == value) return false;
			}
		}
		return true;
	}

	public static Point findFreeSquare(Board b) {
		for(int i=0;i<b.dimension;i++) {
			for(int j=0;j<b.dimension;j++) {
				if(b.elements[i][j] == 0) { return new Point(i,j); }
			}
		}
		return null;
	}
	public static void printSudokuBoard(Board b) {
		for(int i=0;i<b.dimension;i++) {
			String row = new String("");
			for(int j=0;j<b.dimension;j++) {
				row+= b.elements[i][j]+" ";
			}
			System.out.println(row);
		}
	}


}
