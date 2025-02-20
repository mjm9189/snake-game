package com.snakegame.snakegame;

import java.util.ArrayList;

public class Board {

    private final Cell[][] cells;  // 2D array of cells representing the board
    private final EmptyCellsSet emptyCells;


    public Board(int row_count, int col_count) {
        ArrayList<Cell> emptyCellStarts = new ArrayList<>();
        int i = 0;

        this.cells = new Cell[row_count + 2][col_count + 2];
        for (int row = 0; row < row_count + 2; row ++) {
            for (int col = 0; col < col_count + 2; col ++) {
                this.cells[row][col] = new Cell(row, col);
                if (row == 0 || row == row_count + 1 || col == 0 || col == col_count + 1) {
                    this.cells[row][col].setCellType(CellType.WALL);
                } else {
                this.cells[row][col].setEmptyArrayIndex(i);
                emptyCellStarts.add(this.cells[row][col]);
                }
            }
        }

        this.emptyCells = new EmptyCellsSet(emptyCellStarts);
    }

    public Cell getCell(int row, int col) {
        return this.cells[row][col];
    }
}