package snake.core;

/**
 * The OutOfBoundMoveException class represents an exception that is thrown when a move is out of bounds of the game board in the Snake game.
 * It extends the Exception class.
 */
public class OutOfBoundMoveException extends Exception {

    /**
     * Constructs a new OutOfBoundMoveException object with a default error message.
     */
    public OutOfBoundMoveException() {
        super("Game over, try better next time"); // Calls the constructor of the superclass (Exception) with the default error message.
    }

    /**
     * Constructs a new OutOfBoundMoveException object with the specified error message.
     * @param message The error message describing the reason for the exception.
     */
    public OutOfBoundMoveException(String message) {
        super(message); // Calls the constructor of the superclass (Exception) with the specified error message.
    }
}
