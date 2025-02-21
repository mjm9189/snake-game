const gameBoard = document.getElementById("game-board");
const startButton = document.getElementById("start-button");
const logo = document.getElementById("logo");
const score = document.getElementById("score");
const highScore = document.getElementById("highScore");
let currentGameState = {
    snake: [{ x: 6, y: 12 }],
    food: {x: 12, y: 12},
    score: 0,
    highScore: 0,
    inProgress: false,
    gameOver: false
};

async function fetchGameState() {
    try {
        let response = await fetch("http://localhost:8080/state");
        currentGameState = await response.json();
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
        highScore.textContent = currentGameState.highScore.toString().padStart(3, "0");
    } else {
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
    await fetch("http://localhost:8080/move", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ direction: event.key })
    });
});

// Fetch game state every 100ms to sync with backend updates
window.onload = () => {setInterval(fetchGameState, 100)}