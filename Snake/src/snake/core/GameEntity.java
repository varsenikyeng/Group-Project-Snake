package snake.core;

import java.util.Random;
/**
 * The GameEntity class represents an entity in the Snake game.
 * It serves as the base class for other game entities such as Fruit, Obstacle and Heart.
 */
public abstract class GameEntity {
    private Position position;

    private String symbol;
    /**
     * Constructs a new GameEntity object with default position (0,0) and empty symbol.
     */
    public GameEntity(){
        this.position = new Position(0,0);
        this.symbol = " ";
    }
    /**
     * Constructs a new GameEntity object with the specified position and symbol.
     * @param position The position of the game entity on the game board.
     * @param symbol The symbol representing the game entity on the game board.
     */
    public GameEntity(Position position, String symbol){
        this.position = new Position(position);
        this.symbol = symbol;
    }
    /**
     * Gets the position of the game entity.
     * @return The position of the game entity.
     */
    public Position getPosition(){
        return new Position(this.position);
    }
    /**
     * Gets the symbol representing the game entity.
     * @return The symbol representing the game entity.
     */
    public String getSymbol(){
        return this.symbol;
    }
    /**
     * Sets the position of the game entity.
     * @param that The new position of the game entity.
     */
    public void setPosition(Position that) {
        this.position = new Position(that);
    }


    /**
     * Spawns the game entity on the game board at a random position that is not occupied by the snake.
     * @param snake The snake object.
     * @param board The game board, a 2D array of strings.
     */
    public void spawnEntity(Snake snake, String[][] board){
        Random rand = new Random();
        int randomRow, randomColumn;
        do {
            randomRow = rand.nextInt(board.length); //BOARD_ROWS
            randomColumn = rand.nextInt(board[0].length); //BOARD_COLUMNS
        }
        while (!SnakeGame.isPositionFree(randomRow, randomColumn, snake));
        setPosition(new Position(randomRow, randomColumn));
        board[randomRow][randomColumn] = this.symbol;
    }

}

