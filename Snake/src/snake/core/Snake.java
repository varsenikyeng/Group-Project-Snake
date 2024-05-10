package snake.core;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import static snake.core.SnakeGame.BOARD_COLUMNS;
import static snake.core.SnakeGame.BOARD_ROWS;

/**
 * Represents the snake entity in the Snake game.
 */
public class Snake {
    private ArrayList<Position> body; // The segments of the snake's body
    private int speed; // The speed of the snake (unused in this implementation)
    private String direction; // The direction in which the snake is moving
    private boolean isAlive; // Indicates whether the snake is alive
    private Heart heart; // An object representing the heart of the snake (unused in this implementation)

    /**
     * Constructs a new Snake object and initializes its properties.
     */
    public Snake() {
        this.isAlive = true;
        this.direction = "d"; // Default direction: right
        this.body = new ArrayList<>();
        this.body.add(new Position(1, 3)); // Initial position of the snake's head
    }

    /**
     * Retrieves the position of the snake's head.
     * @return The position of the snake's head.
     */
    public Position getHead() {
        return new Position(this.body.getFirst().getRow(), this.body.getFirst().getColumn());
    }

    /**
     * Retrieves the direction in which the snake is moving.
     * @return The direction of the snake.
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Retrieves the status of the snake's life.
     * @return True if the snake is alive, false otherwise.
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * Sets the status of the snake's life.
     * @param alive True to indicate that the snake is alive, false otherwise.
     */
    public void setIsAlive(boolean alive) {
        this.isAlive = alive;
    }

    /**
     * Retrieves the segments of the snake's body.
     * @return The segments of the snake's body.
     */
    public ArrayList<Position> getBody() {
        return this.body;
    }

    /**
     * Calculates the position reachable by the snake in a given direction.
     * @param direction The direction in which the snake intends to move.
     * @return The position reachable by the snake.
     */
    public Position getReachablePosition(String direction) {
        String d = direction.toLowerCase();
        int row;
        int column;
        switch (d) {
            case "w":
                row = this.getHead().getRow() - 1;
                column = this.getHead().getColumn();
                break;
            case "s":
                row = this.getHead().getRow() + 1;
                column = this.getHead().getColumn();
                break;
            case "a":
                row = this.getHead().getRow();
                column = this.getHead().getColumn() - 1;
                break;
            case "d":
                row = this.getHead().getRow();
                column = this.getHead().getColumn() + 1;
                break;
            default:
                row = this.getHead().getRow();
                column = this.getHead().getColumn();
                break;
        }
        return new Position(row, column);
    }

    /**
     * Handles keyboard input events and determines the snake's new position.
     * @param e The KeyEvent representing the keyboard input event.
     * @return The position reachable by the snake based on the keyboard input.
     */
    public Position keyboardCases(KeyEvent e) {
        int key = e.getKeyCode();
        Position newPosition = null;
        switch (key) {
            case KeyEvent.VK_LEFT:
                newPosition = getReachablePosition("a");
                break;
            case KeyEvent.VK_RIGHT:
                newPosition = getReachablePosition("d");
                break;
            case KeyEvent.VK_UP:
                newPosition = getReachablePosition("w");
                break;
            case KeyEvent.VK_DOWN:
                newPosition = getReachablePosition("s");
                break;
        }
        return newPosition;
    }

    /**
     * Moves the snake's head to a new position.
     * @param newPosition The new position of the snake's head.
     */
    public void moveHead(Position newPosition) {
        this.body.set(0, newPosition);
    }

    /**
     * Generates a move from the current position and the specified direction.
     * @param direction The direction in which the move is intended.
     * @return The Move object representing the generated move.
     */
    public Move generateFromPositionAndDirection(String direction) {
        Position newDestination = this.getReachablePosition(direction);
        return new Move(this.getHead(), newDestination);
    }

    /**
     * Increases the length of the snake by adding a new segment to its body.
     * @param toGrow The position where the new segment should be added.
     */
    public void grow(Position toGrow) {
        Position tail = this.body.get(this.body.size() - 1);
        Position newSegment = null;
        if (this.body.size() > 1) {
            newSegment = new Position(toGrow);
        } else {
            switch (this.direction) {
                case "w":
                    newSegment = new Position(tail.getRow() + 1, tail.getColumn());
                    break;
                case "s":
                    newSegment = new Position(tail.getRow() - 1, tail.getColumn());
                    break;
                case "a":
                    newSegment = new Position(tail.getRow(), tail.getColumn() + 1);
                    break;
                case "d":
                    newSegment = new Position(tail.getRow(), tail.getColumn() - 1);
                    break;
            }
        }
        this.body.add(newSegment);
        this.body.trimToSize();
    }

    /**
     * Checks if a given position is valid within the game board boundaries.
     * @param row The row index of the position.
     * @param column The column index of the position.
     * @return True if the position is valid, false otherwise.
     */
    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < BOARD_ROWS && column >= 0 && column < BOARD_COLUMNS;
    }

    /**
     * Decreases the length of the snake by removing the last segment from its body.
     * @throws OutOfBoundMoveException If the snake's body contains only one segment.
     */
    public void shrink() throws OutOfBoundMoveException {
        if (this.body.size() > 1) {
            this.body.removeLast();
        } else {
            this.isAlive = false;
            throw new OutOfBoundMoveException("Snake got poisoned(");
        }
    }

    /**
     * Sets the direction in which the snake should move.
     * @param direction The direction in which the snake should move.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
