package com.snakegame.snakegame;

import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Snake {

    private final LinkedList<Cell> snakeBody = new LinkedList<>();  // Ordered list of snake body cells
    private Cell head;


    /**
     * Constructor for Snake
     *
     * @param initPosition: Starting cell of the snake
     */
    public Snake(Cell initPosition) {
        this.head = initPosition;
        this.snakeBody.add(head);
        this.head.setCellType(CellType.SNAKE);
    }

    /**
     * Moves the snake to nextCell, adjusting snakeBody and head as needed. Method assumes that nextCell is valid to
     * move into.
     *
     * @param nextCell: Cell the snake moves into
     * @return Cell which contained the end of the snake's tail prior to moving
     */
    public Cell move(Cell nextCell) {
        CellType newCellType = nextCell.getCellType();  // Get the type of the next cell to determine what to do

        // Move the head to the next cell, then update the head and snakeBody
        this.head = nextCell;
        this.head.setCellType(CellType.SNAKE);
        this.snakeBody.addFirst(head);

        // Return previous end of tail and set to EMPTY if new cell wasn't food
        if (newCellType == CellType.FOOD) {
            return this.snakeBody.getLast();
        } else {
            Cell tail = this.snakeBody.removeLast();
            tail.setCellType(CellType.EMPTY);
            return tail;
        }
    }

    /**
     * Retrieves the current head of the Snake
     *
     * @return Current head Cell
     */
    public Cell getHead() {
        return this.head;
    }

    /**
     * Retrieves the cells of the snake to be drawn in front-end
     *
     * @return List of Maps containing x and y coordinates of each snake cell
     */
    public List<Map<String, Integer>> getSnakeCells() {
        // Return a list of the snake's cells for front-end use
        List<Map<String, Integer>> snakeCells = new ArrayList<>();
        for (Cell cell : this.snakeBody) {
            snakeCells.add(cell.getCellPosition());
        }
        return snakeCells;
    }
}