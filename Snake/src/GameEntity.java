import java.util.Random;
public abstract class GameEntity {
    private Position position;
    public enum EntityColor {RED, PURPLE}
    private EntityColor entityColor;
    private String symbol;
    public GameEntity(){
        this.position = new Position(0,0);
        this.entityColor = EntityColor.RED;
        this.symbol = " ";
    }
    public GameEntity(EntityColor color, Position position, String symbol){
        this.entityColor = color;
        this.position = new Position(position);
        this.symbol = symbol;
    }

    public Position getPosition(){
        return new Position(this.position);
    }

    public String getSymbol(){
        return this.symbol;
    }
    public EntityColor getEntityColor(){
        return this.entityColor;
    }

    public void setEntityColor(EntityColor entityColor) {
        this.entityColor = entityColor;
    }

    public void setPosition(Position that) {
        this.position = new Position(that);
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public abstract void effect(Snake snake);


    public void spawnEntity(Snake snake, String[][] board){
        Random rand = new Random();
        int randomRow, randomColumn;
        do {
            randomRow = rand.nextInt(board.length); //BOARD_ROWS
            randomColumn = rand.nextInt(board[0].length); //BOARD_COLUMNS
        }
        while (!SnakeGame.isPositionFree(randomRow, randomColumn, snake));
                this.position = new Position(randomRow, randomColumn);
                board[randomRow][randomColumn] = this.symbol;
    }
}

