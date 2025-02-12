package com.snakegame.snakegame;

import java.util.ArrayList;
import java.util.Random;

public class EmptyCellsSet {
    private ArrayList<Cell> emptyCells;

    public EmptyCellsSet(ArrayList<Cell> emptyCells) {
        this.emptyCells = emptyCells;
    }
    
    public void replaceCell(Cell outCell, Cell inCell) {
        int index = outCell.getEmptyArrayIndex();
        outCell.setEmptyArrayIndex(5000);
        this.emptyCells.add(index, inCell);
        inCell.setEmptyArrayIndex(index);
    }

    public void removeCell(Cell cell) {
        Cell last = this.emptyCells.get(this.emptyCells.size() - 1);
        this.replaceCell(cell, last);
    }

    public Cell getRandomCell() {
        Random rand = new Random();
        return this.emptyCells.get(rand.nextInt(this.emptyCells.size()));
    }
}
