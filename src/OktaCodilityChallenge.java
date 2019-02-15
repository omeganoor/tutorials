import java.util.ArrayList;
import java.util.List;

public class OktaCodilityChallenge {

	/* A utility function that returns minimum of 3 integers */
	private static int min(int x, int y, int z) {
		if (x < y)
			return (x < z) ? x : z;
		else
			return (y < z) ? y : z;
	}

	private static int minCost(int cost[][], int m, int n) {
		int i, j;
		int tc[][] = new int[m + 1][n + 1];

		tc[0][0] = cost[0][0];

		/* Initialize first column of total cost(tc) array */
		for (i = 1; i <= m; i++) {
			tc[i][0] = tc[i - 1][0] + cost[i][0];
			// System.out.println(tc[i][0] +" "+tc[i-1][0] +" "+ cost[i][0]);
		}

		/* Initialize first row of tc array */
		for (j = 1; j <= n; j++)
			tc[0][j] = tc[0][j - 1] + cost[0][j];

		/* Construct rest of the tc array */
		for (i = 1; i <= m; i++)
			for (j = 1; j <= n; j++)
				tc[i][j] = min(tc[i - 1][j - 1], tc[i - 1][j], tc[i][j - 1]) + cost[i][j];
		for (int k = 0; k < tc.length; k++) {
			for (int k2 = 0; k2 < tc.length; k2++) {
				System.out.print(tc[k][k2] + " ");
			}
			System.out.println();
		}
		return tc[m][n];
	}

	/* Driver program to test above functions */
	public static void main(String args[]) {
		int cost[][] = { { 10, 1, 10, 1 }, { 1, 1, 1, 10 }, { 10, 1, 10, 1 }, { 1, 10, 1, 1 } };

		System.out.println(solution(cost));
	}

	public static int solution(int[][] A) {
		List<String> allPath = new ArrayList<>();
		int N = A.length;
		int res = 0;
		robotPaths(N - 1, N - 1, "", allPath);
		for (String str : allPath) {
			int result = 1;
			String[] index = str.split("  ");
			for (String in : index) {
				int i = Integer.valueOf(in.trim().split(",")[0]);
				int j = Integer.valueOf(in.trim().split(",")[1]);
				result = result * A[i][j];
			}
			int numZeroes = String.valueOf(result).length() - String.valueOf(result).replaceAll("0+$", "").length();

			if (numZeroes > 0) {
				if (res == 0) {
					res = numZeroes;
				} else {
					res = Math.min(res, numZeroes);
				}
			}
		}
		return res;
	}

	static int numberOfPaths(int m, int n) {
		// Create a 2D table to store results
		// of subproblems
		int count[][] = new int[m][n];

		// Count of paths to reach any cell in
		// first column is 1
		for (int i = 0; i < m; i++)
			count[i][0] = 1;

		// Count of paths to reach any cell in
		// first column is 1
		for (int j = 0; j < n; j++)
			count[0][j] = 1;

		// Calculate count of paths for other
		// cells in bottom-up manner using
		// the recursive solution
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++)

				// By uncommenting the last part the
				// code calculatest he total possible paths
				// if the diagonal Movements are allowed
				count[i][j] = count[i - 1][j] + count[i][j - 1]; // + count[i-1][j-1];

		}
		for (int k = 0; k < count.length; k++) {
			for (int k2 = 0; k2 < count.length; k2++) {
				System.out.print(count[k][k2] + " ");
			}
			System.out.println();
		}
		return count[m - 1][n - 1];
	}

	public static int robotPaths(int down, int right, String path, List<String> allPath) {
		path = path + down + "," + right + "  ";
		if (down == 0 && right == 0) {
			allPath.add(path);
			return 1;
		}

		int counter = 0;

		if (down == 0)
			counter = robotPaths(down, right - 1, path, allPath);
		else if (right == 0)
			counter = robotPaths(down - 1, right, path, allPath);
		else
			counter = robotPaths(down, right - 1, path, allPath) + robotPaths(down - 1, right, path, allPath);

		return counter;
	}

}

class RobotPaths {

	public static int robotPaths(int down, int right, String path, List<String> allPath) {
		path = path + down + "," + right + "  ";
		if (down == 0 && right == 0) {
			// System.out.println(path);
			allPath.add(path);
			// System.out.println();
			return 1;
		}

		int counter = 0;

		if (down == 0)
			counter = robotPaths(down, right - 1, path, allPath);
		else if (right == 0)
			counter = robotPaths(down - 1, right, path, allPath);
		else
			counter = robotPaths(down, right - 1, path, allPath) + robotPaths(down - 1, right, path, allPath);

		return counter;
	}

}
