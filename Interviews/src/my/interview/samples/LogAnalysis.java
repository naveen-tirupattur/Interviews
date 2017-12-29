package my.interview.samples;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] users = new String[1000];
		String[] pages = new String[5000];
		boolean[][] pages2Users = new boolean[5000][1000];
		int userIndex = -1, pageIndex = -1;
		int[][] pages2Pages = new int[5000][5000];

		// Read the file line by line
		BufferedReader br;
		try {

			// Initialize the arrays
			for (int i = 0; i < users.length; i++) {
				users[i] = "";
			}
			for (int i = 0; i < pages.length; i++) {
				pages[i] = "";
			}
			for (int i = 0; i < pages2Pages.length; i++) {
				for (int j = 0; j < pages2Pages[0].length; j++) {
					pages2Pages[i][j] = -1;
				}
			}

			br = new BufferedReader(
					new FileReader("/Users/naveenit7sep/MyWorkspace/Interviews/src/my/interview/samples/Sample.text"));
			String line;
			while ((line = br.readLine()) != null) {
				// process the line.
				String[] data = line.split(",");
				String page = data[1];
				String user = data[2];

				// find the index for page in pages array, if not found insert the page
				int tempPageIndex = findIndex(pages, page);
				if (tempPageIndex == -1) {
					pageIndex++;
					tempPageIndex = pageIndex;

				}
				pages[tempPageIndex] = page;

				// find the index for user in users array, if not found insert the user
				int tempUserIndex = findIndex(users, user);
				if (tempUserIndex == -1) {
					userIndex++;
					tempUserIndex = userIndex;
				}
				users[tempUserIndex] = user;

				// Insert 1 in pages2Users matrix
				pages2Users[tempPageIndex][tempUserIndex] = true;

			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Now we have a matrix of users per page. Generate pairs for all pages
		for (int i = 0; i < pages2Users.length; i++) {
			for (int j = 0; j < pages2Users.length; j++) {
				int affinity = 0;
				for (int k = 0; k < pages2Users[0].length; k++) {

					// ignore the diagonal elements
					if (i != j) {
						if (pages2Users[i][k] && pages2Users[j][k]) {
							affinity++;
						}
					}
				}

				pages2Pages[i][j] = affinity;

			}
		}

		// Now you have a matrix of affinity for each combination. just find the
		// maximum one

		int maxAffinity = 0, pageI = -1, pageJ = -1;
		for (int i = 0; i < pages2Pages.length; i++) {
			for (int j = 0; j < pages2Pages[0].length; j++) {
				// ignore the diagonal elements
				if (i != j) {
					if (pages2Pages[i][j] > maxAffinity) {
						maxAffinity = pages2Pages[i][j];
						pageI = i;
						pageJ = j;
					}

				}
			}
		}

		System.out.println("Maximum affinity for pages: " + pageI + " and " + pageJ + " with value " + maxAffinity);

	}

	public static int findIndex(String[] dataArray, String key) {

		for (int i = 0; i < dataArray.length; i++) {
			if (dataArray[i].equals(key))
				return i;
		}
		return -1;
	}

}
