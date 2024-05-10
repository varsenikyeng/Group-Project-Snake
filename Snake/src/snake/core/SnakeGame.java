package snake.core;

import java.util.Random;
import java.util.Timer;
import java.io.*;

/**
 * Represents the main game logic of the Snake game.
 */
public class SnakeGame {
    /**
     * The number of rows on the game board.
     */
    public static final int BOARD_ROWS = 15;

    /**
     * The number of columns on the game board.
     */
    public static final int BOARD_COLUMNS = 15;

    /**
     * Represents an empty cell on the game board.
     */
    public static final String EMPTY = "    ";

    /**
     * Represents a cell occupied by the snake body on the game board.
     */
    public static final String SNAKE_CELL = " " + "\u001B[32m" + '\u25A0' + "  " + "\u001B[0m";

    /**
     * Represents a cell occupied by the snake head on the game board.
     */
    public static final String SNAKE_HEAD = " " + "\u001B[30m" + '\u25A0' + "  " + "\u001B[0m";

    private Snake snake; // The snake object
    private String board[][]; // The game board
    private GameEntity entity; // The entity (fruit/obstacle) on the game board
    private int score; // The player's score

    /**
     * Constructs a new SnakeGame object and initializes the game board.
     */
    public SnakeGame() {
        this.board = new String[BOARD_ROWS][BOARD_COLUMNS];
        initializeBoard();
        this.snake = new Snake();
        this.score = 0;
        placeInitialFruit();
        updateBoard();
    }

    /**
     * Places the initial fruit on the game board.
     */
    private void placeInitialFruit() {
        this.entity = new Fruit(new Position(0, 0));
        entity.spawnEntity(snake, board);
    }

    /**
     * Sets a random entity (fruit/obstacle) on the game board.
     */
    public void setRandomEntity() {
        Random random = new Random();
        double num = random.nextDouble(1);
        if (num > 0.3) {
            this.entity = new Fruit(new Position(0, 0));
        } else {
            this.entity = new Obstacle(new Position(0, 0));
        }
        entity.spawnEntity(this.snake, this.board);
    }

    /**
     * Retrieves the snake object.
     *
     * @return The snake object.
     */
    public Snake getSnake() {
        return this.snake;
    }

    /**
     * Retrieves a copy of the game board.
     *
     * @return A copy of the game board.
     */
    public String[][] getBoard() {
        String[][] board = new String[BOARD_ROWS][BOARD_COLUMNS];
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                board[i][j] = this.board[i][j];
            }
        }
        return board;
    }

    /**
     * Retrieves the current entity (fruit/obstacle) on the game board.
     *
     * @return The current entity.
     */
    public GameEntity getEntity() {
        return entity;
    }

    /**
     * Retrieves the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the player's score.
     *
     * @param score The player's score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Prints the current state of the game board to the console.
     */
    public void printBoard() {
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                System.out.print(getBoard()[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Initializes the game board with empty cells.
     */
    private void initializeBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    /**
     * Updates the game board to reflect the current state of the game.
     */
    public void updateBoard() {
        initializeBoard();
        board[snake.getBody().get(0).getRow()][snake.getBody().get(0).getColumn()] = SNAKE_HEAD;
        for (int i = 1; i < snake.getBody().size(); i++) {
            board[snake.getBody().get(i).getRow()][snake.getBody().get(i).getColumn()] = SNAKE_CELL;
        }

        board[entity.getPosition().getRow()][entity.getPosition().getColumn()] = entity.getSymbol();
        if (entity instanceof Obstacle && ((Obstacle) entity).isMature()) {
            board[entity.getPosition().getRow()][entity.getPosition().getColumn()] = EMPTY;
            setRandomEntity();
        }
    }

    /**
     * Performs a move in the game and updates the game state accordingly.
     *
     * @param move The move to perform.
     * @return True if the move is valid and executed successfully, false otherwise.
     * @throws OutOfBoundMoveException If the move is out of bounds.
     */
    public boolean performMove(Move move) throws OutOfBoundMoveException {
        Position toGrow = new Position(snake.getBody().getLast());
        if (isValidMove(move.getDestination())) {
            Position destination = move.getDestination();
            Position origin = move.getOrigin();
            board[destination.getRow()][destination.getColumn()] = SNAKE_HEAD;
            board[origin.getRow()][origin.getColumn()] = EMPTY;
            if (snake.getBody().size() > 1) {
                for (int i = getSnake().getBody().size() - 1; i > 0; i--) {
                    snake.getBody().set(i, snake.getBody().get(i - 1));
                }
            }
            snake.moveHead(destination);
            if (destination.equals(entity.getPosition())) {
                if (entity instanceof Fruit) {
                    this.snake.grow(toGrow);
                    score += 3;
                } else {
                    this.snake.shrink();
                    if (score >= 2)
                        score -= 2;
                }
                if (snake.getBody().size() < BOARD_ROWS * BOARD_COLUMNS)
                    setRandomEntity();
            }

            for (int i = 1; i < getSnake().getBody().size(); i++) {
                if (destination.equals(snake.getBody().get(i))) {
                    snake.setIsAlive(false);
                    return false;
                }
            }
            updateBoard();
            return true;
        }
        throw new OutOfBoundMoveException();
    }

    /**
     * Checks if a move is valid.
     *
     * @param position The position to check.
     * @return True if the position is within the game board boundaries, false otherwise.
     */
    public boolean isValidMove(Position position) {
        return position.getRow() >= 0 && position.getRow() < BOARD_ROWS &&
                position.getColumn() >= 0 && position.getColumn() < BOARD_COLUMNS;
    }

    /**
     * Checks if a position on the game board is free from the
     *
     * @param row    The row index of the position.
     * @param column The column index of the position.
     * @param snake  The snake object.
     * @return True if the position is free, false otherwise.
     */
    public static boolean isPositionFree(int row, int column, Snake snake) {
        for (Position segment : snake.getBody()) {
            if (segment.getRow() == row && segment.getColumn() == column) {
                return false;
            }
        }
        return true;
    }
}