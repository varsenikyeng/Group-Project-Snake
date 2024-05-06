package snake.core;

import java.util.Random;
public abstract class GameEntity {
    private Position position;

    private String symbol;
    public GameEntity(){
        this.position = new Position(0,0);
        this.symbol = " ";
    }
    public GameEntity(Position position, String symbol){
        this.position = new Position(position);
        this.symbol = symbol;
    }

    public Position getPosition(){
        return new Position(this.position);
    }

    public String getSymbol(){
        return this.symbol;
    }

    public void setPosition(Position that) {
        this.position = new Position(that);
    }

    //public abstract void effect(Snake snake);

    public void spawnEntity(Snake snake, String[][] board){
        Random rand = new Random();
        int randomRow, randomColumn;
        do {
            randomRow = rand.nextInt(board.length); //BOARD_ROWS
            randomColumn = rand.nextInt(board[0].length); //BOARD_COLUMNS
        }
        while (!SnakeGame.isPositionFree(randomRow, randomColumn, snake));
        setPosition(new Position(randomRow, randomColumn));
        board[randomRow][randomColumn] = this.symbol;
    }

}

