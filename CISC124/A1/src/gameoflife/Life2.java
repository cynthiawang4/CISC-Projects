package gameoflife;

public class Life2 {

	public static int numRows(boolean[][] cells) {
		return cells.length;
	}
	
	public static int numCols(boolean[][] cells) {
		if (Life2.numRows(cells) > 0) {
			return cells[0].length;
		}
		return 0;
	}
	
	public static boolean isValid(boolean[][] cells, int row, int col) {
		if (row < 0 || row >= Life2.numRows(cells)) {
			return false;
		}
		if (col < 0 || col >= Life2.numCols(cells)) {
			return false;
		}
		return true;
	}
	
	public static boolean[][] clone(boolean[][] cells) {
		int rows = Life2.numRows(cells);
		int cols = Life2.numCols(cells);
		boolean[][] copy = new boolean[rows][cols];
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				copy[r][c] = cells[r][c];
			}
		}
		return copy;
	}
	
	public static void printCells(boolean[][] cells) {
		for (boolean[] row : cells) {
			for (boolean c : row) {
				if (c) {
					System.out.print("#");
				}
				else {
					System.out.print("-");
				}
			}
			System.out.println("");
		}
	}
	
	public static boolean[][] neighborhood(boolean[][] cells, int row, int col) {
		if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		
		boolean[][] nhood = new boolean[3][3];
		
		int left = col - 1;
		int top = row - 1;
		
		for (int r = 0; r < 3; r++) {
			int cellsRow = top + r;
			for (int c = 0; c < 3; c++) {
				int cellsCol = left + c;
				if (Life2.isValid(cells, cellsRow, cellsCol)) {
					nhood[r][c] = cells[cellsRow][cellsCol];
				}
			}
		}
		return nhood;
	}
	
	/**
	 * Returns true if the specified cell is alive.
	 * 
	 * @param cells a two-dimensional array
	 * @param row a row index
	 * @param col a column index
	 * @return true if the specified cell is alive
	 * @throws IllegalArgumentException if row or col is not a valid index for
	 *                                   cells
	 */
	public static boolean isAlive(boolean[][] cells, int row, int col) {
		if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
		return cells[row][col];
	}
	
	public static int numAlive(boolean[][] cells) {
		int nAlive = 0;
		
		for (boolean[] row : cells) {
			for (boolean elem : row) {
				if (elem) {
					nAlive += 1;
				}
			}
		}
		return nAlive;
	}
}
