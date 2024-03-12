# Group-Project-Snake
Creating an instance of the well-known Snake game, implementing OOP principles.

Firstly, the game will have three levels of difficulty(easy, medium, hard). The game has a snake, which aims to eat the fruits as much as possible, to become bigger and avoid the obstacles. Obstacles will be the mushrooms and groundholes. If a mushroom is eaten, the snake gets faster. Additionally, the snake will lose one of its 3 lives. If the snake touches the groundhole or itself it will die immediately. Different types of fruits will benefit the snake differently(increase its size in different ways). If the snake takes over the whole area of the grid, the player wins the game.

public class Snake{
//Snakes would have a color;
//
}
1. Game Class
level: represents the difficulty of the game.
gameover: Tracks whether the game has ended.
start(): Initializes the game and starts the main game loop.
update(): Updates the game state and checks for game over conditions.

2. Snake Class
bodyLength: current length of the snake's body
direction: The current direction of the snake's movement.
speed: The speed of the snake.

move(): Updates the snake's position based on its direction and speed.
grow(): Adds a segment to the snake's body.

3. Fruit Class
color : each color represents a certain effect
location : random points where they spawn

4.Obstacle Class 

type: The type of the obstacle (e.g., mushroom, groundhole).

collision(): Defines the effect on the snake when it collides with the obstacle (e.g., speed up, lose life).

5. GameBoard Class
width: The width of the game board.
height: The height of the game board.
snake: An instance of the Snake class.
fruits: Instances of the Fruit class.
obstacles: Instances of the Obstacle class.



