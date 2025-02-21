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
     * Adds cell to array
     *
     * @param cell: cell to be added to array
     */
    public void addCell(Cell cell) {
        this.emptyCells.add(cell);
    }

    /**
     * Removes a previously empty cell from the array
     * 
     * @param cell: cell to be removed from the array
     */
    public void removeCell(Cell cell) {
        this.emptyCells.remove(cell);
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
