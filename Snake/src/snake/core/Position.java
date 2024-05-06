package snake.core;
public class Position {
    private int row;
    private int column;
    public Position(){
        this.row = 0;
        this.column = 0;
    }
    public int getRow() {
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setRow(int row){
        this.row = row;
    }
    public Position(int row, int column){
        this.setRow(row);
        this.setColumn(column);
    }
    public Position(Position that){
        this.setRow(that.row);
        this.setColumn(that.column);
    }

    public static Position generateFromRowAndColumn(int row, int column){
        Position result = null;
        if(row < SnakeGame.BOARD_ROWS && row >= 0
                && column>=0 && column < SnakeGame.BOARD_COLUMNS)
            result = new Position(row, column);
        return result;
    }

    public boolean equals(Object other){
        if (other == null || Position.class != other.getClass()){
            return false;
        }
        Position otherPosition = (Position)other;
        return this.row == otherPosition.getRow() && this.column == otherPosition.getColumn();
    }
}