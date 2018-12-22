/**
 * 
 */
package org.craft.demo.programming.excersises;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author asharma
 *
 */
public final class Matrix {

	private final int pathBlockingValue;
	private final int[][] matrixData;
	private final int numOfRows;
	private final int numOfCols;
	private boolean[][] visited;
	
	/**
	 * @param matrixData
	 */
	public Matrix(final int[][] inputMatrix, final int pathBlockingValue) {
		super();
		//Do the basic validations on the input.
		if (inputMatrix == null || inputMatrix.length == 0) {
			throw new IllegalArgumentException(
					"Input Matrix can not be null or empty. Please provide a valid argument value.");
		}
		this.matrixData = inputMatrix;
		this.numOfRows = matrixData.length;
		this.numOfCols = matrixData[0].length;
		this.pathBlockingValue = pathBlockingValue;
		this.visited = new boolean[numOfRows][numOfCols];
	}

	/**
	 * Tells whether the given row index and column index falls within the given
	 * Matrix boundaries.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private boolean isWithInMatrixBoundary(final int rowIndex, final int colIndex) {
		return (0 <= rowIndex && rowIndex < numOfRows) && (0 <= colIndex && colIndex < numOfCols);
	}

	/**
	 * Tells whether a movement to the cell at the given row and column index is
	 * possible.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private boolean isMovementPossible(final int rowIndex, final int colIndex) {
		return matrixData[rowIndex][colIndex] != pathBlockingValue;
	}

	/**
	 * Tells whether the cell at the given row and column index is already visited
	 * or not.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private boolean isAlreadyVisited(final int rowIndex, final int colIndex) {
		return visited[rowIndex][colIndex];
	}

	/**
	 * Sets a particular cell in the Matrix as visited so that it can be skipped in
	 * later traversals.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 * @return
	 */
	private void markAsVisited(final int rowIndex, final int colIndex) {
		visited[rowIndex][colIndex] = true;
	}

	/**
	 * Finds the minimum distance that one has to cover to reach the given destination starting from the given source in the Matrix.
	 * @param sourceCellRowIndex
	 * @param sourceCellColumnIndex
	 * @param destCellRowIndex
	 * @param destCellColumnIndex
	 * @return minimum distance or -1 if no path exists.
	 */
	public int findMinimumDistance(final int sourceCellRowIndex, final int sourceCellColumnIndex, final int destCellRowIndex, final int destCellColumnIndex) {
		//reset the visited matrix for each run of this method
		this.visited = new boolean[numOfRows][numOfCols];
		
		//Do the basic validations on the input.
		if(!isWithInMatrixBoundary(sourceCellRowIndex, sourceCellColumnIndex)) {
			throw new IllegalArgumentException("The Source cell indexes are not valid for the given input matrix. Please provide a valid row and column index for the source cell.");
		}
		if(!isWithInMatrixBoundary(destCellRowIndex, destCellColumnIndex)) {
			throw new IllegalArgumentException("The Destination cell indexes are not valid for the given input matrix. Please provide a valid row and column index for the destination cell.");
		}
		if(!isMovementPossible(sourceCellRowIndex, sourceCellColumnIndex)) {
			return -1;//implies that source is not valid and hence we can't find a path starting from this point.
		}
		if(!isMovementPossible(destCellRowIndex, destCellColumnIndex)) {
			return -1;//implies that source is not valid and hence we can't find a path starting from this point.
		}
		
		return findMinimumDistanceHelper(sourceCellRowIndex, sourceCellColumnIndex, destCellRowIndex, destCellColumnIndex);
	}

	/**
	 * Helper method to traverse the matrix from source to destination to find the minimum distance.
	 * 
	 * @param sourceCellRowIndex
	 * @param sourceCellColumnIndex
	 * @param destCellRowIndex
	 * @param destCellColumnIndex
	 * @return
	 */
	private int findMinimumDistanceHelper(final int sourceCellRowIndex, final int sourceCellColumnIndex, final int destCellRowIndex, final int destCellColumnIndex) {
		final Queue<CellDistanceHolder> toBeVisitedCells = new ArrayDeque<>();
		toBeVisitedCells.offer(new CellDistanceHolder(sourceCellRowIndex, sourceCellColumnIndex, 0));
		
		while(!toBeVisitedCells.isEmpty()) {
			//Take the next cell from the to be visited cells that are being processed in the breadth first approach.
			final CellDistanceHolder visitingCell = toBeVisitedCells.poll();
			
			//mark this cell as visited.
			markAsVisited(sourceCellRowIndex, sourceCellColumnIndex);
			
			//If we have reached to destination so return the distance. Because using the queue we are going breadth first way and all the nodes at the same distance are 
			//evaluated one after the other, so the first node that we find matching to the destination would be completing the shortest path that we could take from
			//source to destination. And we would just end our search of shortest path there.
			if(visitingCell.isSameCell(destCellRowIndex, destCellColumnIndex)) {
				return visitingCell.getDistanceFromSource();
			}
			
			//Now look at the immediate neighbors on the left if it is a valid node then add it to the nodes that we want to pursue in our path to the destination.
			final CellDistanceHolder leftCell = visitingCell.getLeftCell();
			if(isWithInMatrixBoundary(leftCell.getCellRowIndex(), leftCell.getCellColumnIndex())) {
				if(!isAlreadyVisited(leftCell.getCellRowIndex(), leftCell.getCellColumnIndex())) {
					if(isMovementPossible(leftCell.getCellRowIndex(), leftCell.getCellColumnIndex())) {
						toBeVisitedCells.offer(leftCell);
					} else {
						//This cell could be a cell containing the blocking value. So just mark that cell as visited.
						markAsVisited(leftCell.getCellRowIndex(), leftCell.getCellColumnIndex());
					}
				}
			}
			//Now look at the immediate neighbors on the right if it is a valid node then add it to the nodes that we want to pursue in our path to the destination.
			final CellDistanceHolder rightCell = visitingCell.getRightCell();
			if(isWithInMatrixBoundary(rightCell.getCellRowIndex(), rightCell.getCellColumnIndex())) {
				if(!isAlreadyVisited(rightCell.getCellRowIndex(), rightCell.getCellColumnIndex())) {
					if(isMovementPossible(rightCell.getCellRowIndex(), rightCell.getCellColumnIndex())) {
						toBeVisitedCells.offer(rightCell);
					} else {
						//This cell could be a cell containing the blocking value. So just mark that cell as visited.
						markAsVisited(rightCell.getCellRowIndex(), rightCell.getCellColumnIndex());
					}
				}
			}
			//Now look at the immediate neighbors just above this cell, if it is a valid node then add it to the nodes that we want to pursue in our path to the destination.
			final CellDistanceHolder aboveCell = visitingCell.getAboveCell();
			if(isWithInMatrixBoundary(aboveCell.getCellRowIndex(), aboveCell.getCellColumnIndex())) {
				if(!isAlreadyVisited(aboveCell.getCellRowIndex(), aboveCell.getCellColumnIndex())) {
					if(isMovementPossible(aboveCell.getCellRowIndex(), aboveCell.getCellColumnIndex())) {
						toBeVisitedCells.offer(aboveCell);
					} else {
						//This cell could be a cell containing the blocking value. So just mark that cell as visited.
						markAsVisited(aboveCell.getCellRowIndex(), aboveCell.getCellColumnIndex());
					}
				}
			}
			//Now look at the immediate neighbors on the left if it is a valid node then add it to the nodes that we want to pursue in our path to the destination.
			final CellDistanceHolder belowCell = visitingCell.getBelowCell();
			if(isWithInMatrixBoundary(belowCell.getCellRowIndex(), belowCell.getCellColumnIndex())) {
				if(!isAlreadyVisited(belowCell.getCellRowIndex(), belowCell.getCellColumnIndex())) {
					if(isMovementPossible(belowCell.getCellRowIndex(), belowCell.getCellColumnIndex())) {
						toBeVisitedCells.offer(belowCell);
					} else {
						//This cell could be a cell containing the blocking value. So just mark that cell as visited.
						markAsVisited(belowCell.getCellRowIndex(), belowCell.getCellColumnIndex());
					}
				}
			}
		}
		
		return -1;
	}
	
	private static final class CellDistanceHolder{
		private int cellRowIndex;
		private int cellColumnIndex;
		private int distanceFromSource;
		/**
		 * @param cellRowIndex
		 * @param cellColumnIndex
		 * @param distanceFromSource
		 */
		private CellDistanceHolder(int cellRowIndex, int cellColumnIndex, int distanceFromSource) {
			super();
			this.cellRowIndex = cellRowIndex;
			this.cellColumnIndex = cellColumnIndex;
			this.distanceFromSource = distanceFromSource;
		}
		
		/**
		 * @return the cellRowIndex
		 */
		public int getCellRowIndex() {
			return cellRowIndex;
		}

		/**
		 * @return the cellColumnIndex
		 */
		public int getCellColumnIndex() {
			return cellColumnIndex;
		}

		/**
		 * @return the distanceFromSource
		 */
		public int getDistanceFromSource() {
			return distanceFromSource;
		}

		private boolean isSameCell(int rowIndex, int ColumnIndex) {
			return (cellRowIndex == rowIndex) && (cellColumnIndex == ColumnIndex);
		}

		public CellDistanceHolder getLeftCell() {
			return new CellDistanceHolder(cellRowIndex, cellColumnIndex -1, distanceFromSource + 1);
		}
		
		public CellDistanceHolder getRightCell() {
			return new CellDistanceHolder(cellRowIndex, cellColumnIndex +1, distanceFromSource + 1);
		}
		
		public CellDistanceHolder getAboveCell() {
			return new CellDistanceHolder(cellRowIndex - 1, cellColumnIndex, distanceFromSource + 1);
		}
		
		public CellDistanceHolder getBelowCell() {
			return new CellDistanceHolder(cellRowIndex + 1, cellColumnIndex, distanceFromSource + 1);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [][] inputMatrix = {

                                            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },

                                            { 0, 1, 1, 1, 1, 1, 0, 1, 0, 1 },

                                            { 0, 0, 1, 0, 1, 1, 1, 0, 0, 1 },

                                            { 1, 0, 1, 1, 1, 0, 1, 1, 0, 1 },

                                            { 0, 0, 0, 1, 0, 0, 0, 1, 0, 1 },

                                            { 1, 0, 1, 1, 1, 0, 0, 1, 1, 0 },

                                            { 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },

                                            { 0, 1, 1, 1, 1, 1, 1, 1, 0, 0 },

                                            { 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },

                                            { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },

                              };
		Matrix matrix = new Matrix(inputMatrix, /*pathBlockingValue*/ 0);
		System.out.println(matrix.findMinimumDistance(/*sourceCellRowIndex*/ 0, /*sourceCellColumnIndex*/ 0, /*destCellRowIndex*/ 7, /*destCellColumnIndex*/ 5));
		//Output for this run is: 12
		System.out.println(matrix.findMinimumDistance(/*sourceCellRowIndex*/ 0, /*sourceCellColumnIndex*/ 0, /*destCellRowIndex*/ 1, /*destCellColumnIndex*/ 5));
		//Output for this run is: 6
		System.out.println(matrix.findMinimumDistance(/*sourceCellRowIndex*/ 0, /*sourceCellColumnIndex*/ 0, /*destCellRowIndex*/ 9, /*destCellColumnIndex*/ 2));
		//Output for this run is: 15
		System.out.println(matrix.findMinimumDistance(/*sourceCellRowIndex*/ 0, /*sourceCellColumnIndex*/ 0, /*destCellRowIndex*/ 9, /*destCellColumnIndex*/ 9));
		//Output for this run is: 18
		System.out.println(matrix.findMinimumDistance(/*sourceCellRowIndex*/ 0, /*sourceCellColumnIndex*/ 0, /*destCellRowIndex*/ 2, /*destCellColumnIndex*/ 8));
		//Output for this run is: -1, as no path exists.
	}

}
