package com.snakegame.snakegame;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {
    private final Game game;
    private volatile Map<String, Object> latestGameState;

    public GameController() {
        this.game = new Game();
        this.latestGameState = this.game.getGameState();
    }

    @GetMapping("/state")
    public Map<String, Object> getGameState() {
        if (this.latestGameState.get("inProgress").equals(true)) {
            this.game.takeTurn();
            this.latestGameState = this.game.getGameState();
        }
        return this.latestGameState;
    }

    @PostMapping("/move")
    public String handleKeyPress(@RequestBody Map<String, String> request) {
        if (this.latestGameState.get("inProgress").equals(true)) {
            String direction = request.get("direction");
            this.game.handleKeyPress(direction);
        } else {
            this.game.newGame();
            this.game.takeTurn();
            this.latestGameState = this.game.getGameState();
        }
        return "Success";
    }
}
