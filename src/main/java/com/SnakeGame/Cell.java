package com.SnakeGame;

import java.util.HashMap;

public class Cell {

    private final int row, col;
    private CellType cellType = CellType.EMPTY;
    private int emptyArrayIndex;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public CellType getCellType() {
        return this.cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setEmptyArrayIndex(int index) {
        this.emptyArrayIndex = index;
    }

    public int getEmptyArrayIndex() {
        return this.emptyArrayIndex;
    }

    public HashMap<String, Integer> getCellPosition() {
        HashMap<String, Integer> position = new HashMap<>();
        position.put("row", this.row);
        position.put("col", this.col);
        return position;
    }
}