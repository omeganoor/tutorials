import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/*
public class Maze3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
*/

final class Coordinate3 {

	private final int row;
	private final int col;

	public Coordinate3(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getX() {
		return row;
	}

	public int getY() {
		return col;
	}

	public boolean equals(Object o) {
		/**
		 * http://stackoverflow.com/questions/6364258/override-equals-method NOTE: order
		 * of this check is designed for efficiency
		 */

		/**
		 * easiest. if this results in true then we prevented a lot of hassles.
		 * comparisons can be made without any fear NPE or classcast
		 */
		if (this == o)
			return true;

		/**
		 * Mechanisms to prevent popping up exceptions. Ideally such checks better be
		 * performed at higher level.
		 */
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;

		// now to the check.
		final Coordinate3 coordinate = (Coordinate3) o;
		return coordinate.row == row && coordinate.col == col;
	}

	public int hashCode() {
		return row + col;
	}

	@Override
	public String toString() {
		return row + " : " + col;
	}
}

public class Maze3 {

	private final int[][] maze;

	public Maze3(int[][] maze) {
		if (maze.length == 0)
			throw new IllegalArgumentException("The maze is empty.");
		this.maze = maze;
	}

	/**
	 * Returns the solution to the maze. Of the multiple sources and destinations,
	 * any single path from any source to any destination should be returned. The
	 * path need not be optimal / shortest.
	 * 
	 * @return the set which is the solution to the maze.
	 */
	public Set<Coordinate3> solve() {

		/* trying to find entry point in the first and last row. */
		final Set<Coordinate3> mazeSolution = new LinkedHashSet<Coordinate3>();

		for (int i = 0; i < maze[0].length; i++) {
			if (maze[0][i] == 1) {
				if (solvedMaze(0, i, mazeSolution)) {
					return mazeSolution;
				}
			}

			if (maze[maze.length - 1][i] == 1) {
				if (solvedMaze(0, i, mazeSolution)) {
					return mazeSolution;
				}
			}

		}

		/* trying to find entry point in the first and last column */
		for (int i = 0; i < maze.length; i++) {
			if (maze[i][0] == 1) {
				if (solvedMaze(i, 0, mazeSolution)) {
					System.out.println("case 3");
					return mazeSolution;
				}
			}

			if (maze[i][maze[0].length - 1] == 1) {
				if (solvedMaze(i, maze[0].length - 1, mazeSolution)) {
					return mazeSolution;
				}
			}
		}

		return Collections.emptySet();
	}

	private boolean solvedMaze(int row, int col, Set<Coordinate3> visitedSet) {
		boolean gotMaze = false;

		Coordinate3 coor = new Coordinate3(row, col);

		if (visitedSet.contains(coor)) {
			return false;
		}

		if (isExit(row, col, visitedSet)) {
			visitedSet.add(coor);
			return true;
		}

		visitedSet.add(coor);

		for (Direction dir : Direction.values()) {
			int newRow = row + dir.getY();
			int newCol = col + dir.getX();
			if (!gotMaze && isInBounds(newRow, newCol) && maze[newRow][newCol] == 1) {
				gotMaze = solvedMaze(newRow, newCol, visitedSet);
			}
		}

		if (!gotMaze) {
			visitedSet.remove(coor);
		}

		return gotMaze;
	}

	boolean isExit(int row, int col, Set<Coordinate3> visitedSet) {
		return ((row == 0) || (row == maze.length - 1) || ((col == maze[0].length - 1) || col == 0))
				&& !visitedSet.isEmpty();
	}

	boolean isInBounds(int row, int col) {
		return row >= 0 && col >= 0 && row < maze.length && col < maze[0].length;
	}

	private static enum Direction {
		NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

		int dx;
		int dy;

		private Direction(int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
		}

		public int getX() {
			return dx;
		}

		public int getY() {
			return dy;
		}
	}

	public static void main(String[] args) {
		int[][] m1 = { { 2, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 3, 1 } };

		for (Coordinate3 coord : new Maze3(m1).solve()) {
			System.out.println(coord);
		}

		System.out.println("-------------------------------------");

		int[][] m2 = { { 0, 0, 0, 0 }, { 0, 0, 1, 1 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } };

		for (Coordinate3 coord : new Maze3(m2).solve()) {
			System.out.println(coord);
		}

		System.out.println("-------------------------------------");

		int[][] m3 = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 1, 1, 1, 0 }, { 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 1 },
				{ 0, 0, 0, 0, 0 } };

		for (Coordinate3 coord : new Maze3(m3).solve()) {
			System.out.println(coord);
		}
	}
}