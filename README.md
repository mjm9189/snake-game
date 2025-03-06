## Snake Game

### Project Summary

This is a retro-themed implementation of the classic game Snake. All back-end
game logic was written from scratch in Java, then connected to front-end
using a REST API built in Spring Boot. I made sure to include thorough documentation
of all functions using both JavaDoc docstrings and regular comments throughout
the code. I utilized Maven to build the project.

My front-end was heavily inspired by [this project](https://github.com/Ade-mir/snake-game-js)
by [@Ade-mir](https://github.com/Ade-mir) on GitHub. In particular, I tweaked
his HTML and CSS to add a Start Game button, as well as a Game Over screen.
I also took inspiration from his method of drawing both the snake and its food
on the game board with JavaScript, with major tweaks/additions by myself in 
order to connect to the REST API.

### Instructions to Run
#### Requirements
- Java 21.0.6
- Spring Boot 3.4.3
- Maven 3.9.9
#### How to Run
##### Online
The most up-to-date version of the game is live at https://snake.mjmiller.dev, where it can be played at your convenience.
##### Direct download
First, install all above-listed requirements. After cloning this repo, navigate to `/snake-game`
in your terminal and run the command `mvn spring-boot:run`. Alternatively, opening the repo in a
code editor and clicking the run button on the `SnakegameApplication` class located at
`/snake-game/src/main/java/com/snakegame/snakegame` should also work. Then, in your browser,
navigate to localhost:8080 to play.

### Game Rules
For those unfamiliar with the game, the goal of the player is to control the snake
while trying to eat as many pieces of food as possible. Eating food increases
the score of the game, while also increasing the snake's length. If the snake 
crashes into a wall, or into itself, then the game ends. At the end of a game,
the player's score is compared against their previous highest score, which is
then updated and displayed across from the current game's score.

### Controls
The player can use either the arrow keys or the WASD keys to move the snake.

_Note:_ _One feature I was particularly happy with is a buffer for input keystrokes
to control the snake. This buffer allows players to make many inputs in quick succession
without any being overwritten, but is shallow enough to prevent input lag._

### Potential Future Additions
1. A help button to display game controls
2. A dynamically-updated game leaderboard consisting of the top scores achieved by any player
3. Game speed controls to increase or decrease the difficulty of the game
4. Graphical overhaul with smoother animations
