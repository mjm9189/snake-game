package com.SnakeGame;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Game {

    private Snake snake;
    private Board board;
    private EmptyCellsSet emptyCells;
    private Cell foodCell;
    private String direction = "right";
    private LinkedList<String> dirBuffer = new LinkedList<>();
    private int currScore = 0;
    private int highScore = 0;
    private boolean gameStart = false;

    public void newGame() {
        gameStart = true;
        this.board = new Board(23, 22);
        this.emptyCells = this.board.emptyCells;
        this.snake = new Snake(board.getCell(11, 6));
        this.foodCell = this.board.getCell(11, 15);
        this.emptyCells.removeCell(this.snake.getHead());
        this.emptyCells.removeCell(this.foodCell);
        this.currScore = 0;
    }

    public void endGame() {
        if (currScore > highScore) {
            highScore = currScore;
        }
        gameStart = false;
    }

    public void generateFood() {
        Cell newFood = this.emptyCells.getRandomCell();
        this.emptyCells.replaceCell(this.foodCell, newFood);
        this.foodCell = newFood;
        this.foodCell.setCellType(CellType.FOOD);
    }

    public void takeTurn() {
        Cell nextCell = this.snake.getHead();
        if (this.dirBuffer.size() > 0) {
            this.direction = this.dirBuffer.removeFirst();
        }
        
        switch (this.direction) {
            case "up":
                nextCell = this.board.getCell(this.snake.getHead().getRow() - 1, this.snake.getHead().getCol());
                break;
            case "down":
                nextCell = this.board.getCell(this.snake.getHead().getRow() + 1, this.snake.getHead().getCol());
                break;
            case "left":
                nextCell = this.board.getCell(this.snake.getHead().getRow(), this.snake.getHead().getCol() - 1);
                break;
            case "right":
                nextCell = this.board.getCell(this.snake.getHead().getRow(), this.snake.getHead().getCol() + 1);
                break;
        }

        CellType nextCellType = nextCell.getCellType();
        Cell tail = snake.move(nextCell);
        if (nextCellType == CellType.WALL || nextCellType == CellType.SNAKE) {
            this.endGame();
            return;
        } else if (nextCellType == CellType.FOOD) {
            this.currScore += 1;
            this.generateFood();
        }

        if (tail.getCellType() == CellType.SNAKE) {
            this.emptyCells.removeCell(tail);
        } else if (tail.getCellType() == CellType.EMPTY) {
            this.emptyCells.replaceCell(this.snake.getHead(), tail);
        }
    }

    public void handleKeyPress(String keyPress) {
        if (this.dirBuffer.size() > 2) {
            return;
        }
        switch (keyPress) {
            case "ArrowUp", "w":
                if ((this.dirBuffer.size() == 0) && (this.direction != "down") || this.dirBuffer.getLast() != "down") {
                    this.dirBuffer.add("up");
                }
                break;
            case "ArrowDown", "s":
                if ((this.dirBuffer.size() == 0) && (this.direction != "up") || this.dirBuffer.getLast() != "up") {
                    this.dirBuffer.add("down");
                }
                break;
            case "ArrowLeft", "a":
                if ((this.dirBuffer.size() == 0) && (this.direction != "right") || this.dirBuffer.getLast() != "right") {
                    this.dirBuffer.add("left");
                }
                break;
            case "ArrowRight", "d":
                if ((this.dirBuffer.size() == 0) && (this.direction != "left") || this.dirBuffer.getLast() != "left") {
                    this.dirBuffer.add("right");
                }
                break;
            default:
                break;
        }
    }

    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("inProgress", this.gameStart);
        state.put("score", this.currScore);
        state.put("highScore", this.highScore);
        if (!this.gameStart) {
            return state;
        }
        state.put("snake", this.snake.getSnakeCells());
        state.put("food", this.foodCell.getCellPosition());
        return state;
    }
}