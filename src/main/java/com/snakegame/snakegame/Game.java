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
    private boolean inProgress;  // Flag to track whether a game is in progress or has ended
    private boolean gameOver;  // Flag to determine if game needs to end on next turn


    /**
     * Constructor for Game class
     */
    public Game() {
        this.inProgress = false;
        this.gameOver = false;
        this.currScore = 0;
        this.highScore = 0;
    }

    /**
     * Reset Game attributes for start of a new game
     */
    public void newGame() {
        inProgress = true;
        gameOver = false;
        this.board = new Board(21, 20);
        this.snake = new Snake(board.getCell(12, 6));
        this.foodCell = this.board.getCell(12, 12);
        this.foodCell.setCellType(CellType.FOOD);
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
        if (!this.dirBuffer.isEmpty()) {
            // Set the direction to be moved in for the turn to the earliest in the buffer
            this.direction = this.dirBuffer.removeFirst();
        }

        if (this.gameOver) {
            // End game if previous turn hit resulted in game over
            this.inProgress = false;
            return;
        }

        // Retrieve the Cell into which the snake will move from the board based on the current direction
        Cell nextCell = switch (this.direction) {
            case "up" -> this.board.getCell(this.snake.getHead().getRow() - 1, this.snake.getHead().getCol());
            case "down" -> this.board.getCell(this.snake.getHead().getRow() + 1, this.snake.getHead().getCol());
            case "left" -> this.board.getCell(this.snake.getHead().getRow(), this.snake.getHead().getCol() - 1);
            case "right" -> this.board.getCell(this.snake.getHead().getRow(), this.snake.getHead().getCol() + 1);
            default -> this.snake.getHead();
        };
        CellType nextCellType = nextCell.getCellType();

        if (nextCellType == CellType.WALL || nextCellType == CellType.SNAKE) {
            // Flag the end of the current game if snake hits wall or itself
            if (nextCellType == CellType.WALL) {
                // Move snake to draw collision with wall
                this.snake.move(nextCell);
            }
            this.gameOver = true;
        } else {
            Cell tail = this.snake.move(nextCell);
            if (nextCellType == CellType.FOOD) {
                // Update score and generate new food if snake eats
                this.currScore += 1;
                if (this.currScore > this.highScore) {
                    // Update high score if applicable
                    this.highScore = this.currScore;
                }
                this.generateFood();
            } else {
                // Update EmptyCellSet to include cells the snake has left and remove those it's entered
                Cell head = this.snake.getHead();
                this.board.replaceEmpty(head, tail);
            }
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

            // Only allow movements orthogonal to most recently input direction
            switch (keyPress) {
                case "ArrowUp", "w":
                    if (!Objects.equals(mostRecentDir, "down") && ! Objects.equals(mostRecentDir, "up")) {
                        this.dirBuffer.add("up");
                    }
                    break;
                case "ArrowDown", "s":
                    if (!Objects.equals(mostRecentDir, "up") && !Objects.equals(mostRecentDir, "down")) {
                        this.dirBuffer.add("down");
                    }
                    break;
                case "ArrowLeft", "a":
                    if (!Objects.equals(mostRecentDir, "right") && !Objects.equals(mostRecentDir, "left")) {
                        this.dirBuffer.add("left");
                    }
                    break;
                case "ArrowRight", "d":
                    if (!Objects.equals(mostRecentDir, "left") && !Objects.equals(mostRecentDir, "right")) {
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
        state.put("inProgress", this.inProgress);
        state.put("gameOver", this.gameOver);
        state.put("score", this.currScore);
        state.put("highScore", this.highScore);
        if (!this.inProgress) {
            // Only return values that have been initialized
            return state;
        }
        state.put("snake", this.snake.getSnakeCells());
        state.put("food", this.foodCell.getCellPosition());
        return state;
    }
}