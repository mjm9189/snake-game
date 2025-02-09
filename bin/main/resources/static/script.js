const gameBoard = document.getElementById("game-board");
const instructions = document.getElementById("instructions");
const logo = document.getElementById("logo");
const score = document.getElementById("score");
const highScore = document.getElementById("highScore");
let currentGameState = {
    snake: [{ x: 6, y: 12 }],
    food: {x: 12, y: 12},
    score: 0,
    highScore: 0,
    inProgress: false
};

async function fetchGameState() {
    let response = await fetch("http://localhost:8080/game/state");
    currentGameState = await response.json();
    updateGameDisplay(currentGameState);
}

//Fetch game state every 100ms to sync with backend updates
window.onload = () => {setInterval(fetchGameState, 100)}

document.addEventListener("keydown", async (event) => {
    await fetch("http://localhost:8080/game/move", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ direction: event.key })
    });
});

function updateGameDisplay(gameState) {
    if (currentGameState.inProgress) {
        gameBoard.innerHTML = "";
        instructions.style.display = "none";
        logo.style.display = "none";
        currentGameState.snake.forEach((snakeSegment) => {
            const snakeElement = createGameElement("div", "snake");
            setPosition(snakeElement, snakeSegment);
            gameBoard.appendChild(snakeElement);
        });
        foodElement = createGameElement("div", "food");
        setPosition(foodElement, gameState.food);
        gameBoard.appendChild(foodElement);
        score.textContent = currentGameState.score.toString().padStart(3, "0");
        if (currentGameState.score > currentGameState.highScore) {
            highScore.textContent = score.textContent;
        }
    } else {
        instructions.style.display = "block";
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