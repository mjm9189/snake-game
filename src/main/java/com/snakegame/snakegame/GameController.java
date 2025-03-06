package com.snakegame.snakegame;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Map;

@RestController
@SessionScope
@CrossOrigin
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
    public void handleKeyPress(@RequestBody Map<String, String> request) {
        if (this.latestGameState.get("inProgress").equals(true)) {
            String direction = request.get("direction");
            this.game.handleKeyPress(direction);
        }
    }

    @PostMapping("/newgame")
    public String handleNewGame() {
        this.game.newGame();
        this.latestGameState = this.game.getGameState();
        System.out.println("Game started!");
        return "{\"message\": \"Game started successfully\"}";
    }
}
