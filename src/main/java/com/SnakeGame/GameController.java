package com.SnakeGame;

import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PreDestroy;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:5500") // Allow frontend requests

public class GameController {

    private Game game = new Game();
    private volatile Map<String, Object> latestGameState = this.game.getGameState(); // Stores latest state
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public GameController() {
        startGameLoop();
    }

    // Start a background thread that updates the game state every 100ms
    private void startGameLoop() {
        scheduler.scheduleAtFixedRate(() -> {
            if (this.latestGameState.get("inProgress").equals(true)) {
                game.takeTurn(); // Move the snake automatically
                latestGameState = this.game.getGameState();
            } 
        }, 0L, 100L, TimeUnit.MILLISECONDS);
    }

    @GetMapping("/state")
    public Map<String, Object> getGameState() {
        return game.getGameState();
    }

    @PostMapping("/move")
    public void handleKeyPress(@RequestBody Map<String, String> request) {
        if (this.latestGameState.get("inProgress").equals(true)) {
            String direction = request.get("direction");
            game.handleKeyPress(direction);
        } else {
            game.newGame();
        }
    }

    @PreDestroy
    public void shutdown() {
        scheduler.shutdown();
    }
}
