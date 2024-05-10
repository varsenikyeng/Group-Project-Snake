package snake.core;
/**
 * The Fruit class represents a fruit game entity in the Snake game.
 * It extends the GameEntity class.
 */
public class Fruit extends GameEntity {

    public static final String symbol = " \uD83C\uDF4E ";
    /**
     * Constructs a new Fruit object with the specified position.
     * @param position The position of the fruit on the game board.
     */
    public Fruit(Position position){
        super(position, symbol);
    }
}
