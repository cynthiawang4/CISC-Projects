package gameoflife;

import java.util.Random;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
	
	/**
     * Returns true if the cell {@code cells[row][col]} becomes alive in the next
     * generation, false otherwise. The method does not modify the array; it
     * simply determines whether or not a cell should become alive in the 
     * next generation.
     * 
     * <p>
     * A cell becomes alive if it is currently dead and its neighborhood has
     * exactly 3 alive cells.
     * 
     * @param cells a two-dimensional array
     * @param row   a row index
     * @param col   a column index
     * @return true if the cell {@code cells[row][col]} becomes alive in the next
     *         generation, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for
     *                                   cells
     */
    public static boolean isBorn(boolean[][] cells, int row, int col) {
    	if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
    	
    	boolean[][] nhood = Life2.neighborhood(cells, row, col);
    	int nAlive = Life2.numAlive(nhood);
    	
    	return (!Life2.isAlive(cells, row, col) && nAlive == 3);
    }

    /**
     * Returns true if the cell {@code cells[row][col]} survives into the next
     * generation, false otherwise. The method does not modify the array; it
     * simply determines whether or not a cell should remain alive in the 
     * next generation.
     * 
     * A cell survives into the next generation if it is currently alive and if its
     * 8 neighbors have 2 or 3 alive cells.
     * 
     * <p>
     * @param cells a two-dimensional array
     * @param row   a row index
     * @param col   a column index
     * @return true if the cell {@code cells[row][col]} survives into the next
     *         generation, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for
     *                                   cells
     */
    public static boolean survives(boolean[][] cells, int row, int col) {
    	if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
    	
    	boolean[][] nhood = Life2.neighborhood(cells, row, col);
    	int nAlive = Life2.numAlive(nhood);
    	
    	return (Life2.isAlive(cells, row, col) && nAlive == 3
    			|| Life2.isAlive(cells, row, col) && nAlive == 4);
    }
    
    /**
     * Updates {@code cells} so that it is equal to the next generation of cells.
     * 
     * <p>
     * See the assignment document for details.
     * 
     * @param cells a two-dimensional array
     */
    public static void evolve(boolean[][] cells) {
    	boolean[][] copy = Life2.clone(cells);
    	
    	for (int r = 0; r < copy.length; r++) {
    		for (int i = 0; i < copy[r].length; i++) {
    			if (!copy[r][i] && Life2.isBorn(copy, r, i)) {
    				cells[r][i] = true;
    			}
    			else if (copy[r][i] && !Life2.survives(copy, r, i)) {
    				cells[r][i] = false;
    			}
    		}
    	}
    }
    
    /**
     * Randomly sets each element of {@code cells} to true or false with
     * equal probability.
     * 
     * @param cells a two-dimensional array
     */
    public static void randomize(boolean[][] cells) {
    	Random random = new Random();
    	
    	for (int r = 0; r < cells.length; r++) {
    		for (int i = 0; i < cells[r].length; i++) {
    			cells[r][i] = random.nextBoolean();
    		}
    	}
    }
    
    /**
     * Inserts a pattern of cells into another array of cells. The pattern replaces
     * the elements in {@code cells} starting at {@code cells[row][col]}. The
     * pattern must fit completely within the array {@code cells}, otherwise no
     * cells are replaced and false is returned.
     * 
     * @param pattern a 2d array of replacement cells
     * @param row     the row index of the upper-left corner of cells where the
     *                replacement should begin
     * @param col     the column index of the upper-left corner of cells where the
     *                replacement should begin
     * @param cells   a 2d array of cells
     * @return true if the pattern fits within cells, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for cells
     */
    public static boolean insert(boolean[][] pattern, int row, int col, boolean[][] cells) {
    	int pRows = pattern.length;
    	int pCols = pattern[0].length;
    	
    	if (!Life2.isValid(cells, row, col)) {
			throw new IllegalArgumentException();
		}
    	else if (!Life2.isValid(cells, pRows + row - 1, pCols + col - 1)) {
    		return false;
    	}
    	else {
    		for (int r = 0; r < pRows; r++) {
    			for (int c = 0; c < pCols; c++) {
    				cells[row + r][col + c] = pattern[r][c];
    			}
    		}
    		return true;
    	}
    }
    
    /**
     * Reads a pattern of cells from a file. The pattern format is identical
     * to the output of {@code printCells}. The pattern files must be located
     * in the {@code patterns} folder of the eclipse project.
     * 
     * @param filename the filename of a pattern file
     * @return a 2d array of cells
     */
    public static boolean[][] read(String filename) {
        try {
            Path path = FileSystems.getDefault().getPath("patterns", filename);
            List<String> lines = Files.readAllLines(path);
            
            String firstLine = lines.get(0);
            int rows = lines.size();
            int cols = firstLine.length();
            boolean[][] cells = new boolean[rows][cols];
            
            for (int r = 0; r < rows; r++) {
            	String currentLine = lines.get(r);
    			for (int c = 0; c < cols; c++) {
    				if (currentLine.charAt(c) == '#') {
    					cells[r][c] = true;
    				}
    				else if (currentLine.charAt(c) == '-') {
    					cells[r][c] = false;
    				}
    			}
    		}
            return cells;
        } catch (Exception ex) {
            // some sort of error occurred while reading the file
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
