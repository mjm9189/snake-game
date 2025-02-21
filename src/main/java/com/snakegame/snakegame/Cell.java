package com.snakegame.snakegame;

import java.util.HashMap;

public class Cell {

    private final int row, col;  // coordinates of the cell
    private CellType cellType = CellType.EMPTY;  // type of the cell
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
     * Determines equality of two Cells based on their row and column indices
     *
     * @param obj: object to compare against
     * @return boolean determining equality of cell to input object
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Cell other = (Cell) obj;

        return this.row == other.getRow() && this.col == other.getCol();
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
        position.put("y", this.row + 1);
        position.put("x", this.col + 1);
        return position;
    }
}