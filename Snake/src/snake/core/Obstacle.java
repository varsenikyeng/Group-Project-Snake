package snake.core;

/**
 * The Obstacle class represents an obstacle game entity in the Snake game.
 * It extends the GameEntity class.
 */
public class Obstacle extends GameEntity {
    public final static String symbol = " \uD83C\uDF44 ";
    private long spawnTime; // The time when the obstacle was spawned.
    private static final int LIFETIME = 6000; // The lifetime of the obstacle in milliseconds.

    /**
     * Constructs a new Obstacle object with the specified position.
     * Sets the spawn time to the current system time.
     * @param position The position of the obstacle on the game board.
     */
    public Obstacle(Position position) {
        super(position, symbol); // The Unicode character represents an obstacle icon.
        this.spawnTime = System.currentTimeMillis(); // Sets the spawn time to the current system time.
    }

    /**
     * Checks if the obstacle has exceeded its lifetime.
     * @return True if the obstacle is mature, false otherwise.
     */
    public boolean isMature() {
        return (System.currentTimeMillis() - spawnTime) > LIFETIME; // Calculates the difference between the current time and the spawn time, and checks if it exceeds the obstacle's lifetime.
    }
}
