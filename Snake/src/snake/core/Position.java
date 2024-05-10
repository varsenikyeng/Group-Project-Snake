package snake.core;

/**
 * The Position class represents a position on the game board in the Snake game.
 */
public class Position {
    private int row;
    private int column;

    /**
     * Constructs a new Position object with  row and column at indices (0,0).
     */
    public Position() {
        this.row = 0;
        this.column = 0;
    }

    /**
     * Constructs a new Position object with the specified row and column indices.
     * @param row The row index of the position.
     * @param column The column index of the position.
     */
    public Position(int row, int column) {
        this.setRow(row);
        this.setColumn(column);
    }

    /**
     * Constructs a new Position object by copying another Position object.
     * @param that The Position object to copy.
     */
    public Position(Position that) {
        this.setRow(that.row);
        this.setColumn(that.column);
    }

    /**
     * Gets the row index of the position.
     * @return The row index of the position.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets the column index of the position.
     * @return The column index of the position.
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Sets the row index of the position.
     * @param row The new row index of the position.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the column index of the position.
     * @param column The new column index of the position.
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Generates a new Position object from the specified row and column indices.
     * Returns null if the indices are out of bounds.
     * @param row The row index of the position.
     * @param column The column index of the position.
     * @return A new Position object if the indices are valid, otherwise null.
     */
    public static Position generateFromRowAndColumn(int row, int column) {
        Position result = null;
        if (row < SnakeGame.BOARD_ROWS && row >= 0 &&
                column >= 0 && column < SnakeGame.BOARD_COLUMNS) {
            result = new Position(row, column);
        }
        return result;
    }

    /**
     * Checks if this Position object is equal to another object.
     * Two Position objects are equal if their row and column indices are equal.
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || Position.class != other.getClass()) {
            return false;
        }
        Position otherPosition = (Position) other;
        return this.row == otherPosition.getRow() && this.column == otherPosition.getColumn();
    }
}
