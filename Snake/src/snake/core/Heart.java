package snake.core;

/**
 * The Heart class represents a heart game entity in the Snake game.
 * It extends the GameEntity class.
 */
public class Heart extends GameEntity{
    private int count;
    /**
     * Constructs a new Heart object with the specified position.
     * Initializes the heart count to 3.
     * @param position The position of the heart on the game board.
     */
    public Heart(Position position){
        super(position, "\u2764\ufe0f");
        count = 3;
    }
    /**
     * Gets the current count of hearts.
     * @return The current count of hearts.
     */
    public int getCount(){
        return this.count;
    }
    /**
     * Sets the current count of hearts.
     * @param count The new count of hearts.
     */
    public void setCount(int count){
        this.count = count;
    }

}
