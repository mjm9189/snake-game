package com.snakegame.snakegame;

import java.util.ArrayList;

public class Board {

    private final Cell[][] cells;  // 2D array of cells representing the board
    private final EmptyCellsSet emptyCells;


    /**
     * Constructor for Board class
     *
     * @param row_count: total number of playable rows
     * @param col_count: total number of playable columns
     */
    public Board(int row_count, int col_count) {
        ArrayList<Cell> emptyCellStarts = new ArrayList<>();  // Starter array for emptyCellSet
        int i = 0;  // Index for placement into emptyCellStarts

        this.cells = new Cell[row_count + 2][col_count + 2];  // Create a 2D array of cells with walls around the edges
        for (int row = 0; row < row_count + 2; row ++) {
            for (int col = 0; col < col_count + 2; col ++) {
                this.cells[row][col] = new Cell(row, col);  // Create a new cell at the current position

                // Set the cell type to WALL if it is on the edge, otherwise set it to EMPTY
                if (row == 0 || row == row_count + 1 || col == 0 || col == col_count + 1) {
                    this.cells[row][col].setCellType(CellType.WALL);
                } else {
                this.cells[row][col].setEmptyArrayIndex(i);  // Store the index of the cell in the emptyCellStarts array as attribute
                emptyCellStarts.add(this.cells[row][col]);
                }
            }
        }
        // Create a new EmptyCellsSet with the emptyCellStarts array
        this.emptyCells = new EmptyCellsSet(emptyCellStarts);
    }

    /**
     * Retrieves the cell at a given set of coordinates
     *
     * @param row: Row of desired Cell
     * @param col: Column of desired Cell
     * @return Cell located at indices (row, col)
     */
    public Cell getCell(int row, int col) {
        // Retrieves the cell at the given row and column
        return this.cells[row][col];
    }

    /**
     * Removes a Cell from the emptyCellSet
     *
     * @param cell: Cell being removed
     */
    public void removeEmpty(Cell cell) {
        this.emptyCells.removeCell(cell);
    }

    /**
     * Replaces a Cell in the emptyCellSet with one outside of it
     *
     * @param outCell: Cell being removed
     * @param inCell: Cell being placed in
     */
    public void replaceEmpty(Cell outCell, Cell inCell) {
        this.emptyCells.replaceCell(outCell, inCell);
    }

    /**
     * Retrieves a random empty Cell
     *
     * @return empty Cell
     */
    public Cell getRandomEmpty() {
        return this.emptyCells.getRandomCell();
    }
}