public class OutOfBoundMoveException extends Exception{
    public OutOfBoundMoveException(){
        super("Game over, try better next time");
    }
    public OutOfBoundMoveException(String message){
        super(message);
    }
}
