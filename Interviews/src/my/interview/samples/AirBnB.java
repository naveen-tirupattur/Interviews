package my.interview.samples;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.

 Given
An array of strings where "L" indicates land and "W" indicates water, and a coordinate marking a starting point in the middle of the ocean

The Challenge:
Find and mark the ocean in the map by changing appropriate W's to O's. An ocean coordinate is defined to be any coordinate directly adjacent to any other ocean coordinate.

Input:
LLLLLLLLLLLLLLLLLLLL
LLLLLLLLLLLLLLWLLLLL
LLWWLLLLLLLLLLLLLLLL
LLWWLLLLLLLLLLLLLLLL
LLLLLLLLLLLLLLLLLLLL
LLLLLLLWWLLLLLLLLLLL
LLLLLLLLWWLLLLLLLLLL
LLLLLLLLLWWWLLLLLLLL
LLLLLLLLLLWWWWWWLLLL
LLLLLLLLLLWWWWWWLLLL
LLLLLLLLLLWWWWWWLLLL
LLLLWWLLLLWWWWWWLLLL
LLLLWWWLLLWWWWWWWWWW
LLLLLWWWWWWWWWWWLLLL
LLLLLLLLLLLLLLWWWWLL
LLLLLLLLLLLLLLWLLLLL
LLLLWLLLLLLLLLLLLWLL
LLLLLLLLLLLLLLLLLLWL

Ocean Coordinate:
row = 10
col = 12

Expected Output:
LLLLLLLLLLLLLLLLLLLL
LLLLLLLLLLLLLLWLLLLL
LLWWLLLLLLLLLLLLLLLL
LLWWLLLLLLLLLLLLLLLL
LLLLLLLLLLLLLLLLLLLL
LLLLLLLOOLLLLLLLLLLL
LLLLLLLLOOLLLLLLLLLL
LLLLLLLLLOOOLLLLLLLL
LLLLLLLLLLOOOOOOLLLL
LLLLLLLLLLOOOOOOLLLL
LLLLLLLLLLOOOOOOLLLL
LLLLOOLLLLOOOOOOLLLL
LLLLOOOLLLOOOOOOOOOO
LLLLLOOOOOOOOOOOLLLL
LLLLLLLLLLLLLLOOOOLL
LLLLLLLLLLLLLLOLLLLL
LLLLWLLLLLLLLLLLLWLL
LLLLLLLLLLLLLLLLLLWL


 */

class Solution {

	public static void printOcean(List<String> points, int stringLength, int rowPoint, int colPoint) {

		// Store the points as 2D Array
		char[][] pointsMap = new char[points.size()][stringLength];
		int row = 0, col = 0;

		// Fill the 2D Array
		for (String s : points) {
			for (int i = 0; i < s.length(); i++) {
				pointsMap[row][col++] = s.charAt(i);
			}
			row++;
			col = 0;
		}

		// Update the points to oceans
		updateOceanPoints(pointsMap, rowPoint, colPoint);

		for (int i = 0; i < pointsMap.length; i++) {
			for (int j = 0; j < pointsMap[0].length; j++) {
				System.out.print(pointsMap[i][j]);
			}
			System.out.print("\n");
		}

	}

	public static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

	}

	public static List<Point> getAdjacentPoints(int row, int col, char[][] pointsMap) {

		List<Point> adjacentPoints = new ArrayList<Point>();

		if (row != pointsMap.length - 1)
			adjacentPoints.add(new Point(row + 1, col));

		if (row != 0)
			adjacentPoints.add(new Point(row - 1, col));

		if (col != pointsMap[0].length - 1)
			adjacentPoints.add(new Point(row, col + 1));

		if (col != 0)
			adjacentPoints.add(new Point(row, col - 1));

		if (row != pointsMap.length - 1 && col != pointsMap[0].length - 1)
			adjacentPoints.add(new Point(row + 1, col + 1));

		if (row != pointsMap.length - 1 && col != 0)
			adjacentPoints.add(new Point(row + 1, col - 1));

		if (row != 0 && col != 0)
			adjacentPoints.add(new Point(row - 1, col - 1));

		if (row != 0 && col != pointsMap[0].length - 1)
			adjacentPoints.add(new Point(row - 1, col + 1));

		return adjacentPoints;

	}

	public static void updateOceanPoints(char[][] pointsMap, int row, int col) {

		char c = pointsMap[row][col];

		// If the position is water, compute the adjacent points
		if (c == 'W') {
			// Update the point
			pointsMap[row][col] = 'O';

			List<Point> adjacentPoints = getAdjacentPoints(row, col, pointsMap);
			// For each adjacent point
			for (Point p : adjacentPoints) {
				// Check if adjacent point is Land
				if (pointsMap[p.row][p.col] == 'W')
					updateOceanPoints(pointsMap, p.row, p.col);

			}
		}

	}

	public static void main(String[] args) {
		List<String> points = new ArrayList<String>();
		String s = "LLLLLLLLLLLLLLLLLLLL";
		points.add("LLLLLLLLLLLLLLLLLLLL");
		points.add("LLLLLLLLLLLLLLWLLLLL");
		points.add("LLWWLLLLLLLLLLLLLLLL");
		points.add("LLWWLLLLLLLLLLLLLLLL");
		points.add("LLLLLLLLLLLLLLLLLLLL");
		points.add("LLLLLLLWWLLLLLLLLLLL");
		points.add("LLLLLLLLWWLLLLLLLLLL");
		points.add("LLLLLLLLLWWWLLLLLLLL");
		points.add("LLLLLLLLLLWWWWWWLLLL");
		points.add("LLLLLLLLLLWWWWWWLLLL");
		points.add("LLLLLLLLLLWWWWWWLLLL");
		points.add("LLLLWWLLLLWWWWWWLLLL");
		points.add("LLLLWWWLLLWWWWWWWWWW");
		points.add("LLLLLWWWWWWWWWWWLLLL");
		points.add("LLLLLLLLLLLLLLWWWWLL");
		points.add("LLLLLLLLLLLLLLWLLLLL");
		points.add("LLLLWLLLLLLLLLLLLWLL");
		points.add("LLLLLLLLLLLLLLLLLLWL");

		printOcean(points, s.length(), 10, 12);

	}
}
