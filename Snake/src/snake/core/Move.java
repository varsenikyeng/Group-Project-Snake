package snake.core;

/**
 * The Move class represents a move in the Snake game.
 * It consists of an origin position and a destination position.
 */
public class Move {
    private Position origin;
    private Position destination;

    /**
     * Constructs a new Move object with the specified origin and destination positions.
     * @param origin The origin position of the move.
     * @param destination The destination position of the move.
     */
    public Move(Position origin, Position destination) {
        this.origin = new Position(origin);
        this.destination = new Position(destination);
    }

    /**
     * Constructs a new Move object by copying another Move object.
     * @param that The Move object to copy.
     */
    public Move(Move that) {
        this.origin = new Position(that.origin);
        this.destination = new Position(that.destination);
    }

    /**
     * Gets the origin position of the move.
     * @return The origin position of the move.
     */
    public Position getOrigin() {
        return new Position(this.origin);
    }

    /**
     * Gets the destination position of the move.
     * @return The destination position of the move.
     */
    public Position getDestination() {
        return new Position(this.destination);
    }
}
