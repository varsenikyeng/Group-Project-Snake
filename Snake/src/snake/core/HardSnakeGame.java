package snake.core;

public class HardSnakeGame extends SnakeGame{
    private Heart heart;
    public HardSnakeGame(){
        super();
        this.heart = new Heart(new Position(-1, -1));
    }
    private void loseHeart(){
        this.heart.setCount(heart.getCount() - 1);
    }
    public Heart getHeart(){
        return this.heart;
    }
    @Override
    public boolean performMove(Move move) throws OutOfBoundMoveException{
        Position toGrow = new Position(getSnake().getBody().getLast());
        if (isValidMove(move.getDestination())) {
            Position destination = move.getDestination();
            Position origin = move.getOrigin();
            getBoard()[destination.getRow()][destination.getColumn()] = SNAKE_HEAD;
            getBoard()[origin.getRow()][origin.getColumn()] = EMPTY;
            if(getSnake().getBody().size() > 1){
                for(int i = getSnake().getBody().size() - 1; i > 0; i--){
                    getSnake().getBody().set(i, getSnake().getBody().get(i - 1));
                }
            }
            getSnake().moveHead(destination);
            if (destination.equals(getEntity().getPosition())) {
                if(getEntity() instanceof Fruit){
                    this.getSnake().grow(toGrow);
                    setScore(getScore() + 3);
                }
                else{
                    loseHeart();
                    if(getScore() >= 3)
                        setScore(getScore() - 3);
                }
                if(getSnake().getBody().size() < BOARD_ROWS*BOARD_COLUMNS)
                    setRandomEntity();
            }

            for(int i=1; i< getSnake().getBody().size(); i++){
                if(destination.equals(getSnake().getBody().get(i))){
                    getSnake().setIsAlive(false);
                    return false;
                }
            }
            updateBoard();
            return true;
        }
        throw new OutOfBoundMoveException();
    }

    public void printHearts(){
        for (int i = 0; i < heart.getCount(); i++) {
            System.out.print(" " + heart.getSymbol() + " ");
        }
    }


}
