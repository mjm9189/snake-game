package com.snakegame.snakegame;

import java.util.ArrayList;

public class Board {

    private final Cell[][] cells;  // 2D array of cells representing the board
    private final EmptyCellsSet emptyCells;


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

    public Cell getCell(int row, int col) {
        // Retrieves the cell at the given row and column
        return this.cells[row][col];
    }
}