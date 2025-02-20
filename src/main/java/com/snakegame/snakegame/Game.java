package com.snakegame.snakegame;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.Objects;

public class Game {

    private Snake snake;
    private Board board;
    private Cell foodCell;
    private String direction;  // direction the snake should move in on its next turn if no keypress is buffered
    private LinkedList<String> dirBuffer;  // queue of buffered directions
    private int currScore;
    private int highScore;
    private boolean gameStart;  // Flag to track whether a game is in progress or has ended

    /**
     * Constructor for Game class
     */
    public Game() {
        this.gameStart = false;
        this.currScore = 0;
        this.highScore = 0;
    }

    /**
     * Reset Game attributes for start of a new game
     */
    public void newGame() {
        gameStart = true;
        this.board = new Board(23, 22);
        this.snake = new Snake(board.getCell(12, 6));
        this.foodCell = this.board.getCell(12, 12);
        this.board.removeEmpty(this.snake.getHead());
        this.board.removeEmpty(this.foodCell);
        this.currScore = 0;
        this.dirBuffer = new LinkedList<>();
        this.direction = "right";
    }

    /**
     * Set foodCell to a random empty cell on the board
     */
    public void generateFood() {
        this.foodCell = this.board.getRandomEmpty();
        this.board.removeEmpty(this.foodCell);
        this.foodCell.setCellType(CellType.FOOD);
    }

    /**
     * Updates the game state based on the direction the snake is currently set to move in
     */
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
            if (this.currScore > this.highScore) {
                this.highScore = this.currScore;
            }
            this.generateFood();
        }

        if (tail.getCellType() == CellType.SNAKE) {
            this.emptyCells.removeCell(tail);
        } else if (tail.getCellType() == CellType.EMPTY) {
            this.emptyCells.replaceCell(this.snake.getHead(), tail);
        }
    }

    /**
     * Use keypress received by the front-end to update the movement direction for the snake
     *
     * @param keyPress: string representing a keypress
     */
    public void handleKeyPress(String keyPress) {
        // Only add to buffer if doing so won't cause noticeably delayed movement
        if (this.dirBuffer.size() <= 2) {
            String mostRecentDir;

            // Track direction most recently input by user
            if (this.dirBuffer.isEmpty()) {
                mostRecentDir = this.direction;
            } else {
                mostRecentDir = this.dirBuffer.getLast();
            }

            // Only allow movements orthogonal to most recently input
            switch (keyPress) {
                case "ArrowUp", "w":
                    if (!Objects.equals(mostRecentDir, "down")) {
                        this.dirBuffer.add("up");
                    }
                    break;
                case "ArrowDown", "s":
                    if (!Objects.equals(mostRecentDir, "up")) {
                        this.dirBuffer.add("down");
                    }
                    break;
                case "ArrowLeft", "a":
                    if (!Objects.equals(mostRecentDir, "right")) {
                        this.dirBuffer.add("left");
                    }
                    break;
                case "ArrowRight", "d":
                    if (!Objects.equals(mostRecentDir, "left")) {
                        this.dirBuffer.add("right");
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Retrieve critical game state information to be read by the front-end
     *
     * @return Map of game state values
     */
    public Map<String, Object> getGameState() {
        Map<String, Object> state = new HashMap<>();
        state.put("inProgress", this.gameStart);
        state.put("score", this.currScore);
        state.put("highScore", this.highScore);
        if (!this.gameStart) {
            // Only return values that have been initialized
            return state;
        }
        state.put("snake", this.snake.getSnakeCells());
        state.put("food", this.foodCell.getCellPosition());
        return state;
    }
}