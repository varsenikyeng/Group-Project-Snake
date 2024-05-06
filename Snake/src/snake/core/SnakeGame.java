package snake.core;
import java.util.Random;
import java.util.Timer;
import java.io.*;

public class SnakeGame {
    public static final int BOARD_ROWS = 5;
    public static final int BOARD_COLUMNS = 5;
    public static final String EMPTY = "    ";
    public static final String SNAKE_CELL = " " + "\u001B[32m" + '\u25A0' + "  " + "\u001B[0m";
    public static final String SNAKE_HEAD = " " + "\u001B[30m" + '\u25A0' + "  " + "\u001B[0m";

    private Snake snake;
    private String board[][];
    private GameEntity entity;
    private int score;

    public SnakeGame() {
        this.board = new String[BOARD_ROWS][BOARD_COLUMNS];
        initializeBoard();
        this.snake = new Snake();
        this.score = 0;
        placeInitialFruit();
        updateBoard();
    }

    private void placeInitialFruit() {
        this.entity = new Fruit(new Position(0, 0));
        entity.spawnEntity(snake, board);
    }

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


    public Snake getSnake() {
        return this.snake;
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

    public GameEntity getEntity() {
        return entity;
    }
    public int getScore(){
        return this.score;
    }
    public void setScore(int score){
        this.score = score;
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

    public void updateBoard() {
        initializeBoard();
        board[snake.getBody().get(0).getRow()][snake.getBody().get(0).getColumn()] = SNAKE_HEAD;
        for(int i = 1; i < snake.getBody().size(); i++ ){
            board[snake.getBody().get(i).getRow()][snake.getBody().get(i).getColumn()] = SNAKE_CELL;
        }

        board[entity.getPosition().getRow()][entity.getPosition().getColumn()] = entity.getSymbol();
        if(entity instanceof Obstacle && ((Obstacle) entity).isMature()){
            board[entity.getPosition().getRow()][entity.getPosition().getColumn()] = EMPTY;
            setRandomEntity();
        }
    }
    public boolean performMove(Move move) throws OutOfBoundMoveException {
        Position toGrow = new Position(snake.getBody().getLast());
        if (isValidMove(move.getDestination())) {
            Position destination = move.getDestination();
            Position origin = move.getOrigin();
            board[destination.getRow()][destination.getColumn()] = SNAKE_HEAD;
            board[origin.getRow()][origin.getColumn()] = EMPTY;
            if(snake.getBody().size() > 1){
                for(int i = getSnake().getBody().size() - 1; i > 0; i--){
                    snake.getBody().set(i, snake.getBody().get(i - 1));
                }
            }
            snake.moveHead(destination);
            if (destination.equals(entity.getPosition())) {
                if(entity instanceof Fruit){
                    this.snake.grow(toGrow);
                    score += 3;
                }
                else{
                    this.snake.shrink();
                    if(score >= 2)
                        score -= 2;
                }
                if(snake.getBody().size() < BOARD_ROWS*BOARD_COLUMNS)
                    setRandomEntity();
            }

            for(int i=1; i< getSnake().getBody().size(); i++){
                if(destination.equals(snake.getBody().get(i))){
                    snake.setIsAlive(false);
                    return false;
                }
            }
            updateBoard();
            return true;
        }
        throw new OutOfBoundMoveException();
    }

    public boolean isValidMove(Position position) {
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
