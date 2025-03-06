iconst gameBoard = document.getElementById("game-board");
const startButton = document.getElementById("start-button");
const newGameButton = document.getElementById("new-game-button")
const gameOverImg = document.getElementById("game-over")
const logo = document.getElementById("logo");
const score = document.getElementById("score");
const highScore = document.getElementById("highScore");
let currentGameState;
window.refreshIntervalId = null;

async function fetchGameState() {
    try {
        let response = await fetch("https://snake.mjmiller.dev/state");
        currentGameState = await response.json();
        if (currentGameState.gameOver && !currentGameState.inProgress) {
            clearInterval(window.refreshIntervalId);
        }
        updateGameDisplay(currentGameState);
    } catch (error) {
        console.error("Failed to fetch game state: ", error);
    }
}

function updateGameDisplay(gameState) {
    if (currentGameState.inProgress) {
        gameBoard.innerHTML = "";
        startButton.style.display = "none";
        logo.style.display = "none";
        currentGameState.snake.forEach((snakeSegment) => {
            const snakeElement = createGameElement("div", "snake");
            setPosition(snakeElement, snakeSegment);
            gameBoard.appendChild(snakeElement);
        });
        let foodElement = createGameElement("div", "food");
        setPosition(foodElement, gameState.food);
        gameBoard.appendChild(foodElement);
        score.textContent = currentGameState.score.toString().padStart(3, "0");
    } else if (currentGameState.gameOver) {
        newGameButton.style.display = "block";
        gameOverImg.style.display = "block";
        highScore.textContent = currentGameState.highScore.toString().padStart(3, "0");
    } else {
        // initialState.highScore = currentGameState.highScore;
        // currentGameState = initialState;
        gameBoard.innerHTML = "";
        newGameButton.style.display = "none";
        gameOverImg.style.display = "none";
        startButton.style.display = "block";
        logo.style.display = "block";
    }
}

function createGameElement(tag, className) {
    const element = document.createElement(tag);
    element.className = className;
    return element;
}

function setPosition(element, position) {
    element.style.gridRow = position.y;
    element.style.gridColumn = position.x;
}

document.addEventListener("keydown", async (event) => {
    await fetch("https://snake.mjmiller.dev/move", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ direction: event.key })
    });
});

function newGame() {
    fetch("https://snake.mjmiller.dev/newgame", {
        method: "POST"
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to start game");
            }
            return response.json();
        })
        .then(data => {
            console.log("Game started:", data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
    window.refreshIntervalId = setInterval(fetchGameState, 170)
}

function resetGame() {
    currentGameState.gameOver = false;
    updateGameDisplay();
}