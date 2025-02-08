package com.SnakeGame;

import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Snake {

    private LinkedList<Cell> snakeBody = new LinkedList<>();
    private Cell head;

    public Snake(Cell initPosition) {
        this.head = initPosition;
        this.snakeBody.add(head);
        this.head.setCellType(CellType.SNAKE);
    }

    public Cell move(Cell nextCell) {
        Cell tail = this.snakeBody.removeLast();
        this.head = nextCell;
        this.head.setCellType(CellType.SNAKE);
        this.snakeBody.addFirst(head);
        if (nextCell.getCellType() != CellType.FOOD) {
            tail.setCellType(CellType.EMPTY);
        }  else {
            this.snakeBody.addLast(tail);
        }
        return tail;
    }

    public Cell getHead() {
        return this.head;
    }

    public List<Map<String, Integer>> getSnakeCells() {
        List<Map<String, Integer>> snakeCells = new ArrayList<>();
        for (int i = 0; i < this.snakeBody.size(); i++) {
            snakeCells.add(this.snakeBody.get(i).getCellPosition());
        }
        return snakeCells;
    }
}