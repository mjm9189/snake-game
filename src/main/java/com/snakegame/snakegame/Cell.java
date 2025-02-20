package com.snakegame.snakegame;

import java.util.HashMap;

public class Cell {

    private final int row, col;  // coordinates of the cell
    private CellType cellType = CellType.EMPTY;  // type of the cell
    private int emptyArrayIndex;  // index of the cell in the emptyCells array for the board

    /**
     * Constructor for the Cell class
     *
     * @param row: row of the cell
     * @param col: column of the cell
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Retrieves the cell type
     *
     * @return type of the cell
     */
    public CellType getCellType() {
        return this.cellType;
    }

    /**
     * Sets the cell type
     *
     * @param cellType: type of the cell
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Retrieves the row of the cell
     *
     * @return row of the cell
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Retrieves the column of the cell
     *
     * @return column of the cell
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Retrieves the cell position for front-end use
     *
     * @return HashMap with the x and y coordinates of the cell
     */
    public HashMap<String, Integer> getCellPosition() {
        HashMap<String, Integer> position = new HashMap<>();
        position.put("y", this.row);
        position.put("x", this.col);
        return position;
    }

    /**
     * Sets the index of the cell in the emptyCells array for the board
     *
     * @param index: index of the cell in the emptyCells array for the board
     */
    public void setEmptyArrayIndex(int index) {
        this.emptyArrayIndex = index;
    }

    /**
     * Retrieves the index of the cell in the emptyCells array for the board
     *
     * @return index of the cell in the emptyCells array for the board
     */
    public int getEmptyArrayIndex() {
        return this.emptyArrayIndex;
    }
}