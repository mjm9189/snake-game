package com.snakegame.snakegame;

import java.util.ArrayList;
import java.util.Random;

public class EmptyCellsSet {
    private final ArrayList<Cell> emptyCells;  // Stores empty cells based on the index attribute of each cell

    /**
     * Constructor for the EmptyCellsSet class
     *
     * @param emptyCells: array of empty cells
     */
    public EmptyCellsSet(ArrayList<Cell> emptyCells) {
        this.emptyCells = emptyCells;
    }
    
    /**
     * Swaps the emptyArrayIndex of a previously empty cell with a newly emptied one
     *
     * @param outCell: cell to be removed from the array
     * @param inCell: cell to be added to the array
     */
    public void replaceCell(Cell outCell, Cell inCell) {
        int index = outCell.getEmptyArrayIndex();
        outCell.setEmptyArrayIndex(5000);
        this.emptyCells.add(index, inCell);
        inCell.setEmptyArrayIndex(index);
    }

    /**
     * Removes a previously empty cell from the array, then replaces it with the last cell in the array to ensure that
     * all array elements are real empty cells
     *
     * @param cell: cell to be removed from the array
     */
    public void removeCell(Cell cell) {
        Cell last = this.emptyCells.get(this.emptyCells.size() - 1);
        this.replaceCell(cell, last);
    }

    /**
     * Retrieves a random empty cell from the array for use in generating food
     *
     * @return random empty cell
     */
    public Cell getRandomCell() {
        Random rand = new Random();
        return this.emptyCells.get(rand.nextInt(this.emptyCells.size()));
    }
}
