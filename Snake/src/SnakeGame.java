import java.util.Random;
import java.util.Scanner;
public class SnakeGame {
    public static final int BOARD_ROWS = 15;
    public static final int BOARD_COLUMNS = 15;
    public static final String EMPTY = " .  ";
    public static final String SNAKE_CELL = " " + "\u001B[32m" + '\u25A0' + "  " + "\u001B[0m";
    private Snake snake;
    private String board[][];
    private GameEntity entity;

    public SnakeGame() {
        this.board = new String[BOARD_ROWS][BOARD_COLUMNS];
        initializeBoard();
        this.snake = new Snake();
        updateBoard();
        placeInitialFruit();
    }

    private void placeInitialFruit() {
        this.entity = new Fruit(new Position(0, 0));
        entity.spawnEntity(snake, board);
    }

    public void setRandomEntity() {
        Random random = new Random();
        double num = random.nextDouble(1);
        if (num > 0.2) {
            this.entity = new Fruit(new Position(0, 0));
        } else {
            this.entity = new Obstacle(new Position(0, 0));
        }
        entity.spawnEntity(snake, board);
    }


    public Snake getSnake() {
        return new Snake(this.snake);
    }

    public String[][] getBoard() {
        String[][] board = new String[BOARD_ROWS][BOARD_COLUMNS];
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                board[i][j] = this.board[i][j];
            }
        }
        return board;
    }

    public void printBoard() {
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                System.out.print(getBoard()[i][j]);
            }
            System.out.println();
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private void updateBoard() {
        initializeBoard();
        for (Position segment : snake.getBody()) {
            board[segment.getRow()][segment.getColumn()] = SNAKE_CELL;
        }
    }

    public boolean performMove(Move move) throws OutOfBoundMoveException {
        if (isValidMove(move.getDestination())) {
            Position origin = move.getOrigin();
            Position destination = move.getDestination();

            if (destination.equals(entity.getPosition())) {
                entity.effect(snake);
                setRandomEntity();
            }
            board[destination.getRow()][destination.getColumn()] = SNAKE_CELL;
            board[origin.getRow()][origin.getColumn()] = EMPTY;
            snake.moveHead(destination);
            return true;
        }
        throw new OutOfBoundMoveException();
    }

    private boolean isValidMove(Position position) {
        return position.getRow() >= 0 && position.getRow() < BOARD_ROWS &&
                position.getColumn() >= 0 && position.getColumn() < BOARD_COLUMNS;
    }

    public static boolean isPositionFree(int row, int column, Snake snake) {
        for (Position segment : snake.getBody()) {
            if (segment.getRow() == row && segment.getColumn() == column) {
                return false;
            }
        }
        return true;
    }
}
